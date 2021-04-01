/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.EnumSON;
import ihm.EnumSPRITE;
import ihm.ManagerSon;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.FJeu;
import ihm.packageFenetre.FenetreJeu.ElementInterface.JoueurView;
import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageJeu.Jeu;

/**
 * Observateur du déplacement du joueur qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurDeplacementJoueur extends Observateur{
    private FJeu fjeu;
    private JoueurView vue;
    private Case depart;
    private Coordonnee lastCoord;
    private boolean enMouvement = false;
    
    /**
     * Constructeur d'un observateur de déplacement du joueur
     * @param vue la vue du joueur à déplacer
     * @param fjeu fenêtre dans laquelle le joueur va se déplacer
     */
    public ObservateurDeplacementJoueur(JoueurView vue, FJeu fjeu) {
        super();
        this.fjeu = fjeu;
        this.vue = vue;
        this.depart = Jeu.getInstance().getJoueur().getCase().clone();
        this.lastCoord = depart.getCoordonnee();
    }
    
    /**
     * créer une timeline déplaçant l'attribut vue jusqu'à la coordonnée en paramètre 
     * @param coor coordonnee cible
     * @return Timeline la timeline résultante
     */
    private Timeline moveElementTo(Coordonnee coor) {
        Coordonnee distance = new Coordonnee(coor.getX() - this.vue.getCoorIHM().getX(),coor.getY() - this.vue.getCoorIHM().getY());
        Timeline timeline = new Timeline();
        
        int moveX = -74*distance.getY() + 74*distance.getX();
        int moveY = 38*distance.getY() + 38*distance.getX();
        
        KeyFrame move = new KeyFrame(Duration.seconds(0.5),
                new KeyValue(this.vue.getCaseImg().xProperty(), moveX),
                new KeyValue(this.vue.getCaseImg().yProperty(), moveY));
        
        KeyFrame changeSprite = null;
        
        if((coor.getX()-this.lastCoord.getX() > 0) && (coor.getY()-this.lastCoord.getY() == 0)) {
            changeSprite = new KeyFrame(Duration.millis(1), e -> this.vue.getCaseImg().setImage(ManagerSprite.get(EnumSPRITE.alva_bas_droite)));
        } else if((coor.getX()-this.lastCoord.getX() == 0) && (coor.getY()-this.lastCoord.getY() > 0)) {
            changeSprite = new KeyFrame(Duration.millis(1), e -> this.vue.getCaseImg().setImage(ManagerSprite.get(EnumSPRITE.alva_bas_gauche)));
        } else if((coor.getX()-this.lastCoord.getX() < 0) && (coor.getY()-this.lastCoord.getY() == 0)) {
            changeSprite = new KeyFrame(Duration.millis(1), e -> this.vue.getCaseImg().setImage(ManagerSprite.get(EnumSPRITE.alva_haut_gauche)));
        } else if((coor.getX()-this.lastCoord.getX() == 0) && (coor.getY()-this.lastCoord.getY() < 0)) {
            changeSprite = new KeyFrame(Duration.millis(1), e -> this.vue.getCaseImg().setImage(ManagerSprite.get(EnumSPRITE.alva_haut_droite)));
        }
        
        timeline.getKeyFrames().addAll(changeSprite,move);
        this.lastCoord = coor;
        return timeline;
    }
    
    @Override
    public void avertir() {
        this.fjeu.FermerToutesPopup();
        
        SequentialTransition sequence = new SequentialTransition();
        Case arrivee = Jeu.getInstance().getJoueur().getCase();
        
        ArrayList<Case> mouvements = Jeu.getInstance().getPartie().getDijkstra().getChemin(this.depart,arrivee);
        for(Case c : mouvements) {
            sequence.getChildren().add(moveElementTo(c.getCoordonnee()));
        }
        vue.setEnMouvement(true);
        this.depart = arrivee;
        MediaPlayer bruitVoiture = ManagerSon.get(EnumSON.vroum);
        bruitVoiture.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                bruitVoiture.seek(Duration.ZERO);
            }
        });
        bruitVoiture.play();
        sequence.setOnFinished(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    vue.setEnMouvement(false);
                    bruitVoiture.setAutoPlay(false);
                    bruitVoiture.stop();
                }
            }
        );
        sequence.play();
    }
    
}

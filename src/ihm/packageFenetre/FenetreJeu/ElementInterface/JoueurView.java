/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreJeu.ElementInterface;

import ihm.packageFenetre.FenetreJeu.ElementInterface.ElementMap;
import ihm.EnumSPRITE;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.FJeu;
import metier.PackageCarte.Coordonnee;
import metier.PackageJeu.Joueur;
import javafx.scene.image.ImageView;
import metier.PackageJeu.Jeu;

/**
 * Classe représentant le Joueur en IHM
 * @author UNC
 */
public class JoueurView extends ElementMap{
    private Coordonnee coorIHM;
    private Joueur joueur;
    private FJeu fjeu;
    private boolean enMouvement;
    
    public JoueurView(FJeu fJeu) {
        super(fJeu);
        this.fjeu = fJeu;
        this.enMouvement = false;
        this.joueur = Jeu.getInstance().getJoueur();
        this.coorIHM = this.joueur.getCase().getCoordonnee();
    }

    public Joueur getJoueur() {
        return joueur;
    }
    
    /**
     * Affiche le joueur sur la carte
     */
    @Override
    public void displayImage() {
        this.caseImg.setImage(ManagerSprite.get(EnumSPRITE.alva));
        this.fjeu.getMap().getChildren().add(this.fjeu.getMap().getChildren().size()-2,this.caseImg);
        this.caseImg.setX(this.coorIHM.getX());
        this.caseImg.setY(this.coorIHM.getY());
        this.fjeu.placeNode(this.caseImg,this.joueur.getCase());
        this.caseImg.setDisable(true);
    }

    /**
     * Coordonnées en IHM
     * @return 
     */
    public Coordonnee getCoorIHM() {
        return coorIHM;
    }

    /**
     * Renvoi la case li
     * @return 
     */
    public ImageView getCaseImg() {
        return caseImg;
    }

    public void setCoorIHM(Coordonnee coordonnee) {
        this.coorIHM = coordonnee;
    }

    /**
     * Renvoi si le joueur est en déplacement ou non
     * @return Vrai ou Faux
     */
    public boolean isEnMouvement() {
        return enMouvement;
    }

    /**
     * Défini le joueur comme "En mouvement" ou non
     * @param enMouvement Vrai ou Faux
     */
    public void setEnMouvement(boolean enMouvement) {
        this.enMouvement = enMouvement;
    }
    
}
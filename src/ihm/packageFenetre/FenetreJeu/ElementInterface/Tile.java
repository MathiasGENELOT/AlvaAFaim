/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreJeu.ElementInterface;

import ihm.packageFenetre.FenetreJeu.ElementInterface.MagasinPopView;
import ihm.packageFenetre.FenetreJeu.ElementInterface.ElementMap;
import controleurs.controleurs.ControleurDeplacementJoueur;
import ihm.EnumHERBE;
import ihm.EnumSPRITE;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.FJeu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.Route;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Partie;
import javafx.scene.input.MouseEvent;
import javafx.scene.transform.Translate;
import metier.PackageAlgorithme.generator.GenerateurAleatoire;

/**
 * Class d'une Tuile/Carreau du jeu, vue IHM d'une case logique
 * @author UNC
 */
public class Tile extends ElementMap {
    private Case caseLogic;
    private boolean estAccessible = false;
    private MagasinPopView popupView;
    private ControleurDeplacementJoueur controleurDeplacementJoueur;
    
    /**
     * Constructeur d'une Tile
     * @param partie Prends la partie en cours
     * @param fjeu Fenetre du jeu
     * @param initCoor Coordonnées logique de la tuile
     */
    public Tile(Partie partie, FJeu fjeu, Coordonnee initCoor) {
        super(fjeu);
        this.caseLogic = partie.getCarte().getCases().get(initCoor);
        placeTile();
        this.fenetreMap.getMap().getChildren().add(caseImg);
    
        // Controleur
        controleurDeplacementJoueur = fjeu.getControleurDeplacementJoueur();
        
        // Handler
        this.caseImg.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> interactTile(event));
    }
    
    public Case getCaseLogic() {
        return caseLogic;
    }

    /**
     * Affecte une popup si nécessaire
     * @param popupView 
     */
    public void setPopupView(MagasinPopView popupView) {
        this.popupView = popupView;
    }
    
    public boolean isEstAccessible() {
        return estAccessible;
    }

    public void setEstAccessible(boolean estAccessible) {
        this.estAccessible = estAccessible;
    }
    
    /**
     * Place la case de façon a repsecter une vue isométrique
     */
    private void placeTile() {
        Translate transX = new Translate();
        Translate transY = new Translate();

        transX.setX(74*caseLogic.getCoordonnee().getX());
        transX.setY(38*caseLogic.getCoordonnee().getX());

        transY.setX(-74*caseLogic.getCoordonnee().getY());
        transY.setY(38*caseLogic.getCoordonnee().getY());
        caseImg.getTransforms().addAll(transX,transY);
    }
     
    public Coordonnee getCoordonnee() {
        return this.caseLogic.getCoordonnee();
    }
    
    /**
     * Affiche l'image correspondant à la case
     */
    @Override
    public void displayImage() {
        Random random = new Random();
        switch(caseLogic.getType()) {
            case "Route":
                Route r = (Route) caseLogic;
                caseImg.setImage(ManagerSprite.get(r.getSens()));
                break;
            case "Maison":
                Batiment bMaison = (Batiment) caseLogic;
                if (this.estAccessible) caseImg.setImage(ManagerSprite.get(bMaison.getSens()));
                else caseImg.setImage(ManagerSprite.get(bMaison.getSens()));
                break;
            case "Magasin":
                Batiment bMagasin = (Batiment) caseLogic;
                if (this.estAccessible) caseImg.setImage(ManagerSprite.get(bMagasin.getSens()));
                else caseImg.setImage(ManagerSprite.get(bMagasin.getSens()));
                break;
            case "Herbe":
                ArrayList<EnumHERBE> listEnum = new ArrayList<>();
                for (EnumHERBE enumH : EnumHERBE.values())
                    listEnum.add(enumH);               
                caseImg.setImage(ManagerSprite.get(listEnum.get(Math.abs(random.nextInt())%listEnum.size())));
                //caseImg.setImage(ManagerSprite.get(listEnum.get(GenerateurAleatoire.get().nextPositiveInt()%listEnum.size())));
                break;
        }
    }
    
    /**
     * Interaction de clique avec la case, effectue l'opération en conséquence
     * @param e Evenement de clique
     */
    private void interactTile(MouseEvent e){
        this.fenetreMap.FermerToutesPopup();
        if (this.caseLogic.getType() == "Magasin") {
            if (e.getClickCount() >= 2) {
                controleurDeplacementJoueur.avertir(caseLogic);
            }
            else if(Jeu.getInstance().getJoueur().getCase() == this.getCaseLogic()) 
                this.popupView.showMagasinPane();
            else
                this.popupView.showFirstPane();
            
        } else if (this.caseLogic.getType() == "Maison") 
            controleurDeplacementJoueur.avertir(caseLogic);
    }

    /**
     * Renvoi la popup de la case
     * @return 
     */
    public MagasinPopView getPopupView() {
        return popupView;
    }
    
    /**
     * Renvoi la distance entre le joueur et la case
     * @return 
     */
    public int getCoutDistance() {
        return (Jeu.getInstance().getPartie().getDijkstra().getChemin(Jeu.getInstance().getJoueur().getCase(), this.caseLogic).size()-1)*2;
    }
}

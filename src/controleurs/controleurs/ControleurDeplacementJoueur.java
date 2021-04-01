/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import ihm.packageFenetre.FenetreJeu.ElementInterface.JoueurView;
import metier.PackageCarte.PackageCase.Case;


/**
 * Controleur du déplacement joueur qui hérite de Controleur
 * @author UNC
 */
public class ControleurDeplacementJoueur extends Controleur {
    private JoueurView joueur;
    
    /**
     * Constructeur du controleur d'un déplacement de joueur
     * @param joueur JoueurView vue du joueur a déplacer
     */
    public ControleurDeplacementJoueur(JoueurView joueur) {
        super();
        this.joueur = joueur;
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir(Case caseLogic) {
        if(!this.joueur.isEnMouvement()) {
            this.joueur.getJoueur().seDeplacer(caseLogic);
            this.avertirObservateurs();
        }
    }
}

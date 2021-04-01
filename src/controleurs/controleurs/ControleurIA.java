/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import metier.PackageAlgorithme.packageIA.IASolution;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Partie;

/**
 * Controleur de l'appel à l'IA qui hérite de Controleur
 * @author UNC
 */
public class ControleurIA extends Controleur{
    
    private Partie partie;
    
    /**
     * Constructeur du controleur l'IA
     * @param partie la partie sur laquelle l'IA doit calculer
     */
    public ControleurIA(Partie partie) {
        super();
        this.partie = partie;
        
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir() {
        IASolution iASolution = new IASolution(partie);
        
        int scoreMax = iASolution.start();
        Jeu.getInstance().getPartie().setIAScore(scoreMax);
        this.avertirObservateurs();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import metier.PackageJeu.Jeu;
import metier.PackageRecette.Recette;

/**
 * Controleur de Concocter qui hérite de Controleur
 * @author UNC
 */
public class ControleurConcocter extends Controleur {
    public ControleurConcocter() {
        super();
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir(Recette r) {
        if (Jeu.getInstance().getPartie().concocter(r))
            this.avertirObservateurs();
    }
}

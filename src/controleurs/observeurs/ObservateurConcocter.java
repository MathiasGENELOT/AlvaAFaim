/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.packageFenetre.FenetreFindeJeu.FFin;
import ihm.packageFenetre.FenetreJeu.FJeu;

/**
 * Observateur de concocter qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurConcocter extends Observateur {
    private final FJeu fenetre;
    
    /**
     * Constructeur de l'observateur concocter
     * @param fen FJeu, fenêtre dans laquelle l'affichage de fin de jeu doit apparaitre
     */
    public ObservateurConcocter(FJeu fen) {
        this.fenetre = fen;
    }
    
    @Override
    public void avertir() {
        FFin fenetreFin = new FFin(fenetre,false);
    }
    
}

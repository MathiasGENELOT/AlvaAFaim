/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.packageFenetre.FenetreJeu.ElementInterface.Tile;
import java.util.ArrayList;

/**
 * Observateur des cout du déplacement vers des cases qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurCoutDeplacement extends Observateur{
    private ArrayList<Tile> tiles;

    /**
     * Constructeur de l'observateurs des cout du déplacement
     * @param tiles liste de vue de case à actualiser l'affichage du coût de déplacement jusqu'à celles-ci
     */
    public ObservateurCoutDeplacement(ArrayList<Tile> tiles) {
        super();
        this.tiles = tiles;
    }

    @Override
    public void avertir() {
        for(Tile t : this.tiles)
            if(t.getCaseLogic().getType() == "Magasin")
                t.getPopupView().displayCoutDeplacer();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import ihm.packageFenetre.FenetreJeu.FJeu;
import ihm.packageFenetre.FenetreJeu.ElementInterface.Tile;

/**
 * Controleur d'interaction d'une case qui hérite de Controleur
 * @author UNC
 */
public class ControleurInteractionTile extends Controleur{
    private Tile tile;
    private FJeu fjeu;

    /**
     * Controleur
     * @param tile la vue d'une case clickable
     * @param fjeu la fenetre de jeu où se trouve la case
     */
    public ControleurInteractionTile(Tile tile,FJeu fjeu) {
        super();
        this.tile = tile;
        this.fjeu = fjeu;
        
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir() {
        
        this.avertirObservateurs();
    }
}

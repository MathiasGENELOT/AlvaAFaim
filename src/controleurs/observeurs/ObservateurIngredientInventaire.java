/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.packageFenetre.FenetreJeu.FJeu;
import javafx.scene.layout.Pane;

/**
 * Observateur de l'inventaire du joueur qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurIngredientInventaire extends Observateur{
    private FJeu fjeu;
    
    /**
     * Constructeur d'observateur de l'inventaire
     * @param fjeu 
     */
    public ObservateurIngredientInventaire(FJeu fjeu) {
        super();
        this.fjeu = fjeu;
    }

    
    @Override
    public void avertir() {
        fjeu.displayInvContent();
    }
    
}

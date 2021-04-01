/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.EnumINGREDIENTS;

/**
 * Controleur d'achat d'un Ingredient qui hérite de Controleur
 * @author UNC
 */
public class ControleurAchatIngredient extends Controleur {
        
    private Magasin magasin;
    private EnumINGREDIENTS ingredient;
    
    /**
     * Constructeur du contrôleur Achat Ingrédient
     * @param magasin Magasin où s'effectue l'achat
     * @param ingredient EnumINGREDIENTS l'ingrédient en question
     */
    public ControleurAchatIngredient(Magasin magasin,EnumINGREDIENTS ingredient) {
        super();
        this.magasin = magasin;
        this.ingredient = ingredient;
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir() {
        this.magasin.venteIngredient(this.ingredient);
        this.avertirObservateurs();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.EnumINGREDIENTS;

/**
 * Controleur de vente d'un ingredient qui hérite de Controleur
 * @author UNC
 */
public class ControleurVenteIngredient extends Controleur{
    private Magasin magasin;
    private EnumINGREDIENTS ingredient;
    
    /**
     * Constructeur du controleur de vente d'ingrédient
     * @param magasin magasin où se situe l'ingrédient vendu
     * @param ingredient EnumINGREDIENTS ingrédient vendu
     */
    public ControleurVenteIngredient(Magasin magasin, EnumINGREDIENTS ingredient) {
        super();
        this.ingredient = ingredient;
        this.magasin = magasin;
    }
    
    /**
     * effectue l'action du controleur
     */
    public void avertir() {
        this.magasin.rachatIngredient(this.ingredient);
        this.avertirObservateurs();
    }
    
}

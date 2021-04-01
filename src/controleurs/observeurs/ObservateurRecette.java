/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.EnumSPRITE;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.ElementInterface.IngredientsView;
import ihm.packageFenetre.FenetreJeu.FJeu;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Joueur;
import metier.PackageRecette.EnumRECETTES;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Observateur d'une recette qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurRecette extends Observateur{
    private ArrayList<IngredientsView> listVue = new ArrayList<>();
    private final Joueur joueur;
    private HashMap<Recette,ImageView> imageRecetteViews;
    
    /**
     * Constructeur d'observateur de recette
     * @param fjeu fenêtre contenant la vue de la recette
     */
    public ObservateurRecette(FJeu fjeu) {
        super();
        this.listVue = fjeu.getIngredientsViewRecette();
        this.joueur = Jeu.getInstance().getJoueur();
        this.imageRecetteViews = fjeu.getImageRecetteViews();
    }

    
    
    @Override
    public void avertir() {
        for (IngredientsView vue : this.listVue) {
            vue.majColor(Color.WHITE);
            if (joueur.getInventaire().getIngredient(vue.getIngredientsLogic().getNom()) != null) {
                if (joueur.getInventaire().getIngredient(vue.getIngredientsLogic().getNom()).getNombre() >= vue.getIngredientsLogic().getNombre())
                    vue.majColor(Color.GREENYELLOW);
            }
        }
        for(Recette r : Recettes.get().listRecettesPartie()){
            if (this.joueur.getInventaire().contient(r.getInventaire())){
                this.imageRecetteViews.get(r).setImage(ManagerSprite.get(EnumSPRITE.valueOf(r.getNom() + "_OK")));
            } else {
                this.imageRecetteViews.get(r).setImage(ManagerSprite.get(EnumRECETTES.valueOf(r.getNom() + "")));
            }
        }
    
    }
        
   
}

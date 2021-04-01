/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreJeu.ElementInterface;

import ihm.packageFenetre.FenetreJeu.FJeu;
import ihm.ManagerSprite;
import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.Ingredients;
import controleurs.controleurs.*;
import controleurs.observeurs.*;
import controleurs.observeurs.ObservateurIngredientMagasin;
import ihm.EnumFONTS;
import ihm.ManagerFonts;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import metier.PackageIngredient.EnumINGREDIENTSPRITE;
import metier.PackageJeu.Jeu;

/**
 * Vue d'un ingredient en partie IHM
 * @author UNC
 */
public class IngredientsView {
    private final Ingredients ingredientsLogic;
    private Pane pane;
    private Pane paneNom;
    private Magasin caseMagasin;
    private Text numIngredient;
    private FJeu fjeu;
    private ImageView imgSprite;
    private Text prix;
    private ControleurAchatIngredient controleurAchatIngredient;
    private ControleurVenteIngredient controleurVenteIngredient;
    private Text nomIngredient;
    
    public IngredientsView(Ingredients ingredientsLogic,Tile tile,FJeu fjeu) {
        this.ingredientsLogic = ingredientsLogic;
        this.caseMagasin = (Magasin) tile.getCaseLogic();
        this.fjeu = fjeu;
        this.pane = new Pane();
        this.prix = new Text(caseMagasin.getPrix(ingredientsLogic.getNom()) + " €");
        this.numIngredient = new Text("" + ingredientsLogic.getNombre());
        this.nomIngredient = new Text(ingredientsLogic.getNom().name());
        this.imgSprite = new ImageView();
        
        
        // Controleur
        this.controleurAchatIngredient = new ControleurAchatIngredient(caseMagasin,ingredientsLogic.getNom());
        this.controleurVenteIngredient = new ControleurVenteIngredient(caseMagasin,ingredientsLogic.getNom());

        ObservateurIngredientMagasin observateurIngredient = new ObservateurIngredientMagasin(this, caseMagasin,ingredientsLogic.getNom());
        ObservateurDepense observateurDepense = new ObservateurDepense(fjeu);
        ObservateurIngredientInventaire observateurIngredientInventaire = new ObservateurIngredientInventaire(fjeu);
        ObservateurRecette observateurIngredientRecette = new ObservateurRecette(fjeu);
        
        this.controleurAchatIngredient.addObservateur(observateurIngredient,observateurDepense,observateurIngredientInventaire,observateurIngredientRecette);
        this.controleurVenteIngredient.addObservateur(observateurIngredient,observateurDepense,observateurIngredientInventaire,observateurIngredientRecette);
    }
    
    /**
     * Constructeur IngredientView
     * @param ingredientsLogic Ingrédient partie logique
     * @param fjeu Fenetre du jeu
     */
    public IngredientsView(Ingredients ingredientsLogic,FJeu fjeu) {
        this.ingredientsLogic = ingredientsLogic;
        this.fjeu = fjeu;
        this.pane = new Pane();
        this.numIngredient = new Text("" + ingredientsLogic.getNombre());
        this.nomIngredient = new Text(ingredientsLogic.getNom().name());
        this.imgSprite = new ImageView();
    }
    
    /**
     * Renvoi le visuel de l'ingrédient pour l'afficher dans l'inventaire
     * @return Pane sous forme de rectangle comprenant toutes les infos de l'ingrédient
     */
    public Pane getVisuelInventaire() {
        if (ingredientsLogic.getNombre() == 1) {
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "")));
        } else if (ingredientsLogic.getNombre() > 1){
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "S")));
        }
        
        // nom Ingredient
        nomIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        nomIngredient.setTranslateY(20);
        nomIngredient.setTranslateX(70);
        
        // number Ingredient
        numIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        numIngredient.setTranslateY(45);
        numIngredient.setTranslateX(70);
        
        this.pane.getChildren().addAll(imgSprite,nomIngredient,numIngredient);
        return this.pane;
    }
    
    /**
     * Renvoi le visuel de l'ingrédient pour l'afficher dans la fenetre du magasin
     * @return Pane sous forme de carré pour s'intégrer au magasin
     */
    public Pane getVisuelMagasin() {
        if (this.ingredientsLogic.getNombre() == 1) {
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "")));
        } else if (this.ingredientsLogic.getNombre() > 1){
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "S")));
        }
        // prix Ingredient
        DropShadow shdow = new DropShadow();
        shdow.setRadius(2.0);
        shdow.setOffsetX(1);
        shdow.setOffsetY(1);
        shdow.setColor(Color.BLACK);
        
        prix.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        prix.setFill(Color.WHITE);
        prix.setTranslateY(80);
        prix.setTranslateX(20);
        prix.setEffect(shdow);
        
        // number Ingredient
        numIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        numIngredient.setFill(Color.WHITE);
        numIngredient.setTranslateY(55);
        numIngredient.setTranslateX(50);
        
        // popup nom
        this.paneNom = new Pane();
        this.paneNom.setTranslateX(5);
        this.paneNom.setTranslateY(10);
        this.paneNom.setVisible(false);
        
        this.nomIngredient.setFill(Color.WHITE);
        this.nomIngredient.setFont(ManagerFonts.getLittleFont(EnumFONTS.ALMOND_NOUGAT));
        this.paneNom.getChildren().add(this.nomIngredient);
        
        // popup nom show
        this.imgSprite.addEventHandler(MouseEvent.MOUSE_ENTERED, event -> this.paneNom.setVisible(true));
        this.imgSprite.addEventHandler(MouseEvent.MOUSE_EXITED, event -> this.paneNom.setVisible(false));
        
        this.imgSprite.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> interactIngredient(event));
        
        
        this.pane.getChildren().addAll(imgSprite,prix,numIngredient,paneNom);
        return this.pane;
    }
    
    /**
     * Renvoi le visuel pour le "voir contenhu" de la popup
     * @return Pane sous forme de rectangle contenant les informations de l'ingrédient
     */
    public Pane getVisuelContenuMagasin() {
        if (ingredientsLogic.getNombre() <= 1) {
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "")));
        } else if (ingredientsLogic.getNombre() > 1){
            imgSprite.setImage(ManagerSprite.get(EnumINGREDIENTSPRITE.valueOf(ingredientsLogic.getNom() + "S")));
        }
        
        // prix Ingredient
        this.prix.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        this.prix.setTranslateY(45);
        this.prix.setTranslateX(120);
        
        // nom Ingredient
        this.nomIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        this.nomIngredient.setTranslateY(20);
        this.nomIngredient.setTranslateX(70);;
        
        // number Ingredient
        this.numIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        this.numIngredient.setTranslateY(45);
        this.numIngredient.setTranslateX(70);
        
        this.pane.getChildren().addAll(imgSprite,nomIngredient,numIngredient,prix);
        return this.pane;
    }
    
    /**
     * Renvoi le visuel pour le menu recette à gauche
     * @return Ingrédient écrit a la craie avec son nombre à gauche
     */
    public Pane getVisuelRecette() {
        
        // nom Ingredient
        this.nomIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        this.nomIngredient.setFill(Color.WHITE);
        this.nomIngredient.setTranslateY(5);
        this.nomIngredient.setTranslateX(40);
        
        // number Ingredient
        this.numIngredient = new Text("x " + this.ingredientsLogic.getNombre());
        this.numIngredient.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        this.numIngredient.setFill(Color.WHITE);
        this.numIngredient.setTranslateY(5);
        this.numIngredient.setTranslateX(280);
        this.pane.getChildren().addAll(nomIngredient,numIngredient);

        return this.pane;
    }

    /**
     * Averti les controleurs d'un clic sur l'ingrédient
     * @param event 
     */
    private void interactIngredient(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY) {
            this.controleurAchatIngredient.avertir();
        } else if(event.getButton() == MouseButton.SECONDARY) {
            this.controleurVenteIngredient.avertir();
        }
    }

    /**
     * Renvoi l'ingrédient logique
     * @return 
     */
    public Ingredients getIngredientsLogic() {
        return ingredientsLogic;
    }
    
    /**
     * Change le texte du nombre d'ingrédient
     * @param valeur Nombre d'ingrédients
     */
    public void majNumIngredient(int valeur) {
        this.numIngredient.setText(""+valeur);
    }
    
    /**
     * Change la couleur de l'affichage de l'ingredient
     * @param color 
     */
    public void majColor(Color color) {
        this.nomIngredient.setFill(color);
        this.numIngredient.setFill(color);
    }
    
}

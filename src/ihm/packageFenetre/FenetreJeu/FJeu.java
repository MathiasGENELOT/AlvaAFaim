/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreJeu;

import ihm.packageFenetre.FenetreJeu.ElementInterface.Tile;
import ihm.packageFenetre.FenetreJeu.ElementInterface.MagasinPopView;
import ihm.packageFenetre.FenetreJeu.ElementInterface.JoueurView;
import ihm.packageFenetre.FenetreJeu.ElementInterface.DragablePane;
import ihm.packageFenetre.FenetreFindeJeu.FFin;
import controleurs.controleurs.ControleurConcocter;
import controleurs.controleurs.ControleurDeplacementJoueur;
import controleurs.observeurs.ObservateurConcocter;
import controleurs.observeurs.ObservateurCoutDeplacement;
import controleurs.observeurs.ObservateurDepense;
import controleurs.observeurs.ObservateurDeplacementJoueur;
import ihm.EnumFONTS;
import ihm.EnumINTERFACE;
import ihm.EnumSON;
import ihm.EnumSPRITE;
import ihm.ManagerFonts;
import ihm.ManagerSon;
import ihm.packageFenetre.Fenetre;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.ElementInterface.IngredientsView;
import ihm.packageFenetre.FenetreMenu.FMenu;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageJeu.Jeu;
import java.util.ArrayList;
import java.util.HashMap;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import metier.PackageIngredient.Ingredients;
import metier.PackageJeu.Option;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Fenetre du jeu principale qui gère le bon affichage des modules du jeux
 * @author UNC
 */
public class FJeu extends Fenetre
{

    private Pane map;
    private JoueurView joueurView;
    private Text depenseView = new Text();
    private ArrayList<Tile> tiles = new ArrayList<>();
    private final ControleurDeplacementJoueur controleurDeplacementJoueur;
    private final ControleurConcocter controleurConcocter;
    private Pane inventaireView;
    private ScrollPane contentInventaire;
    private Pane optionView;
    private ImageView darkBg;
    private GridPane recetteView; 
    private ScrollPane ScrollpanRecette = new ScrollPane();
    private ArrayList<VBox> listVboxRecette;
    private ArrayList<IngredientsView> listIngrViewRecettes = new ArrayList<>();
    private HashMap<Recette,ImageView> imageRecetteViews = new HashMap<>();
    
    /**
     * Affiche les éléments de jeu :
     * Carte/Inventaire/Recette/Menu
     * 
     * Initialise les controleurs & observateurs
     * @param primStage Stage principale
     */
    public FJeu(Stage primStage) {
        super(primStage);
        
        // Link CSS
        this.setCSS("ressources/css/CSSFenetreJeu.css");
        
        // Création du Pane map
        this.map = new DragablePane();
        this.depenseView.setX(90);
        this.depenseView.setY(100);
        this.depenseView.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        
        SetDepense(Jeu.getInstance().getJoueur().getDepense());
        
        ImageView pancarte = new ImageView(ManagerSprite.get(EnumSPRITE.pancarte));
        pancarte.setX(15);
        
        ImageView background = new ImageView(ManagerSprite.get(EnumSPRITE.background));
        this.root.getChildren().addAll(background,map,pancarte,depenseView);
        this.joueurView = new JoueurView(this);
        
        // Musique de fond
        ManagerSon.playBackgroundMusic();
        
        // Controleur
        controleurDeplacementJoueur = new ControleurDeplacementJoueur(this.joueurView);
        controleurConcocter = new ControleurConcocter();
        ObservateurDeplacementJoueur observateurDeplacementJoueur = new ObservateurDeplacementJoueur(this.getJoueurView(), this);
        initCarteDuJeu();
        ObservateurCoutDeplacement observateurCoutDeplacement = new ObservateurCoutDeplacement(this.tiles);
        ObservateurDepense observateurDepense = new ObservateurDepense(this);
        ObservateurConcocter obsvervateurConcocter = new ObservateurConcocter(this);
        
        controleurDeplacementJoueur.addObservateur(observateurDeplacementJoueur,observateurDepense,observateurCoutDeplacement);
        controleurConcocter.addObservateur(obsvervateurConcocter);
        
        afficherCarte();
        AfficherInv();
        AfficherLayoutRecette();
        afficherRecette();
        afficherBtnMenu();
        afficherOption();
        
        this.joueurView.displayImage();
        setupPopup();
    }

    public ControleurDeplacementJoueur getControleurDeplacementJoueur() {
        return controleurDeplacementJoueur;
    }
    
    public ArrayList<IngredientsView> getIngredientsViewRecette() {
        return this.listIngrViewRecettes;
    }
    
    @Override
    public Pane getMap() {
        return this.map;
    }
    
    public JoueurView getJoueurView() {
        return joueurView;
    }
    
    public Text SetDepense(int i) {
        depenseView.setText("DEPENSE : " + String.valueOf(i));
        return depenseView;
    }
    
    public ArrayList<Tile> getTiles() {
            return tiles;
    }
    
    /**
     * Initialise la map en ajoutant les Tiles correspondantes
     */
    public void initCarteDuJeu() {
        this.map.setTranslateX(500);
        this.map.setTranslateY(50);
        
        for(int i=0 ;i< Jeu.getInstance().getPartie().getCarte().getNbColonne();i++) {
            for(int j=0 ;j< Jeu.getInstance().getPartie().getCarte().getNbLigne();j++) {
                tiles.add(new Tile(Jeu.getInstance().getPartie(),this,new Coordonnee(i,j)));
            }
        }
    }
    
    /**
     * affiche les images des tiles (case)
     */
    private void afficherCarte() {
        for (Tile t : this.tiles)
            t.displayImage();
    }
    
    /**
     * permet de mettre en place les popup des magasin
     */
    private void setupPopup() {
        for (Tile t : this.tiles)
            if(t.getCaseLogic().getType() == "Magasin")
                t.setPopupView(new MagasinPopView(this, t));
    }
    
    /**
     * Affichage du menu de recette 
     */
    public void AfficherLayoutRecette() {
        ImageView Layout = new ImageView(ManagerSprite.get(EnumINTERFACE.Interface_bg_Recette));
        Layout.setX(1100);
        Layout.setY(0);
        this.root.getChildren().add(Layout);
    }
    
    /**
     * Défini l'image "Inaccessible" sur les case sur lesquelles le joueur ne peut se déplacer
     */
    public void AfficherBatimentsAccessible() {
        ArrayList<Case> batAccessibles = Jeu.getInstance().getPartie().getBatimentsAccessible();
        
        
        for (Case c : batAccessibles) {
            System.out.println(c.getCoordonnee().toString());
            for (Tile tile : this.tiles) {
                if (c.getCoordonnee().equals(tile.getCoordonnee())) tile.setEstAccessible(true);
                else tile.setEstAccessible(false);
                tile.displayImage();
            }
        }
    }
    
    /**
     * Déplie ou replie l'inventaire en jouant un animation
     * @param n Image du bouton "Ouvrir" ou "Fermer"
     * @param inv Pane de l'inventaire
     */
    public void switchInventaireBtnPos(ImageView n, Pane inv) {
        Timeline timeline = new Timeline();
        KeyFrame move;
        KeyFrame move2;
        if(n.getY() == 814) {
            move = new KeyFrame(Duration.seconds(0.4),new KeyValue(n.yProperty(), 614));
            move2 = new KeyFrame(Duration.seconds(0.4),new KeyValue(inv.layoutYProperty(), 654));
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                n.setImage(ManagerSprite.get(EnumINTERFACE.BtInventaireFermer));
            }
        });
        } else {
            move = new KeyFrame(Duration.seconds(0.4),new KeyValue(n.yProperty(), 814));
            move2 = new KeyFrame(Duration.seconds(0.4),new KeyValue(inv.layoutYProperty(), 853));
            timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                n.setImage(ManagerSprite.get(EnumINTERFACE.BtInventaireOuvert));
            }
        });
        }
        
        timeline.getKeyFrames().addAll(move,move2);
        timeline.play();
    }
    
    /**
     * Ferme toutes les popups de la fenetre
     */
    public void FermerToutesPopup() {
        for(Tile t : getTiles())
            if(t.getCaseLogic().getType() == "Magasin") {
                t.getPopupView().hide();
            }
    }

    /**
     * Affiche les boutons du menu de gauche
     */
    private void afficherBtnMenu() {
        ImageView btnMenu = new ImageView(ManagerSprite.get(EnumINTERFACE.quitterBTN));
        btnMenu.setX(1260);
        btnMenu.setY(787);
        btnMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> retourMenu());
        btnMenu.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnMenu.setImage(ManagerSprite.get(EnumINTERFACE.quitterBTNhover));
            }
        });
        btnMenu.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnMenu.setImage(ManagerSprite.get(EnumINTERFACE.quitterBTN));
            }
        });

  
        ImageView btnAbandon = new ImageView(ManagerSprite.get(EnumINTERFACE.abandonnerBTN));
        btnAbandon.setX(1232);
        btnAbandon.setY(647);
        btnAbandon.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> abandon());
        btnAbandon.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnAbandon.setImage(ManagerSprite.get(EnumINTERFACE.abandonnerBTNhover));
            }
        });
        btnAbandon.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnAbandon.setImage(ManagerSprite.get(EnumINTERFACE.abandonnerBTN));
            }
        });
        
        ImageView btnOption = new ImageView(ManagerSprite.get(EnumINTERFACE.optionBTN));
        btnOption.setX(1245);
        btnOption.setY(728);
        btnOption.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> displayOption());
        btnOption.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnOption.setImage(ManagerSprite.get(EnumINTERFACE.optionBTNhover));
            }
        });
        btnOption.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnOption.setImage(ManagerSprite.get(EnumINTERFACE.optionBTN));
            }
        });
        
        this.root.getChildren().addAll(btnMenu,btnAbandon,btnOption);
    }
    
    /**
     * Effectue un retour vers le menu
     */
    private void retourMenu() {
        ManagerSon.stop();
        FMenu menu = new FMenu(primaryStage);
        Fenetre.primaryStage.setScene(menu.GetScene());
    }
    
    /**
     * Affiche le contenu de l'inventaire
     */
    public void displayInvContent() {
        GridPane gridPane = (GridPane) this.contentInventaire.getContent();
        gridPane.getChildren().clear();
        int k = 0;
        int ligne=0;
        ArrayList<Ingredients> copy = new ArrayList<Ingredients>();
        for(Ingredients ing : Jeu.getInstance().getJoueur().getInventaire().getIngredients()) {
            if(ing.getNombre() != 0) {
                copy.add(ing);
            }
        }
        
        for(int i = 0; i<copy.size()/6;i++) {
            for(int j = 0;j<6;j++) {
                Ingredients ingr = copy.get(k);
                k++;
                gridPane.add(new IngredientsView(ingr,this).getVisuelInventaire(),j,i);
            }
            ligne = i;
        }
        for(int i = 0;i<copy.size()%6;i++) {
            Ingredients ingr = copy.get(k);
            k++;
            gridPane.add(new IngredientsView(ingr,this).getVisuelInventaire(),i,ligne+1);
        }
    }
    
    /**
     * affiche le background de l'inventaire
     */
    public void AfficherInv() { 
        this.inventaireView = new Pane();
        
        // contenue inv
        this.contentInventaire = new ScrollPane();
        // Fixe les règles sur la scrollbar
        this.contentInventaire.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.contentInventaire.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        this.contentInventaire.setPrefSize(1100, 176);
        this.contentInventaire.translateYProperty().set(14);
        this.contentInventaire.translateXProperty().set(14);
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(100);
        gridPane.setVgap(15);
        
        this.contentInventaire.setContent(gridPane);
        this.contentInventaire.setId("invContent");
        
        ImageView inventaire_bg = new ImageView(ManagerSprite.get(EnumINTERFACE.Interface_bg_Inventaire));
        this.inventaireView.layoutXProperty().set(0);
        this.inventaireView.layoutYProperty().set(860);
        this.inventaireView.getChildren().add(0,inventaire_bg);
        this.inventaireView.getChildren().addAll(this.contentInventaire);
        displayInvContent();
        
        ImageView btnInv = new ImageView(ManagerSprite.get(EnumINTERFACE.BtInventaireOuvert));
        btnInv.setX(0);
        btnInv.setY(814);
        
        btnInv.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> switchInventaireBtnPos(btnInv,this.inventaireView));
  
        this.root.getChildren().addAll(this.inventaireView,btnInv);
    }

    public HashMap<Recette, ImageView> getImageRecetteViews() {
        return imageRecetteViews;
    }
    
    /**
     * affiche les recettes sur le menu de gauche
     */
    public void afficherRecette() {
        this.ScrollpanRecette = new ScrollPane();
        this.recetteView = new GridPane();
        
        this.ScrollpanRecette.setContent(recetteView);
        this.ScrollpanRecette.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.ScrollpanRecette.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        this.ScrollpanRecette.setPrefSize(350,390);
        this.ScrollpanRecette.setLayoutX(1160);
        this.ScrollpanRecette.setLayoutY(190);
        
        this.listVboxRecette = new ArrayList<VBox>();
        int i = 0;
        for (Recette r : Recettes.get().listRecettesPartie())
        {
            ImageView img = new ImageView(ManagerSprite.get(r.getNom()));
            this.imageRecetteViews.put(r,img);
            Pane p = new Pane(img);
            GridPane g = ContenuGridPaneRecette(r);
            
            this.listVboxRecette.add(new VBox(p,g));
            this.listVboxRecette.get(i).getChildren().get(1).setVisible(false);
            this.listVboxRecette.get(i).getChildren().get(1).managedProperty().bind(this.listVboxRecette.get(i).getChildren().get(1).visibleProperty());

            VBox vb = this.listVboxRecette.get(i);
            p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> EventBoutonRecette(event,vb,r));
            i++;
        }

        for(int j =0;j<this.listVboxRecette.size();j++)
        {
            this.recetteView.add(this.listVboxRecette.get(j),0,j); 
        }

        this.root.getChildren().addAll(this.ScrollpanRecette); 
       

    }
    
    /**
     * Evenement lorsque qu'on clique sur une recette
     * Déplie la recette et affiche les ingrédients
     * @param e Evenement clique
     * @param v VBox centrer
     * @param r Recette
     */
    public void EventBoutonRecette(MouseEvent e, VBox v, Recette r){
        if (e.getClickCount() >= 2) {
            controleurConcocter.avertir(r);
        }
        else if(!v.getChildren().get(1).isVisible())
        {
            for(VBox instance : this.listVboxRecette)
            {
               instance.getChildren().get(1).setVisible(false);
               instance.getChildren().get(1).managedProperty().bind(instance.getChildren().get(1).visibleProperty());
            }
            v.getChildren().get(1).setVisible(true);
            v.getChildren().get(1);
            this.recetteView.autosize();
        }
        else
        {
            v.getChildren().get(1).setVisible(false);            
        }
    }
    
    /**
     * Renvoi le Pane d'une recette
     * @param r Recette
     * @return GridPane contenant les ingredients et leur vues
     */
    public GridPane ContenuGridPaneRecette(Recette r) {
        GridPane g = new GridPane();
        g.getChildren().clear();
        int k =0;
        for(Ingredients i :r.getInventaire().getIngredients())
        {
            IngredientsView ingrViewRecette = new IngredientsView(i, this);
            this.listIngrViewRecettes.add(ingrViewRecette);
            g.add(ingrViewRecette.getVisuelRecette(),0,k);
            k++;
        }        
        return g;
    }
    
    /**
     * Fonction qui avertie de la sauvegarde
     */
    private void sauvegarde() {
        // Appel de Sauvegarde
    }

    /**
     * Fonction qui lance l'abandon de la game
     */
    private void abandon() {
        // Appel de Solution
        FFin fenetreFin = new FFin(this,true);
        ManagerSon.stop();
    }

    /**
     * Affiche les options
     */
    private void displayOption() {
        FadeTransition ft = new FadeTransition(Duration.seconds(1.5),optionView);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        optionView.setDisable(false);
    }
    
    /**
     * Cache les options
     */
    private void hideOption() {
        FadeTransition ft = new FadeTransition(Duration.seconds(1.5),optionView);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        optionView.setDisable(true);
    }

    /**
     * Ajoute les éléments des options
     */
    private void afficherOption() {
        optionView = new Pane();
        darkBg = new ImageView(ManagerSprite.get(EnumINTERFACE.darkBackground));
        darkBg.setOpacity(1);
        ImageView back = new ImageView(ManagerSprite.get(EnumINTERFACE.option_background));
        back.setTranslateX(310);
        back.setTranslateY(150);
        
                
        // Option Slider Volume
        
        Pane sliderPane = new Pane();
        Slider musicSlider = new Slider();
        sliderPane.getChildren().add(musicSlider);
        musicSlider.setValue(Option.getInstance().getBackVolume());
        musicSlider.setId("slider");
        musicSlider.setTranslateY(20);
        musicSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                double volume = musicSlider.getValue();
                Option.getInstance().setVolumeMusic(volume/100);
            }
        });
      
        
        Slider volumeSlider = new Slider();
        sliderPane.getChildren().add(volumeSlider);
        volumeSlider.setValue(Option.getInstance().getVolume());
        volumeSlider.setId("slider");
        volumeSlider.setTranslateY(120);
        sliderPane.setLayoutX(460);
        sliderPane.setLayoutY(280);
        volumeSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                double volume = volumeSlider.getValue();
                Option.getInstance().setVolume(volume/100);
            }
        });
        
        sliderPane.opacityProperty().set(1);
        
        Text volumeText = new Text("Volume principal");
        volumeText.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        volumeText.setTranslateY(100);
        volumeText.setFill(Color.WHITE);
        volumeText.opacityProperty().bind(sliderPane.opacityProperty());
        
        Text musicText = new Text("Volume des musiques");
        musicText.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        musicText.setX(0);
        musicText.setY(0);
        musicText.setFill(Color.WHITE);
        musicText.opacityProperty().bind(sliderPane.opacityProperty());
        
        ImageView close = new ImageView(ManagerSprite.get(EnumINTERFACE.optionCross));
        close.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                hideOption();
            }
        });
        close.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                close.setImage(ManagerSprite.get(EnumINTERFACE.optionCross_hover));
            }
        });
        close.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                close.setImage(ManagerSprite.get(EnumINTERFACE.optionCross));
            }
        });
        close.setX(700);
        close.setY(200);
        
        sliderPane.getChildren().addAll(musicText,volumeText);
        
        optionView.getChildren().addAll(darkBg,back,sliderPane,close);
        optionView.setOpacity(0);
        optionView.setDisable(true);
        this.root.getChildren().addAll(optionView);
    }

}
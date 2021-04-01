/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreJeu.ElementInterface;

import controleurs.controleurs.ControleurDeplacementJoueur;
import ihm.EnumFONTS;
import ihm.EnumINTERFACE;
import ihm.EnumSON;
import ihm.EnumSPRITE;
import ihm.ManagerFonts;
import ihm.ManagerSon;
import ihm.packageFenetre.FenetreJeu.ElementInterface.IngredientsView;
import ihm.ManagerSprite;
import ihm.packageFenetre.FenetreJeu.FJeu;
import javafx.event.EventHandler;
import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.Ingredients;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.transform.Translate;

/**
 * Classe permettant d'afficher toutes les Popup lié au magasin
 * @author UNC
 */
public class MagasinPopView {
    
    private final Pane firstPane;
    private Pane magasinContentPane;
    private final Pane magasinPane;
    private final FJeu fenetre;
    private final Tile tile;
    private final Magasin magasin;
    private final Text cout = new Text();
    private final ImageView btnSeDeplacer;
    private ControleurDeplacementJoueur controleurDeplacementJoueur;
    
    /**
     * Création de la fenetre MagasinPopup
     * @param fenetre Fenetre du jeu
     * @param tile Case sur laquelle apparait la popup
     */
    public MagasinPopView(FJeu fenetre, Tile tile) {
        this.firstPane = new Pane();
        this.magasinContentPane = new Pane();
        this.magasinPane = new Pane();
        this.fenetre = fenetre;
        this.tile = tile;
        this.magasin = (Magasin) tile.getCaseLogic();
        this.btnSeDeplacer = new ImageView(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer));
        this.cout.setTranslateX(40);
        this.cout.setTranslateY(205);
        //this.cout.setTranslateY(100);
        this.cout.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        
        // Controleur
        this.controleurDeplacementJoueur = fenetre.getControleurDeplacementJoueur();

        displayPane();
        placePopup();

    }
    
    /**
     * Affiche la premiere interface popup ou l'utilisateur choisi
     * entre Se déplacer et voir le contenu
     */
    public void showFirstPane() {
        this.firstPane.setVisible(true);
    }
    
    /**
     * Affiche "Voir contenu" de la popup
     */
    public void showContentPane() {
        this.magasinContentPane.setVisible(true);
    }
    
    /**
     * affiche la popup achat dans le magasin
     */
    public void showMagasinPane() {
        ManagerSon.play(EnumSON.magasin);
        this.magasinPane.setVisible(true);
    }
    
    /**
     * Ajoute les Pane au Jeu
     */
    private void displayPane() {
        // Première fenetre
        this.firstPane.setVisible(false);
        this.firstPane.getChildren().add(new ImageView(ManagerSprite.get(EnumINTERFACE.infobullPopup)));
        this.fenetre.getMap().getChildren().add(this.firstPane);
        this.firstPane.toFront();
        displayBtnContenu();
        displayBtnDeplacer();
        displayCoutDeplacer();
        ImageView distance = new ImageView(ManagerSprite.get(EnumINTERFACE.pancarte));
        distance.setTranslateX(20);
        distance.setTranslateY(170);
        this.firstPane.getChildren().add(distance);
        this.firstPane.getChildren().add(cout);
        displayCross(this.firstPane,302,32);
        
        // Fenetre Contenue
        displayMagasinContent();
        
        // Fenetre magasin
        this.magasinPane.setVisible(false);
        this.magasinPane.getChildren().add(new ImageView(ManagerSprite.get(EnumINTERFACE.infofullMagasin)));
        this.magasinPane.toFront();
        this.fenetre.getMap().getChildren().add(this.magasinPane);
        displayMagasin();
        displayCross(this.magasinPane,247,23);
    }

    /**
     * cache tout les popups lié au magasin
     */
    public void hide() {
        this.firstPane.setVisible(false);
        this.magasinContentPane.setVisible(false);
        this.magasinPane.setVisible(false);
    }
    
    /**
     * Place la popup sur la cadrillage isométrique
     */
    private void placePopup() {
        Translate transX = new Translate();
        Translate transY = new Translate();
        transX.setX(74*tile.getCaseLogic().getCoordonnee().getX()-50);
        transX.setY(38*tile.getCaseLogic().getCoordonnee().getX()-100);

        transY.setX(-74*tile.getCaseLogic().getCoordonnee().getY()-50);
        transY.setY(38*tile.getCaseLogic().getCoordonnee().getY()-100);
        firstPane.getTransforms().addAll(transX,transY);
        
        Translate transX3 = new Translate();
        Translate transY3 = new Translate();
        transX3.setX(74*tile.getCaseLogic().getCoordonnee().getX()-40);
        transX3.setY(38*tile.getCaseLogic().getCoordonnee().getX()-100);

        transY3.setX(-74*tile.getCaseLogic().getCoordonnee().getY()-40);
        transY3.setY(38*tile.getCaseLogic().getCoordonnee().getY()-100);
        magasinContentPane.getTransforms().addAll(transX3,transY3);
        
        Translate transX2 = new Translate();
        Translate transY2 = new Translate();
        transX2.setX(74*tile.getCaseLogic().getCoordonnee().getX()-43);
        transX2.setY(38*tile.getCaseLogic().getCoordonnee().getX()-115);

        transY2.setX(-74*tile.getCaseLogic().getCoordonnee().getY()-43);
        transY2.setY(38*tile.getCaseLogic().getCoordonnee().getY()-115);
        magasinPane.getTransforms().addAll(transX2,transY2);
    }

    /**
     * Affiche la croix
     * @param p Pane dans lequel la croix s'affiche
     * @param x Position X dans le Pane
     * @param y Position Y dans le Pane
     */
    private void displayCross(Pane p, int x, int y) {
        ImageView cross = new ImageView(ManagerSprite.get(EnumINTERFACE.cross));
        cross.setTranslateX(x);
        cross.setTranslateY(y);
        
        cross.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hide());
        cross.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                cross.setImage(ManagerSprite.get(EnumINTERFACE.crosshover));
            }
        });
        cross.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                cross.setImage(ManagerSprite.get(EnumINTERFACE.cross));
            }
        });
        p.getChildren().add(cross);
    }
    
    /**
     * Affiche le bouton voir contenu
     */
    private void displayBtnContenu() {
        ImageView btnContenu = new ImageView(ManagerSprite.get(EnumINTERFACE.BtVoirMagasin));
        btnContenu.setTranslateX(65);
        btnContenu.setTranslateY(79);
        btnContenu.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnContenu.setImage(ManagerSprite.get(EnumINTERFACE.BtVoirMagasin_hover));
            }
        });
        btnContenu.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnContenu.setImage(ManagerSprite.get(EnumINTERFACE.BtVoirMagasin));
            }
        });
        
        btnContenu.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> openContenu());
        this.firstPane.getChildren().add(btnContenu);
    }
    
    /**
     * Affiche bouton se déplacer
     */
    private void displayBtnDeplacer() {
        btnSeDeplacer.setTranslateX(216);
        btnSeDeplacer.setTranslateY(78);
        btnSeDeplacer.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> this.controleurDeplacementJoueur.avertir(magasin));
        btnSeDeplacer.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer_hover));
            }
        });
        btnSeDeplacer.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer));
            }
        });
        this.firstPane.getChildren().add(btnSeDeplacer);
    }
    
    /**
     * Affiche le bouton retour
     */
    private void displayArrow() {
        ImageView arrow = new ImageView(ManagerSprite.get(EnumINTERFACE.smallArrow));
        arrow.setTranslateX(25);
        arrow.setTranslateY(45);
        
        arrow.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> goBack());
        this.magasinContentPane.getChildren().add(arrow);
    }

    /**
     * Affiche le voir contenu du magasin
     */
    private void openContenu() {
        this.firstPane.setVisible(false);
        this.magasinContentPane.setVisible(true);
    }
    
    /**
     * Retour en arrière dans la popup
     */
    private void goBack() {
        this.magasinContentPane.setVisible(false);
        this.firstPane.setVisible(true);
    }

    /**
     * Affiche le cout du déplacement
     * N'affiche rien si inaccessible
     */
    public void displayCoutDeplacer() {
        
        int i = this.tile.getCoutDistance();
        if (i >= 0) {
            this.btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer));
            this.btnSeDeplacer.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer_hover));
            }
            });
            btnSeDeplacer.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtSeDeplacer));
                }
            });
            cout.setText("COUT  TRAJET : " + i);
        } else {
            this.btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtInaccessible));
            this.btnSeDeplacer.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtInaccessible));
            }
            });
            btnSeDeplacer.setOnMouseExited(new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {
                    btnSeDeplacer.setImage(ManagerSprite.get(EnumINTERFACE.BtInaccessible));
                }
            });
            cout.setText("");
        }
    }
    
    /**
     * Affiche le contneu du magasin
     */
    private void displayMagasinContent() {
        this.magasinContentPane = new Pane();
        
        this.magasinContentPane.setVisible(false);
        this.magasinContentPane.getChildren().add(new ImageView(ManagerSprite.get(EnumINTERFACE.voir_contenu)));
        this.magasinContentPane.toFront();
        this.fenetre.getMap().getChildren().add(this.magasinContentPane);
        
        displayCross(this.magasinContentPane,260,45);
        displayArrow();
        
        ScrollPane content = new ScrollPane();
        // Fixe les règles sur la scrollbar
        content.setHbarPolicy(ScrollBarPolicy.NEVER);
        content.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        content.setPrefSize(205, 157);
        
        content.setTranslateX(50);
        content.setTranslateY(43);
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(2));
        gridPane.setHgap(10);
        gridPane.setVgap(0);
        
        for(int i = 0; i<this.magasin.getInventaire().getIngredients().size();i++) {
            Ingredients ingr = this.magasin.getInventaire().getIngredients().get(i);
            gridPane.add(new IngredientsView(ingr, this.tile,this.fenetre).getVisuelContenuMagasin(),1,i);
        }
        
        content.setContent(gridPane);
        
        this.magasinContentPane.getChildren().add(content);
    }
    
    /**
     * Affiche le magasin et son inventaire
     */
    public void displayMagasin() {
        ScrollPane content = new ScrollPane();
        // Fixe les règles sur la scrollbar
        content.setHbarPolicy(ScrollBarPolicy.NEVER);
        content.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
        content.setPrefSize(305, 207);
        content.setId("magasin");
        
        content.setTranslateX(5);
        content.setTranslateY(53);
        
        
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(40);
        gridPane.setVgap(22);
        //gridPane.setGridLinesVisible(true);
        gridPane.setStyle("-fx-background-image: url('ressources/images/interface/rayon.png');"
                        + "-fx-background-repeat: repeat-y;");
        
        int k = 0;
        int ligne=0;
        for(int i = 0; i<this.magasin.getInventaire().getIngredients().size()/3;i++) {
            for(int j = 0;j<3;j++) {
                Ingredients ingr = this.magasin.getInventaire().getIngredients().get(k);
                k++;
                gridPane.add(new IngredientsView(ingr, this.tile,this.fenetre).getVisuelMagasin(),j,i);
            }
            ligne = i;
        }
        for(int i = 0;i<this.magasin.getInventaire().getIngredients().size()%3;i++) {
            Ingredients ingr = this.magasin.getInventaire().getIngredients().get(k);
            k++;
            gridPane.add(new IngredientsView(ingr, this.tile,this.fenetre).getVisuelMagasin(),i,ligne+1);
        }
        
        content.setContent(gridPane);
        
        this.magasinPane.getChildren().add(content);
    }
    
    /**
     * Actualise les interface du magasins
     */
    public void Actualise() {
        displayMagasin();
        displayMagasinContent();
    }
}

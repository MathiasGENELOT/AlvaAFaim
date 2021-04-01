/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreMenu;

import controleurs.controleurs.ControleurIA;
import ihm.EnumFONTS;
import ihm.EnumINTERFACE;
import ihm.EnumMENU;
import ihm.EnumSON;
import ihm.ManagerFonts;
import ihm.ManagerSon;
import ihm.ManagerSprite;
import ihm.packageFenetre.Fenetre;
import ihm.packageFenetre.FenetreJeu.FJeu;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import metier.PackageJeu.Jeu;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import metier.PackageJeu.Option;

/**
 * Fenetre du menu principale
 * @author UNC
 */
public class FMenu extends Fenetre{
    private ImageView allu;
    private ImageView cover;
    private Pane coverPane;
    private Pane buttonsPane;
    private Pane seedPane;
    private TextField seedTextField;
    private ImageView playButton;
    private ImageView chargement;
    private final Pane sliderPane;
    private final ImageView backButton;
    private final ImageView backButtonOption;
    private Pane optionPane;
    private final ImageView hiddenButtons;
    
    /**
     * Constructeur d'écran du menu
     * Initialise et place les éléments graphique
     * @param primStage Stage principal
     */
    public FMenu(Stage primStage) {
        super(primStage);
        
        this.setCSS("ressources/css/CSSFenetreMenu.css");
        buttonsPane = new Pane();
        
        // Création des boutons
        int x = 1098;
        int y = 201;
        
        seedPane = new Pane();
        optionPane = new Pane();
        
        // Bonton continuer
        ImageView continueButton = new ImageView(ManagerSprite.get(EnumMENU.continuer));
        continueButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                continueButton.setImage(ManagerSprite.get(EnumMENU.continue_hover));
                ManagerSon.play(EnumSON.choix);
            }
        });
        continueButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                continueButton.setImage(ManagerSprite.get(EnumMENU.continuer));
            }
        });
        continueButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> CreateGame(event));
        continueButton.setX(x);
        continueButton.setY(y);
        
        // Bouton nouvelle game
        ImageView newGameButton = new ImageView(ManagerSprite.get(EnumMENU.newgame));
        newGameButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> DisplayNewGame(event));
        newGameButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);
                newGameButton.setImage(ManagerSprite.get(EnumMENU.newgame_hover));
            }
        });
        newGameButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                newGameButton.setImage(ManagerSprite.get(EnumMENU.newgame));
            }
        });
        newGameButton.setX(x);
        newGameButton.setY(y+113);
        
        // Bouton option
        ImageView optionButton = new ImageView(ManagerSprite.get(EnumMENU.option));
        optionButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> DisplayOption(event));
        optionButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);
                optionButton.setImage(ManagerSprite.get(EnumMENU.option_hover));
            }
        });
        optionButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                optionButton.setImage(ManagerSprite.get(EnumMENU.option));
            }
        });
        optionButton.setX(x);
        optionButton.setY(y+113*2);
        
        
        // Bouton quitter
        ImageView quitButton = new ImageView(ManagerSprite.get(EnumMENU.quit));
        quitButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> onQuit(event));
        quitButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);
                quitButton.setImage(ManagerSprite.get(EnumMENU.quit_hover));
            }
        });
        quitButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                quitButton.setImage(ManagerSprite.get(EnumMENU.quit));
            }
        });
        quitButton.setX(x);
        quitButton.setY(y+113*3);
        
        // Création de l'IHM Nouvelle partie
        
        seedTextField = new TextField();
        seedTextField.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        seedTextField.setId("seedField");
        seedTextField.setPromptText("Seed (optionnel)");
        seedTextField.setLayoutX(800);
        seedTextField.setLayoutY(345);
        seedTextField.opacityProperty().set(0);
        seedTextField.setFocusTraversable(false);
        
        ImageView seedBackground = new ImageView(ManagerSprite.get(EnumMENU.seedBG));
        seedBackground.setX(762);
        seedBackground.setY(312);
        seedBackground.opacityProperty().bind(seedTextField.opacityProperty());
        
        // Jouer
        playButton = new ImageView(ManagerSprite.get(EnumMENU.jouer));
        playButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> NouvellePartie(seedTextField.getText()));
        playButton.setX(762);
        playButton.setY(428);
        playButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);
                playButton.setImage(ManagerSprite.get(EnumMENU.jouer_hover));
            }
        });
        playButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                playButton.setImage(ManagerSprite.get(EnumMENU.jouer));
            }
        });
        playButton.opacityProperty().set(0);
        
        // Retour seed
        backButton = new ImageView(ManagerSprite.get(EnumMENU.retour));
        backButton.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hide(event));
        backButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);;
                backButton.setImage(ManagerSprite.get(EnumMENU.retour_hover));
            }
        });
        backButton.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                backButton.setImage(ManagerSprite.get(EnumMENU.retour));
            }
        });
        backButton.setX(930);
        backButton.setY(425);
        backButton.opacityProperty().set(0);
        
        // Retour seed
        backButtonOption = new ImageView(ManagerSprite.get(EnumMENU.retour));
        backButtonOption.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> hide(event));
        backButtonOption.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                ManagerSon.play(EnumSON.choix);
                backButtonOption.setImage(ManagerSprite.get(EnumMENU.retour_hover));
            }
        });
        backButtonOption.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                backButtonOption.setImage(ManagerSprite.get(EnumMENU.retour));
            }
        });
        backButtonOption.setX(930);
        backButtonOption.setY(425);
        backButtonOption.opacityProperty().set(0);
        
        // Option Slider Volume
        
        sliderPane = new Pane();
        Slider musicSlider = new Slider();
        sliderPane.getChildren().add(musicSlider);
        musicSlider.setValue(Option.getInstance().getBackVolume());
        musicSlider.setId("slider");
        musicSlider.setTranslateY(15);
        musicSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                double volume = musicSlider.getValue();
                Option.getInstance().setVolumeMusic(volume);
            }
        });
      
        
        Slider volumeSlider = new Slider();
        sliderPane.getChildren().add(volumeSlider);
        volumeSlider.setValue(Option.getInstance().getVolume());
        volumeSlider.setId("slider");
        volumeSlider.setTranslateY(125);
        sliderPane.setLayoutX(830);
        sliderPane.setLayoutY(233);
        volumeSlider.setOnMouseReleased(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                double volume = volumeSlider.getValue();
                Option.getInstance().setVolume(volume);
            }
        });
        
        sliderPane.opacityProperty().set(0);
        
        Text volumeText = new Text("Volume principal");
        volumeText.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        volumeText.setX(855);
        volumeText.setY(345);
        volumeText.setFill(Color.WHITE);
        volumeText.opacityProperty().bind(sliderPane.opacityProperty());
        
        Text musicText = new Text("Volume des musiques");
        musicText.setFont(ManagerFonts.getMediumFont(EnumFONTS.ALMOND_NOUGAT));
        musicText.setX(855);
        musicText.setY(241);
        musicText.setFill(Color.WHITE);
        musicText.opacityProperty().bind(sliderPane.opacityProperty());
        
        // Masque sombre sur les boutons
        this.hiddenButtons = new ImageView(ManagerSprite.get(EnumMENU.hiddenBTN));
        hiddenButtons.setX(1097);
        hiddenButtons.setY(198);
        hiddenButtons.opacityProperty().set(0);
        
        
        seedPane.getChildren().addAll(seedBackground,backButton,seedTextField,playButton,hiddenButtons);
        
        
        optionPane.getChildren().addAll(musicText,volumeText,backButtonOption,sliderPane);
        
        buttonsPane.getChildren().addAll(continueButton,newGameButton,optionButton,quitButton);
        // Ajout à la fenêtre
        
        this.root.getChildren().addAll(optionPane,seedPane,buttonsPane);
        
        
        displayMenu();
    }

    /**
     * Lance l'animation de chargement de la partie
     */
    public void chargement() {
        this.chargement = new ImageView(ManagerSprite.get(EnumINTERFACE.chargement));
        this.root.getChildren().add(chargement);
        chargement.setVisible(false);
    }
    
    /**
     * Cache et replie la tablette 
     * @param event 
     */
    public void hide(InputEvent event) {
        Timeline timeline = playAnimation(0);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                buttonsPane.toFront();
            }
        });
        FadeTransition ft = new FadeTransition(Duration.seconds(1),seedTextField);
        ft.setFromValue(seedTextField.getOpacity());
        ft.setToValue(0);
        ft.play();
        
        FadeTransition ft2 = new FadeTransition(Duration.seconds(1),playButton);
        ft2.setFromValue(playButton.getOpacity());
        ft2.setToValue(0);
        ft2.play();
        
        FadeTransition ft3 = new FadeTransition(Duration.seconds(1),backButton);
        ft3.setFromValue(backButton.getOpacity());
        ft3.setToValue(0);
        ft3.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                timeline.play();
                buttonsPane.toFront();
            }
        });
        ft3.play();
        
        FadeTransition ft4 = new FadeTransition(Duration.seconds(1),backButtonOption);
        ft4.setFromValue(backButtonOption.getOpacity());
        ft4.setToValue(0);
        ft4.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                timeline.play();
                buttonsPane.toFront();
            }
        });
        ft4.play();
        
        FadeTransition ft5 = new FadeTransition(Duration.seconds(1),sliderPane);
        ft5.setFromValue(sliderPane.getOpacity());
        ft5.setToValue(0);
        ft5.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                buttonsPane.toFront();
            }
        });
        ft5.play();
        FadeTransition ft6 = new FadeTransition(Duration.seconds(1.5),hiddenButtons);
        ft6.setFromValue(hiddenButtons.getOpacity());
        ft6.setToValue(0);
        ft6.play();
       
    }
    
    /**
     * Affiche les éléments pour lancer une nouvelle partie
     * @param event 
     */
    public void DisplayNewGame(InputEvent event) {
        Timeline timeline = playAnimation(-320);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1.5),seedTextField);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();

                FadeTransition ft2 = new FadeTransition(Duration.seconds(1.5),playButton);
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft2.play();
                
                FadeTransition ft3 = new FadeTransition(Duration.seconds(1.5),backButton);
                ft3.setFromValue(0);
                ft3.setToValue(1);
                ft3.play();
                
                FadeTransition ft4 = new FadeTransition(Duration.seconds(1.5),hiddenButtons);
                ft4.setFromValue(0);
                ft4.setToValue(1);
                ft4.play();
                
                seedPane.toFront();
            }
        });
        timeline.play();
    }
    
    /**
     * Affiche les options
     * @param event 
     */
    public void DisplayOption(InputEvent event) {
        Timeline timeline = playAnimation(-320);
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                FadeTransition ft = new FadeTransition(Duration.seconds(1.5),sliderPane);
                ft.setFromValue(0);
                ft.setToValue(1);
                ft.play();
                
                FadeTransition ft2 = new FadeTransition(Duration.seconds(1.5),backButtonOption);
                ft2.setFromValue(0);
                ft2.setToValue(1);
                ft2.play();
                
                FadeTransition ft4 = new FadeTransition(Duration.seconds(1.5),hiddenButtons);
                ft4.setFromValue(0);
                ft4.setToValue(1);
                ft4.play();
               
                optionPane.toFront();
            }
        });
        timeline.play();
        hiddenButtons.toFront();
        buttonsPane.toBack();
    }
    
    /**
     * Lance une nouvelle partie
     * @param event
     * @param seedString 
     */
    public void NouvellePartie(String seedString)
    {
        Jeu.getInstance().NouvellePartie(seedString);
        ControleurIA controleurIA = new ControleurIA(Jeu.getInstance().getPartie());
        
        // Chargement de l'IA
        Task<Void> task = new Task<Void>(){
            @Override
            protected Void call()  {
                controleurIA.avertir();
                return null;
            }
        };
        //start Task
        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();

        // Fake chargement
        Timeline tl = new Timeline();
        KeyFrame frame1 = new KeyFrame(Duration.seconds(1), eF1 -> chargement.setImage(ManagerSprite.get(EnumINTERFACE.chargement1)));
        KeyFrame frame2 = new KeyFrame(Duration.seconds(2), eF2 -> chargement.setImage(ManagerSprite.get(EnumINTERFACE.chargement2)));
        KeyFrame frame3 = new KeyFrame(Duration.seconds(3), eF3 -> chargement.setImage(ManagerSprite.get(EnumINTERFACE.chargement3)));
        KeyFrame frame4 = new KeyFrame(Duration.seconds(4), eF4 -> chargement.setImage(ManagerSprite.get(EnumINTERFACE.chargement4)));
        KeyFrame frame5 = new KeyFrame(Duration.seconds(5), eF5 -> chargement.setImage(ManagerSprite.get(EnumINTERFACE.chargement)));

        tl.getKeyFrames().addAll(frame1,frame2,frame3,frame4,frame5);

        tl.setOnFinished((eTimeLine)-> {
            Fenetre fenetreJeu = new FJeu(primaryStage);
            Fenetre.primaryStage.setScene(fenetreJeu.GetScene());
        });
        
        // durant l'IA
        task.setOnRunning(e -> {
            chargement();
            chargement.setVisible(true);
            tl.play();
        });

    }
    
    /**
     * Affiche la tablette et ses éléments visuels
     */
    public void displayMenu() {
        cover = new ImageView(ManagerSprite.get(EnumMENU.cover));
        cover.setTranslateX(175);
        cover.setTranslateY(149);
        
        allu = new ImageView(ManagerSprite.get(EnumMENU.allu));
        allu.setTranslateX(960);
        allu.setTranslateY(158);
        
        coverPane = new Pane();
        coverPane.getChildren().addAll(cover,allu);
        
        this.root.getChildren().add(coverPane);
    }
    
    /**
     * Déplace la tablette sur l'axe y sous forme d'animation
     * @param y décalement sur l'axe y
     * @return Timeline qui décale la tablette
     */
    public Timeline playAnimation(int y) {
        Timeline anim = new Timeline();
        
        KeyFrame k1 = new KeyFrame(Duration.seconds(1),
                new KeyValue(allu.xProperty(),y),
                new KeyValue(cover.xProperty(),y));
        
        anim.getKeyFrames().add(k1);
        return anim;
    }
    
    /**
     * Fonction qui ferme le jeu
     * @param event 
     */
    private void onQuit(MouseEvent event) {
        primaryStage.close();
    }

    /**
     * Fonction qui charge la dernière sauvegarde
     * @param event Evenement de clique
     */
    private void CreateGame(MouseEvent event) {
        //TODO
    }
    
}

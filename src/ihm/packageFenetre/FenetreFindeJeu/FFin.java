/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreFindeJeu;


import controleurs.observeurs.ObservateurScoreMax;
import ihm.EnumFONTS;
import ihm.EnumINTERFACE;
import ihm.ManagerFonts;
import ihm.ManagerSprite;
import ihm.packageFenetre.Fenetre;
import ihm.packageFenetre.FenetreJeu.FJeu;
import ihm.packageFenetre.FenetreMenu.FMenu;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;
import metier.PackageJeu.Jeu;

/**
 * Classe "Fin de jeu" qui affiche l'écran de fin
 * @author UNC
 */
public class FFin {
    private Pane paneFin;
    private Jeu jeu;
    private final FJeu fenetre;
    private ImageView darkBg;
    private TextField maxScore = new TextField("Calcul...");
    private final boolean aAbandonne;
    
    public FFin(FJeu fen,boolean aAbandonne) {
        this.paneFin = new Pane();
        this.jeu = Jeu.getInstance();
        this.fenetre = fen;
        this.aAbandonne = aAbandonne;
        ObservateurScoreMax observateurScoreMax = new ObservateurScoreMax(this, jeu.getPartie());
        
        
        displayEndGame();
        playAnimation();
        
        observateurScoreMax.avertir();
    }
    
    /**
     * Joue l'animation d'apparition de l'écran qui tombe
     */
    public void playAnimation() {
        Timeline anim = new Timeline();
        
        FadeTransition ft = new FadeTransition(Duration.seconds(1.5),this.darkBg);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
        
        KeyFrame k1 = new KeyFrame(Duration.seconds(0.5),new KeyValue(paneFin.layoutYProperty(),620));
        KeyFrame k2 = new KeyFrame(Duration.seconds(1),new KeyValue(paneFin.layoutYProperty(),570));
        
        anim.getKeyFrames().addAll(k1,k2);
        anim.play();
    }
    
    /**
     * Ajoute l'écran au jeu et l'affiche
     */
    public void displayEndGame() {
        this.darkBg = new ImageView(ManagerSprite.get(EnumINTERFACE.darkBackground));
        this.darkBg.opacityProperty().set(0);
        this.paneFin.getChildren().add(new ImageView(ManagerSprite.get(EnumINTERFACE.background_fin)));
        this.paneFin.translateXProperty().set(120);
        this.paneFin.translateYProperty().set(-620);
        displayScore();
        displaySeed();
        displayButtons();
        displayMaxScore();
        displayStars();
        if (aAbandonne) displayAbandon();
        this.fenetre.getRoot().getChildren().addAll(darkBg,paneFin);
    }
            
    /**
     * Ajoute la seed à l'écran
     */
    public void displaySeed() {
        TextField seed = new TextField();
        seed.setText(this.jeu.getPartie().getSeed());
        seed.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        seed.setTranslateX(86);
        seed.setTranslateY(400);
        seed.setEditable(false);
        seed.setId("seed");
        this.paneFin.getChildren().add(seed);
    }
    
    /**
     * Affiche les dépenses du joueur
     */
    public void displayScore() {
        Text score = new Text();
        score.setFont(ManagerFonts.getBigFont(EnumFONTS.LEMON_JUICE));
        score.setFill(Color.web("a2db2e"));
        score.setText("" + this.jeu.getJoueur().getDepense());
        
        VBox vbox = new VBox();
        vbox.getChildren().add(score);
        vbox.setAlignment(Pos.CENTER_RIGHT);
        vbox.setTranslateX(511);
        vbox.setTranslateY(375);
        this.paneFin.getChildren().add(vbox);
    }
    
    /**
     * Affiche les dépenses de l'IA
     */
    public void displayMaxScore() {
        maxScore.setFont(ManagerFonts.getMediumFont(EnumFONTS.LEMON_JUICE));
        maxScore.setTranslateX(660);
        maxScore.setTranslateY(400);
        maxScore.setAlignment(Pos.CENTER);
        maxScore.setEditable(false);
        maxScore.setId("seed");
        this.paneFin.getChildren().add(maxScore);
    }
    
    /**
     * Change l'affichage du score de l'IA
     * @param score 
     */
    public void setScoreMax(int score) {
        if (score != 0)
            maxScore.setText("" + score);
    }
    
    /**
     * Affiche les boutons Rejouer / Quitter
     */
    public void displayButtons() {
        ImageView retryBTN = new ImageView(ManagerSprite.get(EnumINTERFACE.retryBTN));
        retryBTN.setTranslateX(270);
        retryBTN.setTranslateY(530);
        retryBTN.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> retry());
        retryBTN.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                retryBTN.setImage(ManagerSprite.get(EnumINTERFACE.retryBTNhover));
            }
        });
        retryBTN.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                retryBTN.setImage(ManagerSprite.get(EnumINTERFACE.retryBTN));
            }
        });
        
        ImageView leaveBTN = new ImageView(ManagerSprite.get(EnumINTERFACE.leaveBTN));
        leaveBTN.setTranslateX(490);
        leaveBTN.setTranslateY(530);
        leaveBTN.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> retourMenu());
        leaveBTN.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                leaveBTN.setImage(ManagerSprite.get(EnumINTERFACE.leaveBTNhover));
            }
        });
        leaveBTN.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                leaveBTN.setImage(ManagerSprite.get(EnumINTERFACE.leaveBTN));
            }
        });
        this.paneFin.getChildren().addAll(retryBTN,leaveBTN);
    }
    
    /**
     * Affiche les dépenses par rapport à l'IA sous forme d'étoile
     * Exemple 4 étoiles sur 5 si on a presque réussi parfaitement
     */
    public void displayStars() {
        
    }

    /**
     * Après appuis "Rejouer" relance la partie 
     */
    private void retry() {
        Jeu jeu = Jeu.getInstance();
        int scoreMax = jeu.getPartie().getIAScore();
        jeu.NouvellePartie(this.jeu.getPartie().getSeed());
        jeu.getPartie().setIAScore(scoreMax);
        
        Fenetre fenetreJeu = new FJeu(this.fenetre.getPrimaryStage());
        
        this.fenetre.getPrimaryStage().setScene(fenetreJeu.GetScene());
    }

    /**
     * Retourne l'utilisateur au menu et quitte la partie
     */
    private void retourMenu() {
        FMenu menu = new FMenu(this.fenetre.getPrimaryStage());
        this.fenetre.getPrimaryStage().setScene(menu.GetScene());
    }

    /**
     * Affichage de l'abandon
     */
    private void displayAbandon() {
        ImageView abandonView = new ImageView(ManagerSprite.get(EnumINTERFACE.ABANDON));
        
        abandonView.setTranslateX(365);
        abandonView.setTranslateY(450);
        this.paneFin.getChildren().add(abandonView);
    }
 }

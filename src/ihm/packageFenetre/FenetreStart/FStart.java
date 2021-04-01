/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm.packageFenetre.FenetreStart;

import ihm.packageFenetre.Fenetre;
import ihm.packageFenetre.FenetreMenu.FMenu;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
<<<<<<< .mine
 * Fenetre de lancement du jeu
 * @author UNC
||||||| .r445
 *
 * @author antho
=======
 * 
 * @author UNC
>>>>>>> .r450
 */
public class FStart extends Fenetre{
    
    public FStart(Stage primStage) {
        super(primStage);
        this.setCSS("ressources/css/CSSFenetreStart.css");
    }
    
    /**
     * Affiche l'écran titre de chargement & de crédit
     */
    public void load() {
        Timeline anim = new Timeline();
        
        KeyFrame move = new KeyFrame(Duration.seconds(1));
        
        anim.setOnFinished((e)-> {
            Fenetre fenetreMenu = new FMenu(primaryStage);
            Fenetre.primaryStage.setScene(fenetreMenu.GetScene());
        });
        anim.getKeyFrames().add(move);
        
        anim.play();
    }

}

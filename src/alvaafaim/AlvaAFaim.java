package alvaafaim;


import ihm.EnumSPRITE;
import ihm.ManagerSprite;
import javafx.application.Application;
import javafx.stage.Stage;
import ihm.packageFenetre.FenetreStart.FStart;

/**
 * Alva a faim !
 * @author UNC
 */
public class AlvaAFaim extends Application {
    
    
    @Override
    public void start(Stage primaryStage) {
        FStart start = new FStart(primaryStage);
        
        primaryStage.setTitle("Alva A Faim");
        primaryStage.getIcons().add(ManagerSprite.get(EnumSPRITE.icon));
        primaryStage.setScene(start.GetScene());
        primaryStage.setResizable(false);
        primaryStage.show();
        start.load();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

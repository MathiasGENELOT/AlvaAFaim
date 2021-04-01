package ihm.packageFenetre.FenetreJeu.ElementInterface;

import ihm.packageFenetre.Fenetre;
import ihm.packageFenetre.FenetreJeu.FJeu;
import javafx.scene.image.ImageView;

/**
 * Element graphique de la map
 * @author UNC
 */
public abstract class ElementMap {
    protected ImageView caseImg;
    protected FJeu fenetreMap;

    /**
     * Renvoi la fenetre du jeu
     * @return 
     */
    public Fenetre getFenetreMap() {
        return fenetreMap;
    }
    
    public ElementMap(FJeu fJeu) {
        this.fenetreMap = fJeu;
        this.caseImg = new ImageView();
    }
    
    /**
     * Fonction abstraite qui affiche l'image
     */
    protected abstract void displayImage();

    /**
     * Renvoi l'image de l'élément
     * @return 
     */
    public ImageView getCaseImg() {
        return caseImg;
    }

    /**
     * Défini l'image de la map
     * @param caseImg 
     */
    public void setCaseImg(ImageView caseImg) {
        this.caseImg = caseImg;
    }
}

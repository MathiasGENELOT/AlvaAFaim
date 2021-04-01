package metier.PackageRecette;

import metier.PackageIngredient.Inventaire;
import java.util.ArrayList;

/**
 * Représente une recette
 * @author UNC
 */
public class Recette {

    private Inventaire inventaire; // inventaire de la recette
    private boolean estPret;       // si la recette est prête ou non
    private EnumRECETTES nom;      // type de recette
    
    public Recette(EnumRECETTES nom, Inventaire inventaire) {
        this.nom = nom;
        this.inventaire = inventaire;
    }
    
    public Inventaire getInventaire() {
        return inventaire;
    }

    public EnumRECETTES getNom() {
        return nom;
    }
    
    public boolean isEstPret() {
        return this.estPret;
    }

    public void setEstPret(boolean estPret) {
        this.estPret = estPret;
    }

    
    /**
    * Convertit le nom de l'énumération en toString correct à afficher
    * 
    */
    @Override
    public String toString() {
        char[] actual = this.nom.toString().toCharArray();
        String res = "";
        
        for(char c : actual) {
            String space = " "; 
            if (String.valueOf(c).equals("_")) {
                char[] spaceChar = space.toCharArray();
                res += spaceChar[0];
            }
            else res += c;
        }
        return res;
    }
}
package metier.PackageCarte;

import metier.PackageCarte.PackageCase.Case;
import java.util.*;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.Magasin;

/**
 * Représente la carte
 * @author UNC
 */
public class Carte {

    private HashMap<Coordonnee, Case> cases; // hashmap contient toutes les cases et les coordonnées de la map
    private int nbColonne;      // nb de case sur une colone 
    private int nbLigne;        // nb de case sur une ligne

    public int getNbColonne() {
        return nbColonne;
    }
    
    public int getNbLigne() {
        return nbLigne;
    }
    
    /**
     * Construit la carte
     */
    public Carte(HashMap<Coordonnee, Case> cases, int nbColonne,  int nbLigne) {
        this.cases = cases;
        this.nbColonne = nbColonne;
        this.nbLigne = nbLigne;
        
    }
    
    /**
     * retourne la case correspondant à la maison de la partie
     * @return Case
     */
    public Case getMaison() {
        Case res = null;
        
        for(Case c : cases.values())
            if (c.getType() == "Maison") res = c;
        
        return res;
    }
    
    /**
     * Retourne l'entièreté des cases de la carte
     * @return hashmap de case et coordonnée
     */
    public HashMap<Coordonnee, Case> getCases() {
        return this.cases;
    }
}
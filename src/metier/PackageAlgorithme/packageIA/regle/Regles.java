package metier.PackageAlgorithme.packageIA.regle;

import java.util.Collection;
import java.util.HashMap;

/**
 * Singleton contenant les règles du jeu de alva a faim, lisible par l'IA
 * @author UNC
 */
public class Regles {
    private static Regles instance;
    private HashMap<String,Regle> regles = new HashMap<>();
    
    private Regles() {}
    
    /**
     * Retourne la règle
     * @param nom le nom de la règle
     * @return une règle 
     */
    public static Regle get(String nom) {
        if (instance == null) instance = new Regles();

        return instance.regles.get(nom);
    }
    
    /**
     * Ajout d'une règle à la liste des règles
     * @param nom le nom de la règle
     * @param regle la règle
     */
    public static void ajoutRegle(String nom, Regle regle) {
        if (instance == null) instance = new Regles();
        instance.regles.put(nom, regle);
    }
    
    /**
     * Retourne toutes les règles
     * @return Collection<Regle>
     */
    public static Collection<Regle> getRegles() {
        return instance.regles.values();
    }
    
    /**
     * vide la liste de règle
     */
    public static void reset() {
        instance = null;
    }
    
}

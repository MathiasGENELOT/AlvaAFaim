/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.parametre;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Singleton d'une liste de paramètre
 * @author UNC
 */
public class Parametres {
    private static Parametres instance;
    private HashMap<String,Parametre> parametres = new HashMap<>();
    
    private Parametres() {}
    
    /**
     * Retourne un paramètre selon son nom
     * @param param le nom du paramètre
     * @return un paramètre
     */
    public static Parametre get(String param) {
        Parametre res;
        
        if (instance == null) instance = new Parametres();
        
        if (!instance.parametres.containsKey(param))
            instance.parametres.put(param, new Parametre(param));
        res = instance.parametres.get(param);
        return res;
    }
    
    /**
     * retourne tous les paramètres existants
     * @return String[]
     */
    public static String[] Types() {
        if (instance == null) instance = new Parametres();
        return (String[]) instance.parametres.keySet().toArray();
    }
    
    /**
     * reset la liste de paramètre
     */
    public static void reset() {
        instance = null;
    }
}

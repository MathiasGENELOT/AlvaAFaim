package metier.PackageCarte;

import metier.PackageAlgorithme.algorithme.AlgorithmeDur;
import metier.PackageAlgorithme.algorithme.AlgorithmeGenMap;
import metier.PackageAlgorithme.algorithme.AlgorithmeProcedural;

/**
 * Représente une fabrique de carte
 * @author UNC
 */
public class FabriqueCarte {

    private static AlgorithmeGenMap algo; // algorithme de génération aléatoire
    /**
     * Créer une carte, un niveau
     * @param difficulte
     */
    public static Carte CreerCarte(String typeAlgo,int seed) {
        Carte res = null;
        
        switch(typeAlgo){
            case "dur":
                algo = new AlgorithmeDur();
                break;
            case "procedural": 
                algo = new AlgorithmeProcedural();
                break;
        }
        
        res = new Carte(algo.calcul(seed),algo.getNbColonne(),algo.getNbLigne());
        return res;
    }
}
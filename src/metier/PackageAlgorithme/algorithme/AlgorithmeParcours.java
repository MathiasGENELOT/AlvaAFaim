package metier.PackageAlgorithme.algorithme;

import metier.PackageCarte.Carte;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageJeu.Partie;
import java.util.ArrayList;
import java.util.HashMap;
import metier.PackageCarte.PackageCase.EnumCASE;

/**
 * Algorithme qui créer des cartes "au hasard"
 * @author UNC
 */
public class AlgorithmeParcours {
    
    protected Partie partie;
    protected HashMap<Case, Integer> distances = new HashMap<>(); // distance de chacune des cases
    
    public AlgorithmeParcours(Partie partie) {
        this.partie = partie;
    }
    
    /**
     * renvoie la liste des bâtiments accessibles en fonction de la où est la case passée en paramètre
     * @param actuel case
     * @return ArrayList<Case> liste de case
     */
    public ArrayList<Case> getBatimentsAccessible(Case actuel) {
        ArrayList<Case> res = new ArrayList<>();
        ArrayList<Case> caseATraiter = new ArrayList<>();
        HashMap<Case, Boolean> caseVu = new HashMap<>();
        
                
        caseATraiter.add(actuel);
        caseVu.put(actuel,true);
        //calcul
        while (!caseATraiter.isEmpty()) {
            Case caseEnCours = caseATraiter.remove(0);
            
            for (Case v : caseEnCours.getVoisins()) 
                if (caseVu.get(v) == null)
                    if (v.getType() == "Magasin" || v.getType() == "Maison") {
                        res.add(v);
                        caseVu.put(v,true);
                    }
                    else if (v.getType() == "Route") {
                        caseATraiter.add(v);
                        caseVu.put(v,true);
                    }
        }
        
        return res;
    }
    
    /**
     * renvoie la liste des cases accessibles pour une case donnée
     * @param actuel case
     * @return ArrayList<Case>
     */
    public ArrayList<Case> parcoursLargeur(Case actuel) {
        ArrayList<Case> res = new ArrayList<>();
        ArrayList<Case> caseATraiter = new ArrayList<>();
        HashMap<Case, Boolean> caseVu = new HashMap<>();
                
        caseATraiter.add(actuel);
        caseVu.put(actuel,true);
        //calcul
        while (!caseATraiter.isEmpty()) {
            Case caseEnCours = caseATraiter.remove(0);
            
            for (Case v : caseEnCours.getVoisins()) 
                if (caseVu.get(v) == null)
                    if (v.getTypeEnum() != EnumCASE.HERBE) {
                        res.add(v);
                        caseVu.put(v,true);
                        caseATraiter.add(v);
                    }
        }
        
        return res;
    }
    
    public Carte getCarte() {
        return this.partie.getCarte();
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.algorithme;

import metier.PackageAlgorithme.algorithme.AlgorithmeParcours;
import metier.PackageCarte.Carte;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageJeu.Partie;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Classe, Algorithme de Dijkstra, fille de AlgorithmeParcours
 * @author UNC
 */
public class Dijkstra extends AlgorithmeParcours {
    
     private HashMap<Case,Case> predecesseur; // case précedente deja parcourus
    private int infini = 1+ getCarte().getNbColonne()* getCarte().getNbLigne()*16; // valeur infini (case a ne pas parcourir)
    private HashMap<Case, Boolean> caseVu = new HashMap<>(); // CaseVu par l'algorithme
    
    public Dijkstra(Partie partie) {
        super(partie);
        this.caseVu = new HashMap<>();
        this.predecesseur = new HashMap<>();
    }

    public int getInfini() {
        return infini;
    }
    
    /**
     * Renvoie le coût en mouvement pour aller à une certaine case
     * @param destination, la case en question
     * @return un entier
     */
    protected int coutMouvementVers(Case destination) {
        int res = this.infini;
        
        switch(destination.getType()) {
            case "Herbe": res = this.infini;
                break;
            case "Route": res = 1;
                break;
            case "Maison": res = 20;
                break;
            case "Magasin": res = 20;
                break;
        }
        
        return res;
    }
    
    /**
     * Initialise l'algorithme en fonction d'une case de départ
     * @param depart la case de départ
     */
    private void initialisation(Case depart) {
        for (Case v : getCarte().getCases().values()) {
            this.distances.put(v, infini);
            this.caseVu.put(v, false);
            this.predecesseur.put(v, null);
        }
        this.distances.put(depart, 0);
    }
    
    /**
     * Principe de relachement de l'algorithme en fonction de deux cases a et b
     * @param a une case
     * @param b une case
     */
    private void relachement(Case a, Case b) {
        if (this.distances.get(b) > this.distances.get(a) + coutMouvementVers(b)) {
            this.distances.put(b, this.distances.get(a) + coutMouvementVers(b));
            this.predecesseur.put(b, a);
        }
    }
    
    /**
     * calcule la case la plus proche
     * @return une case
     */
    private Case getCaseLaPlusProche() {
        int distanceMin = this.infini;
        Case res = null;
        for (Case c : getCarte().getCases().values()) {
            if (!this.caseVu.get(c))
                if (this.distances.get(c) < distanceMin) {
                    distanceMin = this.distances.get(c);
                    res = c;
                }
        }
        return res;
    }
    /**
     * Calcule la distance depuis une case de départ passée en paramètre
     * @param depart 
     */
    private void calculerDistancesDepuis(Case depart) {
        this.initialisation(depart);
        Case caseLaPlusProche = this.getCaseLaPlusProche();
        while (caseLaPlusProche != null) {
            caseVu.put(caseLaPlusProche,true);
            for (int i=0;i < caseLaPlusProche.getVoisins().size();i++)
                this.relachement(caseLaPlusProche, caseLaPlusProche.getVoisins().get(i));
            
            caseLaPlusProche = this.getCaseLaPlusProche();
        }
    }
    /**
     * Permet d'obtenir le chemin entre deux cases passée en paramètre
     * @param depart la case de départ
     * @param batiment le bâtiment
     * @return une liste de case qui constitue le chemin entre 2 cases
     */
    public ArrayList<Case> getChemin(Case depart, Case batiment) {
        this.calculerDistancesDepuis(depart);
        
        ArrayList<Case> resultat = new ArrayList<>();
        
        if (this.getBatimentsAccessible(depart).contains(batiment))  {
            //Initialisation
            Case caseActu = batiment;
            Case casePrec = this.predecesseur.get(batiment);

            //Calcul
            while(casePrec != null) {
                resultat.add(caseActu);
                caseActu = casePrec;
                casePrec = this.predecesseur.get(caseActu);
            }
        }
        Collections.reverse(resultat);
        return resultat;
    }
}

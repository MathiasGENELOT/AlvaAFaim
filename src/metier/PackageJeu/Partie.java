package metier.PackageJeu;

import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;
import metier.PackageCarte.FabriqueCarte;
import metier.PackageCarte.Carte;
import metier.PackageAlgorithme.algorithme.AlgorithmeParcours;
import metier.PackageAlgorithme.algorithme.Dijkstra;
import metier.PackageCarte.PackageCase.Case;
import java.util.ArrayList;
import java.util.Random;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageCarte.PackageCase.Maison;
import metier.PackageIngredient.Ingredients;
import metier.PackageIngredient.Inventaire;

/**
 * Represente une partie
 * @author UNC
 */
public class Partie {
    
    private Dijkstra dijkstra; // contient un algo de dijkstra
    private Carte carte;       // carte de la partie
    private Joueur joueur;     // joueur de la partie
    private String seed;       // seed de la partie
    private int IAScore = 0;   // score de l'IA
    
    /**
     * Construit la partie
     */
    public Partie(String seed) {
        this.seed = seed;
        int seedInt = 0;
        try
        {
            seedInt = Integer.valueOf(seed);
        } catch (Exception e)
        {
            if (seed.length() > 0) 
                for (int i = 0 ; i < seed.length() ; i++)
                {
                    char c = seed.charAt(i);
                    seedInt += Character.getNumericValue(c);
                }
        }
        
        Random r = new Random();
        if (seedInt == 0) {
            seedInt = r.nextInt();
            this.seed = String.valueOf(seedInt);
        }
        
        //this.carte = FabriqueCarte.CreerCarte("dur");
        this.carte = FabriqueCarte.CreerCarte("procedural",seedInt);
        
        this.joueur = new Joueur(this.carte.getMaison(),this);
        this.dijkstra = new Dijkstra(this);
    }
    
    public Dijkstra getDijkstra() {
        return dijkstra;
    }

    public String getSeed() {
        return this.seed;
    }
    /**
     * Permet d'obtenir les bâtiments accessibles pour le joueur dans la partie
     * @return liste des bâtiments accesibles
     */
    public ArrayList<Case> getBatimentsAccessible() {
        AlgorithmeParcours algo = new AlgorithmeParcours(this);
        return algo.getBatimentsAccessible(this.joueur.getCase());
    }

    /**
     * Permet de concocter une recette
     * @param recette
     */
    public boolean concocter(Recette recette) {
        return this.joueur.concocter(recette);
    }

    public Carte getCarte() {
        return this.carte;
    }

    public Joueur getJoueur() {
        return this.joueur;
    }
    /**
     * permet d'obtenir la liste des magasins de la partie
     * @return la liste des magasins
     */
    public ArrayList<Magasin> getMagasins() {
        ArrayList<Magasin> res = new ArrayList<>();
        
        for (Case c : carte.getCases().values())
            if (c.getTypeEnum() == EnumCASE.MAGASIN)
                res.add((Magasin) c);
        return res;
    }
    /**
     * permet d'obtenir la liste des cases de la partie
     * @return la liste des cases
     */
    public ArrayList<Case> getCases() {
        ArrayList<Case> res = new ArrayList<>();
        
        for (Case c : carte.getCases().values())
                res.add(c);
        return res;
    }
    /**
     * permet d'obtenir la maison de la partie
     * @return la maison
     */
    public Maison getMaison() {
        return (Maison) this.carte.getMaison();
    }
    /**
     * permet d'obtenir les ingrédients disponible dans la partie
     * @return les ingrédients de la partie
     */
    public ArrayList<Ingredients> getIngredientsPartie() {
        Inventaire res = new Inventaire();
        
        for (Magasin c : this.getMagasins())
            for (Ingredients ingr : c.getInventaire().getIngredients())
                res.ajouterIngredient(ingr);
        
        return res.getIngredients();
    }

    public void setIAScore(int score) {
        this.IAScore = score;
    }
    
    public int getIAScore() {
        return this.IAScore;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA;
import metier.PackageAlgorithme.packageIA.action.ActionAjout;
import metier.PackageAlgorithme.packageIA.action.ActionSet;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionEgale;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionInferieur;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionNotEgale;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionSuperieur;
import metier.PackageAlgorithme.packageIA.regle.Regle;
import metier.PackageAlgorithme.packageIA.regle.Regles;
import metier.PackageAlgorithme.packageIA.tree.Arbre;
import metier.PackageAlgorithme.packageIA.tree.Node;
import metier.PackageIngredient.Ingredients;
import metier.PackageJeu.Partie;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * classe de lancement de l'IA, calcule de score max de Alva a faim
 * @author UNC
 */
public class IASolution {
    private Partie partie;
    private Arbre arbre;
    
    /**
     * Constructeur de l'IA (calcul coût) 
     * @param partie partie dans laquelle l'IA va effectuer ses calculs
     */
    public IASolution(Partie partie) {
        String seed = partie.getSeed();
        this.partie = new Partie(seed);
    }
    
    /**
     * lance les calculs de l'IA
     * @return le score final calculé
     */
    public int start() {
        reset();
        initArbre();
        initParametres();
        initRegles();
        
        System.out.println(partie.getSeed());
        
        AstarAlgorithme astar = new AstarAlgorithme(arbre, 1, 50, partie);
        astar.Calcul();
        int scoreMax = astar.getScoreFinal();
        System.out.println(scoreMax);
        return scoreMax;
    }
    
    /**
     * initialise l'arbre
     */
    private void initArbre() {
        Etat etatRacine = new Etat();
        arbre = new Arbre();
        Node racine = new Node(null,etatRacine, partie);
        arbre.setRacine(racine);
    }
    
    /**
     * initialise les paramètres de l'IA
     */
    private void initParametres() {
        
        // Goal paramètre ( fin si etat = 1)
        arbre.getRacine().getEtat().setParametre(Parametres.get("RecetteConcoctable"),0);
        
        // Recette paramètre
        arbre.getRacine().getEtat().setParametre(Parametres.get("VouloirRecette"), -1);
            
        // Ingredient paramètre
        for(Ingredients ingr : this.partie.getIngredientsPartie()) {
            arbre.getRacine().getEtat().setParametre(Parametres.get("IA_"+ingr.getNom().toString()),0);
        }
        
        // Inventaire magasin
        for (int i = 0 ; i < this.partie.getMagasins().size(); i++) {
                for(Ingredients ingr : this.partie.getMagasins().get(i).getInventaire().getIngredients()) {
                    arbre.getRacine().getEtat().setParametre(Parametres.get("Magasin_"+i+"_PouvoirAchat_"+ingr.getNom().toString()), ingr.getNombre());
                }
        }
        
        // Bâtiment (Maison = -1) paramètre
        arbre.getRacine().getEtat().setParametre(Parametres.get("Batiment"),-1);
    }
    
    /**
     * initialise les règles
     */
    private void initRegles() {
        
        
        // Vouloir recette règle
        for (int rInt = 0 ; rInt < Recettes.get().listRecettesPartiePossible().size() ; rInt++) {
            Regle regleRecette = new Regle("VOULOIR");
            Recette r = Recettes.get().listRecettesPartiePossible().get(rInt);
            
            // Acions
            regleRecette.setActions(new ActionSet(Parametres.get("VouloirRecette"), rInt));
            
            // Préconditions
                regleRecette.setPreconditions(new PreconditionEgale(Parametres.get("VouloirRecette"), -1));
            
            Regles.ajoutRegle("VOULOIR_"+r.getNom().toString(),regleRecette);
        }
        
        // Se déplacer règles
        
            // Maison
            Regle regleMaison = new Regle("DEPLACER");
        
            // Actions
            regleMaison.setActions(new ActionSet(Parametres.get("Batiment"), -1));

            // Préconditions
            regleMaison.setPreconditions(new PreconditionSuperieur(Parametres.get("VouloirRecette"), 0));
            regleMaison.setPreconditions(new PreconditionSuperieur(Parametres.get("Batiment"), 0));
            
            Regles.ajoutRegle("DEPLACER_MAISON",regleMaison);
        
        
            // Magasin    
            for (int i = 0 ; i < this.partie.getMagasins().size(); i++) {
                Regle regle = new Regle("DEPLACER");
            
                // Action
                regle.setActions(new ActionSet(Parametres.get("Batiment"), i));
            
                // Precondition
                regle.setPreconditions(new PreconditionNotEgale(Parametres.get("Batiment"), i));
                regle.setPreconditions(new PreconditionNotEgale(Parametres.get("VouloirRecette"), -1));
            
                Regles.ajoutRegle("DEPLACER_MAGASIN_"+i,regle);
            }
        
        
        // Acheter un ingrédient règles
        for(int i = 0 ; i < this.partie.getMagasins().size() ; i++) {
            for (Ingredients ingr : this.partie.getMagasins().get(i).getInventaire().getIngredients()) {
                for (int j = 0 ; j < Recettes.get().listRecettesPartiePossible().size() ; j++) 
                    if (Recettes.get().listRecettesPartiePossible().get(j).getInventaire().getIngredient(ingr.getNom()) != null) {
                        Regle regle = new Regle("ACHAT");
                
                        // Actions
                        regle.setActions(new ActionAjout(Parametres.get("IA_"+ingr.getNom().toString()), 1));
                        regle.setActions(new ActionAjout(Parametres.get("Magasin_"+i+"_PouvoirAchat_"+ingr.getNom().toString()),-1));
                
                        // Préconditions
                
                        regle.setPreconditions(new PreconditionSuperieur(Parametres.get("Magasin_"+i+"_PouvoirAchat_"+ingr.getNom().toString()),1));
                        regle.setPreconditions(new PreconditionEgale(Parametres.get("VouloirRecette"), j));
                        regle.setPreconditions(new PreconditionInferieur(Parametres.get("IA_"), Recettes.get().listRecettesPartiePossible().get(j).getInventaire().getIngredient(ingr.getNom()).getNombre()));
                        regle.setPreconditions(new PreconditionEgale(Parametres.get("Batiment"), i));
            
                        Regles.ajoutRegle("Magasin_"+i+"_ACHAT_"+ingr.getNom().toString()+"_PourRecette_"+Recettes.get().listRecettesPartiePossible().get(j).getNom(),regle);
                    }
            }
        }
            
        
        // Concocter recette
        for (int rInt = 0 ; rInt < Recettes.get().listRecettesPartiePossible().size() ; rInt++) {
            Regle regleConcocter = new Regle("CONCOCTER");
            Recette r = Recettes.get().listRecettesPartiePossible().get(rInt);
            
            // Actions
            regleConcocter.setActions(new ActionSet(Parametres.get("RecetteConcoctable"), 1));
        
            // Precondition
            for (Ingredients ingr : r.getInventaire().getIngredients()) {
                regleConcocter.setPreconditions(new PreconditionSuperieur(Parametres.get("IA_"+ingr.getNom().toString()), ingr.getNombre()));
            }
            regleConcocter.setPreconditions(new PreconditionEgale(Parametres.get("VouloirRecette"), rInt));
            regleConcocter.setPreconditions(new PreconditionEgale(Parametres.get("Batiment"), -1));
                
            Regles.ajoutRegle("CONCOCTER_"+rInt, regleConcocter);
        }
    }
    
    /**
     * reset les multitons Regles et Parametres 
     */
    public void reset() {
        Regles.reset();
        Parametres.reset();
    }
}

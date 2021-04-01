/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import metier.PackageAlgorithme.algorithme.BellmanFord;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageAlgorithme.packageIA.regle.Regle;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Ingredients;
import metier.PackageJeu.Partie;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Classe représentant le noeud appartenant à un arbre
 * @author UNC
 */
public class Node {
    private Etat etat;
    /**
     * cout / heuristique initialisé à -1
     * change lorsque demandé
     */
    private int cout = -1;
    private int heuristic = -1;
    private HashMap<Regle, Node> fils = new HashMap<>();
    private Node parent;
    private Partie partie;
    private boolean vue = false;
    
    /**
     * Constructeur d'un noeud
     * @param parent noeud parent du noeud, null si racine principale (du premier arbre seulement)
     * @param etat état correspondant à l'etat de l'ia sur ce noeud
     * @param partie partie où est fait les calculs
     */
    public Node(Node parent, Etat etat, Partie partie) {
        this.parent = parent;
        this.etat = etat;
        this.partie = partie;
    }

    public HashMap<Regle, Node> getFils() {
        return fils;
    }

    public boolean isVue() {
        return vue;
    }

    public void setVue(boolean vue) {
        this.vue = vue;
    }
    
    public Regle getRegle() {
        Regle res = null;
        if (this.parent != null) {
            for (Regle r : this.parent.fils.keySet())
                if (this.parent.fils.get(r) == this) {
                    res = r;
                }
        }
        return res;
    }
    
    /**
     * retourne l'heuristique qui se calcule au moment de l'appel de la fonction
     * @return un entier, l'heuristique
     */
    public int getHeuristic() {
        
        if (this.heuristic == -1) { // Si l'heuristic n'est pas set
            this.heuristic = 0;
            // si on a une recette
            if (etat.getValue(Parametres.get("VouloirRecette")) != -1) {
                
                /*
                    Initiakisation
                */
                
                // Clone la partie
                Partie partieClone = new Partie(partie.getSeed());
                
                // Case actuelle dans clone
                if (this.etat.getValue(Parametres.get("Batiment")) == -1) {
                    partieClone.getJoueur().setCaseJoueur(partieClone.getMaison());
                } else {
                    partieClone.getJoueur().setCaseJoueur(partieClone.getMagasins().get(this.etat.getValue(Parametres.get("Batiment"))));
                }
                
                // Ingrédient restant dans recette voulue
                Recette rVoulue = Recettes.get().listRecettesPartiePossible().get(etat.getValue(Parametres.get("VouloirRecette")));
                ArrayList<EnumINGREDIENTS> ingrRestant = new ArrayList<>();
                
                for (Ingredients ingr : rVoulue.getInventaire().getIngredients()) {
                    for (int i = 0 ; i < (ingr.getNombre() - etat.getValue(Parametres.get("IA_"+ingr.getNom().toString()))) ; i++) 
                            ingrRestant.add(ingr.getNom());
                }
                
                
                /*
                    CALCULS
                */
                
                BellmanFord bmf = new BellmanFord(partieClone);
                
                for (EnumINGREDIENTS ingrActuel : ingrRestant) {
                    int coutTotIngr = 0;
                    Magasin MagasinChoisi = null;
                    for (Magasin m : partieClone.getMagasins()) {
                        if (m.contient(ingrActuel, 1)) {
                            
                            // Déplacement
                            int coutDeplacement = bmf.getChemin(partieClone.getJoueur().getCase(), m);
                            
                            // Achat
                            int coutAchat = m.getPrix(ingrActuel);
                            
                            // Total
                            if (coutTotIngr != 0) {
                                if (coutDeplacement + coutAchat < coutTotIngr) {
                                    coutTotIngr = coutDeplacement + coutAchat;
                                    MagasinChoisi = m;
                                }
                            } else {
                                coutTotIngr = coutDeplacement + coutAchat;
                            }
                        }
                    }
                    
                    //Modifiaction du joueur dans la partie clone
                    if (MagasinChoisi != null) {
                        partieClone.getJoueur().setCaseJoueur(MagasinChoisi);
                        MagasinChoisi.getInventaire().enleverIngredient(ingrActuel,1);
                    }
                    // Ajout au total
                    this.heuristic += coutTotIngr;
                }
                this.heuristic += bmf.getChemin(partieClone.getJoueur().getCase(), partie.getMaison());
                
            }
        }
        return this.heuristic;
    }
    
    public Etat getEtat() {
        return this.etat;
    }

    public Node getParent() {
        return this.parent;
    }

    /**
     * retourne le coût qui se calcule au moment du premier appel de la fonction
     * @return un entier, le coût
     */
    public int getCout() {
        
        if (this.cout == -1) {
            
        this.cout = 0;
        switch (getRegle().getType()) {
            case "VOULOIR": 
                this.cout = 0;
                break;
                
            case "DEPLACER": 
                
                BellmanFord bellmanford = new BellmanFord(partie);
                
                // Case départ
                Case depart;
                if (this.getParent().getEtat().getValue(Parametres.get("Batiment")) == -1)
                    depart = partie.getMaison();
                else
                    depart = partie.getMagasins().get(this.getParent().getEtat().getValue(Parametres.get("Batiment")));
                
                // Case arrivée
                Case arrivee;
                if (this.getEtat().getValue(Parametres.get("Batiment")) == -1)
                    arrivee = partie.getMaison();
                else
                    arrivee = partie.getMagasins().get(this.getEtat().getValue(Parametres.get("Batiment")));
                
                // Total
                this.cout = bellmanford.getChemin(depart, arrivee);
                break;
                
            case "ACHAT": 
                
                // Magasin actuel
                Magasin magasin = partie.getMagasins().get(this.getEtat().getValue(Parametres.get("Batiment")));
                
                // Recherche de l'ingrédient acheté
                for (Ingredients ingr : magasin.getInventaire().getIngredients()) {
                    int nbIngrdeIA = this.getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString()));
                    int nbIngrdeIA_Avant = this.getParent().getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString()));
                    
                    if (( nbIngrdeIA - nbIngrdeIA_Avant ) == 1) {
                        // On est sur ici de faire cette etape qu'une seule fois
                        this.cout = magasin.getPrix(ingr.getNom());
                    }
                }
                break;
                
            case "CONCOCTER": 
                this.cout = 0;
                break;
        }
        if (this.parent != null)
            this.cout += this.parent.getCout();
        }

        
        return this.cout;
    }
    
    /**
     * ajout d'un fils au noeud
     * @param regle règle permettant de donner ce fils
     * @param fils noeud fils à ajouter
     */
    public void AjoutFils(Regle regle, Node fils) {
        this.fils.put(regle, fils);
    }
    
    public void setCout(int cout) {
        this.cout = cout;
    }
    
    /**
     * distance jusqu'au noeud passé en paramètre
     * @param racineArbre noeud jusqu'à où on doit calculer
     * @return un entier, la distance
     */
    public int getProfondeur(Node racineArbre) {
        int res = 1;
        
        if (this != racineArbre && parent != null)
            res += parent.getProfondeur(racineArbre);
        
        return res;
    }
    
    /**
     * debug only
     * @return 
     */
    public int nbParent() {
        int res = 0;
        
        if (this.parent != null) {
            res += 1;
            res += parent.nbParent();
        }
        return res;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.etat);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Node) {
            Node other = (Node) obj;
            if (Objects.equals(this.etat, other.etat)) {
                res = true;
            }
        }
        return res;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA;

import java.util.ArrayList;
import metier.PackageAlgorithme.algorithme.BellmanFord;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageAlgorithme.packageIA.regle.Regle;
import metier.PackageAlgorithme.packageIA.regle.Regles;
import metier.PackageAlgorithme.packageIA.tree.Arbre;
import metier.PackageAlgorithme.packageIA.tree.Node;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageIngredient.Ingredients;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Partie;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Classe de l'algorithme d'astar exploité par l'IA, calcule de score max d'alva a faim
 * @author UNC
 */
public class AstarAlgorithme {
    private Arbre tree;
    private int scoreFinal = 0;
    private int pas;
    private int essaiAvNextPas;
    private Node current;
    private final Partie partie;
    
    /**
     * Constructeur de Astar
     * @param tree l'abre initial commençant l'algo
     * @param pas nombre de noeud "sauté" tous les essaiAvNextPas essai
     * @param essaiAvNextPas nombre de test sur des nodes avant de revenir sur un arbre neuf 
     * @param partie la partie ou effectuer les calculs
     */
    public AstarAlgorithme(Arbre tree, int pas, int essaiAvNextPas, Partie partie) {
        this.partie = partie;
        this.tree = tree;
        this.essaiAvNextPas = essaiAvNextPas;
        this.pas = pas;
        this.current = tree.getRacine();
        current.setCout(0);
    }
    
    /**
     * lancer les calculs de l'algo
     */
    public void Calcul() {
        current = this.tree.getRacine();
        int essaies = essaiAvNextPas;
        
        while (!estObjectif(current)) {
            while (essaies > 0 && !estObjectif(current)) {
            
                for (Regle regle : Regles.getRegles()) {
                    if (regle.estValide(current.getEtat())) {
                        Etat fils = regle.realiserActions(current.getEtat());
                        Node filsNode = new Node(current,fils,partie);
                        current.AjoutFils(regle, filsNode);
                    
                        this.tree.ajoutFeuille(filsNode);
                    }
                }
                                
//                System.out.println("--------------------- Fils");
//                for (Node nf : current.getFils().values()) {
//                    debug(nf);
//                }
//                System.out.println(" FIn Fils ---------------");
                
                current = MeilleurNode();
                
                debug(current);
                
                essaies--;
            }
            
            if (!estObjectif(current)) {
                nextPas();
                current = this.tree.getRacine();
                essaies = essaiAvNextPas;
                System.out.println("" + current.getProfondeur(null));
            }
            
        }
        cheminFinal(current);
    }
    
    /**
     * si un noeud est objectif
     * @param node noeud de l'arbre
     * @return boolean
     */
    private boolean estObjectif(Node node) {
        return node.getEtat().getValue(Parametres.get("RecetteConcoctable")) == 1;
    }
    
    /**
     * trouve le noeud au plus bas coût parmis les feuilles de l'arbre
     * @return Node
     */
    private Node MeilleurNode() {
        Node res = null;
        for (Node nodeActuel : this.tree.getFeuilles()) {
            if (res == null) {
                res = nodeActuel;
            } else if ( (res.getCout() + res.getHeuristic()) >= (nodeActuel.getCout() + nodeActuel.getHeuristic())) {
                res = nodeActuel;
            }
        }
        this.tree.retireFeuille(res);
        return res;
    }
    
    /**
     * saute au prochain arbre, la racine étant le noeud + pas en direction de le meilleur noeud actuel à la fin de ces calculs
     */
    private void nextPas() {
        current = MeilleurNode();
        
        Node nouvelleRacine = this.tree.nodeVers(current, pas);
        this.tree = new Arbre();
        this.tree.setRacine(nouvelleRacine);
    }
    
    /**
     * remonte le parcours final de l'ia afin de modifier le score final
     * @param node 
     */
    private void cheminFinal(Node node) {
        Node nodeA = node;
        
        while (nodeA.getParent() != null) {
            //debug(nodeA);
            nodeA = nodeA.getParent();
        }
        scoreFinal = node.getCout();
    }
    
    /**
     * debug console de l'état d'un noeud
     * @param node Node a debug
     */
    private void debug(Node node) {
        System.out.println("Etat Type : " + node.getRegle().getType());
        System.out.println(" - Cout En Jeu : " + node.getCout());
        System.out.println(" - Heuristic : " + node.getHeuristic());
        
        switch(node.getRegle().getType()) {
            
            case "VOULOIR":
                System.out.println(" - Recette : " + Recettes.get().listRecettesPartiePossible().get(node.getEtat().getValue(Parametres.get("VouloirRecette"))).getNom().toString());
                break;
                
            case "DEPLACER":
                BellmanFord bellmanford = new BellmanFord(partie);
                Case depart;
                Case arrivee;
                if (node.getParent().getEtat().getValue(Parametres.get("Batiment")) == -1)
                    depart = partie.getMaison();
                else
                    depart = partie.getMagasins().get(node.getParent().getEtat().getValue(Parametres.get("Batiment")));
                
                if (node.getEtat().getValue(Parametres.get("Batiment")) == -1)
                    arrivee = partie.getMaison();
                else
                    arrivee = partie.getMagasins().get(node.getEtat().getValue(Parametres.get("Batiment")));
                
                System.out.println(" - Cout du trajet : " + bellmanford.getChemin(depart,arrivee));
                System.out.println("Depart : " + depart + " Arrivée : " + arrivee);
                break;
                
            case "ACHAT":
                Magasin magasin = partie.getMagasins().get(node.getParent().getEtat().getValue(Parametres.get("Batiment")));
                
                
                for (Ingredients ingr : this.partie.getIngredientsPartie()) {
                    int nbIngrdeIA = node.getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString()));
                    int nbIngrdeIA_Avant = node.getParent().getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString()));
                    if (( nbIngrdeIA - nbIngrdeIA_Avant ) == 1) {
                        int coutAction = magasin.getPrix(ingr.getNom());
                        System.out.println(" - Cout de l'ingrédient : " + coutAction);
                        System.out.println(" - Ingrédient acheté : " + ingr.getNom().toString());
                    }
                }
                break;
                
            case "CONCOCTER":
                System.out.println(" - Recette : " + Recettes.get().listRecettesPartiePossible().get(node.getEtat().getValue(Parametres.get("VouloirRecette"))).getNom().toString());
                break;
        }
        
        System.out.println(" - Ingrédients de l'IA :");
        for (Ingredients ingr : this.partie.getIngredientsPartie()) {
            if (node.getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString())) > 0) {
                System.out.println(" [ "+ ingr.getNom().toString() +" x "+ node.getEtat().getValue(Parametres.get("IA_"+ingr.getNom().toString())) +" ]");
            }
        }
        
        Case actuel;
        
        if (node.getEtat().getValue(Parametres.get("Batiment")) == -1)
                    actuel = partie.getMaison();
                else
                    actuel = partie.getMagasins().get(node.getEtat().getValue(Parametres.get("Batiment")));
        System.out.println(" - Actuellement sur : " + actuel);
        
        System.out.println("---");
    }
    
    /**
     * Disponible après Calcul() !!
     * @return un entier, le score final
     */
    public int getScoreFinal() {
        return this.scoreFinal;
    }
}

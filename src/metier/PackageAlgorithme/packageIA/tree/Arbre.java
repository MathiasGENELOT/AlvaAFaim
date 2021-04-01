/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.tree;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Classe représentant l'arbre composé de node
 * @author UNC
 */
public class Arbre {
    private Node racine;
    private ArrayList<Node> feuilles = new ArrayList<>();
    
    public Arbre() {
    }

    public void setRacine(Node racine) {
        this.racine = racine;
    }
    
    public Node getRacine() {
        return this.racine;
    }
    
    public int getProfondeur(Node node) {
        return node.getProfondeur(this.racine);
    }
    
    /**
     * renvoie le noeud en direction de <node> de distance <pas> de la racine
     * @param node noeud
     * @param pas un entier
     * @return un noeud
     */
    public Node nodeVers(Node node, int pas) {
        Node res = node;
        
        for (int i = 0; i < node.getProfondeur(this.racine)-(pas+1); i++) {
            res = res.getParent();
        }
        return res;
    }
    
    /**
     * ajout de feuille à l'arbre
     * @param node le noeud
     */
    public void ajoutFeuille(Node node) {
        
        if (this.feuilles.contains(node)) {
            this.feuilles.remove(node);
        }
        
        this.feuilles.add(node);
    }
    
    public Collection<Node> getFeuilles() {
        return this.feuilles;
    }

    public void retireFeuille(Node res) {
        this.feuilles.remove(res);
    }
}

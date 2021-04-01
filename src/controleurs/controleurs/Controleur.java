/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.controleurs;

import controleurs.observeurs.Observateur;
import java.util.ArrayList;

/**
 * Classe Principal des controleurs
 * @author UNC
 */
public abstract class Controleur {
    
    private ArrayList<Observateur> observateurs;
    
    public Controleur() {
        this.observateurs=new ArrayList<>();
    }
    
    /**
     * ajout d'observateurs
     * @param obs Observateur...
     */
    public void addObservateur(Observateur... obs){
        for (Observateur o : obs)
            this.observateurs.add(o);
    }
    
    /**
     * enclanche les observateurs (avertir)
     */
    public void avertirObservateurs() {
        for(Observateur obs : this.observateurs) {
            obs.avertir();
        }
    }
}

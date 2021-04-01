/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.action;

import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametre;

/**
 * Action d'une règle
 * @author UNC
 */
public abstract class Action {
    protected Parametre parametre;
    protected Integer value;
    
    /**
     * Constructeur d'action abstrait
     * @param parametre paramètre modifié par l'action
     * @param val un entier valeur de l'action
     */
    public Action(Parametre parametre, Integer val) {
        this.parametre = parametre;
        this.value = val;
    }
    
    /**
     * effectuer l'action
     * @param etat l'état d'arrivée
     * @return un état, le nouvel état donné après l'action
     */
    public abstract Etat realiser(Etat etat);
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.action;

import java.util.Objects;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametre;

/**
 * Action d'ajout qui hérite de la classe Action
 * @author UNC
 */
public class ActionAjout extends Action{

    /**
     * Constructeur d'action d'ajout
     * @param parametre le paramètre à modifier 
     * @param val un entier, la valeur à ajouter
     */
    public ActionAjout(Parametre parametre, Integer val) {
        super(parametre, val);
    }

    @Override
    public Etat realiser(Etat etat) {
        Etat res = new Etat(etat);
        res.setParametre(parametre, etat.getValue(parametre) + value);
        return res;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.parametre);
        hash = 19 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if (obj instanceof ActionAjout) {
            ActionAjout other = (ActionAjout) obj;
            if (!this.parametre.equals(other.parametre)) res = false;
            if (!this.value.equals(other.value)) res = false;
            
        } else res = false;

        return res;
    }
    
}

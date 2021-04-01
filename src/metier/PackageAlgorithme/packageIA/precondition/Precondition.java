/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.precondition;

import java.util.Objects;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametre;

/**
 * Précondition d'une règle de l'IA
 * @author UNC
 */
public abstract class Precondition {
    protected Parametre parametre;
    protected Integer value;
    
    /**
     * Constructeur abstrait
     * @param parametre paramètre devant valider la condition
     * @param val un entier, la valeur de la précondition
     */
    public Precondition(Parametre parametre, Integer val) {
        this.parametre = parametre;
        this.value = val;
    }
    
    /**
     * teste si l'état en paramètre remplie la condition
     * @param etat état devant être testé
     * @return un booléen
     */
    public abstract boolean estValide(Etat etat);

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.parametre);
        hash = 47 * hash + Objects.hashCode(this.value);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if (obj instanceof Precondition) {
            Precondition other = (Precondition) obj;
            if (!this.parametre.equals(other.parametre)) res = false;
            if (!this.value.equals(other.value)) res = false;
            
        } else res = false;

        return res;
    }
    
    
}

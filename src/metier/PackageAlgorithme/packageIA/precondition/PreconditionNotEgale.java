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
 * Précondition non égale d'une règle de l'IA qui hérite de Precondition
 * @author UNC
 */
public class PreconditionNotEgale extends Precondition{

    /**
     * Constructeur d'une condition non égale
     * @param parametre paramètre devant être validé
     * @param val un entier, la valeur à comparer
     */
    public PreconditionNotEgale(Parametre parametre, Integer val) {
        super(parametre, val);
    }

    @Override
    public boolean estValide(Etat etat) {
        boolean res = false;
        if (!etat.getValue(parametre).equals(value)) res = true;
        return res;
    }
    
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

        if (obj instanceof PreconditionNotEgale) {
            PreconditionNotEgale other = (PreconditionNotEgale) obj;
            if (!this.parametre.equals(other.parametre)) res = false;
            if (!this.value.equals(other.value)) res = false;
            
        } else res = false;

        return res;
    }
}

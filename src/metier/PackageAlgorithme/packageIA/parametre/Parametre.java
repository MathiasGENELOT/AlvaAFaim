/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.parametre;

import java.util.Objects;

/**
 * Paramètre d'un état
 * @author UNC
 */
public class Parametre {
    private String nom;
    
    /**
     * Constructeur d'un paramètre
     * @param nom le nom 
     */
    public Parametre(String nom) {
        this.nom = nom;
    }
    
    public String getNom() {
        return this.nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.nom);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = false;
        if (obj instanceof Parametre) {
            Parametre other = (Parametre) obj;
            if (this.nom.equals(other.nom)) res = true;
        }
        return res;
    }
    
    
}

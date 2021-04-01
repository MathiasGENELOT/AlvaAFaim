/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.precondition;

import java.util.ArrayList;
import java.util.Objects;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametre;

/**
 * Précondition dans une liste d'une règle de l'IA qui hérite de Precondition
 * @author UNC
 */
public class PreconditionDansListe extends Precondition {
    private ArrayList<Integer> valeurs = new ArrayList<>();
    
    /**
     * Constructeur de la précondition valdiant : est dans la liste
     * @param parametre paramètre devant être validé
     * @param valeurs ArrayList<Integer> liste à tester
     */
    public PreconditionDansListe(Parametre parametre, ArrayList<Integer> valeurs) {
        super(parametre, null);
        this.valeurs = valeurs;
    }

    @Override
    public boolean estValide(Etat etat) {
        return (valeurs.contains(etat.getValue(parametre)));
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.parametre);
        hash = 47 * hash + Objects.hashCode(this.valeurs);
        return hash;
    }
    
    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if (obj instanceof PreconditionDansListe) {
            PreconditionDansListe other = (PreconditionDansListe) obj;
            if (!this.parametre.equals(other.parametre)) res = false;
            if (!this.valeurs.equals(other.valeurs)) res = false;
            
        } else res = false;

        return res;
    }
    
}

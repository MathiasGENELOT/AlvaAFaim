/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageCarte.PackageCase;

import metier.PackageCarte.Coordonnee;
import metier.PackageIngredient.EnumINGREDIENTS;

/**
 * Case d'herbe, ne possédant pas de particularité, qui hérite de Case
 * @author UNC
 */
public class Herbe extends Case {

    public Herbe(Coordonnee coor) {
        super(coor);
    }

    @Override
    public String getType() {
        return "Herbe";
    }

    @Override
    public int getPrix() {
        return 0;
    }

    @Override
    public int getPrix(EnumINGREDIENTS ing) {
        return 0;
    }

    @Override
    public EnumCASE getTypeEnum() {
        return EnumCASE.HERBE;
    }
}

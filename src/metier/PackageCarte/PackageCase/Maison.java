package metier.PackageCarte.PackageCase;

import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.EnumBATIMENTS;

/**
 * Représente un bâtiment Maison qui hérite de Bâtiment
 * @author UNC
 */
public class Maison extends Batiment 
{
    
    public Maison(Coordonnee coor) 
    {
        super(coor);
    }

    @Override
    public String getType() 
    {
        return "Maison";
    }
    
    @Override
    public int getPrix() {
        return 0;
    }

    @Override
    public EnumCASE getTypeEnum() {
        return EnumCASE.MAISON;
    }
}
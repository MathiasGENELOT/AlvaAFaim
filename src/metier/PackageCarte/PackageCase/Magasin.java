package metier.PackageCarte.PackageCase;

import metier.PackageCarte.Coordonnee;

/**
 * Représente un batiment Magasin qui hérite de Bâtiment
 * @author UNC
 */
public class Magasin extends Batiment {
    
    public Magasin(Coordonnee coor) 
    {
        super(coor);
    }

    @Override
    public String getType() 
    {
        return "Magasin";
    }
    
    @Override
    public int getPrix() {
        return 0;
    }

    @Override
    public EnumCASE getTypeEnum() {
        return EnumCASE.MAGASIN;
    }
}
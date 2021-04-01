/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageBatiment;

import metier.PackageCarte.PackageCase.Magasin;
import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.FabriqueCase;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Ingredients;
import metier.PackageIngredient.Inventaire;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Galadrielle
 */
public class BatimentTest 
{
    /**
     * Test of rachatIngredient and VenteIngrédient methods, of class Batiment.
     */
    @Test
    public void testRachatVenteIngredient() 
    {
        EnumINGREDIENTS ingredient = EnumINGREDIENTS.TOMATE;
        Coordonnee coor = new Coordonnee(1,2);
        Batiment instance = new Magasin(coor);
        instance.rachatIngredient(ingredient);
        
        boolean res = instance.venteIngredient(ingredient);
        assertTrue(res);
        boolean res2 = instance.venteIngredient(ingredient);
        assertFalse(res2);
    }
    
    @Test
    public void testInitInventaire()
    {
        Inventaire inv1 = new Inventaire();
        inv1.ajouterIngredient(EnumINGREDIENTS.TOMATE,1);
        inv1.ajouterIngredient(EnumINGREDIENTS.VIN, 2);
        Batiment b1 = (Batiment) FabriqueCase.creer(new Coordonnee(6, 1), EnumCASE.MAGASIN);
        HashMap<EnumINGREDIENTS, Integer> prix1 = new HashMap<EnumINGREDIENTS, Integer>();
        prix1.put(EnumINGREDIENTS.TOMATE, 3);
        b1.initInventaire(inv1, prix1);
        Assert.assertEquals(3, b1.getPrix(EnumINGREDIENTS.TOMATE));
        Ingredients i1 = new Ingredients(EnumINGREDIENTS.TOMATE, 1);
        Ingredients i2 = new Ingredients(EnumINGREDIENTS.VIN, 2);
        Assert.assertEquals(true,b1.getInventaire().getIngredients().get(0).equals(i1));
        Assert.assertEquals(true,b1.getInventaire().getIngredients().get(1).equals(i2));
        Assert.assertEquals(false,b1.getInventaire().getIngredients().get(0).equals(i2));
    }
}

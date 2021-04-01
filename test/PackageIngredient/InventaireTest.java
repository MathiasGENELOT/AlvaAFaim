/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageIngredient;

import metier.PackageIngredient.Inventaire;
import metier.PackageIngredient.Ingredients;
import metier.PackageIngredient.EnumINGREDIENTS;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Mathias
 */
public class InventaireTest {
    
    public InventaireTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ajouterIngredient method, of class Inventaire.
     */
    @Test
    public void testAjouterIngredient() {
        Inventaire actual = new Inventaire(); 
        Ingredients expected = new Ingredients(EnumINGREDIENTS.TOMATE, 1);
        actual.ajouterIngredient(EnumINGREDIENTS.TOMATE,1);
        Assert.assertEquals(expected,actual.getIngredients().get(0));
        Assert.assertNotNull(actual);
    }

    /**
     * Test of contient method, of class Inventaire.
     */
    @Test
    public void testContient() {
        Inventaire i1 = new Inventaire(); // inventaire de la recette
        Inventaire i2 = new Inventaire(); // inventaire du personnage
        i1.ajouterIngredient(EnumINGREDIENTS.TOMATE,1);
        i1.ajouterIngredient(EnumINGREDIENTS.PARMESAN,2);
        
        i2.ajouterIngredient(EnumINGREDIENTS.PARMESAN, 3);
        i2.ajouterIngredient(EnumINGREDIENTS.TOMATE, 1);
        i2.ajouterIngredient(EnumINGREDIENTS.PATE, 2);
        i2.ajouterIngredient(EnumINGREDIENTS.MOZZARELLA,3);
        Assert.assertEquals(false,i1.contient(i2));
        Assert.assertEquals(true,i2.contient(i1));
        
        i1.ajouterIngredient(EnumINGREDIENTS.VIN,0);
        Assert.assertEquals(true,i2.contient(i1));
    }

    /**
     * Test of enleverIngredient method, of class Inventaire.
     */
    @Test
    public void testEnleverIngredient() {
        Inventaire actual1 = new Inventaire();
        Ingredients tomate = new Ingredients(EnumINGREDIENTS.TOMATE, 2);
        actual1.getIngredients().add(tomate);
        
        Ingredients expected1 = new Ingredients(EnumINGREDIENTS.TOMATE, 1);
        Ingredients expected2 = new Ingredients(EnumINGREDIENTS.TOMATE, 0);
        
        actual1.enleverIngredient(EnumINGREDIENTS.TOMATE,1);
        Assert.assertEquals(expected1,actual1.getIngredients().get(0));
        Assert.assertNotNull(actual1);
        
        actual1.enleverIngredient(EnumINGREDIENTS.TOMATE,1);
        Assert.assertEquals(expected2,actual1.getIngredients().get(0));
        
        boolean actual2 = actual1.enleverIngredient(EnumINGREDIENTS.TOMATE,1);
        
        Assert.assertFalse(actual2);
    }
    
}

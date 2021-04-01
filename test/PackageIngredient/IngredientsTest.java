/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageIngredient;

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
public class IngredientsTest {
    
    public IngredientsTest() {
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
     * Test of getNombre method, of class Ingredients.
     */
    @Test
    public void testGetNombre() {
        int result = 0;
        Ingredients ingredient = new Ingredients(EnumINGREDIENTS.JAMBON,4);
        result = ingredient.getNombre();
        Assert.assertEquals(4,result);
    }

    /**
     * Test of setNombre method, of class Ingredients.
     */
    @Test
    public void testSetNombre() {
        Ingredients ingredient = new Ingredients(EnumINGREDIENTS.JAMBON,4);
        ingredient.setNombre(2);
        Assert.assertEquals(2,ingredient.getNombre()); 
    }

    /**
     * Test of getNom method, of class Ingredients.
     */
    @Test
    public void testGetNom() {
        EnumINGREDIENTS result = null;
        Ingredients ingredient = new Ingredients(EnumINGREDIENTS.JAMBON,4);
        result = ingredient.getNom();
        Assert.assertEquals(EnumINGREDIENTS.JAMBON,result);
    }
    
}

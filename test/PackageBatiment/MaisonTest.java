/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageBatiment;

import metier.PackageCarte.PackageCase.Maison;
import metier.PackageCarte.Coordonnee;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Galadrielle
 */
public class MaisonTest {
    
    /**
     * Test of getType method, of class Maison.
     */
    @Test
    public void testGetType() {
        Coordonnee coor = new Coordonnee(1,2);
        Maison instance = new Maison(coor);
        String expResult = "Maison";
        String result = instance.getType();
        assertEquals(expResult, result);
    }
    
}

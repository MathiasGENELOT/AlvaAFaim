/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.parametre;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class ParametreTest {
    
    public ParametreTest() {
    }
    
    /**
     * Test of equals method, of class Parametre.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Parametre obj = Parametres.get("Test");
        Parametre instance = Parametres.get("Test");
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}

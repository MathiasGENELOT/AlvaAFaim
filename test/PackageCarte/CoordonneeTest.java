/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageCarte;

import metier.PackageCarte.EnumVOISIN;
import metier.PackageCarte.Coordonnee;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class CoordonneeTest {

    /**
     * Test of getVoisin method, of class Coordonnee.
     */
    @Test
    public void testGetVoisin() {
        System.out.println("getVoisin");
        EnumVOISIN sens = EnumVOISIN.BAS;
        Coordonnee instance = new Coordonnee(1, 1);
        Coordonnee expResult = new Coordonnee(1, 2);
        Coordonnee result = instance.getVoisin(sens);
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Coordonnee.
     */
    @Test
    public void testEquals() {
        Coordonnee o = new Coordonnee(1, 1);
        Coordonnee instance = new Coordonnee(1, 1);
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
    
}

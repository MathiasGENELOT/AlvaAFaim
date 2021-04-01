/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.precondition;

import metier.PackageAlgorithme.packageIA.Etat;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class PreconditionEgaleTest {
    
    public PreconditionEgaleTest() {
    }

    /**
     * Test of estValide method, of class PreconditionEgale.
     */
    @Test
    public void testEstValide() {
        System.out.println("estValide");
        Etat etat = null;
        PreconditionEgale instance = null;
        boolean expResult = false;
        boolean result = instance.estValide(etat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hashCode method, of class PreconditionEgale.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        PreconditionEgale instance = null;
        int expResult = 0;
        int result = instance.hashCode();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class PreconditionEgale.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        PreconditionEgale instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

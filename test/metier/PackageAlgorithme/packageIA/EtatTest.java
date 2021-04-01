/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA;

import metier.PackageAlgorithme.packageIA.parametre.Parametre;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class EtatTest {
    
    public EtatTest() {
    }

    /**
     * Test of equals method, of class Etat.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Etat obj = new Etat();
        obj.setParametre(Parametres.get("TEST1"), 1);
        obj.setParametre(Parametres.get("TEST2"), 5);
        
        Etat instance = new Etat();
        instance.setParametre(Parametres.get("TEST1"), 1);
        instance.setParametre(Parametres.get("TEST2"), 5);
        
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
    }
    
}

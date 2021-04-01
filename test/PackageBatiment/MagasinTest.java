/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PackageBatiment;

import metier.PackageCarte.PackageCase.Magasin;
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
public class MagasinTest 
{
    /**
     * Test of getType method, of class Magasin.
     */
    @Test
    public void testGetType() 
    {
        Coordonnee coor = new Coordonnee(1,2);
        Magasin instance = new Magasin(coor);
        String expResult = "Magasin";
        String result = instance.getType();
        assertEquals(expResult, result);
    }
    
}

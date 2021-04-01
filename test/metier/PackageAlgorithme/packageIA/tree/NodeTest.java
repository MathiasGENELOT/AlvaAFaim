/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.tree;

import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class NodeTest {
    
    public NodeTest() {
    }
    /**
     * Test of equals method, of class Node.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        
        Etat etat = new Etat();
        etat.setParametre(Parametres.get("Test1"), 1);
        etat.setParametre(Parametres.get("Test2"), 0);
        etat.setParametre(Parametres.get("Test3"), 3);
        
        Etat etat2 = new Etat();
        etat2.setParametre(Parametres.get("Test1"), 1);
        etat2.setParametre(Parametres.get("Test2"), 0);
        etat2.setParametre(Parametres.get("Test3"), 3);
        
        Object obj = new Node(null, etat, null);
        Node instance = new Node(null, etat2, null);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}

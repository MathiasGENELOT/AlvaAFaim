/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.regle;

import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.action.Action;
import metier.PackageAlgorithme.packageIA.action.ActionSet;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageAlgorithme.packageIA.precondition.Precondition;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionEgale;
import metier.PackageAlgorithme.packageIA.precondition.PreconditionSuperieur;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class RegleTest {
    
    public RegleTest() {
    }

    /**
     * Test of equals method, of class Regle.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Regle obj = new Regle("");
        obj.setActions(new ActionSet(Parametres.get("test"),2));
        obj.setPreconditions(new PreconditionEgale(Parametres.get("testPreco"),1));
        
        Regle instance = new Regle("");
        instance.setActions(new ActionSet(Parametres.get("test"),2));
        instance.setPreconditions(new PreconditionEgale(Parametres.get("testPreco"),1));
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.precondition;

import java.util.ArrayList;
import java.util.Arrays;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageRecette.Recettes;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class PreconditionDansListeTest {
    
    public PreconditionDansListeTest() {
    }

    /**
     * Test of equals method, of class PreconditionDansListe.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new PreconditionDansListe(Parametres.get("TEST"),new ArrayList<>(Arrays.asList(1,2)));
        PreconditionDansListe instance = new PreconditionDansListe(Parametres.get("TEST"),new ArrayList<>(Arrays.asList(1,2)));
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);

    }
    
    
    @Test
    public void testRegleAchat() {
        System.out.println(Recettes.get().listRecettes().get(1));
        assertFalse(Recettes.get().listRecettes().get(1).getInventaire().getIngredient(EnumINGREDIENTS.OEUF) != null);
        assertTrue(Recettes.get().listRecettes().get(1).getInventaire().getIngredient(EnumINGREDIENTS.VIANDE) != null);
    }
    
    
}

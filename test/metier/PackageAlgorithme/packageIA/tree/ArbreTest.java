/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.tree;

import java.util.Collection;
import metier.PackageAlgorithme.packageIA.Etat;
import metier.PackageAlgorithme.packageIA.parametre.Parametres;
import metier.PackageJeu.Partie;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antho
 */
public class ArbreTest {
    
    public ArbreTest() {}

    /**
     * Test of ajoutFeuille method, of class Arbre.
     */
    @Test
    public void testAjoutFeuille() {
        System.out.println("ajoutFeuille");
        Etat etat = new Etat();
        etat.setParametre(Parametres.get("TEST1"), 0);
        etat.setParametre(Parametres.get("TEST2"), 43);
        
        Etat etat1 = new Etat();
        etat1.setParametre(Parametres.get("TEST1"), 0);
        etat1.setParametre(Parametres.get("TEST2"), 43);
        
        Node node1 = new Node(null, etat, new Partie("testest"));
        Node node2 = new Node(node1, etat1, new Partie("testest"));
        Arbre instance = new Arbre();
        instance.ajoutFeuille(node1);
        instance.ajoutFeuille(node2);
        
        assertEquals(1,instance.getFeuilles().size());
        
    }
    
}

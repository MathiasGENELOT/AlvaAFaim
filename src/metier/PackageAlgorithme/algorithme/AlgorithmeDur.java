/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.algorithme;

import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.FabriqueCase;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Inventaire;
import java.util.HashMap;
import metier.PackageAlgorithme.algorithme.AlgorithmeGenMap;
import metier.PackageRecette.EnumRECETTES;
import metier.PackageRecette.FabriqueRecette;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Algorithme de génération fixe de carte
 * @author UNC
 */
public class AlgorithmeDur extends AlgorithmeGenMap{
    
        @Override
    public HashMap<Coordonnee, Case> calcul(int seed) 
    {
        int tailleMap = 10;
        // remplir d'herbe
        init(tailleMap,tailleMap);
        
        // placer maison
        ajouterCaseV(new Coordonnee(2, 2),EnumCASE.MAISON);
        
        // Recette
        Recettes.get().ajouterRecette(EnumRECETTES.PIZZA);
        Recettes.get().ajouterRecette(EnumRECETTES.BOEUF_BOURGUIGNON);
        Recettes.get().ajouterRecette(EnumRECETTES.CRUMBLE_AUX_POMMES);
        Recettes.get().ajouterRecette(EnumRECETTES.CRUMBLE_AUX_POMMES);
        
        //ajouter magasins
        Case c1 = FabriqueCase.creer(new Coordonnee(6, 1), EnumCASE.MAGASIN);
        Case c2 = FabriqueCase.creer(new Coordonnee(8, 2), EnumCASE.MAGASIN);
        Case c3 = FabriqueCase.creer(new Coordonnee(6, 5), EnumCASE.MAGASIN);
        Case c4 = FabriqueCase.creer(new Coordonnee(2, 5), EnumCASE.MAGASIN);
        ajouterCaseV(c1);
        ajouterCaseV(c2);
        ajouterCaseV(c3);
        ajouterCaseV(c4);         
        Batiment b1 = (Batiment) c1;
        Batiment b2 = (Batiment) c2;
        Batiment b3 = (Batiment) c3;
        Batiment b4 = (Batiment) c4;
        
        //création de l'inventaire pour les magasins        
        Inventaire i1 = new Inventaire();
        HashMap<EnumINGREDIENTS, Integer> h1 = new HashMap<EnumINGREDIENTS, Integer>();
        Inventaire i2 = new Inventaire();
        HashMap<EnumINGREDIENTS, Integer> h2 = new HashMap<EnumINGREDIENTS, Integer>();
        Inventaire i3 = new Inventaire();
        HashMap<EnumINGREDIENTS, Integer> h3 = new HashMap<EnumINGREDIENTS, Integer>();
        Inventaire i4 = new Inventaire();
        HashMap<EnumINGREDIENTS, Integer> h4 = new HashMap<EnumINGREDIENTS, Integer>();
        
        //magasin 1
        i1.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON,3);
        i1.ajouterIngredient(EnumINGREDIENTS.TOMATE,1);
        h1.put(EnumINGREDIENTS.CHAMPIGNON,1);
        h1.put(EnumINGREDIENTS.TOMATE, 2);
        b1.initInventaire(i1,h1);
        
        //magasin 2
        i2.ajouterIngredient(EnumINGREDIENTS.VIN,2);
        i2.ajouterIngredient(EnumINGREDIENTS.EMMENTAL,2);
        h2.put(EnumINGREDIENTS.VIN, 12);
        h2.put(EnumINGREDIENTS.EMMENTAL,4);
        b2.initInventaire(i2,h2);
        
        //magasin 3
        i3.ajouterIngredient(EnumINGREDIENTS.EMMENTAL,1);
        i3.ajouterIngredient(EnumINGREDIENTS.LARDON,3);
        i3.ajouterIngredient(EnumINGREDIENTS.OEUF,2);
        h3.put(EnumINGREDIENTS.EMMENTAL, 14);
        h3.put(EnumINGREDIENTS.LARDON, 3);
        h3.put(EnumINGREDIENTS.OEUF, 5);
        b3.initInventaire(i3,h3);
        
        //magasin 4

        i4.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON,3);
        i4.ajouterIngredient(EnumINGREDIENTS.PATE,2);
        i4.ajouterIngredient(EnumINGREDIENTS.JAMBON,4);
        i4.ajouterIngredient(EnumINGREDIENTS.PARMESAN,3);
        h4.put(EnumINGREDIENTS.CHAMPIGNON,5);
        h4.put(EnumINGREDIENTS.PATE,4);
        h4.put(EnumINGREDIENTS.JAMBON,2);
        h4.put(EnumINGREDIENTS.PARMESAN,1);
        b4.initInventaire(i4,h4);
        
        Recette r =FabriqueRecette.CreerRecette(EnumRECETTES.PIZZA);
        
        //ajouter route
        ajouterCaseV(new Coordonnee(3, 2),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(4, 2),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(4, 1),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(5, 1),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(7, 1),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(8, 1),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(8, 3),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(8, 4),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(7, 4),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(7, 5),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(5, 3),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(5, 5),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(5, 4),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(6, 2),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(6, 3),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(4, 5),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(3, 5),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(2, 4),EnumCASE.ROUTE);
        ajouterCaseV(new Coordonnee(2, 3),EnumCASE.ROUTE);
        
        calculVoisin();
        calculSens();
        return this.cases;
    }
    
}

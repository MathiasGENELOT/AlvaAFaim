package metier.PackageAlgorithme.algorithme;

import java.util.ArrayList;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.PackageCase.Case;
import java.util.HashMap;
import java.util.Random;
import metier.PackageAlgorithme.generator.GenerateurAleatoire;
import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.FabriqueCase;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Ingredients;
import metier.PackageIngredient.Inventaire;
import metier.PackageRecette.EnumRECETTES;
import metier.PackageRecette.Recette;
import metier.PackageRecette.Recettes;

/**
 * Nous utilisons un algorithme procédural pour créer les cartes
 * @author UNC
 */
public class AlgorithmeProcedural extends AlgorithmeGenMap {

    @Override
    public HashMap<Coordonnee, Case> calcul(int seed) {
        
        GenerateurAleatoire.get().setSeedGlobal(seed);
        
        nbColonne = GenerateurAleatoire.get().nextPositiveInt()%8 + 8;
        nbLigne = GenerateurAleatoire.get().nextPositiveInt()%8 + 8;
        
        init(nbColonne,nbLigne);
        
        //Maison aléatoire
        Case maison = FabriqueCase.creer(new Coordonnee(GenerateurAleatoire.get().nextPositiveInt()%(nbColonne-2)+1, GenerateurAleatoire.get().nextPositiveInt()%(nbLigne-2)+1),EnumCASE.MAISON);

        ajouterCaseV(maison);
        
        int nbRecette = GenerateurAleatoire.get().nextPositiveInt()%3 + 4;
        int nbMagasin = (nbColonne*nbLigne)/25;
        
        //Recette aléatoire
        Recettes.get().ResetRecettePartie();
        ArrayList<EnumRECETTES> recettesJeu = new ArrayList<>();
        
        for (Recette r : Recettes.get().listRecettes()) {
            recettesJeu.add(r.getNom());
        }
        
        
        for (int i = 0 ; i < nbRecette ; i++) {
            EnumRECETTES enumR = recettesJeu.remove(GenerateurAleatoire.get().nextPositiveInt()%recettesJeu.size());
            Recettes.get().ajouterRecette(enumR);
        }
        
        //Ingrédients aléatoires
        ArrayList<EnumINGREDIENTS> enumIngrPartie = new ArrayList<>();
        for (EnumINGREDIENTS enumIngr : EnumINGREDIENTS.values()) {
            enumIngrPartie.add(enumIngr);
        }
        
        int recettesConcoctables = 0;
        
        //Recettes possibles déjà check 
        ArrayList<EnumRECETTES> recettesConcoctable = new ArrayList<>();
        Inventaire inventairePartie = new Inventaire();
        
        ArrayList<Inventaire> invMagasin = new ArrayList<>();
        ArrayList<HashMap<EnumINGREDIENTS,Integer>> prixMagasin = new ArrayList<>();
        
        for (int j = 0 ; j < nbMagasin ; j++) {
            invMagasin.add(new Inventaire());
            prixMagasin.add(new HashMap<>());
        }
        
        while (recettesConcoctables < 3) {
            
            int inventaireIt = GenerateurAleatoire.get().nextPositiveInt()%invMagasin.size();
            EnumINGREDIENTS enumIngrRandom = enumIngrPartie.get(GenerateurAleatoire.get().nextPositiveInt()%enumIngrPartie.size());
            int nbIngrRandom = (GenerateurAleatoire.get().nextPositiveInt()%2)+1 ;
            
            invMagasin.get(inventaireIt).ajouterIngredient(enumIngrRandom, nbIngrRandom);
            inventairePartie.ajouterIngredient(enumIngrRandom, nbIngrRandom);
            
            for (Recette recettePartie : Recettes.get().listRecettesPartie()) {
                if (!recettesConcoctable.contains(recettePartie.getNom()) && inventairePartie.contient(recettePartie.getInventaire())) {
                    recettesConcoctable.add(recettePartie.getNom());
                    Recettes.get().ajouterRecettePossible(recettePartie.getNom());
                    recettesConcoctables++;
                }
            }
        }
        
        for (int j = 0 ; j < prixMagasin.size() ; j++) {
            for (Ingredients ingrInvMag : invMagasin.get(j).getIngredients())
                prixMagasin.get(j).put(ingrInvMag.getNom(), (GenerateurAleatoire.get().nextPositiveInt()%7) + 4);
        }

        //Magasins aléatoires
        for (int i = 0 ; i < nbMagasin;i++) {
            Batiment b = (Batiment) ajouterCaseAlea(EnumCASE.MAGASIN);
            b.initInventaire(invMagasin.get(i), prixMagasin.get(i));
        }

        
        
        //Routes aléatoires
        remplirRoute();
            
        //Supression des arrêtes
        suprAleaVoisin();
        
        //Supression routes inatégnables
        NettoyageRoutes(maison);
        
        Effeuillage();
            
        calculSens();
        return this.cases;
    }
    /**
     * Ajoute un bâtiment à un endroit aléatoire sur la carte en fonction de son enumBatiment
     * @param type le type de la case
     * @return renvoie la case ajoutée
     */
    public Case ajouterCaseAlea(EnumCASE type) {
        Case res = null;
        ArrayList<Case> caseDispo = getCaseDispo();
        if (caseDispo.size() > 0) {
            Coordonnee coorDispo = caseDispo.get(GenerateurAleatoire.get().nextPositiveInt()%caseDispo.size()).getCoordonnee();
            res = FabriqueCase.creer(coorDispo, type);
            ajouterCaseV(res);
        }
        return res;
    }
    /**
     *  remplie la carte de route.
     */
    public void remplirRoute() {
        for (Case c : this.cases.values())
            if (!(c.getCoordonnee().getX() == 0 || c.getCoordonnee().getY() == 0 || c.getCoordonnee().getX() == nbColonne-1 || c.getCoordonnee().getY() == nbLigne-1))
                if (cases.get(c.getCoordonnee()).getTypeEnum() == EnumCASE.HERBE)
                    ajouterCaseV(c.getCoordonnee(), EnumCASE.ROUTE);
    }
}
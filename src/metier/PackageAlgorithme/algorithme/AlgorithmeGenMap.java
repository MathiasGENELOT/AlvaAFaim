/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.algorithme;

import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.EnumBATIMENTS;
import metier.PackageCarte.EnumROUTES;
import metier.PackageCarte.EnumVOISIN;
import metier.PackageCarte.PackageCase.Batiment;
import metier.PackageCarte.PackageCase.Case;
import metier.PackageCarte.PackageCase.EnumCASE;
import metier.PackageCarte.PackageCase.FabriqueCase;
import metier.PackageCarte.PackageCase.Route;
import java.util.ArrayList;
import java.util.HashMap;
import metier.PackageAlgorithme.algorithme.AlgorithmeParcours;
import metier.PackageAlgorithme.generator.GenerateurAleatoire;

/**
 * Algorithme de génération de carte
 * @author UNC
 */
public abstract class AlgorithmeGenMap{
    
    protected HashMap<Coordonnee, Case> cases = new HashMap(); // hashmap de toutes les cases de la carte de la partie
    protected HashMap<Case, Boolean> casesDispo = new HashMap(); // hashmap de toutes les cases disponnible 
    protected int nbColonne; // nombre de colone de la carte
    protected int nbLigne;  // nombre de ligne de la carte
    
    /**
     * calcule une hashmap pour une carte
     */
    public abstract HashMap<Coordonnee, Case> calcul(int seed);

    public int getNbColonne() {
        return nbColonne;
    }

    public int getNbLigne() {
        return nbLigne;
    }
    
    
    /**
     * remplie la map d'herbe pour l'initialisé
     * @param nbColonne nombre de colonne de la carte
     * @param nbLigne nombre de ligne de la carte
     */
    protected void init(int nbColonne, int nbLigne) {
        this.nbColonne = nbColonne;
        this.nbLigne = nbLigne;
        
        for(int x = 0; x<nbColonne;x++)
            for(int y = 0; y<nbLigne;y++) {
                this.cases.put(new Coordonnee(x,y),FabriqueCase.creer(new Coordonnee(x,y), EnumCASE.HERBE));
                if (x == 0 || y == 0 || x == nbColonne-1 || y == nbLigne-1)
                    this.casesDispo.put(this.cases.get(new Coordonnee(x,y)), false);
                else
                    this.casesDispo.put(this.cases.get(new Coordonnee(x,y)), true);
            }
        
        calculVoisin();
    }
        
    /**
     * ajout d'une case à la hashmap
     * ATTENTION : RECALCULE DES VOISINS
     * @param coor les coordonnées de la case
     * @param typeCase le type de la case
     * @return la case ajoutée
     */
    protected Case ajouterCaseV(Coordonnee coor, EnumCASE typeCase) {
        Case res = FabriqueCase.creer(coor, typeCase);
        cases.put(coor, res);
        calculVoisin();
        
        calcCaseDispo();
        return res;
    }
    
    /**
     * supprime un lien entre deux cases
     * @param a la première case
     * @param b la deuxième case
     */
    protected void suprVoisin(Case a, Case b) {
        a.suprVoisin(b);
        b.suprVoisin(a);
    }
    
     /**
     * ajoute un lien entre deux cases
     * @param a la première case
     * @param b la deuxième case
     */
    protected void ajoutVoisin(Case a, Case b) {
        a.ajouterVoisin(b);
        b.ajouterVoisin(a);
    }
    
    /**
     * supprime aléatoirement des arrêtes
     */
    protected void suprAleaVoisin() {
        double nbArreteASupr = (nbColonne*nbLigne)*1.2;
        
        for (int i = 0;i< nbArreteASupr; i++) {
            Coordonnee coorAlea = new Coordonnee(GenerateurAleatoire.get().nextPositiveInt()%nbColonne, GenerateurAleatoire.get().nextPositiveInt()%nbLigne);
            Case a = this.cases.get(coorAlea);
            if (a.getVoisins().size() != 0) {
                Case b = a.getVoisins().get(GenerateurAleatoire.get().nextPositiveInt()%a.getVoisins().size());
                suprVoisin(a, b);
                if (!checkCartePossible()) {
                    //nbArreteASupr++;
                    ajoutVoisin(a, b);
                }
            }
        }
    }
    
    /**
     * ajout d'une case à la hashmap
     * ATTENTION : RECALCULE DES VOISINS
     * @param c la case concernée
     * @return la case ajoutée
     */
    protected Case ajouterCaseV(Case c) {
        Case res = cases.put(c.getCoordonnee(),c);
        calculVoisin();
        
        calcCaseDispo();
        return res;
    }
    /**
     * Permet d'obtenir les cases disponibles pour le placement optimal des bâtiments 
     * @return les cases disponibles
     */
    protected ArrayList<Case> getCaseDispo() {
        ArrayList<Case> res = new ArrayList<>();
        for (Case c : this.cases.values()) {
            if (casesDispo.get(c)) {
                res.add(c);
            }
        }
        return res;
    }
    
    /**
     * Calcuel des contraintes des alentours de toutes les cases
     */
    private void calcCaseDispo() {
        for (Case c : this.cases.values()) {
            switch (c.getTypeEnum()) {
                case MAISON:
                    casesDispo.put(c, false);
                    for (Case voisin_1 : c.getVoisins()) {
                        casesDispo.put(voisin_1, false);
                        for (Case voisin_2 : voisin_1.getVoisins()) {
                            casesDispo.put(voisin_2, false);
                            for (Case voisin_3 : voisin_2.getVoisins()) {
                                casesDispo.put(voisin_3, false);
                            }
                        }
                    }
                    break;
                    
                case MAGASIN:
                    casesDispo.put(c, false);
                    for (Case voisin_1 : c.getVoisins()) {
                        casesDispo.put(voisin_1, false);
                        for (Case voisin_2 : voisin_1.getVoisins()) {
                            casesDispo.put(voisin_2, false);
                        }
                    }
                    break;
            }
        }
    }
    /**
     * calcule des voisins des cases
     */
    protected void calculVoisin() {
        
        for(int i=0 ;i<this.nbColonne ;i++) {
            for(int j=0 ;j<this.nbLigne ;j++) {
                Coordonnee cooCase = new Coordonnee(i,j);
                this.cases.get(cooCase).clearVoisin();
                
                for(EnumVOISIN sensVoisin : EnumVOISIN.values()) {
                    Coordonnee cooVoisin = cooCase.getVoisin(sensVoisin);
                    if(this.cases.get(cooVoisin) != null) {
                        this.cases.get(cooCase).ajouterVoisin(this.cases.get(cooVoisin));
                    }
                }
            }
        }
    }
    /**
     * renvoie si la carte est possible
     * @return un booléen si la carte est possible
     */
    protected boolean checkCartePossible() {
        boolean res = true;
        ArrayList<Case> caseATester = new ArrayList<>();
        ArrayList<Case> magasins = new ArrayList<>();
        HashMap<Case, Boolean> casesOk = new HashMap<>();
        AlgorithmeParcours algoParcours = new AlgorithmeParcours(null);
        
        for (Case c : this.cases.values()) {
            if (c.getTypeEnum() == EnumCASE.MAISON) {
                caseATester.add(c);
                casesOk.put(c, true);
            }
            if (c.getTypeEnum() == EnumCASE.MAGASIN)
                magasins.add(c);
        }
        
        for (Case c : magasins)
            casesOk.put(c, null);
        
        while (!caseATester.isEmpty()) {
            for (Case c : algoParcours.getBatimentsAccessible(caseATester.remove(0))) {
                if (casesOk.get(c) == null) {
                    casesOk.put(c, true);
                    caseATester.add(c);
                }
            }
        }
        if (casesOk.containsValue(null)) res = false;
        
        return res;
    }
    /**
     * Calcule le sens d'une case (permet l'affichage de sprite personnalisé)
     */
    protected void calculSens() {
        
        for (Case caseATester : this.cases.values()) {


            if (caseATester.getType() != "Herbe") {

                ArrayList<EnumVOISIN> boolVoisin = new ArrayList<>();

                for (EnumVOISIN sensVoisin : EnumVOISIN.values()) {
                    Coordonnee cooVoisin = caseATester.getCoordonnee().getVoisin(sensVoisin);
                    if (cooVoisin != null) {
                        for(Case caseVoisin : caseATester.getVoisins()) {
                            if (caseVoisin.getCoordonnee().equals(cooVoisin)) {
                                if (caseVoisin.getType() != "Herbe") boolVoisin.add(sensVoisin);
                            }
                        }
                    }
                }

                String res = "";

                for(EnumVOISIN enumV : boolVoisin) {
                    switch(enumV) {
                        case HAUT: res += "_HAUT";
                            break;
                        case DROITE: res += "_DROITE";
                            break; 
                        case BAS: res += "_BAS";
                            break; 
                        case GAUCHE: res += "_GAUCHE";
                            break;
                    }
                }

                if (caseATester.getType() == "Route") {
                    Route c = (Route) caseATester;
                    
                    c.setSens(EnumROUTES.valueOf("ROUTE" + res));
                } else {
                    Batiment c = (Batiment) caseATester;
                    if (c.getType() == "Maison") c.setSens(EnumBATIMENTS.valueOf("MAISON" + res));
                    if (c.getType() == "Magasin") c.setSens(EnumBATIMENTS.valueOf("MAGASIN" + res));
                }
            }
        }
    }
    /**
     * Effeuille la carte pour obtenir une carte plus propre avec moins d'impasse (enlève les cases routes inutiles au jeu)
     */
    protected void Effeuillage()
    {
        for(int i=0;i<100;i++)
        for(Case c :cases.values())
            if(c.getTypeEnum()== EnumCASE.ROUTE)
            {
                int count = 0;
                for (Case caseVoisin : c.getVoisins())
                    if (caseVoisin.getTypeEnum() != EnumCASE.HERBE)
                        count++;
                
                if (count <= 1)
                {
                    for (Case caseVoisin : c.getVoisins())
                    {
                        caseVoisin.suprVoisin(c);          
                    }
                    this.cases.put(c.getCoordonnee(),FabriqueCase.creer(c.getCoordonnee(),EnumCASE.HERBE));
                    
                }
                
            }
        
        
        
    }
    /**
     * Enlève les cartes impossibles à parcourir par le joueur dans la partie 
     * @param depart 
     */
    protected void NettoyageRoutes(Case depart)
    {
        AlgorithmeParcours algoParcours = new AlgorithmeParcours(null);
        ArrayList<Case> routesOk = algoParcours.parcoursLargeur(depart);
        for(Case c :cases.values())
            if(c.getTypeEnum() == EnumCASE.ROUTE && !routesOk.contains(c)) {
                this.cases.put(c.getCoordonnee(),FabriqueCase.creer(c.getCoordonnee(),EnumCASE.HERBE));
            }
                
        
        
    }
}
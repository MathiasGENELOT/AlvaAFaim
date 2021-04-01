package metier.PackageCarte.PackageCase;

import java.util.ArrayList;
import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Ingredients;
import metier.PackageIngredient.Inventaire;
import metier.PackageCarte.Coordonnee;
import metier.PackageCarte.EnumBATIMENTS;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Joueur;
import java.util.HashMap;
import java.util.Objects;

/**
 * Case de bâtiment abstraite qui hérite de Case
 * @author UNC
 */
public abstract class Batiment extends Case{

    private Inventaire inventaire;                      // inventaire d'un bâtiment
    private HashMap<EnumINGREDIENTS, Integer> prix;     // hashmap de chacun des ingrédients et de leur prix 
    private Inventaire inventaireInit = new Inventaire(); // inventaire initiale du bâtiment
    private boolean estAccessible;                      // si le bâtiment est accessible
    private EnumBATIMENTS sens;                         // type de bâtiment et son sens
    
    /**
     * Construit le batiment
     * @param coor les coordonnées du bâtiment
     */
    public Batiment(Coordonnee coor) {
        super(coor);
        this.inventaire = new Inventaire();
    }

    public boolean estAccessible() {
        return estAccessible;
    }
    
    public HashMap<EnumINGREDIENTS,Integer> getHashMapPrix() {
        return this.prix;
    }
    /**
     * Permet de savoir si un ingredient est contenu dans l'inventaire de magasin
     * @param nom
     * @param nombre
     * @return boolean s'il contient l'ingrédient ou non
     */
    public boolean contient(EnumINGREDIENTS nom, int nombre) {
        Inventaire instance = new Inventaire();
        instance.ajouterIngredient(nom, nombre);
        
        return this.inventaire.contient(instance);
    }
        
    public EnumBATIMENTS getSens() {
        return sens;
    }

    public void setSens(EnumBATIMENTS sens) {
        this.sens = sens;
    }

    public void setAccessible(boolean estAccessible) {
        this.estAccessible = estAccessible;
    }
    
    /**
     * rachat d'un ingredient par le bâtiment (false si non possible)
     * @param ingredient l'ingrédient en question
     * @return un booléen
     */
    public boolean rachatIngredient(EnumINGREDIENTS ingredient) {
        boolean res = false;
        
        for(Ingredients iInit : this.inventaireInit.getIngredients())
        {
            for(Ingredients iChanger : this.inventaire.getIngredients())
            {
                if(iInit.getNom() == ingredient && iInit.getNom() == iChanger.getNom() && iInit.getNombre() > iChanger.getNombre())
                {
                    Joueur j = Jeu.getInstance().getJoueur();
                    j.setDepense(j.getDepense()-prix.get(ingredient));
                    j.retirerIngredient(ingredient);
                    this.inventaire.ajouterIngredient(ingredient, 1);
                    res = true;
                }
            }
        }
        return res;
    }

    /**
     * vente d'un ingrédient par le bâtiment (false si non possible)
     * @param ingredient l'ingrédient en question
     */
    public boolean venteIngredient(EnumINGREDIENTS ingredient) {
        boolean res = this.inventaire.enleverIngredient(ingredient, 1);  
        
        if (res)
        {
            Joueur j = Jeu.getInstance().getJoueur();
            j.setDepense(j.getDepense()+prix.get(ingredient));
            j.ajouterIngredient(ingredient);
        }
        return res;
    }

    public Inventaire getInventaire() {
        return this.inventaire;
    }
    
    @Override
    public int getPrix(EnumINGREDIENTS ing){
        return this.prix.get(ing);
    }
    /**
     * Permet d'initialiser l'inventaire et l'inventaire initial et les prix du magasin
     * @param invInit l'invintaire initial
     * @param Ing 
     */
    public void initInventaire(Inventaire invInit, HashMap<EnumINGREDIENTS, Integer> Ing) {
        this.inventaireInit = invInit.clone();
        this.inventaire = invInit;
        this.prix = Ing;
    }

    /**
     * renvoie le type du bâtiment
     * @return 
     */
    @Override
    public abstract String getType();
    
    @Override
    public boolean equals(Object o) {
       boolean res = true;
       
       if (!(o instanceof Batiment)) res = false;
       if(this.hashCode() != o.hashCode()) res = false;
       
       return res;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.getCoordonnee());
        return hash;
    }
}
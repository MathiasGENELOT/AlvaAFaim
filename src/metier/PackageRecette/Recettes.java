package metier.PackageRecette;

import java.util.*;

/**
 * Represente un gestionnaire de recettes
 * @author UNC
 */
public class Recettes {

	private ArrayList<Recette> recettes = new ArrayList<Recette>();
        private ArrayList<Recette> recettesPartie = new ArrayList<Recette>();
        private ArrayList<Recette> recettesPartiePossible = new ArrayList<Recette>();
        private static Recettes instance;
        
	private Recettes() {
            EnumRECETTES[] tabEnum = EnumRECETTES.values();
            for(EnumRECETTES enumR : tabEnum)
                CreerRecette(enumR);  
	}

        public static Recettes get() {
            if (instance == null) instance = new Recettes();
            return instance;
        }
        
	public ArrayList<Recette> listRecettes() {
            return this.recettes;
	}

	public ArrayList<Recette> listRecettesPartie() {
            return this.recettesPartie;
	}
        
        public ArrayList<Recette> listRecettesPartiePossible() {
            return this.recettesPartiePossible;
	}
        /**
         * Remet à 0 les recettes de la partie et les recettes possibles de la partie
         */
        public void ResetRecettePartie() {
            this.recettesPartie = new ArrayList<Recette>();
            this.recettesPartiePossible = new ArrayList<Recette>();
        }
            
	/**
	 * Permet d'ajouter une recette à la liste des recettes de la partie à partir de la liste recette
	 * @param recette
	 */
	public void ajouterRecette(EnumRECETTES recette) {
            for(Recette r : recettes)
                if(r.getNom() == recette)
                    recettesPartie.add(r);
                    
	}
        /**
	 * Permet d'ajouter une recette à la liste des recettes de la partie possible à partir de la liste recette
	 * @param recette
	 */
        public void ajouterRecettePossible(EnumRECETTES recette) {
            for(Recette r : recettes)
                if(r.getNom() == recette)
                    recettesPartiePossible.add(r);
                    
	}
        /**
         * Permet d'obtenir une recette a partir d'une EnumRecettes
         * @param nom
         * @return une Recette
         */
        public Recette get(EnumRECETTES nom) {
            Recette res = null;
            for (Recette recette : recettes) {
                if (nom == recette.getNom())
                    res = recette;
            }
            return res;
        }

	/**
	 * Créer une recette 
	 * @param nom
	 */
	public void CreerRecette(EnumRECETTES nom) {
           recettes.add(FabriqueRecette.CreerRecette(nom));
	}

}
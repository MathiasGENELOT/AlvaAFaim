package metier.PackageRecette;

import metier.PackageIngredient.EnumINGREDIENTS;
import metier.PackageIngredient.Inventaire;

/**
 * Représente une fabrique de recette
 * @author UNC
 */
public class FabriqueRecette {

	/**
	 * Créer une recette à partir d'une EnumRecettes
	 * @param nom
	 */
	public static Recette CreerRecette(EnumRECETTES nom) {
		Recette r = null;
                Inventaire i = new Inventaire();
                
        switch (nom) {
                case PIZZA: // 12
                    i.ajouterIngredient(EnumINGREDIENTS.PATE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.TOMATE, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.MOZZARELLA, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.JAMBON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.PARMESAN, 1);
                    break;
                case BOEUF_BOURGUIGNON: // 11
                    i.ajouterIngredient(EnumINGREDIENTS.VIANDE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.VIN, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.LARDON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.CAROTTE, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.PATATE, 2);
                    break;
                case TARTIFLETTE: // 8
                    i.ajouterIngredient(EnumINGREDIENTS.PATATE, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.REBLOCHON, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.LARDON, 4);
                    i.ajouterIngredient(EnumINGREDIENTS.OIGNON, 1);
                    break;
                case SANDWICH: // 8
                    i.ajouterIngredient(EnumINGREDIENTS.JAMBON, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.PAIN, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.TOMATE, 4);
                    break;
                case OMELETTE_COMPLETE:
                    i.ajouterIngredient(EnumINGREDIENTS.OEUF, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.LARDON, 4);
                    i.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.EMMENTAL, 1);
                    break;
                case CAKE_SALE_AU_JAMBON: // 8
                    i.ajouterIngredient(EnumINGREDIENTS.JAMBON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.OEUF, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.FARINE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.EMMENTAL, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.OLIVE, 3);
                    break;
                case VERRINE_SAUMON: // 8
                    i.ajouterIngredient(EnumINGREDIENTS.SAUMON, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.CREME, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.CITRON, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.MOZZARELLA, 1);
                    break;
                case COOKIE: // 8
                    i.ajouterIngredient(EnumINGREDIENTS.PATE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.SUCRE, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.CHOCOLAT, 3);
                    break;
                case FONDANTS_AU_CHOCOLAT: // 9
                    i.ajouterIngredient(EnumINGREDIENTS.CHOCOLAT, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.FARINE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.OEUF, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.SUCRE, 3);
                    break;
                case CRUMBLE_AUX_POMMES: // 9
                    i.ajouterIngredient(EnumINGREDIENTS.POMME, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.FARINE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.SUCRE, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.CITRON, 2);
                    break;
                case TARTE_AUX_CITRONS: // 9
                    i.ajouterIngredient(EnumINGREDIENTS.FARINE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.OEUF, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.CITRON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.SUCRE, 2);
                    break;
                case BOEUF_WELLINGTON: // 9
                    i.ajouterIngredient(EnumINGREDIENTS.VIANDE, 3);
                    i.ajouterIngredient(EnumINGREDIENTS.CHAMPIGNON, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.OEUF, 2);
                    i.ajouterIngredient(EnumINGREDIENTS.PATE, 1);
                    i.ajouterIngredient(EnumINGREDIENTS.BEURRE, 1);
                    break;
                default:
                    throw new AssertionError(nom.name());
            
        }
            r = new Recette(nom,i);
            return r;
	}

}
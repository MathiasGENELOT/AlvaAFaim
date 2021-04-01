package metier.PackageIngredient;

/**
 * Représente un ingrédient
 * @author UNC
 */
public class Ingredients {
       
	private int nombre;         // représente la quantité de l'ingrédient
	private EnumINGREDIENTS nom;// représente le type d'ingrédient
       
	public int getNombre() {
		return this.nombre;
	}

	public void setNombre(int nombre) {
		this.nombre = nombre;
	}

	public EnumINGREDIENTS getNom() {
		return this.nom;
	}

	/**
	 * Construit l'ingrédient
	 * @param nom le nom de l'ingrédient
	 * @param nombre le nombre d'ingrédient
	 */
	public Ingredients(EnumINGREDIENTS nom, int nombre) {
            this.nom = nom;
            this.nombre = nombre;
	}
        
        @Override
        public boolean equals(Object o) {
            Ingredients ingredients = (Ingredients) o;
            return (this.nombre == ingredients.nombre && this.nom == ingredients.nom);
        }

}
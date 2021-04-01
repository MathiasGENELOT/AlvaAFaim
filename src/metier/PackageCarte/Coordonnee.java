package metier.PackageCarte;

/**
 * Représente des coordonnées
 * @author UNC
 */
public class Coordonnee 
{

	private int x;
	private int y;

        public Coordonnee(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
	public int getX() 
        {
		return this.x;
	}

	public void setX(int x) 
        {
		this.x = x;
	}

	public int getY() 
        {
		return this.y;
	}

	public void setY(int y) 
        {
		this.y = y;
	}

    /**
    * Renvoie la case voisine en fonction du mouvement
    * @param mouvement de TypeMouvement
    * @return une Coordonnée
    */
   public Coordonnee getVoisin(EnumVOISIN sens) {
       Coordonnee res = new Coordonnee(x, y);
       switch(sens) {
           case HAUT: 
               res.y -= 1;
           break;

           case BAS : 
               res.y += 1;
           break;

           case GAUCHE : 
               res.x -= 1;
           break;

           case DROITE : 
               res.x += 1;
           break;
           
       }
       return res;
   }
   
   @Override
   public boolean equals(Object o) {
       boolean res = true;
       
       if (!(o instanceof Coordonnee)) res = false;
       if(this.hashCode() != o.hashCode()) res = false;
       
       return res;
   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.x;
        hash = 23 * hash + this.y;
        return hash;
    }
    
    @Override
    public String toString() {
        return this.x + " , "+ this.y;
    }
}
package metier.PackageAlgorithme.packageIA;

import metier.PackageAlgorithme.packageIA.parametre.Parametre;
import java.util.HashMap;
import java.util.Objects;

/**
 * Représente un état de l'IA dans l'arbre
 * @author UNC
 */
public class Etat {
    private HashMap<Parametre,Integer> parametres;
    
    public Etat() {
        this.parametres = new HashMap<>();
    }
    
    /**
     * constructeur copie d'un état
     * @param etat Etat l'etat a copier
     */
    public Etat(Etat etat) {
        this.parametres = new HashMap<>();
        for (Parametre param : etat.parametres.keySet()) {
            this.setParametre(param, etat.getValue(param));
        }
    }
    
    
    public void setParametre(Parametre param, int value) {
        parametres.put(param, value);
    }
    
    /**
     * Retourne la valeur d'un paramètre
     * @param parametre le parameère concerné
     * @return un entier, 0 si aucun paramètre n'existe
     */
    public Integer getValue(Parametre parametre) {
        if (!parametres.containsKey(parametre))
            return 0;
        
        return this.parametres.get(parametre);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.parametres);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;

        if (obj instanceof Etat) {
            Etat other = (Etat) obj;
            if (this.parametres.size() != other.parametres.size()) res = false;
            else
                for (Parametre param : other.parametres.keySet())
                    if (!this.getValue(param).equals(other.getValue(param))) res = false;

        } else res = false;

        return res;
    }
    
    
    
}

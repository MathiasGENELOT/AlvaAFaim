/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.packageIA.regle;

import metier.PackageAlgorithme.packageIA.precondition.Precondition;
import metier.PackageAlgorithme.packageIA.action.Action;
import java.util.ArrayList;
import java.util.Objects;
import metier.PackageAlgorithme.packageIA.Etat;

/**
 * Règle du jeu de alva a faim, lisible par l'IA
 * @author UNC
 */
public class Regle {
    private ArrayList<Precondition> preconditions = new ArrayList<>();
    private ArrayList<Action> actions = new ArrayList<>();
    private String type;
    
    /**
     * Constructeur d'une règle
     * @param type String = DEPLACER || CONCOCTER || ACHAT || VOULOIR
     */
    public Regle(String type) {
        this.type = type;
    }
    
    /**
     * ajout de préconditions
     * @param preconditions Précondition...
     */
    public void setPreconditions(Precondition... preconditions) {
        for (Precondition preco : preconditions)
            this.preconditions.add(preco);
    }
    
    /**
     * ajout d'actions
     * @param action Actions...
     */
    public void setActions(Action action) {
        this.actions.add(action);
    }
    
    public String getType() {
        return type;
    }
    
    /**
     * test un état
     * @param etat Etat
     * @return un booléen
     */
    public boolean estValide(Etat etat) {
        boolean res = true;
        
        for(Precondition preco : preconditions)
            if (!preco.estValide(etat)) res = false;
        
        return res;
    }
    
    /**
     * applique les actions de la règle sur l'état renvoyant alors un nouveau modifié
     * @param etat état initial
     * @return état final
     */
    public Etat realiserActions(Etat etat) {
        Etat res = new Etat(etat);
        
        for(Action act : actions)
            res = act.realiser(res);
        
        return res;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.preconditions);
        hash = 89 * hash + Objects.hashCode(this.actions);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        boolean res = true;
        
        if (obj instanceof Regle) {
            Regle other = (Regle) obj;
            
            if (this.preconditions.size() == other.preconditions.size())
                for (Precondition preco : other.preconditions) {
                    if (!this.preconditions.contains(preco))
                        res = false;
                }
            else res = false;
                    
            if (this.actions.size() == other.actions.size())
                for (Action act : other.actions) {
                    if (!this.actions.contains(act)) 
                        res = false;
                }
            else res = false;
            
        } else res = false;

        return res;
    }
    
    
}

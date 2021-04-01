/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.packageFenetre.FenetreJeu.FJeu;
import metier.PackageJeu.Jeu;
import metier.PackageJeu.Joueur;

/**
 * Observateur de  la dépense du joueur qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurDepense extends Observateur{
    private Joueur joueur;
    private FJeu fjeu;
    
    /**
     * Constructeur d'observateur de la dépense du joueur
     * @param fjeu fenêtre dans laquelle va apparaitre la dépense
     */
    public ObservateurDepense(FJeu fjeu) {
        super();
        this.fjeu = fjeu;
        this.joueur = Jeu.getInstance().getJoueur();
    }
    
    @Override
    public void avertir() {
        this.fjeu.SetDepense(this.joueur.getDepense());
    }
    
}

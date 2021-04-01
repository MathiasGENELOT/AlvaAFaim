/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

import ihm.packageFenetre.FenetreFindeJeu.FFin;
import metier.PackageJeu.Partie;

/**
 * Observateur du score Max de la partie qui hérite de la classe Observateur
 * @author UNC
 */
public class ObservateurScoreMax extends Observateur{

    private final FFin fenetre;
    private final Partie partie;

    /**
     * Constructeur d'observateur du score maximum de la partie
     * @param fenetre fenêtre de fin de jeu contenant l'affichage du score
     * @param partie la partie contenant le score max à afficher
     */
    public ObservateurScoreMax(FFin fenetre, Partie partie) {
        this.fenetre = fenetre;
        this.partie = partie;
    }
    
    @Override
    public void avertir() {
        fenetre.setScoreMax(partie.getIAScore());
    }
    
}

package metier.PackageJeu;

/**
 * Represente le jeu
 * @author UNC
 */
public class Jeu {

    private Partie partie;       // le jeu contient une partie
    private static Jeu instance; // une seule instance de jeu

    private Jeu() {
    }

    public static Jeu getInstance() {
        if (instance == null)
            instance = new Jeu();
        return instance;
    }

    /**
     * Permet de créer une partie
     */
    public void NouvellePartie(String seedString) {
        this.partie = new Partie(seedString);
    }
    
    public Partie getPartie(String seedString) {
        return new Partie(seedString);
    }

    /**
     * Permet de charger une partie existante
     */
    public void ChargerPartie() {
            // TODO - implement Jeu.ChargerPartie
            throw new UnsupportedOperationException();
    }

    public Partie getPartie() {
        return this.partie;
    }

    public Joueur getJoueur() {
        return this.partie.getJoueur();
    }

    /**
     * Permet de changer les options du jeu
     */
    public void Options() {
            // TODO - implement Jeu.Options
            throw new UnsupportedOperationException();
    }

}
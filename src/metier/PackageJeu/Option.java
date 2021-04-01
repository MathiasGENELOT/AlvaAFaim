package metier.PackageJeu;

import ihm.ManagerSon;

/**
 * Classe singleton stock des options du jeu 
 * @author UNC
 */
public class Option {
    
    private static Option instance = null;
    private double volume = 100;   
    private double backVolume = 100;
    private Option() {
    }
    
    /**
     * retourne la classe Option
     * @return une option
     */
    public static Option getInstance() {
        if (instance == null) instance = new Option();
        
        return instance;
    }
    
    /**
     * actualise le volume du jeu
     * @param val valeur du volume
     */
    public void setVolume(double val) {
        this.volume = val;
        ManagerSon.setVolume(volume);
    }
    
     /**
     * actualise le volume des musiques
     * @param val valeur du volume
     */
    public void setVolumeMusic(double val) {
        this.backVolume = val;
        ManagerSon.setBackgroundVolume(val);
    }

    public double getVolume() {
        return volume;
    }

    public double getBackVolume() {
        return backVolume;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * Classe qui gère les sons du jeux
 * @author UNC
 */
public class ManagerSon {
    private static ManagerSon instance;
    private final static HashMap<EnumSON, MediaPlayer> listMP3 = new HashMap<>();
    private static MediaPlayer backgroundMusic;
    
    /**
     * Récupère tous les fichier .mp3 et les charge dans une liste static
     */
    private ManagerSon() {
        for (EnumSON enumson : EnumSON.values()) {
            URL urlSon = getClass().getResource("/ressources/music/" + enumson.name() + ".mp3");
            listMP3.put(enumson,new MediaPlayer(new Media(urlSon.toExternalForm())));
        }
        backgroundMusic = new MediaPlayer(new Media(getClass().getResource("/ressources/music/background.mp3").toExternalForm()));
        backgroundMusic.setVolume(0.1);
    }
    
    /**
     * Renvoi un son en fonction de son enum
     * @param sonName Nom dans l'enum
     * @return MediaPLayer pret a être joué
     */
    public static MediaPlayer get(EnumSON sonName){
        if (instance == null) instance = new ManagerSon();
        
        return listMP3.get(sonName);
    }
    
    /**
     * Change le volume des sons
     * @param volume volume sonore
     */
    public static void setVolume(double volume) {
        if (instance == null) instance = new ManagerSon();
        for(Map.Entry<EnumSON, MediaPlayer> mp3 : listMP3.entrySet()) {
            mp3.getValue().setVolume(volume);
        }
    }
    
    /**
     * Change le volume de la musique de fond
     * @param volume volume sonore
     */
    public static void setBackgroundVolume(double volume) {
        if (instance == null) instance = new ManagerSon();
        backgroundMusic.setVolume(volume);
    }
    
    /**
     * Lance la lecture d'un son
     * @param sonName 
     */
    public static void play(EnumSON sonName) {
        if (instance == null) instance = new ManagerSon();
        listMP3.get(sonName).seek(Duration.ZERO);
        listMP3.get(sonName).play();
    }
    
    /**
     * Lance la musique de fond
     * @param sonName 
     */
    public static void playBackgroundMusic() {
        if (instance == null) instance = new ManagerSon();
        backgroundMusic.seek(Duration.ZERO);
        backgroundMusic.setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                backgroundMusic.seek(Duration.ZERO);
            }
        });
        backgroundMusic.play();
    }
    
    /**
     * Arrete la lecture de tout les sons
     */
    public static void stop() {
        if (instance == null) instance = new ManagerSon();
        for(Map.Entry<EnumSON, MediaPlayer> mp3 : listMP3.entrySet()) {
            mp3.getValue().stop();
        }
        backgroundMusic.stop();
    }
    
    /**
     * Arrete la lecture de tout les sons
     */
    public static void stop(EnumSON son) {
        if (instance == null) instance = new ManagerSon();
        for(Map.Entry<EnumSON, MediaPlayer> mp3 : listMP3.entrySet()) {
            mp3.getValue().stop();
        }
        backgroundMusic.stop();
    }
}

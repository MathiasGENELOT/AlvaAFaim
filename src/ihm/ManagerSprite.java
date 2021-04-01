/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import metier.PackageCarte.EnumBATIMENTS;
import metier.PackageCarte.EnumROUTES;
import java.io.File;
import java.net.URL;
import java.util.HashMap;
import javafx.scene.image.Image;
import metier.PackageIngredient.EnumINGREDIENTSPRITE;
import metier.PackageRecette.EnumRECETTES;

/**
 * Classe servant à gérer les sprites (images)
 * @author UNC
 */
public class ManagerSprite {
    private static ManagerSprite instance;
    private final static HashMap<EnumINTERFACE, Image> listInterface = new HashMap<>();
    private final static HashMap<EnumSPRITE, Image> listSprite = new HashMap<>();
    private final static HashMap<EnumINGREDIENTSPRITE, Image> listIngredients = new HashMap<>();
    private final static HashMap<EnumROUTES, Image> listSpriteRoutes = new HashMap<>();
    private final static HashMap<EnumBATIMENTS, Image> listSpriteBatiments = new HashMap<>();
    private final static HashMap<EnumRECETTES, Image> listSpriteRecettes = new HashMap<>();
    private final static HashMap<EnumHERBE, Image> listSpriteHerbe = new HashMap<>();
    private final static HashMap<EnumMENU, Image> listSpriteMenu = new HashMap<>();
    
    
    private ManagerSprite() {
        
        for (EnumINTERFACE enumsprite : EnumINTERFACE.values()) {
            URL urlImage = getClass().getResource("/ressources/images/interface/" + enumsprite.name() +".png");
            listInterface.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumMENU enumsprite : EnumMENU.values()){
            URL urlImage = getClass().getResource("/ressources/images/Menu_principal/" + enumsprite.name() +".png");
            listSpriteMenu.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumSPRITE enumsprite : EnumSPRITE.values()) {
            URL urlImage = getClass().getResource("/ressources/images/map/Autres/" + enumsprite.name() +".png");
            listSprite.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumINGREDIENTSPRITE enumsprite : EnumINGREDIENTSPRITE.values()) {
            URL urlImage = getClass().getResource("/ressources/images/Ingredients/" + enumsprite.name() +".png");
            listIngredients.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumROUTES enumsprite : EnumROUTES.values()) {
            URL urlImage = getClass().getResource("/ressources/images/map/Routes/" + enumsprite.name() +".png");
            listSpriteRoutes.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumBATIMENTS enumsprite : EnumBATIMENTS.values()) {
            URL urlImage = getClass().getResource("/ressources/images/map/Batiments/" + enumsprite.name() +".png");
            listSpriteBatiments.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
       
        for (EnumRECETTES enumsprite : EnumRECETTES.values()) {
            URL urlImage = getClass().getResource("/ressources/images/Recettes/" + enumsprite.name() +".png");
            listSpriteRecettes.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
        
        for (EnumHERBE enumsprite : EnumHERBE.values()) {
            URL urlImage = getClass().getResource("/ressources/images/map/Vides/" + enumsprite.name() +".png");
            listSpriteHerbe.put(enumsprite,new Image(urlImage.toExternalForm()));
        }
    }
    
    
    /**
     * Retourne le sprite voulu
     * @param spriteName le nom de sprite
     * @return une image
     */
    public static Image get(EnumSPRITE spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSprite.get(spriteName);
    }
    
    /**
     * Retourne le sprite de l'ingrédient voulu
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumINGREDIENTSPRITE spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listIngredients.get(spriteName);
    }
    
    /**
     * Retourne le sprite de l'interface voulue
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumINTERFACE spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listInterface.get(spriteName);
    }
    
    /**
     * Retourne le sprite de la route voulue
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumROUTES spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSpriteRoutes.get(spriteName);
    }
        
    /**
     * Retourne le sprite du bâtiment voulu
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumBATIMENTS spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSpriteBatiments.get(spriteName);
    }
    
    /**
     * Retourne le sprite de la case voulue
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumHERBE spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSpriteHerbe.get(spriteName);
    }
    
    /**
     * Retourne le sprite de la recette voulue
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumRECETTES spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSpriteRecettes.get(spriteName);
    }
    
    /**
     * Retourne le sprite du menu voulu
     * @param spriteName le nom du sprite
     * @return une image
     */
    public static Image get(EnumMENU spriteName) {
        if (instance == null) instance = new ManagerSprite();

        return listSpriteMenu.get(spriteName);
    }
}

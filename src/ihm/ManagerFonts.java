/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ihm;

import java.net.URL;
import java.util.HashMap;
import javafx.scene.image.Image;
import javafx.scene.text.Font;

/**
 * Classe servant à gérer les différentes polices et les différentes tailles
 * @author UNC
 */
public class ManagerFonts 
{
    private static ManagerFonts instance;
    private final static HashMap<EnumFONTS, Font> listLittleFonts = new HashMap<>();
    private final static HashMap<EnumFONTS, Font> listMediumFonts = new HashMap<>();
    private final static HashMap<EnumFONTS, Font> listBigFonts = new HashMap<>();
    
    private ManagerFonts()
    {
        for (EnumFONTS enumfonts : EnumFONTS.values())
        {
            URL urlFont = getClass().getResource("/ressources/fonts/" + enumfonts.name() +".otf");
            listLittleFonts.put(enumfonts,Font.loadFont(urlFont.toExternalForm(),12));
        }
        
        for (EnumFONTS enumfonts : EnumFONTS.values())
        {
            URL urlFont = getClass().getResource("/ressources/fonts/" + enumfonts.name() +".otf");
            listMediumFonts.put(enumfonts,Font.loadFont(urlFont.toExternalForm(),25));
        }
        
        for (EnumFONTS enumfonts : EnumFONTS.values())
        {
            URL urlFont = getClass().getResource("/ressources/fonts/" + enumfonts.name() +".otf");
            listBigFonts.put(enumfonts,Font.loadFont(urlFont.toExternalForm(),45));
        }
    }
    
    /**
     * Méthode retournant la police voulu en petite taille
     * @param nameFont le nom de la police
     * @return la police
     */
    public static Font getLittleFont(EnumFONTS nameFont)
    {
        if(instance == null)
        {
            instance = new ManagerFonts();
        }
        Font font = listLittleFonts.get(nameFont);
        return font;
    }
    
    /**
     * Méthode retournant la police voulu en moyenne taille
     * @param nameFont le nom de la police
     * @return la police
     */
    public static Font getMediumFont(EnumFONTS nameFont)
    {
        if(instance == null)
        {
            instance = new ManagerFonts();
        }
        Font font = listMediumFonts.get(nameFont);
        return font;
    }
    
    /**
     * Méthode retournant la police voulu en grande taille
     * @param nameFont la nom de la police
     * @return la police
     */
    public static Font getBigFont(EnumFONTS nameFont)
    {
        if(instance == null)
        {
            instance = new ManagerFonts();
        }
        Font font = listBigFonts.get(nameFont);
        return font;
    }
}
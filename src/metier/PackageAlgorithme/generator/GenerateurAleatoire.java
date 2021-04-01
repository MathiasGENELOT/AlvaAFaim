/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier.PackageAlgorithme.generator;

import java.util.Random;

/**
 * Classe du génrateur aléatoire
 * @author UNC
 */
public class GenerateurAleatoire {
    private static GenerateurAleatoire instance;
    private int seedGlobal;
    private int seedLocal;
    private Random random;
    
    private GenerateurAleatoire() {}
    
    public static GenerateurAleatoire get() {
        if (instance == null) instance = new GenerateurAleatoire();
        return instance;
    }

    public void setSeedGlobal(int seedGlobal) {
        random = new Random(seedGlobal);
        seedGlobal = seedGlobal;
        seedLocal = 0;
    }

    public void setSeedLocal(int seedLocal) {
        seedLocal = seedLocal;
    }
    
    public int nextPositiveInt() {
        int res = random.nextInt();
        res = Math.abs(res);
        return res;
    }
}
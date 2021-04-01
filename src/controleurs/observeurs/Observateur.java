/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleurs.observeurs;

/**
 * Observateur principal
 * @author UNC
 */
public abstract class Observateur {
    /**
     * actionne les modifications IHM de l'observateur en question
     */
    public abstract void avertir();
}

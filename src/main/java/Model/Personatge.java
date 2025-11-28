/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author alumnet
 */
public abstract class Personatge {
    private String nom;
    private int vida;

    public Personatge(String nom, int vida) {
        this.nom = nom;
        this.vida = vida;
    }

    public String getNom() {
        return nom;
    }

    public int getVida() {
        return vida;
    }
    
    public void rebreDany(int punts) {
        this.vida -= punts;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Random;

/**
 *
 * @author alumnet
 */
public class Mag extends Personatge implements Runnable {

    public Mag(String nom, int vida) {
        super(nom, vida);
    }
    
    @Override
    public void run() {
        System.out.println("🧙🎭 Personatge " + super.getNom() + " creat!");
        Random rand = new Random();
        
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(super.getNom()  + ": ataca l'enemic!");
                Thread.sleep(rand.nextInt(1001) + 500); // Pausa dde 500ms a 1500ms
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

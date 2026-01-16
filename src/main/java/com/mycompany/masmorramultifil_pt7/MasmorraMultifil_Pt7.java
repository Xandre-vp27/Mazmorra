/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.masmorramultifil_pt7;

import Model.*;
import Model.Character;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alumnet
 */
public class MasmorraMultifil_Pt7 {

    private static List<Character> heroes = new ArrayList();
    private static int ogreMaxHealth = 1000;
    private static Ogre ogre = new Ogre("Ogre", ogreMaxHealth, heroes);
    private static Warrior warrior = new Warrior("Corvan", 100, 140, 30, 1500, 1000, ogre);
        
    private static Wizard wizard = new Wizard("Greiflum", 100, 60, 10, 1000, 600, ogre);
    private static Rogue rogue = new Rogue("Darrel", 100, 30, 5, 500, 200, ogre);
    
    public static void main(String[] args) throws InterruptedException {
        
        heroes.add(warrior);        
        heroes.add(wizard);        
        heroes.add(rogue);

        System.out.println("=== 🏁 BATTLE STARTS 🏁 ===\n");

        // Creamos el Thread y indicamos el personaje
        Thread filC1 = new Thread(warrior);
        Thread filC2 = new Thread(wizard);
        Thread filC3 = new Thread(rogue);
        Thread filC4 = new Thread(ogre);

        // --- ASIGNACIÓN DE PRIORIDADES ---
        filC4.setPriority(Thread.MAX_PRIORITY);
        filC1.setPriority(Thread.NORM_PRIORITY);
        filC2.setPriority(Thread.NORM_PRIORITY);
        filC3.setPriority(Thread.NORM_PRIORITY);

        filC1.start();
        filC2.start();
        filC3.start();
        filC4.start();

        int seconds = 0;
        char[] ogreGraphHealth = new char[20];
        float ogrePercHealth;
        int ogrePointsHealth;

        while (ogre.isAlive() && (filC1.isAlive() || filC2.isAlive() || filC3.isAlive())) {
            Thread.sleep(1000); //Pausa de 1sg para no saturar la consola
            seconds++; //Variable que cuenta sg de la batalla

            // Calcular cuántos héroes hay vivos
            int heroesAlive = 0;
            if (filC1.isAlive()) {
                heroesAlive++;
            }
            if (filC2.isAlive()) {
                heroesAlive++;
            }

            if (filC3.isAlive()) {
                heroesAlive++;
            }

            float ogreCurrentHealth = (float) ogre.getHealth();
            float fOgreMaxHealth = (float) ogreMaxHealth;
            ogrePercHealth = (ogreCurrentHealth * 100) / fOgreMaxHealth;
            ogrePointsHealth = (ogre.getHealth() * 20) / ogreMaxHealth;

            for (int i = 0; i < 20; i++) {
                if (i < ogrePointsHealth) {
                    ogreGraphHealth[i] = '█';
                } else {
                    ogreGraphHealth[i] = '░';
                }
            }

            String graphHealth = new String(ogreGraphHealth);

            System.out.println("⏱ " + seconds + "s | " // Segundos de batalla
                    + graphHealth + " " + ogrePercHealth + "% (" + ogre.getHealth() + "/" + ogreMaxHealth // Pintar vida del ogro
                    + " HP) | 🎭 Heroes alive: " + heroesAlive); //Mostrar héroes vivos
        }

        System.out.println("\n=== 🏁 BATTLE ENDS 🏁 ===");

        // Comprobar quien ha ganado la batalla
        if (ogre.getHealth() == 0) {
            System.out.println("HEROES WIN!!");
        } else {
            System.out.println("OGRE HAS WON...");
        }
    }
    
    public void initializeThreads() {
        
    }

}

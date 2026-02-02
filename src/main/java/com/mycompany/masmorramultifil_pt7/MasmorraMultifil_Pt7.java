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

    // --- VARIABLES GLOBALES ---
    static Ogre ogre;
    static Warrior warrior;
    static Wizard wizard;
    static Rogue rogue;
    static Healer healer;

    static Thread tOgre;
    static Thread tWarrior;
    static Thread tWizard;
    static Thread tRogue;
    static Thread tHealer;

    // CONSTANTES DE CONFIGURACIÓN
    static final int OGRE_MAX_HEALTH = 3500;
    static final int WARRIOR_MAX_HEALTH = 200;
    static final int WIZARD_MAX_HEALTH = 200;
    static final int ROGUE_MAX_HEALTH = 200;
    static final int HEALER_HEALTH = 150;

    public static void main(String[] args) {
        System.out.println("=== 🏁 BATTLE STARTS ===\n");

        initCharacters();

        startThreads();

        monitorBattle();
    }

    /**
     * Instancia los personajes y configura la lista de enemigos.
     */
    private static void initCharacters() {
        // Lista compartida de enemigos para el Ogro
        List<Character> heroesList = new ArrayList<>();
        // Lista para la sanadora
        List<Character> heroesToHeal = new ArrayList<>();

        ogre = new Ogre("Grom The Ogre", OGRE_MAX_HEALTH, heroesList);

        warrior = new Warrior("Corvan", WARRIOR_MAX_HEALTH, 140, 30, 1500, 1000, ogre);
        wizard = new Wizard("Greiflum", WIZARD_MAX_HEALTH, 60, 10, 1000, 600, ogre);
        rogue = new Rogue("Darrel", ROGUE_MAX_HEALTH, 30, 5, 500, 200, ogre);

        healer = new Healer("Eylin", HEALER_HEALTH, heroesToHeal, ogre);

        heroesToHeal.add(warrior);
        heroesToHeal.add(wizard);
        heroesToHeal.add(rogue);

        heroesList.addAll(heroesToHeal);        
        heroesList.add(healer); 

        System.out.println("✅ Game entities initialized.");
    }

    /**
     * Convierte los personajes Runnables en Threads, asigna Prioridades y los inicia.
     */
    private static void startThreads() {
        tOgre = new Thread(ogre);
        tWarrior = new Thread(warrior);
        tWizard = new Thread(wizard);
        tRogue = new Thread(rogue);
        tHealer = new Thread(healer);

        // --- ASIGNACIÓN DE PRIORIDADES  ---
        tOgre.setPriority(Thread.MAX_PRIORITY);
        tWarrior.setPriority(Thread.NORM_PRIORITY);
        tWizard.setPriority(Thread.NORM_PRIORITY);
        tRogue.setPriority(Thread.NORM_PRIORITY);
        tHealer.setPriority(Thread.NORM_PRIORITY);

        // Arrancar todos
        tOgre.start();
        tWarrior.start();
        tWizard.start();
        tRogue.start();
        tHealer.start();

        System.out.println("✅ Threads started.\n");
    }

    /**
     * Mantiene el Thread principal vivo y muestra la batalla cada segundo.
     */
    private static void monitorBattle() {
        int seconds = 0;
        try {
            // El bucle se mantiene mientras el Ogro viva y quede al menos un héroe vivo
            while (ogre.isAlive() && (tWarrior.isAlive() || tWizard.isAlive() || tRogue.isAlive())) {

                Thread.sleep(1000); // Pausa de 1s para no saturar consola 
                seconds++;

                printStatus(seconds);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printFinalResult();
    }

    /**
     * Imprime una línea de estado con la barra de vida y contador de héroes vivos.
     */
    private static void printStatus(int seconds) {
        // Contador héroes vivos
        int heroesAlive = 0;
        if (tWarrior.isAlive()) {
            heroesAlive++;
        }
        if (tWizard.isAlive()) {
            heroesAlive++;
        }
        if (tRogue.isAlive()) {
            heroesAlive++;
        }
        if (tHealer.isAlive()) {
            heroesAlive++;
        }

        // Calcula porcentajes para la barra de vida
        float ogreCurrentHealth = (float) ogre.getHealth();
        float fOgreMaxHealth = (float) OGRE_MAX_HEALTH;
        float ogrePercHealth = (ogreCurrentHealth * 100) / fOgreMaxHealth;
        int blocksToPaint = (int) ((ogre.getHealth() * 20) / OGRE_MAX_HEALTH);

        // Construir barra de vida con ASCII
        StringBuilder graphHealth = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            if (i < blocksToPaint) {
                graphHealth.append("█");
            } else {
                graphHealth.append("░");
            }
        }

        // Imprime línea formateada
        System.out.println("\n⏱ " + seconds + "s | "
                + graphHealth + " " + String.format("%.1f", ogrePercHealth) + "% ("
                + ogre.getHealth() + "/" + OGRE_MAX_HEALTH + " HP) | 🎭 Heroes active: " + heroesAlive + "\n");
    }

    /**
     * Comprueba e imprime quién ha ganado.
     */
    private static void printFinalResult() {
        System.out.println("\n=== 🏁 BATTLE ENDS 🏁 ===");

        if (!ogre.isAlive()) {
            System.out.println("🏆 HEROES HAVE WON! The beast is slain.");
        } else {
            System.out.println("💀 OGRE WINS! The party has been wiped out.");
            System.out.println("Ogre remaining HP: " + ogre.getHealth());
        }
    }
}

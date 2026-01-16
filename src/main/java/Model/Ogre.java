package Model;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ogre implements Runnable { 

    private String name;
    private int health;
    private int maxHealth;
    private boolean inFury = false;

    // Lista de enemigos para poder atacarles
    private List<Character> enemies;
    private Random rand = new Random();

    public Ogre(String name, int health, List<Character> enemies) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.enemies = enemies;
    }

    // --- GETTERS BÁSICOS ---
    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    // --- LÓGICA DE ATAQUE ---
    @Override
    public void run() {
        System.out.println("👹😈​ THE OGRE " + this.name + " ROARS AND ENTERS THE BATTLE!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Ogre.class.getName()).log(Level.SEVERE, null, ex);
        }

        while (this.isAlive()) {
           
            // Comprobar si quedan héroes vivos
            if (allHeroesDead()) {
                System.out.println("👹 Ogre: 'HAHAHA! ALL DEAD!'");
                break; // Salimos del bucle si ganamos
            }

            // Elige un héroe aleatorio de la lista
            int targetIndex = rand.nextInt(enemies.size());
            Character victim = enemies.get(targetIndex);

            // ATAQUE
            if (victim.isAlive()) {
                System.out.println("👹 THE OGRE ATTACKS " + victim.getName() + "!");

                // Cálculo de daño del Ogro 
                int damage = rand.nextInt(21) + 20;

                // Llama al método synchronized de defensa del héroe
                victim.receiveAttack(damage);
            }

            // COOLDOWN de ataque
            try {
                Thread.sleep(rand.nextInt(401) + 400); // (400-800ms)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // Método auxiliar para saber si ha ganado
    private boolean allHeroesDead() {
        for (Character c : enemies) {
            if (c.isAlive()) {
                return false;
            }
        }
        return true;
    }

    // --- RECIBIR DAÑO ---
    public synchronized void receiveDamage(int points) {
        if (!isAlive()) {
            return;
        }

        // Cuando está en modo furia recibe la mitad del daño
        if (inFury) {
            points = points / 2;
            System.out.println("\t🛡️ " + name + " resists the attack (Fury)!");
        }

        this.health -= points;
        if (this.health < 0) {
            this.health = 0; // Control de errores para que no acabe la partida con vida negativa
        }

        // Set modo furia
        if (this.health < 750 && !inFury) {
            inFury = true;
            System.out.println("\t😡😡😡 GRRAAAAH!! " + name + " ENTERED BERSERKER RAGE! 😡😡😡");
        }

    }
}

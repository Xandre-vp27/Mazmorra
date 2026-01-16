package Model;

import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ogre implements Runnable { // Ahora es un HILO (Runnable)

    private String name;
    private int health;
    private int maxHealth;
    private boolean inFury = false;

    // Lista de enemigos para poder atacarles
    private List<Character> enemies;
    private Random rand = new Random();

    // Constructor actualizado: Ahora pide la lista de enemigos
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
            // 1. Elegir una víctima al azar
            // Comprobamos si queda alguien vivo antes de atacar
            if (allHeroesDead()) {
                System.out.println("👹 Ogre: 'HAHAHA! ALL DEAD!'");
                break; // Salimos del bucle si ganamos
            }

            // Elegimos un índice aleatorio de la lista
            int targetIndex = rand.nextInt(enemies.size());
            Character victim = enemies.get(targetIndex);

            // 2. ATACAR (Si la víctima está viva)
            if (victim.isAlive()) {
                System.out.println("👹 THE OGRE ATTACKS " + victim.getName() + "!");

                // Daño base del Ogro (según PDF 20-40)
                int damage = rand.nextInt(21) + 20;

                // Llamamos al método synchronized de defensa del héroe
                victim.receiveAttack(damage);
            }

            // 3. DESCANSO (Cooldown)
            try {
                // El Ogro es rápido (400-800ms)
                Thread.sleep(rand.nextInt(401) + 400);
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

        if (inFury) {
            points = points / 2;
            System.out.println("\t🛡️ " + name + " resists the attack (Fury)!");
        }

        this.health -= points;
        if (this.health < 0) {
            this.health = 0;
        }

        if (this.health < 750 && !inFury) {
            inFury = true;
            System.out.println("\t😡😡😡 GRRAAAAH!! " + name + " ENTERED BERSERKER RAGE! 😡😡😡");
        }

    }
}

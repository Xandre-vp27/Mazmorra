/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author alumnet
 */
public class Ogre {

    private String name;
    private int health;
    private int healthMax;
    private boolean inFury = false;

    public Ogre(String name, int health) {
        this.name = name;
        this.health = health;
        this.healthMax = health;
    }

    public int getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public synchronized void receiveDamage(int points) {
        if (!isAlive()) { // Comprueba si ha muerto
            return; 
        }
        if (inFury) { // Si está en furia, reduce el daño / 2
            points = points / 2;
            System.out.println("\t🛡️ The " + name + " is in Fury mode! He resists the attack!");
        }

        this.health -= points;
        if (this.health < 0) {
            this.health = 0;
        }

        // Comprobación si entra en furia (<750 health)
        if (this.health < 750 && !inFury) {
            inFury = true;
            System.out.println("\t😡😡😡 GRRAAAAH!! " + name + " has entered BERSERKER RAGE! 😡😡😡");
        }

        // Mecánica de golpe muy fuerte (>80 points), el Ogre se bloquea
        if (points > 80) {
            System.out.println("\t💫 The " + name + " takes a massive hit! (Stunned for 2s)");
            try {
                Thread.sleep(2000); // Pausa dentro del synchronized de 2s por el golpe
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

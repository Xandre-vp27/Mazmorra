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
public class Wizard extends Character implements Runnable {

    public Wizard(String name, int health, int attackDamageMax, int attackDamageMin, int attackVelocityMax, int attackVelocityMin, int attackTimeMax, int attackTimeMin, Ogre ogre) {
        super(name, health, attackDamageMax, attackDamageMin, attackVelocityMax, attackVelocityMin, attackTimeMax, attackTimeMin, ogre);
    }
    
    @Override
    public void run() {
        System.out.println("🧙🎭 Character " + super.getName() + " enters the battle!");

        // Bucle mientras el ogro está vivo
        while (ogre.isAlive()) {

            int damage = super.getDamage();

            ogre.receiveDamage(damage);
            System.out.println("⚔" + super.getName() + " attack with " + damage + " points!");

            // Cooldown del ataque
            try {
                Thread.sleep(super.getVelocity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Random;


public abstract class Character {

    private String name;
    private int health;

    private int attackDamageMax;
    private int attackDamageMin;

    private int attackVelocityMax;
    private int attackVelocityMin;

    private int attackTimeMax;
    private int attackTimeMin;
    
    protected Ogre ogre;

    Random rand = new Random();

    public Character(String name, int health) {
        this.name = name;
        this.health = health;
    }

    public Character(String name, int health, int attackDamageMax, int attackDamageMin, int attackVelocityMax, int attackVelocityMin, int attackTimeMax, int attackTimeMin, Ogre ogre) {
        this.name = name;
        this.health = health;
        this.attackDamageMax = attackDamageMax;
        this.attackDamageMin = attackDamageMin;
        this.attackVelocityMax = attackVelocityMax;
        this.attackVelocityMin = attackVelocityMin;
        this.attackTimeMax = attackTimeMax;
        this.attackTimeMin = attackTimeMin;
        this.ogre = ogre;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getDamage() {
        return rand.nextInt(this.attackDamageMax - this.attackDamageMin - 1) + this.attackDamageMin;
    }

    public int getVelocity() {
        return rand.nextInt(this.attackVelocityMax - this.attackVelocityMin - 1) + this.attackVelocityMin;
    }

    public int getTime() {
        return rand.nextInt(this.attackTimeMax - this.attackTimeMin - 1) + this.attackTimeMin;
    }

}

package Model;

import java.util.Random;

public abstract class Character {

    private String name;
    private int health;
    private int maxHealth; // Guardo la vida máxima para saber el tope

    // Stats de ataque
    private int maxAttackDamage;
    private int minAttackDamage;
    private int maxAttackSpeed;
    private int minAttackSpeed;
    
    // Referencia al enemigo (Ogre)
    protected Ogre ogre; 

    protected Random rand = new Random();

    public Character(String name, int health, int maxAttackDamage, int minAttackDamage, int maxAttackSpeed, int minAttackSpeed, Ogre ogre) {
        this.name = name;
        this.health = health;
        this.maxHealth = health;
        this.maxAttackDamage = maxAttackDamage;
        this.minAttackDamage = minAttackDamage;
        this.maxAttackSpeed = maxAttackSpeed;
        this.minAttackSpeed = minAttackSpeed;
        this.ogre = ogre;
    }

    // Getters y Setters básicos
    public String getName() { return name; }
    
    public int getHealth() { return health; }
    
    public boolean isAlive() { return health > 0; }

    // Cálculos aleatorios para el ataque
    public int getDamage() {
        return rand.nextInt(this.maxAttackDamage - this.minAttackDamage + 1) + this.minAttackDamage;
    }

    public int getVelocity() {
        return rand.nextInt(this.maxAttackSpeed - this.minAttackSpeed + 1) + this.minAttackSpeed;
    }

    // --- NUEVO MÉTODO: DEFENSA Y ESQUIVA ---
    // Debe ser synchronized para que dos enemigos no le resten vida al mismo tiempo
    public synchronized void receiveAttack(int damage) {
        if (!isAlive()) return; // Si ya está muerto, no le pegues más

        // 1. Lógica de Esquiva (Dodge Mechanics)
        // Calculamos un número del 0 al 99. Si sale < 20, esquiva (20% probabilidad) 
        int dodgeChance = rand.nextInt(100); 
        
        if (dodgeChance < 20) {
            // Esquiva exitosa: El daño se anula
            System.out.println("\t💨 " + this.name + " HAS DODGED the attack! (0 dmg)"); 
            return; // Salimos del método sin restar vida
        }

        // 2. Si no esquiva, recibe el daño
        this.health -= damage;
        if (this.health < 0) this.health = 0;

        System.out.println("\t🩸 " + this.name + " takes " + damage + " damage! (" + this.health + "/" + this.maxHealth + " HP)");
    }
}
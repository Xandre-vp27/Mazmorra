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

    // Flag para saber si la muerte es definitiva (La sanadora ha muerto)
    protected boolean permaDeath = false;
    
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

    // --- DEFENSA Y ESQUIVA ---
    // Debe ser synchronized para que a dos enemigos no le resten vida al mismo tiempo
    public synchronized void receiveAttack(int damage) {
        if (permaDeath || health <= 0) return;

        // Calculo para esquivar del 20%
        int dodgeChance = rand.nextInt(100); 
        if (dodgeChance < 20) {
            System.out.println("\t💨 " + this.name + " HAS DODGED the attack! (0 dmg)"); 
            return; // Ha esquivado el golpe y salimos del método
        }

        // Si no esquiva, recibe el daño
        this.health -= damage;
        if (this.health < 0) this.health = 0;

        System.out.println("\t🩸 " + this.name + " takes " + damage + " damage! (" + this.health + "/" + this.maxHealth + " HP)");
        
        // Si el golpe ha sido mortal (0 HP), despertamos al hilo por si estaba en wait()
        if (this.health == 0) {
            System.out.println("💀 " + this.name + " dies from the injuries!");
            this.permaDeath = true; // Confirmamos muerte definitiva
            this.notifyAll();
        }
    }
    
    // --- HÉROE KO ---
    // Si su vida es <10, se detendrá aquí esperando a la Sanadora.
    public synchronized void checkHealthStatus() {
        // Mientras esté en estado KO (<10), vivo (>0) y Eylin siga viva (!permaDeath)
        while (this.health < 10 && this.health > 0 && !permaDeath) {
            try {
                System.out.println("🚑 " + this.name + " IS KO! Waiting for the healer... (Health: " + this.health + ")");
                
                // El hilo se bloquea y libera el monitor
                this.wait(); 
                
                System.out.println("✨ " + this.name + " wakes up!"); // Mensaje que aparece luego de recibir el notify()
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    // Método para la Sanadora: Cura y Despierta.
    public synchronized void restoreHealth(int amount) {
        // Solo curamos si está herido pero no muerto definitivamente
        if (this.health > 0 && !permaDeath) {
            this.health += amount;
            if (this.health > this.maxHealth) this.health = this.maxHealth;
            
            System.out.println("💖The healer heals " + this.name + "! (+ " + amount + " HP). New HP: " + this.health);
            
            // Despertamos al hilo que estaba en wait()
            this.notify(); 
        }
    }
    
    // Método de emergencia: Si la sanadora muere, despierta a todos SIN curar.
    public synchronized void forceWakeUp() {
        this.permaDeath = true; // Marcamos que ya no hay posibilidad de recuperarse
        this.notifyAll(); // Despierta a todos los hilos atrapados en wait
    }
}
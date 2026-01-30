package Model;

public class Wizard extends Character implements Runnable {

    public Wizard(String name, int health, int attackDamageMax, int attackDamageMin, int attackVelocityMax, int attackVelocityMin, Ogre ogre) {
        super(name, health, attackDamageMax, attackDamageMin, attackVelocityMax, attackVelocityMin, ogre);
    }

    // Runnable. 
    @Override
    public void run() {
        System.out.println("🧙🎭 Character " + super.getName() + " enters the battle!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex);
        }

        // Atacar y recibir daño
        while (super.ogre.isAlive() && this.isAlive()) {
            
            super.checkHealthStatus(); // Si tiene <10 de vida, no puede atacar, está 'KO'
            if (!this.isAlive()) break; // Comprobación de si una vez se 'despierta' sigue vivo o el Ogro lo ha rematado
            
            int damage = super.getDamage();
            
            // Ataque al Ogro
            super.ogre.receiveDamage(damage);
            System.out.println("⚔ " + super.getName() + " attacks with " + damage + " points!");

            try {
                Thread.sleep(super.getVelocity());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
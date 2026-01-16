package Model;


public class Rogue extends Character implements Runnable {

    // Eliminados attackTimeMax y attackTimeMin
    public Rogue(String name, int health, int attackDamageMax, int attackDamageMin, int attackVelocityMax, int attackVelocityMin, Ogre ogre) {
        super(name, health, attackDamageMax, attackDamageMin, attackVelocityMax, attackVelocityMin, ogre);
    }

    // Runnable
    @Override
    public void run() {
        System.out.println("🧙🎭 Character " + super.getName() + " enters the battle!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            System.out.println("Error: " + ex);
        }

        // Atacar y recibir daño
        while (super.ogre.isAlive() && this.isAlive()) { // Comprobamos también que el Rogue esté vivo

            int damage = super.getDamage();
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

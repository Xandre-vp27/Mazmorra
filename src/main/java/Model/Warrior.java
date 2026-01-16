package Model;

public class Warrior extends Character implements Runnable {

    public Warrior(String name, int health, int attackDamageMax, int attackDamageMin, int attackVelocityMax, int attackVelocityMin, Ogre ogre) {
        super(name, health, attackDamageMax, attackDamageMin, attackVelocityMax, attackVelocityMin, ogre);
    }

    @Override
    public void run() {
        System.out.println("⚔ Character " + super.getName() + " enters the battle!");

        while (super.ogre.isAlive() && this.isAlive()) {
            
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
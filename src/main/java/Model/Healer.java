/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

public class Healer implements Runnable {

    private String name;
    private int mana;
    private int maxMana = 80;
    private List<Character> heroesToHeal; // Lista de personajes curables

    public Healer(String name, List<Character> heroesToHeal) {
        this.name = name;
        this.heroesToHeal = heroesToHeal;
        this.mana = maxMana; // Empieza con el maná a tope
    }

    @Override
    public void run() {
        System.out.println("✨ Healer " + this.name + " enters the battlefield!");

        while (true) {
            try {
                // REGENERACIÓN DE MANÁ (Cada 0.5s) 
                Thread.sleep(500); 
                if (this.mana < this.maxMana) {
                    this.mana++;
                }

                // BUSCAR HÉROES HERIDOS
                for (Character hero : heroesToHeal) {
                    
                    // Si el héroe está en estado crítico (<10) y sigue vivo (>0)
                    if (hero.getHealth() < 10 && hero.getHealth() > 0) {
                        
                        // Comprovación de que tenga >50 maná para curar
                        if (this.mana >= 50) {
                            System.out.println("✨ " + this.name + " found " + hero.getName() + " unconscious! Casting HEAL...");
                            
                            this.mana -= 50;
                            
                            // Llama al método de curar y despertar (notify) del héroe
                            hero.restoreHealth(50); 
                            
                        } else {
                            System.out.println("⚠️ " + this.name + " needs mana to heal " + hero.getName() + " (" + mana + "/50)");
                        }
                    }
                }

            } catch (InterruptedException e) {
                System.out.println("💀 Healer " + this.name + " has fallen!"); // Si la sanadora muere, rompemos su hilo
                break; 
            }
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.masmorramultifil_pt7;

import Model.*;

/**
 *
 * @author alumnet
 */
public class MasmorraMultifil_Pt7 {

    public static void main(String[] args) {

        System.out.println("=== 🏁 BATTLE STARTS 🏁 ===\n");

        Ogre ogre = new Ogre("Ogre", 500);

        Warrior warrior = new Warrior("Corvan", 100, 140, 30, 1500, 1000, 500, 300, ogre);
        Wizard wizard = new Wizard("Greiflum", 100, 60, 10, 1000, 600, 200, 100, ogre);
        Rogue rogue = new Rogue("Darrel", 100, 30, 5, 500, 200, 50, 10, ogre);

        // Creem el Thread i indiquem el personatge
        Thread filC1 = new Thread(warrior);
        Thread filC2 = new Thread(wizard);
        Thread filC3 = new Thread(rogue);
        filC1.start();
        filC2.start();
        filC3.start();

    }

}

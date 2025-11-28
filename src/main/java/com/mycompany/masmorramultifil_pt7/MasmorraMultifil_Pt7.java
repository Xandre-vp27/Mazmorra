/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.masmorramultifil_pt7;

import Model.Guerrer;
import Model.Mag;

/**
 *
 * @author alumnet
 */
public class MasmorraMultifil_Pt7 {

    public static void main(String[] args) {

        System.out.println("--- Iniciant Masmoora Multifil ---\n");

        Guerrer p1 = new Guerrer("Guerrer", 100);
        Mag p2 = new Mag("Mag", 100);

        // Creem el Thread i indiquem el personatge
        Thread filP1 = new Thread(p1);
        Thread filP2 = new Thread(p2);
        filP1.start();
        filP2.start();

    }
}

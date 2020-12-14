/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.Scanner;

/**
 *
 * @author anadis
 */
public class KonsoliIO implements IO {

    private Scanner lukija;

    public KonsoliIO() {
        lukija = new Scanner(System.in);
    }

    public void print(String m) {
        System.out.println(m);
    }

    public String nextLine() {
        return lukija.nextLine();
    }
}

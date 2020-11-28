/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

/**
 *
 * @author anadis
 */
public class Plus extends Komento {

    private int luku = 0;
    
    public Plus() {
        super();
    }
    
    @Override
    public int suorita(int edellinen, int luku) {
        this.luku = edellinen;
        return edellinen+=luku;
    }

    @Override
    public int peru() {
        return this.luku;
    }

    
}

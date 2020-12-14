/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

/**
 *
 * @author anadis
 */
public abstract class KiviPaperiSakset {

    IO io;
    
    public KiviPaperiSakset(IO io) {
        this.io = io;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        String ekanSiirto = ensimmaisenSiirto();
        String tokanSiirto = toisenSiirto(ekanSiirto);

        while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            io.print(tuomari.toString());
            io.print("");

            ekanSiirto = ensimmaisenSiirto();
            
            tokanSiirto = toisenSiirto(ekanSiirto);
            io.print(tokanSiirto);
        }

        io.print("");
        io.print("Kiitos!");
        io.print(tuomari.toString());
    }

    protected String ensimmaisenSiirto() {
        io.print("Ensimmäisen pelaajan siirto: ");
        return io.nextLine();
    }

    abstract protected String toisenSiirto(String ekanSiirto);

    protected static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}

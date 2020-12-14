package ohtu.kivipaperisakset;

public class Paaohjelma {

    private static final IO io = new KonsoliIO();

    public static void main(String[] args) {

        KPSPeliTehdas peliTehdas = new KPSPeliTehdas(io);

        while (true) {
            io.print("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = io.nextLine();
            if (vastaus.endsWith("a") || vastaus.endsWith("b") || vastaus.endsWith("c")) {
                KiviPaperiSakset peli = peliTehdas.hae(vastaus);
                io.print("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
                peli.pelaa();
            } else {
                break;
            }

        }

    }
}

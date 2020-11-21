package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, OLETUSKASVATUS = 5;
    private int kasvatuskoko, kohta;
    private int[] lukujono;
    private int alkioidenLkm;

    public IntJoukko() {
        initial(KAPASITEETTI, OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        initial(kapasiteetti, OLETUSKASVATUS);

    }

    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        initial(kapasiteetti, kasvatuskoko);

    }

    public void initial(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasitteetti väärin");//heitin vaan jotain :D
        }
        if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("kasvatuskoko väärin");//heitin vaan jotain :D
        }
        lukujono = new int[kapasiteetti];
        for (int i = 0; i < lukujono.length; i++) {
            lukujono[i] = 0;
        }
        this.kasvatuskoko = kasvatuskoko;
        this.alkioidenLkm = 0;
        this.kohta = 0;
    }

    public boolean lisaa(int luku) {

        if (alkioidenLkm == 0) {
            lukujono[0] = luku;
            alkioidenLkm++;
            return true;
        }
        if (!kuuluu(luku)) {
            lukujono[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % lukujono.length == 0) {
                kasvataTaulukko();
            }
            return true;
        }
        return false;
    }

    public void kasvataTaulukko() {
        int[] taulukkoOld = new int[lukujono.length];
        taulukkoOld = lukujono;
        kopioiTaulukko(lukujono, taulukkoOld);
        lukujono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, lukujono);
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == lukujono[i]) {
                this.kohta = i;
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {

        if (kuuluu(luku)) {
            lukujono[kohta] = 0;
            siirra();
            return true;
        }

        return false;
    }

    private void siirra() {

        for (int j = kohta; j < alkioidenLkm - 1; j++) {
            int apu = lukujono[j];
            lukujono[j] = lukujono[j + 1];
            lukujono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }

    @Override
    public String toString() {
        
        switch (alkioidenLkm) {
            case 0:
                return "{}";
            case 1:
                return "{" + lukujono[0] + "}";
            default:
                String tuotos = "{";
                for (int i = 0; i < alkioidenLkm - 1; i++) {
                    tuotos += lukujono[i];
                    tuotos += ", ";
                }
                tuotos += lukujono[alkioidenLkm - 1];
                tuotos += "}";
                return tuotos;
        }
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = lukujono[i];
        }
        return taulu;
    }

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        for (int luku : a.toIntArray()) {
            x.lisaa(luku);
        }
        for (int luku : b.toIntArray()) {
            x.lisaa(luku);
        }
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        for (int luku : a.toIntArray()) {
            if (b.kuuluu(luku)) {
                y.lisaa(luku);
            }
        }
        return y;
    }

    public static IntJoukko erotus(IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        for (int luku : a.toIntArray()) {
            if (!b.kuuluu(luku)) {
                z.lisaa(luku);
            }
        }

        return z;
    }

}
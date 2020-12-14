package ohtu.kivipaperisakset;

public class KPSTekoaly extends KiviPaperiSakset {

    private TekoalyI tekoaly;

    public KPSTekoaly(IO io, TekoalyI tekoaly) {
        super(io);
        this.tekoaly = tekoaly;
    }

    @Override
    protected String toisenSiirto(String ekanSiirto) {
        String tokanSiirto = tekoaly.annaSiirto();
        tekoaly.asetaSiirto(ekanSiirto);
        io.print("Tietokone valitsi: ");
        return tokanSiirto;
    }
}

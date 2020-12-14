/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu.kivipaperisakset;

import java.util.HashMap;

/**
 *
 * @author anadis
 */
public class KPSPeliTehdas {
    private HashMap<String, KiviPaperiSakset> kpsPelaajat;

    public KPSPeliTehdas(IO io) {
        this.kpsPelaajat = new HashMap<String, KiviPaperiSakset>();
        this.kpsPelaajat.put("a", new KPSPelaajaVsPelaaja(io));
        this.kpsPelaajat.put("b", new KPSTekoaly(io, new TekoalyYksinkertainen()));
        this.kpsPelaajat.put("c", new KPSTekoaly(io, new TekoalyParannettu(20)));
    }

    public KiviPaperiSakset hae(String pelityyppi) {
        return this.kpsPelaajat.getOrDefault(pelityyppi, null);
    }
}

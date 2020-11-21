package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {

    Pankki pankki;
    Varasto varasto;
    Viitegeneraattori viite;
    
    @Before
    public void setUp() {
           
        pankki = mock(Pankki.class);
        varasto = mock(Varasto.class);
        viite = mock(Viitegeneraattori.class);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // luodaan ensin mock-oliot
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viite);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
//        nimi, viite, tiliNumero, kaupanTili, summa
    }
    
    @Test
    public void ostoksienPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikein() {
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sukla", 10));
        
        when(varasto.saldo(3)).thenReturn(10);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kahvi", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("bob", "123456");
        
        verify(pankki).tilisiirto(eq("bob"), anyInt(), eq("123456"), anyString(), eq(17));   
    }
    
    @Test
    public void samojenOstoksienPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        Kauppa k = new Kauppa(varasto, pankki, viite);              

        k.aloitaAsiointi();
        k.lisaaKoriin(1);   
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");

        verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10)); 
    }
    @Test
    public void ostoksienPaaytyttyaPankinMetodiaTilisiirtoKutsutaanOikeinKunTuoteettaEiOle() {
        when(varasto.saldo(2)).thenReturn(10);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "sukla", 10));
        
        when(varasto.saldo(3)).thenReturn(0);
        when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "kahvi", 7));
        
        Kauppa k = new Kauppa(varasto, pankki, viite);
        k.aloitaAsiointi();
        k.lisaaKoriin(2);
        k.lisaaKoriin(3);
        k.tilimaksu("bob", "123456");
        
        verify(pankki).tilisiirto(eq("bob"), anyInt(), eq("123456"), anyString(), eq(10));   
    }
}
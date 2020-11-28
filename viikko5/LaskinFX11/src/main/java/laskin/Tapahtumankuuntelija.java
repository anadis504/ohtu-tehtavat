package laskin;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {

    private TextField tuloskentta;
    private TextField syotekentta;
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;
    private Sovelluslogiikka sovellus;
    private Komentotehdas tehdas;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(TextField tuloskentta, TextField syotekentta, Button plus, Button miinus, Button nollaa, Button undo) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
        this.tehdas = new Komentotehdas(plus, miinus, nollaa, undo);
    }

    @Override
    public void handle(Event event) {
        int arvo = 0;

        try {
            arvo = Integer.parseInt(syotekentta.getText());
        } catch (Exception e) {
        }
        int tulos = 0;
        int nykyinen = Integer.parseInt(tuloskentta.getText());
        if (event.getTarget() != undo) {
            Komento komento = tehdas.hae((Button) event.getTarget());
            edellinen = komento;
            tulos = komento.suorita(nykyinen, arvo);
        } else {
            tulos = edellinen.peru();
        }

        syotekentta.setText("");
        tuloskentta.setText("" + tulos);

        if (tulos == 0) {
            nollaa.disableProperty().set(true);
        } else {
            nollaa.disableProperty().set(false);
        }
        undo.disableProperty().set(false);
    }

}

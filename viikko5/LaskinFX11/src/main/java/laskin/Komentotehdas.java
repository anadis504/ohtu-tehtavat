/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laskin;

import java.util.HashMap;
import javafx.scene.control.Button;

/**
 *
 * @author anadis
 */
public class Komentotehdas {

    private HashMap<Button, Komento> komennot;
    private Button plus;
    private Button miinus;
    private Button nollaa;
    private Button undo;

    public Komentotehdas(Button plus, Button miinus, Button nollaa, Button undo) {
        this.komennot = new HashMap<>();
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        komennot.put(plus, new Plus());
        komennot.put(miinus, new Miinus());
        komennot.put(nollaa, new Nollaa());
    }

    public Komento hae(Button button) {
        return komennot.get(button);
    }
}

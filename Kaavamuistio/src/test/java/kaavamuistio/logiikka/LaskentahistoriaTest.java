/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaavamuistio.logiikka;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veini
 */
public class LaskentahistoriaTest {
    
    public LaskentahistoriaTest() {
        Laskentahistoria historia = new Laskentahistoria();
    }
    
    @Test
    public void testLisaaRiviJaKaikkiRivit() {
        Laskentahistoria laskentahistoria = new Laskentahistoria(3);
        ArrayList<String> parametrit = new ArrayList<>();
        assertEquals(laskentahistoria.kaikkiRivit(), "");
        laskentahistoria.lisaaRivi("rivi1");
        assertEquals(laskentahistoria.kaikkiRivit(), "rivi1\n");
        laskentahistoria.lisaaRivi("rivi2");
        laskentahistoria.lisaaRivi("rivi3");
        parametrit.add("x");
        laskentahistoria.lisaaRivi(parametrit, "tulos");
        laskentahistoria.lisaaRivi("rivi4");
        assertEquals(laskentahistoria.kaikkiRivit(), "rivi4\n[x]: tulos\nrivi3\n");
    }
}

package kaavamuistio;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KaavamuistioTest {
    
    private Kaavamuistio kaavamuistio;
    
    public KaavamuistioTest() {
        kaavamuistio = new Kaavamuistio();
        kaavamuistio.lisaaKaava("Energian suhde massaan ja valonnopeuteen", "_m__c_^2");
        kaavamuistio.lisaaKaava("Potentiaalienergia", "_m__c_^2");
        kaavamuistio.lisaaKaava("Nopeus matkan ja ajan suhteenajan", "_s_/_t_");
    }

    @Test
    public void testKaavojenIndeksit() {
        assertEquals("[0, 1]", kaavamuistio.kaavojenIndeksit("energia").toString());
        assertEquals("[2]", kaavamuistio.kaavojenIndeksit("nopeus").toString());
    }

    @Test
    public void testHaeKaava() {
        assertEquals("Potentiaalienergia: _m__c_^2", kaavamuistio.haeKaava(1).toString());
    }

    @Test
    public void testKaavanIndeksi() {
        assertEquals(1, kaavamuistio.kaavanIndeksi("Potentiaalienergia"));
    }

    @Test
    public void testLisaaKaava() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        assertEquals("[0, 1, 2, 3]", kaavamuistio.kaavojenIndeksit("").toString());
        assertEquals("a: _b_*_c_", kaavamuistio.haeKaava(3).toString());
    }

    @Test
    public void testMuutaKaava() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean onnistuiko = kaavamuistio.muutaKaava("a", "_b_*_c_/2");
        assertEquals("a: _b_*_c_/2", kaavamuistio.haeKaava(3).toString());
        assertEquals(true, onnistuiko);
    }

    @Test
    public void testMuutaKaavanNimi() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean onnistuiko = kaavamuistio.muutaKaavanNimi("a", "g");
        assertEquals("g: _b_*_c_", kaavamuistio.haeKaava(3).toString());
        assertEquals(true, onnistuiko);
    }
    
}

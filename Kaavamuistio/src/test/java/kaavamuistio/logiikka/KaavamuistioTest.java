package kaavamuistio.logiikka;

import org.junit.Test;
import static org.junit.Assert.*;

public class KaavamuistioTest {
    
    private final Kaavamuistio kaavamuistio;
    
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
    public void testKaavojenNimet() {
        assertEquals("[Energian suhde massaan ja valonnopeuteen, Potentiaalienergia]",
                kaavamuistio.kaavojenNimet("energia").toString());
        assertEquals("[Nopeus matkan ja ajan suhteenajan]", kaavamuistio.kaavojenNimet("nopeus").toString());
    }

    @Test
    public void testHaeKaava() {
        assertEquals("Potentiaalienergia: _m__c_^2", kaavamuistio.haeKaava(1).toString());
        assertEquals(null, kaavamuistio.haeKaava(-1));
        assertEquals(null, kaavamuistio.haeKaava(3));
    }

    @Test
    public void testHaeKaavaNimella() {
        assertEquals("Potentiaalienergia: _m__c_^2", kaavamuistio.haeKaava("potentiaalienergia").toString());
    }

    @Test
    public void testKaavanIndeksi() {
        assertEquals(1, kaavamuistio.kaavanIndeksi("Potentiaalienergia"));
    }

    @Test
    public void testLisaaKaava() {
        boolean a = kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean b = kaavamuistio.lisaaKaava("a", "jotain");
        
        assertEquals(true, a && !b);
        
        assertEquals("[0, 1, 2, 3]", kaavamuistio.kaavojenIndeksit("").toString());
        assertEquals("a: jotain", kaavamuistio.haeKaava(3).toString());
    }

    @Test
    public void testPoistaKaava() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean a = kaavamuistio.poistaKaava("a");
        boolean b = kaavamuistio.poistaKaava("a");
        
        assertEquals(true, a && !b);
        
        assertEquals("[0, 1, 2]", kaavamuistio.kaavojenIndeksit("").toString());
    }

    @Test
    public void testMuutaKaava() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean onnistuiko = kaavamuistio.muutaKaava("a", "_b_*_c_/2");
        assertEquals("a: _b_*_c_/2", kaavamuistio.haeKaava(3).toString());
        assertEquals(true, onnistuiko);
        assertEquals(false, kaavamuistio.muutaKaava("ei ole olemassa", "_b_*_c_/2"));
    }

    @Test
    public void testMuutaKaavanNimi() {
        kaavamuistio.lisaaKaava("a", "_b_*_c_");
        boolean a = kaavamuistio.muutaKaavanNimi("a", "g");
        boolean b = kaavamuistio.muutaKaavanNimi("et", "qs");
        assertEquals("g: _b_*_c_", kaavamuistio.haeKaava(3).toString());
        assertEquals(true, a && !b);
    }
    
}

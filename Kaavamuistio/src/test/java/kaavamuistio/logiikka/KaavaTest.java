package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.logiikka.Kaava;
import org.junit.Test;
import static org.junit.Assert.*;

public class KaavaTest {
    
    private String alkukaava, virhekaava, tavoitekaava;
    
    private Kaava kaava;
    
    public KaavaTest() {
        alkukaava = "_a_*5+87/_a_*_b_";
        virhekaava = "_c_*5+87/_a_*_b";
        tavoitekaava = "_c_*5+87/_a_*_b_";
        kaava = new Kaava("Kokeilukaava", alkukaava);
    }

    @Test
    public void konstruktoriAsettaaKaavanOikein() {
        assertEquals(alkukaava,kaava.getKaava());
        kaava = new Kaava("Kokeilu", virhekaava);
        assertEquals(kaava.getKaava(),tavoitekaava);
    }

    @Test
    public void muutaKaavaaMuuttaaKaavaa() {
        kaava.muutaKaavaa(tavoitekaava);
        assertEquals(tavoitekaava,kaava.getKaava());
    }
    
    @Test
    public void tarkistaKaavanEheysKorjaa() {
        kaava.muutaKaavaa(virhekaava);
        assertEquals(tavoitekaava,kaava.getKaava());
    }
    
    @Test
    public void haeMuuttujatHakeeOikein() {
        assertEquals("[a, b]",kaava.haeMuuttujat().toString());
        kaava.muutaKaavaa(tavoitekaava);
        assertEquals("[c, a, b]",kaava.haeMuuttujat().toString());
    }
    
    @Test
    public void sijoitaParametritToimii() {
        ArrayList<String> parametrit = new ArrayList<>();
        parametrit.add("23");
        parametrit.add("74");
        assertEquals(alkukaava.replaceAll("_a_", "23").replaceAll("_b_", "74"),kaava.sijoitaParametrit(parametrit));
        
        assertEquals("Virhe", kaava.sijoitaParametrit(new ArrayList<String>()));
    }
    
    //@Test
    // Ei toimi eri alustoilla samalla tavalla
    public void laskeToimii() {
        Kaava k = new Kaava("Nimi", "(_a_+_b_)*2*0.5");
        
        ArrayList<String> parametrit = new ArrayList<>();
        parametrit.add("23.0");
        parametrit.add("74.0");
        
        assertEquals("97.0", k.laske(parametrit));
        assertEquals("[23.0, 74.0]: 97.0\n", k.getLaskentahistoria().kaikkiRivit());
    }
}

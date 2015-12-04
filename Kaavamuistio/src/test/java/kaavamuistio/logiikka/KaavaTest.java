package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.logiikka.Kaava;
import kaavamuistio.palvelut.Laskin;
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
    
    @Test
    public void laskeToimii() {
        // Luodaan ensin laskin, jotta staattiset kentät tulevat käyttöön
        Laskin l = new Laskin();
        
        Kaava k = new Kaava("Nimi", "\"\"+parseInt(23+_a_)");
        ArrayList<String> param = new ArrayList<>();
        param.add("15");
        assertEquals("38", k.laske(param));
        assertEquals("[15]: 38\n", k.getLaskentahistoria(true));
    }
}

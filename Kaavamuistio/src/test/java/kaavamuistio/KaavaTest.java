/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaavamuistio;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veini
 */
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
    }
    
}

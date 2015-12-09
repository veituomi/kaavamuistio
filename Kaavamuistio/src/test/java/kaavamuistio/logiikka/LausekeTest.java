package kaavamuistio.logiikka;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class LausekeTest {
    
    private String alkukaava, virhekaava, tavoitekaava;
    
    private Lauseke lauseke;
    
    public LausekeTest() {
        alkukaava = "_a_*5+87/_a_*_b_";
        virhekaava = "_c_*5+87/_a_*_b";
        tavoitekaava = "_c_*5+87/_a_*_b_";
        lauseke = new Lauseke(alkukaava);
    }
    
    @Test
    public void tarkistaLausekkeenEheysKorjaa() {
        lauseke.muuta(virhekaava);
        assertEquals(tavoitekaava,lauseke.getLauseke());
    }
    
    
    @Test
    public void sijoitaParametritToimii() {
        ArrayList<String> parametrit = new ArrayList<>();
        parametrit.add("23");
        parametrit.add("74");
        assertEquals(alkukaava.replaceAll("_a_", "23").replaceAll("_b_", "74"), lauseke.sijoitaParametrit(parametrit));
        
        assertEquals("Virhe", lauseke.sijoitaParametrit(new ArrayList<String>()));
    }
}

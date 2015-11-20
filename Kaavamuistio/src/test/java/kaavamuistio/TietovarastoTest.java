package kaavamuistio;

import kaavamuistio.logiikka.Kaavamuistio;
import kaavamuistio.logiikka.Laskentahistoria;
import kaavamuistio.logiikka.Kaava;
import org.junit.Test;
import static org.junit.Assert.*;

public class TietovarastoTest {
    
    public TietovarastoTest() {
    }

    @Test
    public void tietoSailyyLevylleTallennettuna() {
        Kaavamuistio kaavamuistio = new Kaavamuistio();
        kaavamuistio.lisaaKaava(new Kaava("Nelio", "_x_*_x_"));
        kaavamuistio.lisaaKaava(new Kaava("Kuutio", "_x_*_x_*_x_"));
        kaavamuistio.lisaaKaava(new Kaava("Binomi", "_x_+_y_"));
        
        Laskentahistoria laskentahistoria = new Laskentahistoria();
        laskentahistoria.lisaaRivi("laskettua");
        String vertailuhistoria = laskentahistoria.kaikkiRivit();
        kaavamuistio.haeKaava(2).setLaskentahistoria(laskentahistoria);
        
        Tietovarasto.tallennaKaavamuistio("tallennettuKaavamuistio", kaavamuistio);
        
        kaavamuistio = Tietovarasto.avaaKaavamuistio("tallennettuKaavamuistio");
        
        assertEquals("Nelio",kaavamuistio.haeKaava(0).getNimi());
        assertEquals(vertailuhistoria,kaavamuistio.haeKaava(2).getLaskentahistoria().kaikkiRivit());
        assertEquals("_x_*_x_*_x_",kaavamuistio.haeKaava(1).getKaava());
    }
    
}

package kaavamuistio.palvelut;

import kaavamuistio.palvelut.Laskin;
import org.junit.Test;
import static org.junit.Assert.*;

public class LaskinTest {
    
    private Laskin laskin;
    
    public LaskinTest() {
        laskin = new Laskin();
    }
    
    @Test
    public void laskeAntaaOikeanVastauksen() {
        assertEquals(295810, (int)Double.parseDouble(laskin.laske("948*312+34.5")));
    }
    
    @Test
    public void laskePalauttaaVirheilmoituksen() {
        assertEquals("Virhe", laskin.laske("948*(312+34"));
    }
}

package kaavamuistio;

import org.junit.Test;
import static org.junit.Assert.*;

public class JuoksevaLaskuriTest {
    
    public JuoksevaLaskuriTest() {
    }
    
    @Test
    public void testSeuraavaKasvaaJaPysahtyy() {
        JuoksevaLaskuri laskuri = new JuoksevaLaskuri(0, 3, -2);
        assertEquals(0, laskuri.seuraava());
        assertEquals(1, laskuri.seuraava());
        assertEquals(2, laskuri.seuraava());
        assertEquals(3, laskuri.seuraava());
        assertEquals(-2, laskuri.seuraava());
    }
    
    @Test
    public void testSeuraavaPieneneeJaPysahtyy() {
        JuoksevaLaskuri laskuri = new JuoksevaLaskuri(3, 0, -2);
        assertEquals(3, laskuri.seuraava());
        assertEquals(2, laskuri.seuraava());
        assertEquals(1, laskuri.seuraava());
        assertEquals(0, laskuri.seuraava());
        assertEquals(-2, laskuri.seuraava());
    }
    
    @Test
    public void testSeuraavaKaanteisellaJarjestyksella() {
        JuoksevaLaskuri laskuri = new JuoksevaLaskuri(0, 3, true);
        assertEquals(3, laskuri.seuraava());
        assertEquals(2, laskuri.seuraava());
        assertEquals(1, laskuri.seuraava());
        assertEquals(0, laskuri.seuraava());
        assertEquals(-1, laskuri.seuraava());
    }
    
}

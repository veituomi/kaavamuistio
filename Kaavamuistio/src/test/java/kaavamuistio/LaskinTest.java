/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kaavamuistio;

import kaavamuistio.Laskin;
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
public class LaskinTest {
    
    private Laskin laskin;
    
    public LaskinTest() {
        laskin = new Laskin();
    }
    
    @Test
    public void laskeAntaaOikeanVastauksen() {
        assertEquals("295810", laskin.laske("948*312+34"));
    }
    
    @Test
    public void laskePalauttaaVirheilmoituksen() {
        assertEquals("Virhe", laskin.laske("948*(312+34"));
    }
}

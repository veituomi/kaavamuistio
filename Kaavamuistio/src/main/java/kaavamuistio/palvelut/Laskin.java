package kaavamuistio.palvelut;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Luokka tarjoaa menetelmän lausekkeen laskuun
 */
public class Laskin {
    // Lähtökohtaisesti käytetään JavaScript-moottoria laskemiseen.
    // Mahdollisuuksien puitteissa tavoitteena on kirjoittaa oma ratkaisija.
    private static ScriptEngineManager scriptEngineManager;
    private static ScriptEngine scriptEngine;
    
    public Laskin() {
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    }
    
    /**
    * Metodi yrittää laskea lausekkeelle arvon
    *
    * @param   lauseke   Laskimelle annettu kaava
    * 
    * @return arvo tai ilmoitus virheestä
    */
    public static String laske(String lauseke) {
        try {
            return scriptEngine.eval(lauseke).toString();
        }
        catch (Exception e) {
            return "Virhe";
        }
    }
}
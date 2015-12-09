package kaavamuistio.palvelut;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Luokka tarjoaa menetelmän lausekkeen laskuun
 */
public class Laskin {
    private static ScriptEngineManager scriptEngineManager;
    private static ScriptEngine scriptEngine;
    
    /**
     * Alustaa laskimen tarvitsemat resurssit
     */
    public Laskin() {
        scriptEngineManager = new ScriptEngineManager();
        scriptEngine = scriptEngineManager.getEngineByName("JavaScript");
    }
    
    /**
    * Yrittää laskea lausekkeelle arvon
    *
    * @param   lauseke   Laskimelle annettu kaava
    * 
    * @return arvo tai ilmoitus virheestä
    */
    public static String laske(String lauseke) {
        try {
            return scriptEngine.eval(lauseke).toString();
        } catch (Exception e) {
            return "Virhe";
        }
    }
}

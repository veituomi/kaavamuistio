package kaavamuistio;

import java.util.ArrayList;

public class Laskentahistoria {
    private ArrayList<String> laskut;
    private int kokorajoite;
    
    public Laskentahistoria() {
        laskut = new ArrayList<>();
        kokorajoite = 1000;
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @param   parametrit       Kaavaan käytetyt parametrit
    * @param   tulos
    * 
    */
    public void lisaaRivi(ArrayList<String> parametrit, String tulos) {
        laskut.add(parametrit.toString()+": "+tulos);
        while (laskut.size() > kokorajoite) {
            laskut.remove(0);
        }
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @param   rivi     Lisättävä rivi
    * 
    */
    public void lisaaRivi(String rivi) {
        laskut.add(rivi);
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @return koko historia
    */
    public String kaikkiRivit() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String rivi : laskut) {
            stringBuilder.append(rivi+"\n");
        }
        return stringBuilder.toString();
    }
}

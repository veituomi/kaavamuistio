package kaavamuistio.logiikka;

import java.util.ArrayList;

/**
 * Luokka pitää historiaa kaavalla tehdyistä laskuista
 */
public class Laskentahistoria {
    private ArrayList<String> laskut;
    private int kokorajoite;
    
    public Laskentahistoria() {
        laskut = new ArrayList<>();
        kokorajoite = 10;
    }
    
    /**
    * Konstruktori luo laskentahistorian, jonka kokorajoite poikkeaa vakiosta
    *
    * @param   kokorajoite
    * 
    */
    public Laskentahistoria(int kokorajoite) {
        laskut = new ArrayList<>();
        this.kokorajoite = kokorajoite;
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @param   parametrit       Kaavaan käytetyt parametrit
    * @param   tulos
    * 
    */
    public void lisaaRivi(ArrayList<String> parametrit, String tulos) {
        lisaaRivi(parametrit.toString()+": "+tulos);
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @param   rivi     Lisättävä rivi
    * 
    */
    public void lisaaRivi(String rivi) {
        laskut.add(rivi);
        while (laskut.size() > kokorajoite) {
            laskut.remove(0);
        }
    }
    
    /**
    * Metodi lisää rivin historiaan
    *
    * @return koko historia
    */
    public String kaikkiRivit() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = laskut.size()-1; i>=0; --i) {
            String rivi = laskut.get(i);
            stringBuilder.append(rivi).append("\n");
        }
        return stringBuilder.toString();
    }
}

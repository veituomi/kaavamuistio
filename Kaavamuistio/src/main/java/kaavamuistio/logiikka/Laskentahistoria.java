package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.*;

/**
 * Luokka pitää historiaa kaavalla tehdyistä laskuista
 */
public class Laskentahistoria {
    private ArrayList<String> laskut;
    private int kokorajoite;
    
    public Laskentahistoria() {
        laskut = new ArrayList<>();
        kokorajoite = 25;
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
    * Metodi lisää rivin historiaan (käänteisessä järjestyksessä)
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
    * Metodi palauttaa koko historian yhtenä merkkijonona
    *
    * @param kaanteinenJarjestys true jos halutaan käänteisenä
    * 
    * @return koko historia
    */
    public String kaikkiRivit(boolean kaanteinenJarjestys) {
        if (laskut.size() < 1) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        JuoksevaLaskuri laskuri
                = new JuoksevaLaskuri(0, laskut.size()-1, kaanteinenJarjestys);
        for (int i = laskuri.seuraava(); i!=-1; i = laskuri.seuraava()) {
            stringBuilder.append(laskut.get(i)).append("\n");
        }
        return stringBuilder.toString();
    }
    
    /**
    * Metodi palauttaa koko historian yhtenä merkkijonona käänteisessä
    * järjestyksessä
    * 
    * @return koko historia
    */
    public String kaikkiRivit() {
        return kaikkiRivit(true);
    }
}

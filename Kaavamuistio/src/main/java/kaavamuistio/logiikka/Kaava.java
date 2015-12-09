package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.palvelut.Laskin;

/**
 * Luokka tarjoaa tavan käsitellä kaavoja
 */
public class Kaava {
    private Lauseke lauseke;
    
    private String nimi;
    private Laskentahistoria laskentahistoria;
    
    /**
     * Luo kaavan
     * @param nimi
     * @param lauseke
     */
    public Kaava(String nimi, String lauseke) {
        this.lauseke = new Lauseke(lauseke);
        this.nimi = nimi;
        this.laskentahistoria = new Laskentahistoria();
    }
    
    public String getKaava() {
        return lauseke.getLauseke();
    }
    
    public String getNimi() {
        return nimi;
    }
    
    /**
     * Palauttaa laskentahistorian tekstimuodossa
     * @param kaanteinenJarjestys true jos uusin halutaan ensin
     * @return laskentahistoria tekstimuodossa
     */
    public String getLaskentahistoria(boolean kaanteinenJarjestys) {
        return laskentahistoria.kaikkiRivit(kaanteinenJarjestys);
    }
    
    public void setLaskentahistoria(Laskentahistoria lh) {
        laskentahistoria = lh;
    }
    
    /**
    * Muuttaa kaavan lauseketta
    * 
    * @param lause uusi lauseke
    */
    public void muutaKaavaa(String lause) {
        lauseke.muuta(lause);
    }
    
    
    /**
    * Muuttaa kaavan nimen
    * 
    * @param nimi uusi nimi
    */
    public void muutaNimi(String nimi) {
        this.nimi = nimi;
    }
    
    /**
    * Etsii kaavassa esiintyvät muuttujat
    *
    * @return lista muuttujista
    */
    public ArrayList<String> haeMuuttujat() {
        return lauseke.getMuuttujat();
    }
    
    /**
     * Sijoittaa annetut parametrit, jos niitä on yhtä paljon kuin muuttujia
     *
     * @param   parametrit   Kaavaan syötetyt parametrit
     * @return lauseke johon on sijoitettu parametrit
     */
    /*public String sijoitaParametrit(ArrayList<String> parametrit) {
        return lauseke.sijoitaParametrit(parametrit);
    }*/
    
    /**
     * Kertoo, onko annettu merkkijono sama kuin kaavan nimi
     * @param verrattava
     * @return palauttaa true jos on
     */
    public boolean onkoSamaNimi(String verrattava) {
        return nimi.toLowerCase().equals(verrattava.toLowerCase());
    }
    
    /**
     * Kertoo, sisältyykö nimeen annettua merkkijonoa
     * @param hakutermi
     * @return palauttaa true jos sisältyy
     */
    public boolean sisaltaakoNimi(String hakutermi) {
        return nimi.toLowerCase().contains(hakutermi.toLowerCase());
    }
    
    /**
    * Laskee lausekkeen arvon ja lisää sen historiaan
    * 
    * @param parametrit lausekkeeseen syötettävät parametrit
    * 
    * @return lukuarvo, joka vastaa lauseketta
    */
    public String laske(ArrayList<String> parametrit) {
        String tulos = Laskin.laske(lauseke.sijoitaParametrit(parametrit));
        laskentahistoria.lisaaRivi(parametrit, tulos);
        return tulos;
    }
    
    @Override
    public String toString() {
        return nimi + ": " + lauseke.getLauseke();
    }
}

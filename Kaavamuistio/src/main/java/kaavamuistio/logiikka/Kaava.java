package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.palvelut.Laskin;

/**
 * Luokka tarjoaa tavan käsitellä kaavoja
 */
public class Kaava {
    private String kaava;
    private String nimi;
    private Laskentahistoria laskentahistoria;
    
    public Kaava(String nimi, String kaava) {
        this.kaava = kaava;
        this.nimi = nimi;
        tarkistaKaavanEheys();
    }
    
    public String getKaava() {
        return kaava;
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
        if (laskentahistoria == null)
            laskentahistoria = new Laskentahistoria();
        return laskentahistoria.kaikkiRivit(kaanteinenJarjestys);
    }
    
    public void setLaskentahistoria(Laskentahistoria lh) {
        laskentahistoria = lh;
    }
    
    /**
    * Muuttaa kaavan lauseketta
    * 
    * @param kaava uusi lauseke
    */
    public void muutaKaavaa(String kaava) {
        this.kaava = kaava;
        tarkistaKaavanEheys();
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
    * Käy läpi ja mahdollisesti korjaa vioittuneen kaavan
    */
    public void tarkistaKaavanEheys() {
        String[] osiinJaettuKaava = (" "+kaava+" ").split("_");
        if (osiinJaettuKaava.length % 2 == 0)
            kaava = kaava + "_";
    }
    
    /**
    * Etsii kaavassa esiintyvät muuttujat
    *
    * @return lista muuttujista
    */
    public ArrayList<String> haeMuuttujat() {
        ArrayList<String> muuttujat = new ArrayList<>();
        String[] osiinJaettuKaava = kaava.split("_");
        for (int i = 0; i < osiinJaettuKaava.length; ++i) {
            if (i % 2 == 1 && !muuttujat.contains(osiinJaettuKaava[i])
                    && !osiinJaettuKaava[i].isEmpty())
                muuttujat.add(osiinJaettuKaava[i]);
        }
        return muuttujat;
    }
    
    /**
     * Sijoittaa annetut parametrit, jos niitä on yhtä paljon kuin muuttujia
     *
     * @param   parametrit   Kaavaan syötetyt parametrit
     * @return lauseke johon on sijoitettu parametrit
     */
    public String sijoitaParametrit(ArrayList<String> parametrit) {
        ArrayList<String> muuttujat = haeMuuttujat();
        if (parametrit.size() != muuttujat.size())
            return "Virhe";
        String sijoitettuKaava = kaava;
        for (int i = 0; i < parametrit.size(); ++i) {
            sijoitettuKaava = sijoitettuKaava.replaceAll("_"+muuttujat.get(i)+"_", parametrit.get(i));
        }
        return sijoitettuKaava;
    }
    
    /**
    * Laskee lausekkeen arvon ja lisää sen historiaan
    * 
    * @param parametrit lausekkeeseen syötettävät parametrit
    * 
    * @return lukuarvo, joka vastaa lauseketta
    */
    public String laske(ArrayList<String> parametrit) {
        String tulos = Laskin.laske(sijoitaParametrit(parametrit));
        if (laskentahistoria == null) {
            laskentahistoria = new Laskentahistoria();
        }
        laskentahistoria.lisaaRivi(parametrit, tulos);
        return tulos;
    }
    
    @Override
    public String toString() {
        return nimi+": "+kaava;
    }
}

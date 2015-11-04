package kaavamuistio;

import java.util.ArrayList;

class Kaava {
    private String kaava;
    
    public Kaava(String kaava) {
        this.kaava = kaava;
        tarkistaKaavanEheys();
    }
    
    public String getKaava() {
        return kaava;
    }
    
    /**
    * Metodi muuttaa kaavaa
    * 
    * @param kaava Uusi kaava
    */
    public void muutaKaavaa(String kaava) {
        this.kaava = kaava;
        tarkistaKaavanEheys();
    }
    
    /**
    * Metodi tarkistaa ja mahdollisesti korjaa vioittuneen kaavan.
    * Tälle voi löytyä vielä parempaakin käyttöä. :D
    */
    public void tarkistaKaavanEheys() {
        String[] osiinJaettuKaava = (" "+kaava+" ").split("_");
        if (osiinJaettuKaava.length % 2 == 0)
            kaava = kaava + "_";
    }
    
    /**
    * Metodi etsii kaavassa esiintyvät muuttujat
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
    * Metodi sijoittaa annetut parametrit, jos niitä on yhtä paljon
    * kuin muuttujia
    *
    * @param   parametrit   Kaavaan syötetyt parametrit
    * 
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
    * Metodi laskee lausekkeen arvon
    * 
    * @return lauseke johon on sijoitettu parametrit
    */
    public String laske() {
        return "";
    }
}

package kaavamuistio.logiikka;

import java.util.ArrayList;

public class Lauseke {
    private String lauseke;
    
    private ArrayList<String> muuttujat;
    
    public Lauseke(String lauseke) {
        this.lauseke = lauseke;
        tarkistaEheys();
    }
    
    public String getLauseke() {
        return lauseke;
    }
    
    public ArrayList<String> getMuuttujat() {
        return muuttujat;
    }
    
    public void muuta(String lause) {
        lauseke = lause;
        tarkistaEheys();
    }
    
    private void tarkistaEheys() {
        String[] osiinJaettuKaava = (" " + lauseke + " ").split("_");
        if (osiinJaettuKaava.length % 2 == 0)
            lauseke = lauseke + "_";
        selvitaMuuttujat();
    }
    
    private void selvitaMuuttujat() {
        muuttujat = new ArrayList<>();
        String[] osiinJaettuKaava = lauseke.split("_");
        for (int i = 0; i < osiinJaettuKaava.length; ++i) {
            if (i % 2 == 1 && !muuttujat.contains(osiinJaettuKaava[i])
                    && !osiinJaettuKaava[i].isEmpty())
                muuttujat.add(osiinJaettuKaava[i]);
        }
    }
    
    /**
     * Sijoittaa annetut parametrit, jos niitä on yhtä paljon kuin muuttujia
     *
     * @param   parametrit   Kaavaan syötetyt parametrit
     * @return lauseke johon on sijoitettu parametrit
     */
    public String sijoitaParametrit(ArrayList<String> parametrit) {
        if (parametrit.size() != muuttujat.size())
            return "Virhe";
        String sijoitettuKaava = lauseke;
        for (int i = 0; i < parametrit.size(); ++i) {
            sijoitettuKaava = sijoitettuKaava.replaceAll("_" + muuttujat.get(i)
                    + "_", parametrit.get(i));
        }
        return sijoitettuKaava;
    }
}

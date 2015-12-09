package kaavamuistio.logiikka;

import java.util.ArrayList;
import kaavamuistio.palvelut.Laskin;

/**
 * Luokka tarjoaa tavan hallita listaa kaavoista helposti
 */
public class Kaavamuistio {
    private ArrayList<Kaava> kaavat;
    
    /**
     * Luo uuden kaavamuistion, lisäksi alustaa laskimen tarvitsemat komponentit
     */
    public Kaavamuistio() {
        kaavat = new ArrayList<>();
        Laskin laskin = new Laskin();
    }
    
    /**
    * Hakee kaavojen indeksit
    *
    * @param   haku Pitää sisältyä nimeen
    * 
    * @return listan indeksit, joihin haku osuu
    */
    public ArrayList<Integer> kaavojenIndeksit(String haku) {
        ArrayList<Integer> lista = new ArrayList<>();
        int i = 0;
        for (Kaava kaava : kaavat) {
            if (kaava.sisaltaakoNimi(haku))
                lista.add(i);
            ++i;
        }
        return lista;
    }
    
    /**
    * Hakee kaavojen nimet
    *
    * @param   haku Pitää sisältyä nimeen
    * 
    * @return Palauttaa listan nimistä, joihin haku osuu
    */
    public ArrayList<String> kaavojenNimet(String haku) {
        ArrayList<String> lista = new ArrayList<>();
        for (Kaava kaava : kaavat) {
            if (kaava.sisaltaakoNimi(haku))
                lista.add(kaava.getNimi());
        }
        return lista;
    }
    
    /**
    * Palauttaa kaavan
    *
    * @param   indeksi Kaavan indeksi muistiossa
    * 
    * @return palauttaa indeksiä vastaavan kaavan
    */
    public Kaava haeKaava(int indeksi) {
        if (indeksi > -1 && indeksi < kaavat.size())
            return kaavat.get(indeksi);
        return null;
    }
    
    /**
    * Palauttaa kaavan
    *
    * @param   nimi Kaavan nimi muistiossa
    * 
    * @return palauttaa nimeä vastaavan kaavan
    */
    public Kaava haeKaava(String nimi) {
        return haeKaava(kaavanIndeksi(nimi));
    }
    
    /**
    * Palauttaa kaavan indeksin muistiossa
    *
    * @param   nimi Kaavan nimi
    * 
    * @return palauttaa nimeä vastaavan kaavan indeksin
    */
    public int kaavanIndeksi(String nimi) {
        for (int i = 0; i < kaavat.size(); ++i) {
            if (kaavat.get(i).onkoSamaNimi(nimi))
                return i;
        }
        return -1;
    }
    
    /**
    * Lisää kaavan muistioon, jos sen nimistä ei vielä ole
    *
    * @param   nimi Kaavan nimi
    * @param   kaava Kaava
    * 
    * @return true/false onnistumisesta riippuen
    */
    public boolean lisaaKaava(String nimi, String kaava) {
        int i = kaavanIndeksi(nimi);
        if (i == -1) {
            kaavat.add(new Kaava(nimi, kaava));
            return true;
        }
        kaavat.get(i).muutaKaavaa(kaava);
        return false;
    }
    
    /**
    * Lisää kaavan muistioon
    * Tätä käytetään lähinnä muistiota levyltä ladatessa
    * Ei tarkista onko kaava jo olemassa
    *
    * @param   kaava
    */
    public void lisaaKaava(Kaava kaava) {
        kaavat.add(kaava);
    }
    
    /**
    * Poistaa kaavan, jos sen niminen on muistiossa
    *
    * @param   nimi Kaavan nimi
    * 
    * @return true/false onnistumisesta riippuen
    */
    public boolean poistaKaava(String nimi) {
        int i = kaavanIndeksi(nimi);
        if (i != -1) {
            kaavat.remove(i);
            return true;
        }
        return false;
    }
    
    /**
    * Muuttaa muistiossa olevan kaavan lauseketta
    *
    * @param   nimi Kaavan nimi
    * @param   kaava Uusi lauseke
    * 
    * @return true jos kaava on olemassa, false jos ei sen vuoksi voi muuttaa
    */
    public boolean muutaKaava(String nimi, String kaava) {
        int i = kaavanIndeksi(nimi);
        if (i > -1) {
            kaavat.get(i).muutaKaavaa(kaava);
            return true;
        }
        return false;
    }
    
    /**
    * Muuttaa muistiossa olevan kaavan nimeä
    *
    * @param   nimi Kaavan nykyinen nimi
    * @param   uusiNimi Kaavan uusi nimi
    * 
    * @return true jos kaava on olemassa, false jos ei sen vuoksi voi muuttaa
    */
    public boolean muutaKaavanNimi(String nimi, String uusiNimi) {
        int i = kaavanIndeksi(nimi);
        if (i > -1) {
            kaavat.get(i).muutaNimi(uusiNimi);
            return true;
        }
        return false;
    }
}

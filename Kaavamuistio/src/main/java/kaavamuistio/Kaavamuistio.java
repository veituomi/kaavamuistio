package kaavamuistio;

import java.util.ArrayList;

public class Kaavamuistio {
    private ArrayList<Kaava> kaavat;
    private Laskin laskin;
    
    public Kaavamuistio() {
        kaavat = new ArrayList<>();
        laskin = new Laskin();
    }
    
    /**
    * Metodi hakee kaavat
    *
    * @param   haku Pitää sisältyä nimeen
    * 
    * @return Palauttaa listan indekseistä, joihin haku osuu
    */
    public ArrayList<Integer> kaavojenIndeksit(String haku) {
        ArrayList<Integer> lista = new ArrayList<>();
        int i = 0;
        for (Kaava kaava : kaavat) {
            if (kaava.getNimi().toLowerCase().contains(haku.toLowerCase()))
                lista.add(i);
            ++i;
        }
        return lista;
    }
    
    /**
    * Metodi hakee kaavan
    *
    * @param   indeksi Kaavan indeksi muistiossa
    * 
    * @return palauttaa indeksiä vastaavan kaavan
    */
    public Kaava haeKaava(int indeksi) {
        if (indeksi > -1 && indeksi < kaavat.size())
            return kaavat.get(indeksi);
        return new Kaava("Ei kaavaa", "E=mc^2");
    }
    
    /**
    * Metodi palauttaa kaavan indeksin
    *
    * @param   nimi Kaavan nimi
    * 
    * @return palauttaa nimeä vastaavan kaavan indeksin
    */
    public int kaavanIndeksi(String nimi) {
        int i = 0;
        for (Kaava k : kaavat) {
            if (k.getNimi() == nimi)
                return i;
            ++i;
        }
        return -1;
    }
    
    /**
    * Metodi lisää kaavan muistioon
    *
    * @param   nimi Kaavan nimi
    * @param   kaava Kaava
    * 
    * @return true/false onnistumisesta riippuen
    */
    public boolean lisaaKaava(String nimi, String kaava) {
        if (kaavanIndeksi(nimi) == -1) {
            kaavat.add(new Kaava(nimi, kaava));
            return true;
        }
        return false;
    }
    
    /**
    * Metodi muuttaa muistiossa olevaa kaavaa
    *
    * @param   nimi Kaavan nimi
    * @param   kaava Uusi kaava
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
    * Metodi muuttaa muistiossa olevaa kaavaa
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

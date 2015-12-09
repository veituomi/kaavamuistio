package kaavamuistio.tallennus;

import kaavamuistio.logiikka.Kaavamuistio;
import kaavamuistio.logiikka.Kaava;
import kaavamuistio.logiikka.Laskentahistoria;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

/**
 * Luokan tehtävänä on avata ja tallentaa kaavamuistiota
 */
public class Tietovarasto {
    /**
    * Avaa kaavamuistion levyltä
    *
    * @param   hakemistonNimi sijainti, johon muistio on tallennettu
    * 
    * @return avattu kaavamuistio
    */
    public static Kaavamuistio avaaKaavamuistio(String hakemistonNimi) {
        Kaavamuistio kaavamuistio = new Kaavamuistio();
        lisaaKaavat(kaavamuistio, hakemistonNimi);
        return kaavamuistio;
    }
    
    /**
     * Lisää kaavat annettuun kaavamuistioon
     * @param kaavamuistio
     * @param hakemistonNimi sijainti, jossa tiedon pitäisi olla
     */
    public static void lisaaKaavat(Kaavamuistio kaavamuistio, String hakemistonNimi) {
        Scanner lukija = luoTiedostonLukija(hakemistonNimi + "/listaus.txt");
        if (lukija == null) {
            return;
        }
        while (lukija.hasNextLine()) {
            String kaavanTunniste = lukija.nextLine();
            Kaava kaava = lueKaava(hakemistonNimi, kaavanTunniste);
            Laskentahistoria laskentahistoria = lueLaskentahistoria(hakemistonNimi, kaavanTunniste);
            if (kaava != null) {
                kaava.setLaskentahistoria(laskentahistoria);
                kaavamuistio.lisaaKaava(kaava);
            }
        }
    }
    
    /**
     * Yrittää lukea kaavan tiedot levyltä
     * @param hakemistonNimi Sijainti, jossa tiedon pitäisi olla
     * @param kaavanTunniste 
     * @return 
     */
    public static Kaava lueKaava(String hakemistonNimi, String kaavanTunniste) {
        Scanner lukija = luoTiedostonLukija(hakemistonNimi + "/" + kaavanTunniste + "/kaava.txt");
        if (lukija == null) {
            return null;
        }
        String nimi = "";
        if (lukija.hasNextLine()) {
            nimi = lukija.nextLine();
        }
        String lauseke = lueLoputRivit(lukija);
        return new Kaava(nimi, lauseke);
    }
    
    /**
     * Yrittää lukea laskentahistorian levyltä
     * @param hakemistonNimi Sijainti, jossa tiedon pitäisi olla
     * @param kaavanTunniste 
     * @return 
     */
    public static Laskentahistoria lueLaskentahistoria(String hakemistonNimi, String kaavanTunniste) {
        Scanner lukija = luoTiedostonLukija(hakemistonNimi + "/" + kaavanTunniste + "/historia.txt");
        if (lukija == null) {
            return null;
        }
        Laskentahistoria laskentahistoria = new Laskentahistoria();
        while (lukija.hasNextLine()) {
            laskentahistoria.lisaaRivi(lukija.nextLine());
        }
        return laskentahistoria;
    }
    
    /**
    * Yrittää tallentaa kaavamuistion levylle
    *
    * @param   hakemistonNimi   Levylle tallennettavan hakemiston nimi
    * @param   kaavamuistio   Levylle tallennettava kaavamuistio
    */
    public static void tallennaKaavamuistio(String hakemistonNimi, Kaavamuistio kaavamuistio) {
        try {
            new File(hakemistonNimi).mkdir();
            PrintWriter printWriter = new PrintWriter(hakemistonNimi + "/listaus.txt", "UTF-8");
            for (int indeksi : kaavamuistio.kaavojenIndeksit("")) {
                Kaava kaava = kaavamuistio.haeKaava(indeksi);
                String kaavanTunniste = indeksi + "";
                printWriter.println(kaavanTunniste);
                tallennaKaava(kaava, hakemistonNimi, kaavanTunniste);
            }
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) { }
    }
    
    private static void tallennaKaava(Kaava kaava, String hakemistonNimi, String kaavanTunniste) {
        try {
            new File(hakemistonNimi + "/" + kaavanTunniste).mkdir();
            
            PrintWriter printWriter = new PrintWriter(hakemistonNimi + "/"
                + kaavanTunniste + "/kaava.txt", "UTF-8");
            printWriter.println(kaava.getNimi() + "\n" + kaava.getKaava());
            printWriter.close();
            
            printWriter = new PrintWriter(hakemistonNimi + "/" + kaavanTunniste + "/historia.txt", "UTF-8");
            printWriter.print(kaava.getLaskentahistoria(false));
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) { }
    }
    
    private static Scanner luoTiedostonLukija(String polku) {
        try {
            return new Scanner(new FileInputStream(polku), "UTF-8");
        } catch (FileNotFoundException e) {
            return null;
        }
    }
    
    private static String lueLoputRivit(Scanner lukija) {
        String loput = "";
        while (lukija.hasNextLine()) {
            loput += lukija.nextLine();
            if (lukija.hasNextLine()) {
                loput += "\n";
            }
        }
        return loput;
    }
}

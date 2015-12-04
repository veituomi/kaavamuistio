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
        try {
            Scanner lukija = new Scanner(new FileInputStream(hakemistonNimi+"/listaus.txt"), "UTF-8");
            while (lukija.hasNextLine()) {
                String kaavanTunniste = lukija.nextLine();
                Kaava kaava = lueKaava(hakemistonNimi, kaavanTunniste);
                Laskentahistoria laskentahistoria = lueLaskentahistoria(hakemistonNimi, kaavanTunniste);
                if (kaava != null) {
                    kaava.setLaskentahistoria(laskentahistoria);
                    kaavamuistio.lisaaKaava(kaava);
                }
            }
        } catch (FileNotFoundException e) {}
    }
    
    /**
     * Yrittää lukea kaavan tiedot levyltä
     * @param hakemistonNimi Sijainti, jossa tiedon pitäisi olla
     * @param kaavanTunniste 
     * @return 
     */
    public static Kaava lueKaava(String hakemistonNimi, String kaavanTunniste) {
        try {
            Scanner lukija = new Scanner(new FileInputStream(hakemistonNimi+"/"+kaavanTunniste+"/kaava.txt"), "UTF-8");
            String nimi = "", lauseke = "";
            if (lukija.hasNextLine()) {
                nimi = lukija.nextLine();
            }
            while (lukija.hasNextLine()) {
                lauseke += lukija.nextLine();
                if (lukija.hasNextLine())
                    lauseke += "\n";
            }
            return new Kaava(nimi, lauseke);
        } catch (FileNotFoundException e) {}
        return null;
    }
    
    /**
     * Yrittää lukea laskentahistorian levyltä
     * @param hakemistonNimi Sijainti, jossa tiedon pitäisi olla
     * @param kaavanTunniste 
     * @return 
     */
    public static Laskentahistoria lueLaskentahistoria(String hakemistonNimi, String kaavanTunniste) {
        try {
            Scanner lukija = new Scanner(new FileInputStream(hakemistonNimi+"/"+kaavanTunniste+"/historia.txt"), "UTF-8");
            Laskentahistoria laskentahistoria = new Laskentahistoria();
            while (lukija.hasNextLine()) {
                laskentahistoria.lisaaRivi(lukija.nextLine());
            }
            return laskentahistoria;
        } catch (FileNotFoundException e) {}
        return null;
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
            PrintWriter printWriter = new PrintWriter(hakemistonNimi+"/listaus.txt", "UTF-8");
            for (int indeksi : kaavamuistio.kaavojenIndeksit("")) {
                Kaava kaava = kaavamuistio.haeKaava(indeksi);
                String kaavanTunniste = indeksi+"";
                printWriter.println(kaavanTunniste);
                tallennaKaava(kaava, hakemistonNimi, kaavanTunniste);
            }
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {}
    }
    
    private static void tallennaKaava(Kaava kaava, String hakemistonNimi, String kaavanTunniste) {
        try {
            new File(hakemistonNimi+"/"+kaavanTunniste).mkdir();
            
            PrintWriter printWriter = new PrintWriter(hakemistonNimi+"/"+kaavanTunniste+"/kaava.txt", "UTF-8");
            printWriter.println(kaava.getNimi());
            printWriter.println(kaava.getKaava());
            printWriter.close();
            
            printWriter = new PrintWriter(hakemistonNimi+"/"+kaavanTunniste+"/historia.txt", "UTF-8");
            printWriter.print(kaava.getLaskentahistoria(false));
            printWriter.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {}
    }
}

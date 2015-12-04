package kaavamuistio;

/**
 * Luokka toimii laskurina, joka käy kokonaisluvut tietyltä väliltä läpi
 */
public class JuoksevaLaskuri {
    private int tilanne;
    private final int loppu;
    
    private final int valmis;
    private boolean onLopussa = false;
    
    /**
     * @param aloitus
     * @param lopetus
     * @param jarjestys jos true, aloitus ja lopetus ovat keskenään käänteiset
     */
    public JuoksevaLaskuri(int aloitus, int lopetus, boolean jarjestys) {
        if (jarjestys) {
            tilanne = lopetus;
            loppu = aloitus;
        } else {
            tilanne = aloitus;
            loppu = lopetus;
        }
        valmis = -1;
    }
    
    public JuoksevaLaskuri(int aloitus, int lopetus, int arvoValmiina) {
        tilanne = aloitus;
        loppu = lopetus;
        valmis = arvoValmiina;
    }
    
    /**
     * Siirtyy askeleen eteenpäin
     * @return ennen askelta oleva laskurin arvo
     */
    public int seuraava() {
        if (onLopussa) {
            return valmis;
        }
        if (tilanne < loppu) {
            return tilanne++;
        } else if (tilanne > loppu) {
            return tilanne--;
        } else {
            onLopussa = true;
            return loppu;
        }
    }
}

package kaavamuistio;

/**
 * Luokka toimii laskurina, joka käy kokonaisluvut tietyltä väliltä läpi
 */
public class JuoksevaLaskuri {
    private int tilanne;
    private final int loppu;
    
    private boolean onLopussa = false;
    
    /**
     * Luo uuden laskurin, joka menee joko aloituksesta lopetukseen tai toisinpäin
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
    }
    
    /**
     * Siirtyy askeleen eteenpäin
     * @param valmis valmiin laskurin arvo
     * @return ennen askelta oleva laskurin arvo tai valmiin laskurin arvo, jos lopussa
     */
    public int seuraava(int valmis) {
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
    
    /**
     * Siirtyy askeleen eteenpäin
     * @return ennen askelta oleva laskurin arvo tai -1, jos lopussa
     */
    public int seuraava() {
        return this.seuraava(-1);
    }
}

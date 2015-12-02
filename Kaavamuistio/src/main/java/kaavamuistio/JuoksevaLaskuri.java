package kaavamuistio;

public class JuoksevaLaskuri {
    private int tilanne;
    private int loppu;
    
    private int valmis;
    private boolean onLopussa = false;
    
    public JuoksevaLaskuri(int t, int l, boolean j) {
        if (j) {
            tilanne = l;
            loppu = t;
        } else {
            tilanne = t;
            loppu = l;
        }
        valmis = -1;
    }
    
    public JuoksevaLaskuri(int t, int l, int v) {
        tilanne = t;
        loppu = l;
        valmis = v;
    }
    
    public int seuraava() {
        if (onLopussa) {
            return valmis;
        }
        int palautettava = tilanne;
        if (tilanne < loppu) {
            ++tilanne;
        } else if (tilanne > loppu) {
            --tilanne;
        } else {
            onLopussa = true;
        }
        return palautettava;
    }
}

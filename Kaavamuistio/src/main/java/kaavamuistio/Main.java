package kaavamuistio;

import kaavamuistio.kayttoliittyma.Kayttoliittyma;
import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        Kaavamuistio kaavamuistio = new Kaavamuistio();
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma(kaavamuistio);
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}

package javalab.kaavamuistio;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {
        // Luodaan käyttöliittymä ja avataan se
        
        Kayttoliittyma kayttoliittyma = new Kayttoliittyma();
        SwingUtilities.invokeLater(kayttoliittyma);
    }
    
}

package kaavamuistio.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kaavamuistio.logiikka.Kaavamuistio;
import kaavamuistio.Tietovarasto;

/**
 * Luokka toimii käyttöliittymänä ohjelmassa
 */
public class Kayttoliittyma implements Runnable {
    
    private javax.swing.JTextField kaavanNimiTextField;
    private javax.swing.JTextArea kaavanSisaltoTextArea;
    private javax.swing.JButton kaavanLisaysButton;
    private javax.swing.JTextField hakuTextField;
    private javax.swing.JList kaavatList;

    private JFrame frame;
    
    private final Kaavamuistio kaavamuistio;

    public Kayttoliittyma() {
        kaavamuistio = Tietovarasto.avaaKaavamuistio("tallennettuKaavamuistio");
    }
    
    /**
     * Metodi luo ikkunan ja sen sisältämät komponentit
     */
    @Override
    public void run() {
        // Luodaan ikkuna
        frame = new JFrame("Kaavamuistio");
        frame.setPreferredSize(new Dimension(640, 480));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(null);
        
        javax.swing.JButton tallennaButton = new javax.swing.JButton("Tallenna");
        tallennaButton.setBounds(200, 200, 80, 25);
        tallennaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Tietovarasto.tallennaKaavamuistio("tallennettuKaavamuistio", kaavamuistio);
            }
        });
        
        kaavanNimiTextField = new javax.swing.JTextField();
        kaavanNimiTextField.setBounds(50, 50, 80, 25);
        
        kaavanSisaltoTextArea = new javax.swing.JTextArea();
        kaavanSisaltoTextArea.setBounds(250, 100, 120, 25);
        
        kaavanLisaysButton = new javax.swing.JButton("Lisää");
        kaavanLisaysButton.setBounds(150, 50, 120, 25);
        kaavanLisaysButton.addActionListener(new KaavanLisaysActionListener());
        
        hakuTextField = new javax.swing.JTextField();
        hakuTextField.setBounds(50, 100, 120, 25);
        hakuTextField.getDocument().addDocumentListener(new HakuDocumentListener());
        
        kaavatList = new javax.swing.JList(kaavamuistio.kaavojenNimet("").toArray());
        kaavatList.setBounds(100, 130, 80, 100);
        kaavatList.addListSelectionListener(new KaavanValintaSelectionListener());
        
        container.add(kaavanNimiTextField);
        container.add(kaavanSisaltoTextArea);
        container.add(kaavanLisaysButton);
        container.add(hakuTextField);
        container.add(kaavatList);
        
        container.add(tallennaButton);
    }
    
    private class KaavanLisaysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Ensin yritetään lisätä, jos on jo olemassa niin muokataan kaavaa
            if (!kaavamuistio.lisaaKaava(kaavanNimiTextField.getText(), kaavanSisaltoTextArea.getText()))
                kaavamuistio.muutaKaava(kaavanNimiTextField.getText(), kaavanSisaltoTextArea.getText());
            paivitaKaavojenLista();
        }
    }
    
    private class HakuDocumentListener implements DocumentListener {
        @Override
        public void changedUpdate(DocumentEvent e) { }

        @Override
        public void insertUpdate(DocumentEvent e) {
            paivitaKaavojenLista();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            paivitaKaavojenLista();
        }
    }
    
    private void paivitaKaavojenLista() {
        String haettava = hakuTextField.getText();
        kaavatList.removeAll();
        kaavatList.setListData(kaavamuistio.kaavojenNimet(haettava).toArray());
    }
    
    private class KaavanValintaSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            String nimi = (String)kaavatList.getSelectedValue();
            if (nimi != null && kaavamuistio.kaavanIndeksi(nimi) != -1) {
                kaavanNimiTextField.setText(nimi);
                kaavanSisaltoTextArea.setText(
                        kaavamuistio.haeKaava(
                            kaavamuistio.kaavanIndeksi(nimi)
                        ).getKaava());
            }
        }
        
    }

    public JFrame getFrame() {
        return frame;
    }
}
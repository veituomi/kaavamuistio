package kaavamuistio.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kaavamuistio.logiikka.Kaavamuistio;
import kaavamuistio.Tietovarasto;
import kaavamuistio.logiikka.Kaava;

/**
 * Luokka toimii käyttöliittymänä ohjelmassa
 */
public class Kayttoliittyma implements Runnable {
    
    private JTextField kaavanNimiTextField;
    private JTextArea kaavanSisaltoTextArea;
    private JButton kaavanLisaysButton;
    private JTextField hakuTextField;
    private JList kaavatList;
    
    private JButton laskeButton;
    private JTextArea kaavanHistoriaTextArea;
    
    private JPanel parametrikentatPanel;
    private ArrayList<JTextField> parametriTextFieldLista = new ArrayList<>();

    private JFrame frame;
    
    private final Kaavamuistio kaavamuistio;
    
    private Kaava valittuKaava;

    public Kayttoliittyma() {
        kaavamuistio = Tietovarasto.avaaKaavamuistio("kaavamuistio");
    }
    
    /**
     * Metodi luo ikkunan ja sen sisältämät komponentit
     */
    @Override
    public void run() {
        frame = new JFrame("Kaavamuistio");
        frame.setPreferredSize(new Dimension(640, 480));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(null);
        
        /*
        * Tämä on myöhemmin muutettava automaattiseksi
        */
        javax.swing.JButton tallennaButton = new javax.swing.JButton("Tallenna");
        tallennaButton.setBounds(200, 200, 80, 25);
        tallennaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tallenna();
            }
        });
        
        kaavanNimiTextField = new JTextField();
        kaavanNimiTextField.setBounds(50, 50, 80, 25);
        
        kaavanSisaltoTextArea = new JTextArea();
        kaavanSisaltoTextArea.setBounds(250, 100, 120, 25);
        
        kaavanLisaysButton = new JButton("Lisää");
        kaavanLisaysButton.setBounds(150, 50, 120, 25);
        kaavanLisaysButton.addActionListener(new KaavanLisaysActionListener());
        
        hakuTextField = new JTextField();
        hakuTextField.setBounds(50, 100, 120, 25);
        hakuTextField.getDocument().addDocumentListener(new HakuDocumentListener());
        
        kaavatList = new JList(kaavamuistio.kaavojenNimet("").toArray());
        kaavatList.setBounds(100, 130, 80, 100);
        kaavatList.addListSelectionListener(new KaavanValintaSelectionListener());
        
        parametrikentatPanel = new JPanel();
        parametrikentatPanel.setLayout(new GridBagLayout());
        
        JScrollPane parametrienRullaus = new JScrollPane(parametrikentatPanel);
        parametrienRullaus.setBounds(400,10,200,190);
        
        laskeButton = new JButton("Laske");
        laskeButton.setBounds(400,200,100,50);
        laskeButton.addActionListener(new KaavanLaskuActionListener());
        
        kaavanHistoriaTextArea = new JTextArea();
        kaavanHistoriaTextArea.setBounds(400,250,200,200);
        
        container.add(kaavanNimiTextField);
        container.add(kaavanSisaltoTextArea);
        container.add(kaavanLisaysButton);
        container.add(hakuTextField);
        container.add(kaavatList);
        
        container.add(parametrienRullaus);
        container.add(laskeButton);
        container.add(kaavanHistoriaTextArea);
        
        container.add(tallennaButton);
    }
 
    private void paivitaKaavojenLista() {
        String haettava = hakuTextField.getText();
        kaavatList.removeAll();
        kaavatList.setListData(kaavamuistio.kaavojenNimet(haettava).toArray());
    }
    
    private void valitseKaava(String nimi) {
        int kaavanIndeksi = kaavamuistio.kaavanIndeksi(nimi);
        if (kaavanIndeksi != -1) {
            Kaava kaava = kaavamuistio.haeKaava(kaavanIndeksi);
            valittuKaava = kaava;
            kaavanHistoriaTextArea.setText(kaava.getLaskentahistoria().kaikkiRivit());
            muutaParametrikentat(kaava.haeMuuttujat());
        }
    }
    
    private void poistaKaava(String nimi) {
        kaavamuistio.poistaKaava(nimi);
        paivitaKaavojenLista();
    }
    
    private void muutaParametrikentat(ArrayList<String> muuttujat) {
        parametrikentatPanel.removeAll();
        parametriTextFieldLista.clear();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL; c.ipady = 20;
        int i = 0;
        for (String muuttuja : muuttujat) {
            c.gridy = i; c.gridx = 0; c.ipadx = 50;
            JLabel muuttujanNimi = new JLabel(muuttuja);
            parametrikentatPanel.add(muuttujanNimi, c);
            c.gridx = 1; c.ipadx = 80;
            JTextField parametrikentta = new JTextField();
            parametrikentatPanel.add(parametrikentta, c);
            parametriTextFieldLista.add(parametrikentta);
            ++i;
        }
        parametrikentatPanel.updateUI();
    }
    
    private void laske() {
        ArrayList<String> parametrit = new ArrayList<>();
        for (JTextField parametriTextField : parametriTextFieldLista) {
            parametrit.add(parametriTextField.getText());
        }
        valittuKaava.laske(parametrit);
        kaavanHistoriaTextArea.setText(valittuKaava.getLaskentahistoria().kaikkiRivit());
    }
    
    private void tallenna() {
        Tietovarasto.tallennaKaavamuistio("kaavamuistio", kaavamuistio);
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
                valitseKaava(nimi);
            }
        }   
    }
       
    private class KaavanLisaysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nimi = kaavanNimiTextField.getText();
            String sisalto = kaavanSisaltoTextArea.getText();
            if (!kaavamuistio.lisaaKaava(nimi, sisalto))
                kaavamuistio.muutaKaava(nimi, sisalto);
            paivitaKaavojenLista();
            valitseKaava(nimi);
        }
    }
    
    private class KaavanLaskuActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            laske();
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
    

    public JFrame getFrame() {
        return frame;
    }
}
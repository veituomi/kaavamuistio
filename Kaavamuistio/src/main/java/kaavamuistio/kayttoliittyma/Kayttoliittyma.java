package kaavamuistio.kayttoliittyma;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import kaavamuistio.logiikka.Kaavamuistio;
import kaavamuistio.tallennus.Tietovarasto;
import kaavamuistio.logiikka.Kaava;

/**
 * Luokka toimii käyttöliittymänä ohjelmassa
 */
public class Kayttoliittyma implements Runnable {
    
    private JTextField kaavanNimiTextField;
    private JTextArea kaavanSisaltoTextArea;
    private JButton kaavanLisaysButton;
    private JButton kaavanPoistoButton;
    private JTextField hakuTextField;
    private JList kaavatList;
    
    private JButton laskeButton;
    private JTextArea kaavanHistoriaTextArea;
    
    private JPanel parametrikentatPanel;
    private ArrayList<JTextField> parametriTextFieldLista = new ArrayList<>();

    private JFrame frame;
    
    private final Kaavamuistio kaavamuistio;
    
    private Kaava valittuKaava;

    /**
     * Luo uuden käyttöliittymän ja avaa sitä varten tallennetun kaavamuistion
     */
    public Kayttoliittyma() {
        kaavamuistio = Tietovarasto.avaaKaavamuistio("./kaavamuistio");
    }
    
    /**
     * Luo ikkunan ja sen sisältämät komponentit
     */
    @Override
    public void run() {
        frame = new JFrame("Kaavamuistio");
        frame.setPreferredSize(new Dimension(640, 480));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                tallenna();
                System.exit(0);
            }
        });

        luoKomponentit(frame.getContentPane());

        frame.pack();
        frame.setVisible(true);
    }

    private void luoKomponentit(Container container) {
        container.setLayout(null);
        
        lisaaKaavanMuokkausKomponentit(container);
        lisaaKomponentitKaavanValintaan(container);
        lisaaKomponentitKaavanKayttamiseen(container);
    }
 
    private void lisaaKaavanMuokkausKomponentit(Container container) {
        kaavanNimiTextField = new JTextField();
        kaavanNimiTextField.setBounds(10, 10, 150, 25);

        kaavanSisaltoTextArea = new JTextArea();
        JScrollPane kaavanSisallonRullaus = new JScrollPane(kaavanSisaltoTextArea);
        kaavanSisallonRullaus.setBounds(10, 45, 350, 120);

        kaavanLisaysButton = new JButton("Lisää");
        kaavanLisaysButton.setBounds(170, 10, 100, 25);
        kaavanLisaysButton.addActionListener(new KaavanLisaysActionListener());

        kaavanPoistoButton = new JButton("Poista");
        kaavanPoistoButton.setBounds(280, 10, 80, 25);
        kaavanPoistoButton.addActionListener(new KaavanPoistoActionListener());

        hakuTextField = new JTextField();
        hakuTextField.setBounds(60, 195, 300, 25);
        hakuTextField.getDocument().addDocumentListener(new HakuDocumentListener());

        container.add(kaavanNimiTextField);
        container.add(kaavanSisallonRullaus);
        container.add(kaavanLisaysButton);
        container.add(kaavanPoistoButton);
    }
    
    private void lisaaKomponentitKaavanValintaan(Container container) {
        JLabel label = new JLabel("Etsi");
        label.setBounds(15, 195, 40, 25);
        container.add(label);

        kaavatList = new JList(kaavamuistio.kaavojenNimet("").toArray());
        kaavatList.addListSelectionListener(new KaavanValintaSelectionListener());

        JScrollPane kaavalistanRullaus = new JScrollPane(kaavatList);
        kaavalistanRullaus.setBounds(10, 230, 350, 200);
        container.add(hakuTextField);
        container.add(kaavalistanRullaus);
    }
    
    private void lisaaKomponentitKaavanKayttamiseen(Container container) {
        parametrikentatPanel = new JPanel();
        parametrikentatPanel.setLayout(new GridBagLayout());

        JScrollPane parametrienRullaus = new JScrollPane(parametrikentatPanel);
        parametrienRullaus.setBounds(400, 10, 210, 155);

        laskeButton = new JButton("Laske");
        laskeButton.setBounds(400, 195, 210, 25);
        laskeButton.addActionListener(new KaavanLaskuActionListener());

        kaavanHistoriaTextArea = new JTextArea();
        JScrollPane historianRullaus = new JScrollPane(kaavanHistoriaTextArea);
        historianRullaus.setBounds(400, 230, 210, 200);

        container.add(parametrienRullaus);
        container.add(laskeButton);
        container.add(historianRullaus);
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
            kaavanHistoriaTextArea.setText(kaava.getLaskentahistoria(true));
            kaavanHistoriaTextArea.setCaretPosition(0);
            muutaParametrikentat(kaava.haeMuuttujat());
        }
    }
    
    private void muutaParametrikentat(ArrayList<String> muuttujat) {
        parametrikentatPanel.removeAll();
        parametriTextFieldLista.clear();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL; c.ipady = 5;
        int i = 0;
        for (String muuttuja : muuttujat) {
            c.gridy = i; c.gridx = 0; c.ipadx = 20;
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
        kaavanHistoriaTextArea.setText(valittuKaava.getLaskentahistoria(true));
        kaavanHistoriaTextArea.setCaretPosition(0);
    }
    
    private void tallenna() {
        Tietovarasto.tallennaKaavamuistio("./kaavamuistio", kaavamuistio);
    }
    
    private class KaavanValintaSelectionListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            String nimi = (String) kaavatList.getSelectedValue();
            if (nimi != null && kaavamuistio.kaavanIndeksi(nimi) != -1) {
                kaavanNimiTextField.setText(nimi);
                kaavanSisaltoTextArea.setText(
                        kaavamuistio.haeKaava(nimi).getKaava());
                valitseKaava(nimi);
            }
        }   
    }
       
    private class KaavanLisaysActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nimi = kaavanNimiTextField.getText();
            String sisalto = kaavanSisaltoTextArea.getText();
            kaavamuistio.lisaaKaava(nimi, sisalto);
            paivitaKaavojenLista();
            valitseKaava(nimi);
        }
    }
       
    private class KaavanPoistoActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nimi = kaavanNimiTextField.getText();
            kaavamuistio.poistaKaava(nimi);
            paivitaKaavojenLista();
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
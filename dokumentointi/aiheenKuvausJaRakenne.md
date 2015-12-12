**Aihe:** Kaavamuistio
Luodaan ohjelma, johon voi lisätä omia kaavoja tekstimuodossa. Lisäämisen jälkeen käyttäjän tarvitsee syöttää vain kaavan parametrit, ja ohjelma laskee tuloksen. Ohjelma tallentaa sekä kaavat että käyttäjän syöttämien parametrien historian automaattisesti.

**Käyttäjät:** Mielivaltainen useita kaavoja työskennellessään käyttävä henkilö

**Käyttäjän toiminnot:**
* uuden kaavan lisääminen
* kaavojen muokkaaminen
  * kaavojen nimeäminen
* kaavan käyttäminen halutuilla parametreilla
* kaavan laskuhistorian näyttäminen
* kaavan poistaminen

**Ohjelman rakenteen kuvaus**
Ohjelmassa on graafinen käyttöliittymä, joka hyödyntää pitkälti Kaavamuistion metodeja tiedon hallintaan mutta hakee osan tiedoista myös suoraan Kaavalta.

Kaavalla on aina oma Lauseke, mutta sille voidaan asettaa erikseen uusi Laskentahistoria esimerkiksi. Laskentahistorian voi saada Kaavalta vain tekstimuodossa.

Kaava käyttää Laskinta Lausekkeen ratkaisemiseen. Laskin on käytännössä Javascript-moottoria käyttävä luokka.

Tietovarasto huolehtii Kaavamuistion ja Kaavojen tallentamisesta sekä lukemisesta levyltä. Käyttöliittymä kutsuu sen metodeja käynnistyksen ja sulkemisen yhteydessä.

Lisäksi on luokkakaaviosta pois jätetty JuoksevaLaskuri, jolle annetaan ensimmäinen ja viimeinen lukuarvo, joiden väliset kokonaisluvut se palauttaa yksitellen järjestyksessä. Laskuri voi laskea myös vastakkaiseen suuntaan.
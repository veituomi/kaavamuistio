// Kaavan lisääminen
"käyttäjä"->+kayttoliittyma: KaavanLisaysActionListener.actionPerformed()
kayttoliittyma->+kaavamuistio: lisaaKaava(nimi, sisalto)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: -1
kaavamuistio->+*kaava: new Kaava(nimi, kaava)
kaava->-kaava: tarkistaKaavanEheys()
kaavamuistio->-kayttoliittyma: true

kayttoliittyma->+kayttoliittyma: paivitaKaavojenLista()
kayttoliittyma->+kaavamuistio: kaavojenNimet(haettava)
loop Kaikki muistion kaavat
    kaavamuistio->+kaava_x: getNimi()
    kaava_x->-kaavamuistio: nimi
end
kaavamuistio-->-kayttoliittyma: nimienLista
kayttoliittyma-->-kayttoliittyma:

kayttoliittyma->+kayttoliittyma: valitseKaava(nimi)
kayttoliittyma->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kayttoliittyma: 23
kayttoliittyma->+kaavamuistio: haeKaava(23)
kaavamuistio-->-kayttoliittyma: kaava
kayttoliittyma->+kaava: getLaskentahistoria()
kaava-->-kayttoliittyma: laskentahistoria
kayttoliittyma->+laskentahistoria: kaikkiRivit()
laskentahistoria-->-kayttoliittyma: kaikkiRivit
kayttoliittyma->+kaava: haeMuuttujat()
kaava-->-kayttoliittyma: muuttujat
kayttoliittyma-->-kayttoliittyma:

// Kaavan muokkaaminen (lisäys ei onnistunut)
"käyttäjä"->+kayttoliittyma: KaavanLisaysActionListener.actionPerformed()
kayttoliittyma->+kaavamuistio: lisaaKaava(nimi, sisalto)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: 57
kaavamuistio->kayttoliittyma: false
kayttoliittyma->kaavamuistio: muutaKaava(nimi, sisalto)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: 57
kaavamuistio->kaava: muutaKaavaa(sisalto)
kaavamuistio->kayttoliittyma: true

kayttoliittyma->+kayttoliittyma: paivitaKaavojenLista()
kayttoliittyma->+kaavamuistio: kaavojenNimet(haettava)
loop Kaikki muistion kaavat
    kaavamuistio->+kaava_x: getNimi()
    kaava_x->-kaavamuistio: nimi
end
kaavamuistio-->-kayttoliittyma: nimienLista
kayttoliittyma-->-kayttoliittyma:

kayttoliittyma->+kayttoliittyma: valitseKaava(nimi)
kayttoliittyma->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kayttoliittyma: 57
kayttoliittyma->+kaavamuistio: haeKaava(57)
kaavamuistio-->-kayttoliittyma: kaava
kayttoliittyma->+kaava: getLaskentahistoria()
kaava-->-kayttoliittyma: laskentahistoria
kayttoliittyma->+laskentahistoria: kaikkiRivit()
laskentahistoria-->-kayttoliittyma: kaikkiRivit
kayttoliittyma->+kaava: haeMuuttujat()
kaava-->-kayttoliittyma: muuttujat
kayttoliittyma-->-kayttoliittyma:

// Kaavan laskeminen
"käyttäjä"->+kayttoliittyma : laske()
kayttoliittyma->+valittuKaava: laske(parametrit)
valittuKaava->+valittuKaava: sijoitaParametrit(parametrit)
valittuKaava->+valittuKaava: haeMuuttujat()
valittuKaava-->-valittuKaava: muuttujat
valittuKaava-->-valittuKaava: sijoitettuLauseke
valittuKaava->+":Laskin": laske(sijoitettuLauseke)
":Laskin"-->-valittuKaava: tulos
valittuKaava->laskentahistoria: lisaaRivi(tulos, parametrit)
valittuKaava-->-kayttoliittyma: tulos
kayttoliittyma->+valittuKaava: getLaskentahistoria()
valittuKaava-->-kayttoliittyma: laskentahistoria
kayttoliittyma->+laskentahistoria: kaikkiRivit()
laskentahistoria-->-kayttoliittyma: kaikkiRivit
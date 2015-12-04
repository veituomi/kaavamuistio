// Kaavan lisääminen
"käyttäjä"->+kayttoliittyma: Kaavan tiedot syötetty ja painettu Lisää
kayttoliittyma->+kaavamuistio: lisaaKaava(nimi, sisalto)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: -1
kaavamuistio->+*kaava: new Kaava(nimi, sisalto)
kaava->-kaava: tarkistaKaavanEheys()
kaavamuistio-->-kayttoliittyma: true

kayttoliittyma->+kayttoliittyma: paivitaKaavojenLista()
kayttoliittyma->+kaavamuistio: kaavojenNimet(haettava)
loop Kaikki muistion kaavat
    kaavamuistio->+kaava_x: getNimi()
    kaava_x->-kaavamuistio: nimi
end
kaavamuistio-->-kayttoliittyma: nimienLista
kayttoliittyma-->-kayttoliittyma:

kayttoliittyma->+kayttoliittyma: valitseKaava(nimi)
kayttoliittyma->+kaavamuistio: haeKaava(nimi)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: 57
kaavamuistio-->-kayttoliittyma: kaava
kayttoliittyma->+kaava: getLaskentahistoria(true)
kaava->*laskentahistoria: new Laskentahistoria()
kaava->+laskentahistoria: kaikkiRivit(true)
laskentahistoria-->-kaava: kaikkiRivit
kaava-->-kayttoliittyma: kaikkiRivit
kayttoliittyma->+kaava: haeMuuttujat()
kaava-->-kayttoliittyma: muuttujat
kayttoliittyma-->-kayttoliittyma:
kayttoliittyma-->-käyttäjä: näkymä

// Kaavan muokkaaminen (samalla nimellä on jo kaava)
"käyttäjä"->+kayttoliittyma: Kaavan tiedot syötetty ja painettu Lisää
kayttoliittyma->+kaavamuistio: lisaaKaava(nimi, sisalto)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: 57
kaavamuistio->kaava: muutaKaavaa(sisalto)
kaavamuistio-->-kayttoliittyma: false

kayttoliittyma->+kayttoliittyma: paivitaKaavojenLista()
kayttoliittyma->+kaavamuistio: kaavojenNimet(haettava)
loop Kaikki muistion kaavat
    kaavamuistio->+kaava_x: getNimi()
    kaava_x->-kaavamuistio: nimi
end
kaavamuistio-->-kayttoliittyma: nimienLista
kayttoliittyma-->-kayttoliittyma:

kayttoliittyma->+kayttoliittyma: valitseKaava(nimi)
kayttoliittyma->+kaavamuistio: haeKaava(nimi)
kaavamuistio->+kaavamuistio: kaavanIndeksi(nimi)
kaavamuistio-->-kaavamuistio: 57
kaavamuistio-->-kayttoliittyma: kaava
kayttoliittyma->+kaava: getLaskentahistoria(true)
kaava->+laskentahistoria: kaikkiRivit(true)
laskentahistoria-->-kaava: kaikkiRivit
kaava-->-kayttoliittyma: kaikkiRivit
kayttoliittyma->+kaava: haeMuuttujat()
kaava-->-kayttoliittyma: muuttujat
kayttoliittyma-->-kayttoliittyma:
kayttoliittyma-->-käyttäjä: näkymä

// Kaavan laskeminen
"käyttäjä"->+kayttoliittyma : Painettu Laske
kayttoliittyma->+valittuKaava: laske(parametrit)
valittuKaava->+valittuKaava: sijoitaParametrit(parametrit)
valittuKaava->+valittuKaava: haeMuuttujat()
valittuKaava-->-valittuKaava: muuttujat
valittuKaava-->-valittuKaava: sijoitettuLauseke
valittuKaava->+":Laskin": laske(sijoitettuLauseke)
":Laskin"-->-valittuKaava: tulos
valittuKaava->laskentahistoria: lisaaRivi(tulos, parametrit)
valittuKaava-->-kayttoliittyma: tulos
kayttoliittyma->+valittuKaava: getLaskentahistoria(true)
valittuKaava->+laskentahistoria: kaikkiRivit()
laskentahistoria-->-valittuKaava: kaikkiRivit
valittuKaava-->-kayttoliittyma: kaikkiRivit
kayttoliittyma-->-käyttäjä: näkymä

// Kaavojen etsiminen
käyttäjä->+kayttoliittyma: hakutermin syöttäminen
kayttoliittyma->+kayttoliittyma: paivitaKaavojenLista()
kayttoliittyma->+kaavamuistio: kaavojenNimet(haettava)
kaavamuistio-->-kayttoliittyma: lista
kayttoliittyma-->-kayttoliittyma:
kayttoliittyma-->-käyttäjä: kaavat listana
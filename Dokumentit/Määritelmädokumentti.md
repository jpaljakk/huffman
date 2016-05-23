Määritelmädokumentti


Tavoitteena ohjelmoida Huffman tiedonpakkaaja/tiedonpurkaja. Ohjelmalle voidaan
antaa input ja output tiedostojen nimet, jolloin tiedostoja voidaan pakataja ja
myöhemmin purkaa alkuperäiseen muotoonsa. Ohjelman käyttöliittymästä voidaan valita
joko pakkaus tai purkaminen, ja tämän jälkeen antaa tiedostojen nimet. Lisäksi
myös pelkkä huffman koodien ruudulle tulostaminen on mahdollista.

Huffman koodaus on optimaalinen tapa valita bittikuvaus ja bittijonojen pituudet
esiintymistiheyden perusteella, jolloin tietoa saadaan pakattua.
Algoritmin olisi tarkoitus toimia O(n Log n) ajassa. Tilavaatimus on käytännössä
merkityksetön, eikä kasva syötteen mukana. Huffman koodipuussa on 2n-1 solmua, 
missä n on eri merkkien määrä. Eri merkkejä on max 256, jolloin solmuja on 
maksimissaan 511.
 

Käytetyt tietotyypit ovat binääripuu huffman koodien muodostamiseksi.
Jono/prioriteettijono sekä lista/arraylist. Näitä tarvitaan huffman koodauksen
toteuttamiseen. 

Algoritmina on huffman koodaus ja pikajärjestäminen.


Toteutusdokumentti :


Ohjelman huffman koodaus on toteutettu luokilla CanonicalCode, CodeTree, FrequencyTable, HuffmanCompressor, HuffmanDecoder, HuffmanEncoder. Lisäksi on Node rajapinta ja sen toteuttavat InternalNode ja Leaf. BitInputStream ja BitOuputStream tarjoavat bittitason virran sisään ja ulos. Tietorakenteina Geneeriset PriorityQueue ja List.

FrequencyTable laskee merkkien esiintyvyydet, jotka HuffmanEncoder lukee. FrequencyTable luo myös CodeTreen, eli huffman koodipuun, esiintyvyyksien perusteella. CanonicalCode tallentaa CodeTree koodipuun symbolilehtien bittijonojen pituudet. Se myös pystyy luomaan CodeTreen näiden perusteella. HuffmanEncoder käyttää CodeTreeta muuttaessaan merkkejä huffmankoodatuiksi bittijonoiksi, jotka BitOutputStreamin avulla tallennetaan tiedostoon. Tiedoston alkuun tallennetaan CanonicalCoden bittijonojen pituudet, jonka avulla CodeTree voidaan luoda myöhempää purkamista varten. 

HuffmanDecoder aluksi lukee BitInputStreamin avulla CanonicalCoden, jonka avulla luodaan CodeTree. Tämän jälkeen luetaan BitInputStreamin bittejä, joiden mukaan suunnistetaan koodipuussa kunnes tullaan lehteen. Lehden symboli kirjoitetaan tiedostoon. Tätä toistetaan kunnes vastaan tule tiedoston lopun kertova EOF symboli, jolloin tiedosto on dekoodattu alkuperäiseen muotoonsa.


Suorituskyky syötteen suhteen paranee kokoajan, sillä merkkien esiintyvyyksien laskeminen on syötteen suhteen lineaarinen toiminta, mutta koodipuun luonti tarvitsee tehdä vain kerran, oli syöte kuinka suuri tahansa. Mikäli syöte on yksinkertainen niin koodipuun luonti sujuu hieman nopeammin koska se ei käsitä kaikkia 256 merkkiä, mutta käytännössä näin on harvoin ja koodipuun luomiseen kuluva aika on jokatapauksessa olematon. Enkoodausvaiheessa merkkien esiintyvyyksien laskeminen on lineaarinen suhteessa syötteeseen, dekoodauksessa tämä vaihe jää pois. Koodipuun avulla itse enkoodaus ja dekoodaus ovat syötteen suhteen lineaarisia toimenpiteitä. 

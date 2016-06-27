Toteutusdokumentti :


Ohjelman huffman koodaus on toteutettu luokilla CanonicalCode, CodeTree, FrequencyTable, HuffmanCompressor, HuffmanDecoder, HuffmanEncoder. Lisäksi on Node rajapinta ja sen toteuttavat InternalNode ja Leaf. BitInputStream ja BitOuputStream tarjoavat bittitason virran sisään ja ulos. Tietorakenteina Geneeriset PriorityQueue ja List. Nämä on jaettu neljään pakettiin, huffmancompressor, huffmancompressor.algorithm, huffmancompressor.streams, huffmancompressor.structures.

FrequencyTable laskee merkkien esiintyvyydet, jotka HuffmanEncoder lukee. FrequencyTable luo myös CodeTreen, eli huffman koodipuun, esiintyvyyksien perusteella. CanonicalCode tallentaa CodeTree koodipuun symbolilehtien bittijonojen pituudet. Se myös pystyy luomaan CodeTreen näiden perusteella. HuffmanEncoder käyttää CodeTreeta muuttaessaan merkkejä huffmankoodatuiksi bittijonoiksi, jotka BitOutputStreamin avulla tallennetaan tiedostoon. Tiedoston alkuun tallennetaan CanonicalCoden bittijonojen pituudet, jonka avulla CodeTree voidaan luoda myöhempää purkamista varten. 

HuffmanDecoder aluksi lukee BitInputStreamin avulla CanonicalCoden, jonka avulla luodaan CodeTree. Tämän jälkeen luetaan BitInputStreamin bittejä, joiden mukaan suunnistetaan koodipuussa kunnes tullaan lehteen. Lehden symboli kirjoitetaan tiedostoon. Tätä toistetaan kunnes vastaan tule tiedoston lopun kertova EOF symboli, jolloin tiedosto on dekoodattu alkuperäiseen muotoonsa.

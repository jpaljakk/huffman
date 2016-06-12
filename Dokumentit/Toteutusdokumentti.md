Toteutusdokumentti:


Ohjelman huffman koodaus on toteutettu luokilla CanonicalCode, CodeTree, FrequencyTable, HuffmanCompressor, HuffmanDecoder, HuffmanEncoder. Lisäksi on Node rajapinta ja sen toteuttavat InternalNode ja Leaf. BitInputStream ja BitOuputStream tarjoavat bittitason virran sisään ja ulos. Tietorakenteina Geneeriset PriorityQueue ja List.

FrequencyTable laskee merkkien esiintyvyydet. Se luo myös CodeTreen esiintyvyyksien perusteella. CanonicalCode tallentaa CodeTree koodipuun lehtien bittijonojen pituudet. Se myös luo CodeTreen näiden perusteella. HuffmanEncoder käyttää CodeTreeta muuttaessaan merkkejä huffmankoodatuiksi bittijonoiksi, jotka BitInputStreamin avulla tallennetaan tiedostoon. Tiedoston alkuun tallennetaan CanonicalCode, jonka avulla CodeTree voidaan luoda myöhempää purkamista varten.


Testausdokumentti :


Koodausvaiheessa testaus tuli pääosin toteutettua välitulostuksilla, jotka paljastivat virheet helposti, koska etukäteen pystyi tietämään mitä tulostuksen pitäisi olla. Kokonaisuuden toiminnan testauksessa käytössä oli lyhyt testitiedosto, jonka attribuutit olivat selvillä ja jonka enkoodauksen lopputulos paljasti selkeät virheet esim. enkoodatun lopputuloksen koon perusteella. Kokonaisuuden toiminnan testaus loppuvaiheessa perustui siihen, että enkoodattu tiedosto oli odotetun mukainen ja kyseisen tiedoston dekoodaus palautti sen alkuperäiseen muotoon. 

JUnit yksikkötestausta on toteutettu suurimpaan osaan julkisia metodeja. Yksikkötestauksen toteutus tiettyjen luokkien osalta on hankalampaa, koska luokat linkittyvät toisiinsa ja yksittäisten metodien testaaminen ilman monien objektien luomista onnistuu vain tiettyjen metodien kohdalla. Streamien toiminta on testattu käyttämällä ByteArrayStream luokkia apuna. Node rajapinnan toteuttavat luokat, InternalNode ja Leaf, ovat yksinkertaisuudestaan huolimatta testattu, koska niiden täydellinen muista riippumaton testaaminen oli helppo toteuttaa.

Koska ohjelma muuttaa tiedoston kuin tiedoston alkuperäistä pienempään kokoon ja pystyy palauttamaan sen identtiseksi jälkikäteen, voi koodin toimivuudesta olla varma. Toimintaa on testattu useammalla erillaisella tiedostolla.

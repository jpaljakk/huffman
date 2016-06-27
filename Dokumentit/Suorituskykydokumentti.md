Suorituskykydokumentti :


Algoritmin tehokkuuden mittaaminen käyttämällä kellonaikaa apuna,
on melkoisen epätarkka metodi pienillä syötteillä. Toisaalta huffman
koodauksen mittaaminen suurilla syötteillä ei kerro koodipuun luomisen
vaativuudesta, tosin ehkä sillä ei niin väliä, koska operaatio on niin
kevyt. Enkoodauksen ja dekoodauksen nopeuden kasvu hidastuu niin selvästi
yli 1mb syötteillä, että voidaan olettaa 64mb nopeuksien olevan käytännössä
maksiminopeuksia. Nopeusprosentti paljastaa hyvin kuinka nopea itse 
enkoodaus ja dekoodaus on sen jälkeen kun koodipuu on saatu kasattua.
Sekä enkoodauksessa, että dekoodauksessa nopeus on laskettu alkuperäisen
tiedoston koko jaettuna enkoodaukseen/dekoodaukseen käytetty aika.
Tuloksien perusteella algoritmin tehokkuusluokka O(n Log n) koskee
vain symbolien määrän vaikutusta koodipuun luomisen aikavaativuuteen.
Itse pakkaus ja purku on syötteen suhteen lineaarinen, O(n), koska
nopeus pysyy vakiona.

Syötteen	Enkoodaus/	Enkoodaus bps/		Nopeus %
Koko		Dekoodaus	Dekoodaus bps		Maksimista
------------------------------------------------------------------
1024bit 	9ms/4ms  	113/256			 0,695 %	
	
2048bit 	10ms/4ms 	204/682			 1,668 %

4096bit 	10ms/6ms 	409/682			 2,054 %

16kb		15ms/6ms 	1092/2730		 7,194 %

128kb		32ms/18ms 	4096/7281		21,415 %

1mb		75ms/46ms	13981/22795		69,223 %

8mb		380/303ms	22075/27685		93,662 %	

64mb		2874ms/2050ms	22118/31009		100,00 %

# XML-TIM41

Ovako:

Prvo: skinite i instalirajte IntelliJ, skinite trial verziju, ne community. Bice nam dosta tih 30 dana... Instalirajte JDK 8u91.
Drugo: Kada odradite clone ovog gita, otvorite intellij, i kad vam se otvori mali prozor kliknite import project. Kada vam se pojavi lista foldera iz koje treba da odaberete projekat, OBAVEZNO udjite skroz u projekat i odaberite build.gradle file! Jako bitna stvar!
Pojavice se neki prozor, tamo treba da izaberete javu 1.8 (nadam se da tu nece praviti problema), i kliknete ok. 

Zatim, iz u file exploreru nadjite unutar projekta, i src foldera folder angular, udjite unutra. Shift + desni klik -> open command window here, i onda kucajte naredne naredbe:
1. npm install (ako nemate npm instalirajte node.js)
2. browserify src/scripts/dependencies_list.js -o dist/dependencies.js (ako nemate browserify instalirajte)
3. browserify src/scripts/index.js -o dist/sve.js

On odradi svoje, nadjite klasu Application iz intellij, desni klik i run (port 8080 mora biti slobodan da bi to radilo). VOila - u browseru pokrenite localhost:8080/ i to je to :) 

# Trainiing360Ws

# könyvek
 * Brett McLaughilin, Justin Edelson: Java and xml (2006, O'Reilly)
 * ref: w3.org
 * tutorial: w3schools.com
 
# XML
 * történelem:
   * sgml -> xml és html -> xhtml
 * xhtml-t könnyebb feldolgozni, mint a html-t
 * jsoup: html-t kapunk (nincsenek tagek lezárva) --> kijavítja a hibákat
 * szöveges, lassan feldolgozható, lassan lekérdezhető --> nem adattárolásra való
 * xml pragma:
    * xml verzióját szokták beleírni
    * encoding
    * standalone
 * xml szabványnak megfelel: well-formed

# XML névterek
 * motiváció: névütközés kezelése
   * két forrásból érkező xml használhassa ugyanazokat a tageket
   * a minősítés leggyakrabban url legyen
   * xml-en belül aliasszal hivatkozunk rá
     * az alias neve teljesen mindegy, hogy mi
   * ott is használható, ahol deklarálom
     * <ns0:root xmlns:ns0="http://training360.com/schema/catalog>
   * Elegendő az első használatkor deklarálni

# XML validáció
 * szabályrendszerek:
   * tagek nevei <xs:element>
   * mit tartalmazhat
   * multiplicitás
   * attributomok tipussal
 * ha megfelel a sémának: valid
 * attribute vagy tag:
   * ízlés szerint
   * pl csak tagek
   * attributomoknak vannak megszorításai
     * tagen belül lehet tag
     * attributomot nem lehet tovább struktúrálni
     * attr. csak egyszer szerepelhet?
 * az XMLSchema saját magát írja le

# java xml:
 * jaxp (dom, sax, stAX, xslt, xpath)-t mind ismeri
   * implementációk:
     * xerces
     * xalan
# DOM
 * beolvassa az egész fájlt
 * memóriában felépít egy fát
 * sok memóriát igényel (kb a fájl 4x-ét)
 * könnyű dolgozni vele
   * minden tag, attr, element külön objektum
   * ősosztály: Node
   * leszármazottak: Document, Element, Attr, Entity stb
 * A tagek között whitespaceket text-nek tekinti

 
# links
https://www.training360.com/xml-kezeles-es-soap-restful-webszolgaltatasok-megvalositasa-java-platformon-tanfolyam-javax-ws
http://www.learnwebservices.com/
https://github.com/Training360/javax-ws-20200427
http://xmlstar.sourceforge.net/

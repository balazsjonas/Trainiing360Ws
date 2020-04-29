# Training360 WebServices

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
 * standalone="no":
   * DTD-vel együtt kell értelmezni
   * vegye figyelembe a DTD-ben definiált értékeket

# XML névterek
 * motiváció: névütközés kezelése
   * két forrásból érkező xml használhassa ugyanazokat a tageket
   * a minősítés leggyakrabban url legyen
   * xml-en belül aliasszal hivatkozunk rá
     * az alias neve teljesen mindegy, hogy mi
   * ott is használható, ahol deklarálom
     * <ns0:root xmlns:ns0="http://training360.com/schema/catalog>
   * Elegendő az első használatkor deklarálni
   * default névtér esetén nem kell prefix
     * az összes tag ebben a névtérben van
     * teljes doksira vagy részfára

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
 * DTD:
   * nem külön validáció, hanem a parsing process része
   * SAX, DOM támogatja
     * setValidatin(true)
     * error handler definiálható
   * StAX nem támogatja!!

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
 * névterek:
   * ```documentBuilderFactory.setNamespaceAware(true);```
   * root.setAttribute("xmlns:ns", "url")
   * getElementsByTagNameNS: URI-t kell megadni, a prefix/alisas senkit nem érdekel
   * Node.setPrefix

# SAX
 * push parser
   * eseményvezérelt, ha tag-hez ér, akkor szól
 * csak olvasásra

# StAX
 * pull parser
   * én vezérlem a feldolgozást
 * két api:
   * Cursor API: alacsonyabb szintű, ugyanannak a példánynak az állapotát változtatja
     * XMLStreamReader visszaadja a tipust (start, end, stb), textElement, 
   * Iterator API: új példány jön létre
 
# XML séma
 * adattípusok: pl string, decimal, float, date
 * dokumentálásra használható
 * xsi:schemaLocation párokból áll: névtér és xsd url-je
   * parsernek meg lehet mondani, hogy url-helyett egy adott fájlt használjon.
 * adattípusok:
   * xsd:normalizedString: nem lehet \r\l \n\t
   * xsd:ID: egyedi az xml-en belül
# SOAP
 * adattípus: SOAP(XML) de a tartalma tetszőleges
 * ennek leírására WSDL, aminek payloadját XSD-vel elehet leírni.

# módszer
 * TOP down: WSDL -> alkalmazás megírása (kód generálás könnyű)
 * bottom up: alkalmazás előbb
 * meet-in-the-middle: van alkalmazás + wsdl-t kapunk

# web alkalmazások:
 * rétegek: perzisztens, business logic, prezentációs
 * üzleti entitások kiajánlása nem jó ötlet:
   * sok alkalmazás hívja -> nem tudjuk módosítani, mert a klienseket is módosítani kell
   * integrációs réteg (gateway), amit a kliensek hívhatnak
   * ez felel meg a prezentációs rétegnek
     * UI: interface az emberek felé
     * gateway: interface a számítógépek felé
   * API verziózása!
   
# JAX-WS
 * cxf még spring-ws helyett is
 
# tools
 * xmlUnit: xmlunit-core, xmlunit-assertj
 * jaxb-v2-maven plugin  és maven-jaxb-plugin
   * java -> xsd és xsd -> java
   
 
# links
https://www.training360.com/xml-kezeles-es-soap-restful-webszolgaltatasok-megvalositasa-java-platformon-tanfolyam-javax-ws
http://www.learnwebservices.com/
https://github.com/Training360/javax-ws-20200427
http://xmlstar.sourceforge.net/

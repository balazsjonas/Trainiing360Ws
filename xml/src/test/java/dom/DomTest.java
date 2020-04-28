package dom;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.xmlunit.assertj.XmlAssert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomTest {

    public static final String XML = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
            "<!-- Catalog of books -->\n" +
            "<catalog>\n" +
            "    <book isbn10=\"059610149X\">\n" +
            "        <title>Java and XML</title>\n" +
            "        <available />\n" +
            "    </book>\n" +
            "    <book isbn10=\"1590597060\">\n" +
            "        <title>Pro XML Development\n" +
            "            with Java Technology</title>\n" +
            "    </book>\n" +
            "    <book isbn10=\"asdf\">\n" +
            "        <title>1+2$lt;3</title>\n" +
            "    </book>\n" +
            "</catalog>";

    @Test
    void testReadXml() {
        var domService = new DomService();

        var catalog = domService.readXml(new StringReader(XML));
        assertEquals(3, catalog.getBooks().size());
        // TODO 13 és 14-ben már experimental text blockok """-lel
    }

    @Test
    void fromFile() {
        var domService = new DomService();
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/default/catalog.xml")
        ));

        var catalog = domService.readXml3(reader);

        assertEquals(3, catalog.getBooks().size());
        assertThat(catalog.getBooks())
                .extracting(Book::getTitle)
                .contains("Java and XML");
//        assertThat(catalog.getBooks())
//                .extracting(Book::getTitle, Book::getIsbn10)
//                .contains(Tuple.tuple("Java and XML", "059610149X"));
    }

    @Disabled
    @Test
    void xmlUnit() {
        var domService = new DomService();
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/default/catalog.xml")
        ));

        var catalog = domService.readXml3(reader);

        XmlAssert.assertThat(catalog.toString()).and(XML)
                .ignoreWhitespace()
                .areSimilar();

        XmlAssert.assertThat(catalog.toString())
                .valueByXPath("/catalog/book[3]/@isbn10")
                .isEqualTo("059610149X");


    }

    @Test
    void testWriteCatalog() {
        var service = new DomService();
        var catalog = new Catalog(List.of(new Book("111", "aaa"),
                new Book("222", "bbb")));

        StringWriter writer = new StringWriter();
        service.writeCatalog(catalog, writer);

        XmlAssert.assertThat(writer.toString())
                .valueByXPath("/catalog/book[2]")
                .isEqualTo("bbb");
    }
}

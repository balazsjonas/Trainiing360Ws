package dom;

import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DomTest {
    @Test
    void testReadXml() {
        var domService = new DomService();
        String xml ="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
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

        var catalog = domService.readXml(new StringReader(xml));
        assertEquals(3, catalog.getBooks().size());
        // TODO 13 és 14-ben már experimental text blockok """-lel
    }

    @Test
    void fromFile() {
        var domService = new DomService();
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/catalog.xml")
        ));

        var catalog = domService.readXml3(reader);

        assertEquals(3, catalog.getBooks().size());
        assertThat(catalog.getBooks())
                .extracting(Book::getTitle)
                .contains("Java and XML");
        assertThat(catalog.getBooks())
                .extracting(Book::getTitle, Book::getIsbn10)
                .contains(Tuple.tuple("Java and XML", "059610149X"));
    }
}

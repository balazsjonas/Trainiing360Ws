package stax;

import dom.Book;
import dom.Catalog;
import dom.DomService;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StaxService {

    public List<String> readIsbn10Numbers(Reader source) {
        try {
            List<String> isbnList = new ArrayList<>();
            var f = XMLInputFactory.newInstance();
            var r = f.createXMLStreamReader(source);
            while (r.hasNext()) {
                if (r.getEventType() == XMLStreamConstants.START_ELEMENT &&
                        r.getName().getLocalPart().equals("book")
                ) {
                    var isbn10 = r.getAttributeValue("", "isbn10");
                    isbnList.add(isbn10);
                }

                var next = r.next();
            }
            return isbnList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> readTitles(Reader source) {
        try {
            List<String> titles = new ArrayList<>();

            var f = XMLInputFactory.newInstance();
            var r = f.createXMLStreamReader(source);

            while (r.hasNext()) {
                if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
                    if (r.getName().getLocalPart().equals("title")) {
                        titles.add(r.getElementText());
                    }
                }
                r.next();
            }
            return titles;
        } catch (Exception e) {
            throw new IllegalStateException("Can not read", e);
        }
    }

    public void writeCatalog(Writer dest, Catalog catalog) {
        try {
            var f = XMLOutputFactory.newInstance();
            var w = f.createXMLStreamWriter(dest);
            w.writeStartDocument();
            w.writeStartElement("catalog");
            for(var book: catalog.getBooks()) {
                w.writeStartElement("book");
                w.writeAttribute("isbn10", book.getIsbn10());
                w.writeStartElement("title");
                w.writeCharacters(book.getTitle());
                w.writeEndElement();
                w.writeEndElement();
            }
            w.writeEndElement();
            w.writeEndDocument();
            w.flush();


        }
        catch (Exception e) {
            throw new IllegalStateException("Can not write", e);
        }
    }
    public void writeTitles(Writer dest, Catalog catalog) {
        // <catalog><book>...</book>
        try {
            var f = XMLOutputFactory.newInstance();
            var w = f.createXMLStreamWriter(dest);
            w.writeStartDocument();
            w.writeStartElement("catalog");
            for (var book: catalog.getBooks()) {
                w.writeStartElement("book");
                w.writeCharacters(book.getTitle());
                w.writeEndElement();
            }
            w.writeEndElement();
            w.writeEndDocument();
            w.flush();
        }
        catch (Exception e) {
            throw new IllegalStateException("Can not write", e);
        }
    }

    public static void main(String[] args) {
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/default/catalog.xml")
        ));
        try (reader) {
            System.out.println(new StaxService().readIsbn10Numbers(reader));
        } catch (IOException ioe) {
            throw new IllegalStateException("Can not read file", ioe);
        }

        System.out.println("writing catalog");
        var service = new StaxService();
        var catalog = new Catalog(List.of(new dom.Book("111", "aaa"),
                new Book("222", "bbb")));
        var writer = new StringWriter();
        service.writeCatalog(writer, catalog);
        System.out.println(writer.toString());
    }
}

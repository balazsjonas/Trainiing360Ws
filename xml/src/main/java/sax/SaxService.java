package sax;

import dom.Catalog;
import dom.DomService;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;

public class SaxService {
    public Catalog reader(Reader reader) {
        try {
            var factory = SAXParserFactory.newDefaultInstance();
            var saxParser = factory.newSAXParser();
            saxParser.parse(new InputSource(reader), new CatalogHandler());
            return null;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        DomService dom = new DomService();
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/catalog.xml")
        ));
        try(reader) {
            new SaxService().reader(reader);
        }catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}

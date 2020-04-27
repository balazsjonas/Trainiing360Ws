package sax;

import dom.Book;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class CatalogHandler extends DefaultHandler {
    List<Book> bookList = new ArrayList<>();
    Book current = null;
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        // uri: névtér
        System.out.println("start: " + qName);
        if(qName.equals("title")) {
            current = new Book();
            bookList.add(current);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("end: " + qName);
        current=null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        // ez többször is meghivódhat
        if(current != null) {
            System.out.println("ch:start: " + start + "; " + length);
            System.out.println(ch);
        }
//        System.out.println(ch);
        if(current != null && current.getTitle() == null) {

            current.setTitle(new String(ch, start, length));
        }
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println();
        System.out.println("End of document");
        System.out.println(bookList);
    }
}

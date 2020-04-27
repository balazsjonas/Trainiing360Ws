package dom;


import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.util.ArrayList;

public class DomService {
    public static void main(String[] args) {
        DomService dom = new DomService();
        var reader = new BufferedReader(new InputStreamReader(
                DomService.class.getResourceAsStream("/catalog.xml")
        ));
        try (reader) {
            var catalog = dom.readXml(reader);
            System.out.println("print catalog");
            System.out.println(catalog);
        } catch (IOException ioException) {
            throw new UncheckedIOException(ioException);
        }

    }

    public Catalog readXml3(Reader reader) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var doc = builder.parse(new InputSource(reader));
            // printNode(doc);
            var bookElements = doc.getElementsByTagName("book");
            var catalog = new Catalog();
            catalog.setBooks(new ArrayList<>());
            for (int i = 0; i < bookElements.getLength(); i++) {
                var book = new Book();
                book.setIsbn10(((Element) (bookElements.item(i))).getAttribute("isbn10"));
                book.setTitle(((Element) bookElements.item(i)).getElementsByTagName("title").item(0).getChildNodes().item(0).getNodeValue());
                catalog.getBooks().add(book);
            }
            return catalog;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Catalog readXml(Reader reader) {
        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var doc = builder.parse(new InputSource(reader));
            // printNode(doc);

            var catalog = new Catalog();
            catalog.setBooks(new ArrayList<>());

            var catalogNode = doc.getChildNodes().item(1);
            var catalogChildren = catalogNode.getChildNodes();
            for (int i = 0; i < catalogChildren.getLength(); i++) {
                if (catalogChildren.item(i).getNodeName().equals("book")) {
                    var book = new Book();

//                    System.out.println(catalogChildren.item(i).getNodeName());
                    book.setIsbn10(((Element) (catalogChildren.item(i))).getAttribute("isbn10"));
                    if (catalogChildren.item(i).getChildNodes().getLength() > 0) {
                        var titleElement = (Element) catalogChildren.item(i).getChildNodes().item(1);
//                        System.out.println(titleElement.getChildNodes().item(0).getNodeValue());
                        book.setTitle(titleElement.getChildNodes().item(0).getNodeValue());
                    }
                    catalog.getBooks().add(book);
                }
            }

            return catalog;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException("Can not parse XML", e);
        }
    }

    public Catalog readXml2(Reader reader) {
        //Reader jobb mint a String. Ezzel tudunk bufferelve olvasni
        // nem kell az egészet beolvasni

        try {
            var factory = DocumentBuilderFactory.newInstance();
            var builder = factory.newDocumentBuilder();
            var doc = builder.parse(new InputSource(reader));

            printNode(doc);

            var catalog = new Catalog();
            catalog.setBooks(new ArrayList<>());
            var catalogNode = doc.getChildNodes().item(1);
            var books = catalogNode.getChildNodes();
            for (int i = 0; i < books.getLength(); i++) {
                var book = new Book();
                System.out.println(books.item(i).getNodeName());

            }
            return catalog;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new IllegalStateException("cannot parse xml", e);
        }

    }

    private void printNode(Node node) {
        if (node.getNodeType() != Node.TEXT_NODE) {
            System.out.println(node.getNodeName() + " " + node.getNodeValue() + " " + node.getNodeType());
        }
        var children = node.getChildNodes();
        for (int i = 0; i < children.getLength(); i++) {
            printNode(children.item(i));
        }
    }

}

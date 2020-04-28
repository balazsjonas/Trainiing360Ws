package xpath;

import dom.Book;
import dom.Catalog;
import dom.DomService;

import javax.xml.xpath.*;
import java.io.StringWriter;
import java.util.List;

public class Xpath {
    public static void main(String[] args) throws XPathExpressionException {
        var service = new DomService();
        var catalog = new Catalog(List.of(new Book("111", "aaa"),
                new Book("222", "bbb")));

        StringWriter writer = new StringWriter();
        service.writeCatalog(catalog, writer);
        String xml = writer.toString();
        System.out.println(xml);

        var xPathFactory = XPathFactory.newInstance();
        var xPath = xPathFactory.newXPath();
        var expression = xPath.compile("/catalog/book[1]/text()");

        var eval = expression.evaluate(xml); //, XPathConstants.STRING);
        System.out.println(eval);
    }
}

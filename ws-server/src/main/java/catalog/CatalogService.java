package catalog;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class CatalogService {

    @WebMethod // wsdl operation
    public Catalog getCatalog() {
        return new Catalog(
         List.of(new Book("111", "book1"),
                 new Book("22", "book2"))
        );
    }
}

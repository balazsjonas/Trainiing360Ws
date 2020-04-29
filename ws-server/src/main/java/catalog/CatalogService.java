package catalog;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService(
//        targetNamespace = "http://training360.com/catalog"
)
public class CatalogService {

    @WebMethod // wsdl operation

    @WebResult(name = "catalog"
            //, targetNamespace = "http://training360.com/catalog"
    )
    public Catalog getCatalog() {
        return new Catalog(
                List.of(new Book("111", "book1"),
                        new Book("22", "book2"))
        );
    }
}

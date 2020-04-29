package catalog;

import javax.jws.WebService;

@WebService
public interface CatalogServiceApi {
    Catalog getCatalog();
}

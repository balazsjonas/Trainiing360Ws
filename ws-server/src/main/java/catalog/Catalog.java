package catalog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {
 // http://localhost:8080/catalog?xsd=1
    List<Book> books;

    
}

package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("/api/employee")
public class EmployeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Employee> getEmployees() {
        var does = List.of("John Doe", "Jane Doe", "Jack Doe");
        return does.stream().map(Employee::new).collect(Collectors.toList());
    }


}

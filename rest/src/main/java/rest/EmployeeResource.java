package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

@Path("api/employee")
public class EmployeeResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.TEXT_XML)
    public List<Employee> getEmployees() {
        var does = List.of("John Doe", "Jane Doe", "Jack Doe");
        return does.stream().map(Employee::new).collect(Collectors.toList());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Employee createEmployee(Employee employee)  {
        System.out.println("create "+employee);
        return new Employee(employee.getName().toUpperCase());
    }


}

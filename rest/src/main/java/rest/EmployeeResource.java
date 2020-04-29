package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("api/employee")
@Produces(APPLICATION_JSON)
public class EmployeeResource {

    EmployeesBean employeesBean = new EmployeesBean();

    @GET
    public List<Employee> listEmployees() {
        return employeesBean.listEmployees();
    }

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Employee updateEmployee(Employee employee) {
        return employeesBean.updateEmployee(employee);
    }

    public void deleteEmployee(String id) {
        employeesBean.deleteEmployee(id);
    }

    public Employee findEmployeeById(String id) {
        return employeesBean.findEmployeeById(id);
    }


//    @Produces(MediaType.TEXT_XML)
    public List<Employee> getEmployees() {
        var does = List.of("John Doe", "Jane Doe", "Jack Doe");
        return does.stream().map(Employee::new).collect(Collectors.toList());
    }

@POST
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
    public Response createEmployee(Employee employee)  {
    Employee created = employeesBean.createEmployee(employee);
    return Response.status(Response.Status.CREATED)
                .entity(created).build();

    }


}

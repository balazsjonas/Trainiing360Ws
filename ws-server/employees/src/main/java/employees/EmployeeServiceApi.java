package employees;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface EmployeeServiceApi {
    @WebResult(name="employees")
    List<Employee> getEmployees();

    @WebMethod(action = "POST")
    void addEmployee(String name);

    @WebMethod(action="DELETE")
    void deleteEmployee(long id);
}

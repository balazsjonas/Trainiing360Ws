package employees;

import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.List;

@WebService
public class EmployeeService implements EmployeeServiceApi {

    private static final EmployeesBean employeesBean = new EmployeesBean();

    @Override
    public List<Employee> getEmployees() {
        return employeesBean.listEmployees();
    }

    @Override
    public void addEmployee(@WebParam(name = "employee-to-create") String name) {
        var employee = new Employee();
        employee.setName(name);
        employeesBean.createEmployee(employee);
    }

    @Override
    public void deleteEmployee(long id) {
        employeesBean.deleteEmployee(String.valueOf(id));
    }

    public static void main(String[] args) {
        Endpoint.publish("http://localhost:8081/employees", new EmployeeService());
    }
}

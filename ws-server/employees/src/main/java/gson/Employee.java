package gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

    String name;
    int yearOfBirth;

    public static void main(String[] args) throws IOException {
        var john_doe = new Employee("John Doe", 1990);
        var jane_doe = new Employee("Jane Doe", 2000);
        var theDoes = List.of(jane_doe, john_doe);
        var gson = new Gson();
        var adapter = gson.getAdapter(Employee.class);

        String json = gson.toJson(theDoes);
        System.out.println(json);

        Class<Employee> employeeClass = Employee.class;
        var employee = gson.fromJson(gson.toJson(jane_doe), employeeClass);
        System.out.println(employee);

        var employees = gson.fromJson(json, Employee[].class);
        System.out.println(Arrays.toString(employees));

        System.out.println("mi a szösz?");
        Class<? extends List> aClass = theDoes.getClass();
        List list = gson.fromJson(json, List.class);
        System.out.println(list);

        List list1 = gson.fromJson(json, aClass);
        System.out.println(list1);


//
//        Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
//        var employees = adapter.fromJson(json, Employee[].class);
//        System.out.println(employees);
    }
}

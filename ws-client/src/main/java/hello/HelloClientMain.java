package hello;

import com.learnwebservices.services.hello.HelloEndpoint;
import com.learnwebservices.services.hello.HelloEndpointService;
import com.learnwebservices.services.hello.HelloRequest;
import com.learnwebservices.services.hello.HelloResponse;

public class HelloClientMain {
    public static void main(String[] args) {
        var service = new HelloEndpointService();
        var port = service.getHelloEndpointPort();
        var helloRequest = new HelloRequest();
        helloRequest.setName("Jónás");

        var helloResponse = port.sayHello(helloRequest);
        System.out.println(helloResponse.getMessage());

    }
}

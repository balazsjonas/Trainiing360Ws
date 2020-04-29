package rest;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URI;

public class Main {
    public static void main(String[] args) throws IOException {
        var config = new ResourceConfig().packages("rest");
        var server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8082"), config);
//        new ResourceConfig().packages("catalog")
        ClientBuilder.newClient().target("")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.json(new Employee("John Doe")))
        ;
        System.in.read();
        server.shutdown();
    }
}

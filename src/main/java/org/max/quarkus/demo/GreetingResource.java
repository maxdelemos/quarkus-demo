package org.max.quarkus.demo;

import io.quarkus.grpc.GrpcClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import quarkus.HelloReply;
import quarkus.HelloRequest;
import quarkus.GreeterGrpc.GreeterBlockingStub;

@Path("/hello")
public class GreetingResource {

    @GrpcClient("hello")
    GreeterBlockingStub blockingHelloService;

    @GET
    @Path("/{name}")
    public String hello(@PathParam("name") String name) {
        HelloReply reply = blockingHelloService.sayHello(HelloRequest.newBuilder().setName(name).build());
        return generateResponse(reply);
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello RESTEasy";
    }

    public String generateResponse(HelloReply reply) {
        return String.format("%s! HelloService ", reply.getMessage());
    }

}
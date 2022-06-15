package com.example;

import com.example.demo.GreeterGrpc;
import com.example.demo.GreeterGrpc.GreeterBlockingStub;
import com.example.demo.HelloRequest;

import io.grpc.Channel;
import io.grpc.ManagedChannelBuilder;

/**
 * Hello world!
 *
 */
public class App {

    public static void main(String[] args) {
        System.out.println("hello grpc client");

        Channel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();
        GreeterBlockingStub blockingStub = GreeterGrpc.newBlockingStub(channel);

        HelloRequest req = HelloRequest
            .newBuilder()
            .setName("moris")
            .build();

        var res = blockingStub.sayHello(req);
        System.out.println(res.getMessage());
    }
}
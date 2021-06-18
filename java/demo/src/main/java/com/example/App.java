package com.example;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import com.example.demo.*;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import io.grpc.stub.StreamObserver;

/**
 * Hello world!
 *
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class.getName());

    public static void main(String[] args) throws Exception {
        logger.info("Starting server");

        int port = 50051;
        Server server = ServerBuilder
            .forPort(port)
            .addService(new GreeterImpl())
            .addService(ProtoReflectionService.newInstance())
            .build()
            .start();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("shutdow server");
                try {
                server
                    .shutdown()
                    .awaitTermination(3, TimeUnit.SECONDS);
                } catch(InterruptedException e) {
                    logger.severe(e.getMessage());
                }
            }
        });
        logger.info("Server started, listening on " + port);
        server.awaitTermination();
    }

    static class GreeterImpl extends GreeterGrpc.GreeterImplBase {

        @Override
        public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {

            logger.info("sayHello was invoked = " + req.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

}

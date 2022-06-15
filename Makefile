.PHONY: help protoc

all:

protoc:
	rm -rf go/helloworld
	protoc --go_out=./go --go-grpc_out=./go proto/helloworld.proto	

protoc-java:
	 rm -rf ./java/demo/src/main/java/com/example/demo
	 protoc --java_out=./java/demo/src/main/java --grpc-java_out=./java/demo/src/main/java proto/helloworld.proto

protoc-java-client:
	 rm -rf ./java-client/demo/src/main/java/com/example/demo
	 protoc --java_out=./java-client/demo/src/main/java --grpc-java_out=./java-client/demo/src/main/java proto/helloworld.proto

clean:
	rm -rf go/helloworld


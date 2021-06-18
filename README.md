# gRpc Samples

現時点でサーバーのみ。

## Protobuf

以下からダウンロードしてインストールする。

* [Releases · protocolbuffers/protobuf](https://github.com/protocolbuffers/protobuf/releases)

## golang

以下をインストールし、`$GOPATH/bin` をパスに通す。

```sh
go install google.golang.org/protobuf/cmd/protoc-gen-go@latest
go install google.golang.org/grpc/cmd/protoc-gen-go-grpc@latest
```

`grpcurl`のインストール。

```sh
go get github.com/fullstorydev/grpcurl
go install github.com/fullstorydev/grpcurl/cmd/grpcurl
```

`grpcurl` で動作確認する。

```shell
$ grpcurl -plaintext localhost:50051 list
Greeter
grpc.reflection.v1alpha.ServerReflection

$ grpcurl -plaintext localhost:50051 list Greeter
Greeter.SayHello

$ grpcurl -plaintext localhost:50051 Greeter.SayHello
{
  "message": "Hello "
}

$  grpcurl -plaintext -d '{"name" : "moris"}' localhost:50051 Greeter.SayHello
{
  "message": "Hello moris"
}
```

## Java

`protoc-gen-grpc-java` が必要。普通は、Mavenでプラグインを利用すると自動生成してくれるが、ここでは手動生成してみた。自動生成用の実行ファイルは、プラグインに含まれている。

全体をクローンして、以下のフォルダでビルドする。

* [grpc-java/compiler at master · grpc/grpc-java](https://github.com/grpc/grpc-java/tree/master/compiler)

デフォルトだとAndroid版までビルドして難儀するので、以下でスキップできる。`./build/exe/java_plugin/protoc-gen-grpc-java` ができるので、`/usr/local/bin`にでもコピーしておく。

```sh
../gradlew java_pluginExecutable  -PskipAndroid=true
```

以下の手順で生成する。

```sh
rm -rf ./java/demo/src/main/java/com/example/demo
protoc --java_out=./java/demo/src/main/java --grpc-java_out=./java/demo/src/main/java proto/helloworld.proto
```

以下を実行する。

```sh
mvn clean package exec:java -Dexec.mainClass=com.example.App
```

## 参考

参考リンク集。

* [protocolbuffers/protobuf: Protocol Buffers - Google's data interchange format](https://github.com/protocolbuffers/protobuf)
* [サービス間通信のための新技術「gRPC」入門 | さくらのナレッジ](https://knowledge.sakura.ad.jp/24059/)

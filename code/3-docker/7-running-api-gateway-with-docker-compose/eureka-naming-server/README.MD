`URL`
```
http://localhost:8761/
```

```
spring-boot:build-image -DskipTests

docker.io/unfaithful/mmv3-naming-server:0.0.1-SNAPSHOT

docker run -p 9411:9411 openzipkin/zipkin:2.23

docker run -p 8761:8761 unfaithful/mmv3-naming-server:0.0.1-SNAPSHOT
```
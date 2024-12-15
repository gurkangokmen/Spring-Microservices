`URL`
```
http://localhost:8001/currency-exchange/from/USD/to/INR
```


`3-creating-container-image-for-currency-exchange-microservice`
```
spring-boot:build-image -DskipTests

docker run -p 9411:9411 openzipkin/zipkin:2.23

docker run -p 8001:8001 unfaithful/mmv3-currency-exchange-server:0.0.1-SNAPSHOT
```


`Information Link`
```
https://github.com/in28minutes/spring-microservices-v3/tree/main/04.docker
```
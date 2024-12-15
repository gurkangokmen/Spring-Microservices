<h1>URL</h1>

```
http://localhost:8001/currency-exchange/from/USD/to/INR

{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "environment": "8001"
}
```


```
http://localhost:8000/currency-exchange/from/USD/to/INR

{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "environment": "8000"
}
```

```
http://localhost:8001/sample-api
```

`Run Configurations`
```
Dserver.port=8001
```


`22-getting-started-with-circuit-breaker-resilience4j`
```
I added dependencies for actuator,aop and resilience4j.
I added a new controller for sample-api.
```


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>

<dependency>
    <groupId>io.github.resilience4j</groupId>
    <artifactId>resilience4j-spring-boot2</artifactId>
</dependency>
```
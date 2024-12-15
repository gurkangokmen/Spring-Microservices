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

<h1>Circuit Breaker (resilience4j)</h1>

1. Add Dependencies

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

2. Add Configurations

```
# how many times to retry is run
resilience4j.retry.instances.default-sample-api.maxAttempts=5

# I can configure
# what should be the interval between Retries.
# So if an API call is failing, I would wait
# for a little while and then make the API call again.
# I can configure how much time to wait for.
resilience4j.retry.instances.default-sample-api.waitDuration=1s

# Response takes a little bit more time.
# Why is it taking a little bit more time?
# Because each subsequent request
# it would wait for a little longer. (1s, 2s, 4s, 8s, 16s) (exponentially increasing)
resilience4j.retry.instances.default-sample-api.enableExponentialBackoff=true
```

3. Add @Retry Annotation

```java
@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);


    @GetMapping("/sample-api")
    @Retry(name = "default-sample-api", fallbackMethod = "hardcodedResponse")
    public String samleApi() {

        logger.info("Sample API call received");
        ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        return forEntity.getBody();

    }

    //
    // It must have Exception as the parameter, otherwise it will not work
    //
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
```

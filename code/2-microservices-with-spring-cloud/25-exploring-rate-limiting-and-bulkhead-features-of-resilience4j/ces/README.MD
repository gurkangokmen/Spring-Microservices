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

<h1>Circuit Breaker - BulkHead (resilience4j)</h1>

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
# 2 requests per 10 seconds
resilience4j.ratelimiter.instances.default.limitForPeriod=2
resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s

# Configure how many concurrent calls are allowed.
resilience4j.bulkhead.instances.default.maxConcurrentCalls=1
```

4. Add @Bulkhead, @RateLimiter Annotation
```java
@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
    @GetMapping("/sample-api")
    //@Retry(name = "default-sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")

    // 10s => 10000 calls to the sampleApi (in 10s only 10000 calls are allowed)
    @RateLimiter(name = "default")

    // Configure how many concurrent calls are allowed.
    @Bulkhead(name = "default")
    public String samleApi() {
        logger.info("Sample API call received");
        //ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        //return forEntity.getBody();

        return "Sample API";
    }

    //
    // It must have Exception as the parameter, otherwise it will not work
    //
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}
```



`24-playing-with-circuit-breaker-features-of-resilience4j`
```
I added CircuitBreaker annotation to the method in the controller class.
It has name and fallbackMethod attributes.
I did not add any configuration in the application.properties file.
```

`Deailed Documentation:`
```
https://resilience4j.readme.io/docs/circuitbreaker
```

FOR WINDOWS
- run this code in Wimdows PowerShell
```
while (1) {curl http://localhost:8001/sample-api; sleep 0.1}
```


`Result:`
```
The circuit breaker accepts requests for a while and then it stops accepting requests.
Then, it accepts 10 request each minute.
```






`25-exploring-rate-limiting-and-bulkhead-features-of-resilience4j`
```
I added the following settings to the application.properties


# 2 requests per 10 seconds
resilience4j.ratelimiter.instances.default.limitForPeriod=2
resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s

# Configure how many concurrent calls are allowed.
resilience4j.bulkhead.instances.default.maxConcurrentCalls=1
```




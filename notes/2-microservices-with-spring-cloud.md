## Table of Contents

* [Centralized Configuration](#centralized-configuration)
    * [Config Client](#config-client)
    * [Config Server](#config-server)
    * [Repo](#repo)
* [Connection between Microservices](#connection-between-microservices)
    * [Setting Up Dynamic Port in the Response](#setting-up-dynamic-port-in-the-response)
    * [Invoking CE from CCS using RestTemplate](#invoking-ce-from-ccs-using-resttemplate)
    * [Invoking CE from CCS using FeignClient](#invoking-ce-from-ccs-using-feignclient)
* [Service Discovery](#service-discovery)
    * [Eureka Naming Server](#eureka-naming-server)
        * [Feign + Eureka Client Steps](#feign--eureka-client-steps)
        * [EUREKA Server Steps](#eureka-server-steps)
* [Load Balancing](#load-balancing-eureka--feign)
* [Api Gateway](#api-gateway)
    * [Setting Up Spring Api Gateway](#setting-up-spring-api-gateway)
    * [Enabling Discovery Locator With Eureka for Spring Cloud Api Gateway](#enabling-discovery-locator-with-eureka-for-spring-cloud-api-gateway)
    * [Custom Url](#custom-url)

* [Circuit Breaker](#circuit-breaker)
    * [Getting Start with Resilience4j](#getting-start-with-resilience4j)
    * [Resilience4j @Retry and fallback method](#resilience4j-retry-and-fallback-method)
    * [Circuit Breaker Features (Alternative)](#circuit-breaker-features-alternative)
    * [Rate Limiting and Bulkhead]()

## Centralized Configuration

### Config Client

#### 1. pom.xml must consist properties
```xml
<properties>
    <java.version>22</java.version>
    <spring-cloud.version>2023.0.2</spring-cloud.version>
</properties>
```
#### 2. pom.xml must consist dependencyManagement
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
#### 3. pom.xml must consist spring-cloud-starter-config dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

#### 4. connect config server
```
spring.config.import=optional:configserver:http://localhost:8888
```

#### 5. Add @ConfigurationProperties
```
It must be matched with repo file name -> limits-service.properties

@ConfigurationProperties("limits-service")
```

### Config Server


#### 1. pom.xml must consist spring-cloud-config-server dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
#### 2. Add these configurations

`If you don't specify the default branch, the Error!`

spring.cloud.config.server.git.default-label=master

spring.cloud.config.server.git.uri=file:///E:/Computer Science/Java/Spring Boot/Spring Microservices/code/2-microservices-with-spring-cloud/git-localconfig-repo

#### 3. pom.xml must consist properties
```xml
<properties>
    <java.version>22</java.version>
    <spring-cloud.version>2023.0.2</spring-cloud.version>
</properties>
```
#### 4. pom.xml must consist dependencyManagement
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

### Repo

file name and contents must matched!
```
limits-service.properties

limits-service.minimum=1
limits-service.maximum=996
```

## Connection between Microservices

### Setting Up Dynamic Port in the Response

```java
@RestController
public class CurrencyExhangeController {

    @Autowired
    private Environment environment;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {

        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(65));


        String port = environment.getProperty("local.server.port");
        //currencyExchange.setEnvironment("8080"); //v1
        currencyExchange.setEnvironment(port); //v2
        return currencyExchange;
    }
}
```

`VM arguments ( Eclipse )`
```
-Dserver.port=8001
```

`Environment arguments ( IntelliJ )`
```
server.port=8001
```

### Invoking CE from CCS using RestTemplate

```java
@RestController
public class CurrencyConversionController {

    @GetMapping("currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
            ){

        //REST template can be used to make rest API calls.
        HashMap<String,String> uriVariables = new HashMap<>();
        uriVariables.put("from",from);
        uriVariables.put("to",to);

        ResponseEntity<CurrencyConversion> responseEntity = new RestTemplate().getForEntity("http://localhost:8000/currency-exchange/from/{from}/to/{to}", CurrencyConversion.class, uriVariables);

        CurrencyConversion currencyConversion = responseEntity.getBody();
        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment());
    }
}
```

### Invoking CE from CCS using FeignClient

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```


```java
@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }

}
```

```java
@RestController
public class CurrencyExhangeController {

    @Autowired
    private CurrencyExchangeRepository currencyExchangeRepository;

    @Autowired
    private Environment environment;


    // http://localhost:8000/currency-exchange/from/USD/to/INR
    // http://localhost:8000/currency-exchange/from/EUR/to/INR
    // http://localhost:8000/currency-exchange/from/AUD/to/INR
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {

        CurrencyExchange currencyExchange = currencyExchangeRepository.findByFromAndTo(from,to);

        if (currencyExchange==null){
            throw new RuntimeException("Unable to find data for "+ from + " to "+ to);
        }


        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port); //v2
        return currencyExchange;
    }
}
```

```java
// name -> client name, can be anything currency-exchange or currency-exchange2
// note: name -> can be anything in this scenerio (url is exists), other scenerio (url does not exist) cannot be anything!
@FeignClient(name = "currency-exchange",url = "localhost:8000")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
```

```java
@RestController
public class CurrencyConversionController {

    @Autowired
    private CurrencyExchangeProxy proxy;

    //http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/4
    @GetMapping("currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ){


        CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from,to);
        return new CurrencyConversion(
                currencyConversion.getId(),
                from,
                to,
                quantity,
                currencyConversion.getConversionMultiple(),
                quantity.multiply(currencyConversion.getConversionMultiple()),
                currencyConversion.getEnvironment()+" "+ "feign");
    }
}
```

## Service Discovery

### Eureka Naming Server

#### EUREKA Server Steps

1. Add Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

2. Add Configurations

```
# We are creating in here is a Eureka server.
# And
# We don't want to register with itself.

eureka.client.register-with-eureka = false
eureka.client.fetch-registry=false
```

3. Add @EnableEurekaServer Annotation

```java
@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
```

#### Feign + Eureka Client Steps

1-Add Dependencies

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2-Add Proxy (@FeignClient)
```java
@FeignClient(name = "currency-exchange",url = "localhost:8001")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
```

3-Enable Feign (@EnableFeignClients)
```java
@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CurrencyConversionServiceApplication.class, args);
    }
}
```
4-Add Configurations
```
eureka.client.serviceURL.defaultZone = http://localhost:8761/eureka
```

## Load Balancing (Eureka + Feign)

```java
// Name must match → spring.application.name=currency-exchange-server
// Also you can see name at  http://localhost:8761 (eureka server url)
@FeignClient(name = "currency-exchange-server") //WE REMOVE URL FOR LOAD BALANCE,  WE CANNOT SAY SPECIFIC URL! BECAUSE URLS=8000,8001
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
```

## Api Gateway


### Setting Up Spring Api Gateway

![Screenshot 2024-07-18 233118](https://github.com/user-attachments/assets/4dea3198-d7b3-44d2-9146-1c2d705b6350)


`Getting Start Api Gateway`

There would be hundreds microservices like these and these microservices
have a lot of common features like Authentication, authorization, logging, rate limiting.

Zool is not supported by netflix now, Spring Cloud Gateway is now POPULAR!

1. Add Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway-mvc</artifactId>
</dependency>
```

### Enabling Discovery Locator With Eureka for Spring Cloud Api Gateway

`Eureka Server`

1. Add Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-server</artifactId>
</dependency>
```

2. Add Configurations

```
# We are creating in here is a Eureka server.
# And
# We don't want to register with itself.

eureka.client.register-with-eureka = false
eureka.client.fetch-registry=false
```

3. Add @EnableEurekaServer Annotation

```java
@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
```

`Feign + Eureka Client`

1-Add Dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2-We need to do is to create a proxy.
```java

// Name must match → spring.application.name=currency-exchange-server
// Also you can see name at  http://localhost:8761 (eureka server url)
@FeignClient(name = "currency-exchange-server") //WE REMOVE URL FOR LOAD BALANCE,  WE CANNOT SAY SPECIFIC URL! BECAUSE URLS=8000,8001
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
```

3-Enable Feign (@EnableFeignClients)
```java
@SpringBootApplication
@EnableFeignClients
public class CurrencyConversionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CurrencyConversionServiceApplication.class, args);
	}

}
```


4-Add Configurations
```
eureka.client.serviceURL.defaultZone = http://localhost:8761/eureka
```

`Api Gateway + Eureka`

1. Add Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway-mvc</artifactId>
</dependency>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2. Add Configurations

```
spring.cloud.gateway.discovery.locator.enabled=true

eureka.client.serviceURL.defaultZone = http://localhost:8761/eureka
```

### Custom Url

`RouteLocatorBuilder`
`RouteLocator`

```java

@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder){

        //http://localhost:8765/get
        Function<PredicateSpec, Buildable<Route>> routeFunction
                = p -> p.path("/get") //A predicate that checks  if the path of the request matches the given pattern.


                .filters(f->f.addRequestHeader("MyHeader","MyURI") //Add a request header to the request before it is routed by the Gateway.
                        .addRequestParameter("Param","MyValue"))//Add a request parameter to the request before it is routed by the Gateway.
                .uri("http://httpbin.org:80")
                ;

        /**
         *         return builder.routes() //This line starts the process of building the routes using the RouteLocatorBuilder.
         *                 .route(p->p.path("/currency-exchange/**") //This specifies that any request with a path that matches the pattern /currency-exchange/** should be routed.
         *                 .uri("lb://currency-exchange-server")) //This specifies that the matched requests should be routed to the URI lb://currency-exchange-server. The lb:// prefix indicates that this URI is a logical name for a load-balanced service. The actual service instances will be discovered by a load balancer (typically provided by Spring Cloud).
         *                 .build(); //This finalizes the route creation and builds the RouteLocator object.
         *
         */


        /**
         *           return builder.routes() //This starts the definition of a series of routes using the provided RouteLocatorBuilder. routes() is a method of RouteLocatorBuilder that returns a fluent builder to define routes.
         *                           .route(p -> p.path("/currency-conversion-new/**") //This defines a route. The route method takes a lambda expression as a parameter. The lambda expression receives a PredicateSpec object p and configures it. Here, p.path("/currency-conversion-new/**") specifies that this route will match any request with a path that starts with /currency-conversion-new/.
         *                           .filters(f -> f.rewritePath("/currency-conversion-new/(?<segment>.*)", "/currency-conversion-feign/${segment}")) // This configures filters for the route. The filters method takes a lambda expression as a parameter, which receives a GatewayFilterSpec object f. The rewritePath filter rewrites the path of the request before forwarding it. Here, it captures the part of the path after /currency-conversion-new/ and appends it to /currency-conversion-feign/.
         *                           .uri("lb://currency-conversion-service")) //This sets the URI of the route. The uri method specifies the destination for the matched request. Here, lb://currency-conversion-service indicates that the request should be load-balanced to instances of the currency-conversion-service registered with the service registry.
         *                           .build(); //This finalizes the route definitions and builds the RouteLocator object, which is then returned by the gatewayRouter method.
         */

        /**
         * Segment
         *
         * We capture rest of url, and reuse them!
         */
        return builder.routes()
                .route(routeFunction)
                .route(p->p.path("/currency-exchange/**").uri("lb://currency-exchange-server")) //name of eureka server of ces
                .route(p->p.path("/currency-conversion/**").uri("lb://currency-conversion-service")) //name of eureka server of ccs
                .route(p->p.path("/currency-conversion-feign/**").uri("lb://currency-conversion-service")) //name of eureka server of ccs
                .route(p->p.path("/currency-conversion-new/**")
                                .filters(f->f.rewritePath("/currency-conversion-new/(?<segment>.*)","/currency-conversion-feign/${segment}"))

                        .uri("lb://currency-conversion-service"))
                .build();
    }
}

```

### Logging Filter

`LoggerFactory`
`GlobalFilter`

```java
@Component
public class LoggingFilter implements GlobalFilter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        logger.info("Path of the request reveive -> {}",exchange.getRequest().getPath());
        return chain.filter(exchange);
    }
}
```

## Circuit Breaker

https://resilience4j.readme.io/docs/circuitbreaker

### Getting Start with Resilience4j

`Add Dependencies`

`You must add also apo andactuator with resilience4j`

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

### Resilience4j @Retry and fallback method

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

### Circuit Breaker Features (Alternative)

```java
@RestController
public class CircuitBreakerController {

    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);


    @GetMapping("/sample-api")
    //@Retry(name = "default-sample-api", fallbackMethod = "hardcodedResponse")
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
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

### Rate Limiting and Bulkhead

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


```
# 2 requests per 10 seconds
resilience4j.ratelimiter.instances.default.limitForPeriod=2
resilience4j.ratelimiter.instances.default.limitRefreshPeriod=10s

# Configure how many concurrent calls are allowed.
resilience4j.bulkhead.instances.default.maxConcurrentCalls=1
```
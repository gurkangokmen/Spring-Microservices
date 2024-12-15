`URL`
```
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 10,
  "totalCalculatedAmount": 650,
  "environment": "8000 rest template"
}
```


```
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10


{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 10,
  "totalCalculatedAmount": 650,
  "environment": "8001 feign"
}
```
<h1>Feign + Eureka Client</h1>

<h1>Steps For Feign</h1>

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


<h1>Getting Start EUREKA Server Guide</h1>

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

```
@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
```1. Add Dependency

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

```
@SpringBootApplication
@EnableEurekaServer
public class NamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(NamingServerApplication.class, args);
	}

}
```
<h1>Steps</h1>


1-Add Dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```

2-Add Proxy (@FeignClient)
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





---

<h1>URL REST TEMPLATE</h1>

```
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

Response Structure

{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 10,
  "totalCalculatedAmount": 650,
  "environment": "8000 rest template"
}
```

```
http://localhost:8100/currency-conversion/from/EUR/to/INR/quantity/10

Response Structure

{
  "id": 10002,
  "from": "EUR",
  "to": "INR",
  "conversionMultiple": 75,
  "quantity": 10,
  "totalCalculatedAmount": 750,
  "environment": "8000 rest template"
}
```

```
http://localhost:8100/currency-conversion/from/AUD/to/INR/quantity/10

Response Structure

{
  "id": 10003,
  "from": "AUD",
  "to": "INR",
  "conversionMultiple": 25,
  "quantity": 10,
  "totalCalculatedAmount": 250,
  "environment": "8000 rest template"
}
```

<h1>URL FEIGN</h1>

```
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

Response Structure

{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 10,
  "totalCalculatedAmount": 650,
  "environment": "8000 feign"
}
```

```
http://localhost:8100/currency-conversion-feign/from/EUR/to/INR/quantity/10

Response Structure

{
  "id": 10002,
  "from": "EUR",
  "to": "INR",
  "conversionMultiple": 75,
  "quantity": 10,
  "totalCalculatedAmount": 750,
  "environment": "8000 feign"
}
```

```
http://localhost:8100/currency-conversion-feign/from/AUD/to/INR/quantity/10

Response Structure

{
  "id": 10003,
  "from": "AUD",
  "to": "INR",
  "conversionMultiple": 25,
  "quantity": 10,
  "totalCalculatedAmount": 250,
  "environment": "8000 feign"
}
```
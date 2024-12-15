`URL`
```
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
```


`Response Structure`
```
{
"id": 10001,
"from": "USD",
"to": "INR",
"conversionMultiple": 65.00,
"quantity": 10,
"totalCalculatedAmount": 650.00,
"environment": "8000 instance-id"
}
```

`NOTE: NOW WE DO NOT MAKE CONNECTION NAMING SERVER AND CCS`

`WE WILL MAKE CONNECTIONS IN NEXT STEP`

`PROBLEM`
```
localhost:8000 is hard coded! It can be 8001.

@FeignClient(name = "currency-exchange",url = "localhost:8000")
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}
```

`SOLUTION ==> EUREKA NAMING SERVER (LOAD BALANCING)`


<h1>Steps</h1>

```
1-Add Dependency

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
```
```
2-We need to do is to create a proxy.
```
```
3-Enable Feign

Add @EnableFeignClients to Class that includes main method.
```
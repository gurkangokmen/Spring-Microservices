`URL`
```
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
```
```
http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10
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

```
spring-boot:build-image -DskipTests

unfaithful/mmv3-currency-conversion-service:0.0.1-SNAPSHOT

docker run -p 9411:9411 openzipkin/zipkin:2.23

docker run -p 8100:8100 unfaithful/mmv3-currency-conversion-service:0.0.1-SNAPSHOT
```
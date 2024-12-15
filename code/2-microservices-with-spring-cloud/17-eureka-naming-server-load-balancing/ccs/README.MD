`URL`
```
http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10
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

{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "quantity": 10,
  "totalCalculatedAmount": 650,
  "environment": "8000 feign"
}
```


<h1>Feign + Eureka Client</h1>

<h1>Steps For Feign</h1>

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

```
4-Add Configurations

eureka.client.serviceURL.defaultZone = http://localhost:8761/eureka
```

<h1>Getting Start EUREKA Client Guide</h1>

1. Add Dependency

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

2. Add Configurations
```
eureka.client.serviceURL.defaultZone=http://localhost:8761/eureka
```
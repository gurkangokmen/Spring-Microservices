`URL`
```
http://localhost:8080/currency-exchange/from/USD/to/INR
```


`Response Structure`
```
{
"id":10001,
"from":"USD",
"to":"INR",
"conversionMultiple":65.00,
"environment":"8000 instance-id"
}
```

`Run Configurations`
```
Dserver.port=8001
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
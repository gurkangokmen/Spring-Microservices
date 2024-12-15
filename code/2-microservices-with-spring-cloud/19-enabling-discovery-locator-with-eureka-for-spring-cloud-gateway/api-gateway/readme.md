18-setting-up-spring-cloud-api-gateway
```
Spring Cloud Gateway
```
http://localhost:8761

![Screenshot 2024-07-18 233118](https://github.com/user-attachments/assets/4dea3198-d7b3-44d2-9146-1c2d705b6350)


19-enabling-discovery-locator-with-eureka-for-spring-cloud-gateway

Initial

- http://localhost:8765/CURRENCY-EXCHANGE-SERVER/currency-exchange/from/USD/to/INR

- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/10



Lower Case

- http://localhost:8765/currency-exchange-server/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion-service/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10



Custom Routes

- http://localhost:8765/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10



<h1>Getting Start Api Gateway</h1>

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

2. Add Configurations

```
spring.cloud.gateway.discovery.locator.enabled=true
```
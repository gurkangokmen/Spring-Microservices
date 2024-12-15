```
Spring Cloud Gateway
```
http://localhost:8761

![Screenshot 2024-07-18 233118](https://github.com/user-attachments/assets/4dea3198-d7b3-44d2-9146-1c2d705b6350)



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
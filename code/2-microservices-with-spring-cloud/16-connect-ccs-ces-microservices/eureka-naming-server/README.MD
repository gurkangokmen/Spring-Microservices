`URL`
```
http://localhost:8761/
```

<h1>Getting Start EUREKA Guide</h1>

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
```
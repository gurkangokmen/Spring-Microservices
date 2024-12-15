### We need to add h2 dependency and data-jpa dependency
```
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```
```
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
### We must made this configuration to enable h2 database console!
```
spring.h2.console.enabled=true
```
### We wrote data.sql and we see error! Table "USER_DETAILS" not found
### Because the data.SQL is getting executed before our tables are created in H2 database.
### We want to delay
### Solution: this property make delay (defer=ertelemek)
```
spring.jpa.defer-datasource-initialization=true
```

### Note: data.sql automaticly executed



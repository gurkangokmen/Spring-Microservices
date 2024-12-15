# Install Docker

🌐https://docs.docker.com/engine/install/

🌐https://docs.docker.com/desktop/install/windows-install/

`To be sure installation is successful`
```
docker --version
```

# 💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛💛
# Necessary Procedures for Working with Docker

## Launch MySQL as Docker Container

```
docker run --detach 
--env MYSQL_ROOT_PASSWORD=dummypassword 
--env MYSQL_USER=social-media-user 
--env MYSQL_PASSWORD=dummypassword 
--env MYSQL_DATABASE=social-media-database 
--name mysql 
--publish 3307:3306 
mysql:8-oracle
```
```
docker run --detach 
--env MYSQL_ROOT_PASSWORD=12345 
--env MYSQL_PASSWORD=12345 
--env MYSQL_DATABASE=social-media-database 
--name mysql 
--publish 3307:3306 
mysql:8-oracle
```

`Let's dive into to details of docker command`
```
mysql:8-oracle ➡️ mysql:8-oracle is the complete name and the version of this MySQL image.
               ➡️ This specific tag "8-oracle" of mysql can run on any operating system.


```


`But firstly you must be logged in (username and password used in https://login.docker.com/)`
```
docker login
```

`List active containers`
```
docker container ls
```

## Check MySQL Tables
`Firstly we enable/convert console mysql shell available`
```
mysqlsh
```
`We connect database`
```
\connect social-media-user@localhost:3307
```
`We set social-media-database default schema`
```
\use social-media-database
```
`Switch Sql Mode (You were in sql mode before, it gives an error when you writes sql queries)`
```
\sql
```
`If you see results, no problem 💘💘`
```
select * from user_details;
```
```
select * from post;
```



# Restfull Web Services

## Table of Contents

* [Swagger OpenApi Documentation](#swagger-openapi-documentation)
* [Content Negotiation](#content-negotiation)
* [Internationalization](#internationalization)
* [Hateoas](#hateoas)
* [Filtering](#filterig)
    * [Static Filtering](#static-filterig)
    * [Dynamic Filtering](#dynamic-filterig)
* [Actuator | Monitoring APIs](#actuator--monitoring-apis)
* [HAL Explorer](#hal-explorer)
* [H2 Database](#h2-database)

## Swagger OpenApi Documentation

https://springdoc.org/

https://github.com/springdoc/springdoc-openapi

localhost:8080/swagger-ui.html

```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
    <version>2.3.0</version>
</dependency>
```


## Content Negotiation

```
<dependency>
     <groupId>com.fasterxml.jackson.dataformat</groupId>
     <artifactId>jackson-dataformat-xml</artifactId>
</dependency>
```

`Accept header: application/xml, application/json ...`

```
Postman → Automaticly JSON
Chrome → Automaticly XML
Edge → Automaticly XML
```

## Internationalization

```
File names affect result!
```

`They should be named as follows:`

```
messages.properties
messages_nl.properties
```


`Accept-Language header - indicates natural language and locale that the consumer prefers`
```
Example: en - English (Good Morning)
Example: nl - Dutch (Goedemorgen)
Example: fr - French (Bonjour)
Example: de - Deutsch (Guten Morgen)
```

`By Default: Good Morning`
```
http://localhost:8080/hello-world-internationalized
```


```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	private MessageSource messageSource;

	@Autowired //Constructor Injection
	public HelloWorldController(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

    //
    // Internationalization
    //
    @GetMapping(path = "/hello-world-internationalized")
    public String helloWorldInternationalized()
    {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "Default Message", locale);

        //return "Hello World V2";
    }

}

```

`messages.properties`
```
good.morning.message=Good Morning
```

`messages_de.properties`
```
good.morning.message=Guten Morgen
```

`messages_fe.properties`
```
good.morning.message=Bonjour
```

`messages_nl.properties`
```
good.morning.message=Goedemorgen
```

## Hateoas

```java
@GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // http://localhost:8080/users/

    // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user==null){throw new UserNotFoundException("id-"+ id);}

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
```

`http://localhost:8080/users/1`

```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "1994-10-25",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
```

## Filterig

### Static Filterig

```java
/**
 * We don't see any values because all fields are ignored. So, any of them (@JsonIgnoreProperties, @JsonIgnore) is not dominant over the other.
 */
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"field1","field3"}) //If you don't anything, it doesn't affect!
public class SomeBean {
    private String field1;

    @JsonIgnore
    private String field2;
    private String field3;

}
```

```java
@RestController
public class FilteringController {

    //
    // http://localhost:8080/filtering
    //

    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1","value2","value3");
    }

    //
    //http://localhost:8080/filtering-list
    //

    // Filtering works even Lists
    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
    }
}
```

`We don't see any values`


### Dynamic Filterig

```java
@JsonFilter("SomeBeanFilter")
MappingJacksonValue 
SimpleBeanPropertyFilter
FilterProvider
```

```java
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;

}
```

```java
@GetMapping("/filtering")
public MappingJacksonValue filtering(){
    SomeBean someBean = new SomeBean("value1", "value2", "value3");
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);


    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
}

// Filtering works even Lists
@GetMapping("/filtering-list")
public MappingJacksonValue filteringList(){
    List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
}
```


## Actuator | Monitoring APIs


```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```


```text
By Default, health is active
```

`To active all!`
```properties
management.endpoints.web.exposure.include= *
```

## HAL Explorer

```
<dependency>
    <groupId>org.springframework.data</groupId>
    <artifactId>spring-data-rest-hal-explorer</artifactId>
</dependency>
```

```
http://localhost:8080/explorer/index.html#uri=/

http://localhost:8080/explorer/index.html#uri=/filtering

http://localhost:8080/explorer/index.html#uri=/users

http://localhost:8080/explorer/index.html#uri=/users/1
```

## H2 Database

` We need to add h2 dependency and data-jpa dependency`
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


` We must made this configuration to enable h2 database console!`
```
spring.h2.console.enabled=true
```
` We wrote data.sql and we see error! Table "USER_DETAILS" not found`

` Because the data.SQL is getting executed before our tables are created in H2 database.`

` We want to delay`
` Solution: this property make delay (defer=ertelemek)`
```
spring.jpa.defer-datasource-initialization=true
```

` Note: data.sql automaticly executed`

` Make dynamic url to static h2 database url`
```
spring.datasource.url=jdbc:h2:mem:testdb
```
`To Access H2 Database Console`
```
http://localhost:8080/h2-console
```
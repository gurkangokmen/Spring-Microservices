```
# To Access Database Console, It must be enabled
spring.h2.console.enabled = true
```

```
http://localhost:8080/h2-console
```

```
# H2 db url is dynamic, now we set fix url
spring.datasource.url=jdbc:h2:mem:testdb
```
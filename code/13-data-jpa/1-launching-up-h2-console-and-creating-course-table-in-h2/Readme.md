```
# TO ACCESS DB, FIRSTLY IT MUST BE ENABLED
spring.h2.console.enabled = true
```

```
http://localhost:8080/h2-console
```

```
# H2 db url is dynamic, now we set fix url
spring.datasource.url=jdbc:h2:mem:testdb
```

`Important Note`
```
If we have devtools, always h2 console enabled if you don't write spring.h2.console.enabled=true
```

# Important
```
limits-service.properties
⬇️
http://localhost:8888/limits-service/default


limits-service.properties

limits-service.minimum=1
limits-service.maximum=996
```

```
limits-service-dev.properties
⬇️
http://localhost:8888/limits-service/dev

limits-service-dev.properties

limits-service.minimum=5
limits-service.maximum=995
```

```
limits-service-qa.properties
⬇️
http://localhost:8888/limits-service/qa

limits-service-qa.properties

limits-service.minimum=6
limits-service.maximum=994
```

# Notes

`Client that connects to a Spring Cloud Config Server to fetch that application's configuration.`
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

`Central management for configuration via Git,SVN, or HashiCorp Vault.`
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```


`pwd ➡️ show directory path`

```
Gürkan Gökmen@DESKTOP-QBB5RMN MINGW64 /e/Computer Science/Java/Spring Boot/Spring Microservices/code/2-microservices-with-spring-cloud/git-localconfig-repo (master)
$ pwd
/e/Computer Science/Java/Spring Boot/Spring Microservices/code/2-microservices-with-spring-cloud/git-localconfig-repo
```

```
limits-service in url ➡️ name of file in git-localconfig-repo (limits-service.properties) so they are mapping!
http://localhost:8888/limits-service/default
```



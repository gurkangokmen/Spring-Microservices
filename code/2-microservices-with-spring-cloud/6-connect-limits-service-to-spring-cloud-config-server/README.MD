# Getting Spring-Cloud-Starter-Config

For Config Server, You must do five steps.

## 1. pom.xml must consist properties
```xml
<properties>
    <java.version>22</java.version>
    <spring-cloud.version>2023.0.2</spring-cloud.version>
</properties>
```
## 2. pom.xml must consist dependencyManagement
```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.2</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```
## 3. pom.xml must consist spring-cloud-starter-config dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

## 4. connect config server
```
spring.config.import=optional:configserver:http://localhost:888
```

## 5. Add @ConfigurationProperties
```
It must be matched with repo file name -> limits-service.properties

@ConfigurationProperties("limits-service")
```

---

# Note

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

# Getting Spring-Cloud-Config-Server with Repo

## 1. pom.xml must consist spring-cloud-config-server dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
## 2. Add these configurations

`If you don't specify the default branch, the Error!`

spring.cloud.config.server.git.default-label=master

spring.cloud.config.server.git.uri=file:///E:/Computer Science/Java/Spring Boot/Spring Microservices/code/2-microservices-with-spring-cloud/git-localconfig-repo

## 3. pom.xml must consist properties
```xml
<properties>
    <java.version>22</java.version>
    <spring-cloud.version>2023.0.2</spring-cloud.version>
</properties>
```
## 4. pom.xml must consist dependencyManagement
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





# Repo

file name and contents must matched!
```
limits-service.properties

limits-service.minimum=1
limits-service.maximum=996
```

# Notes

`Client that connects to a Spring Cloud Config Server to fetch that application's configuration.`
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

`Central management for configuration via Git,SVN, or HashiCorp Vault.`
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```





```
limits-service.properties (NOTE: Revise 7-configuring-profiles-for-limits-service Project)
⬇️
http://localhost:8888/limits-service/default
```
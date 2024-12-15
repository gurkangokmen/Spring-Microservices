# Getting Spring-Cloud-Config-Server

## 1. pom.xml must consist spring-cloud-config-server dependency
```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```



# Notes
<h2>Config Client<h2/>
`Client that connects to a Spring Cloud Config Server to fetch that application's configuration.`
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```



<h2>Config Server<h2/>
`Central management for configuration via Git,SVN, or HashiCorp Vault.`
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-config-server</artifactId>
</dependency>
```
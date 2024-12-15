# Getting Spring-Cloud-Starter-Config

For Config Client, You must do five steps.

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


# Errors & Solutions

### 💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘💘
#### Error Name: Could Not Acquire Lock(s)
#### File: pom.xml

![Screenshot 2024-06-28 221912](https://github.com/gurkangokmen/algorithms/assets/122023578/04155c56-286f-450b-9fc7-28545505f105)
```
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>3.3.1</version>
    <relativePath/> <!-- lookup parent from repository -->
</parent>
```
ERROR VERSION

```
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```


CORRECT VERSION
```
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

`I wrote 2023.0.2 instead of ${spring-cloud.version}, and no errors!`

# Notes

`Client that connects to a Spring Cloud Config Server to fetch that application's configuration.`
```
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```
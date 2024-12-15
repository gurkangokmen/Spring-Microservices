`URL`
```
http://localhost:8001/currency-exchange/from/USD/to/INR
```


`1-connecting-currency-exchange-microservice-with-zipkin`
```
DO NOT FORGET RUN OPEN ZIPKIN SERVER

docker run -p 9411:9411 openzipkin/zipkin:2.23
```


`Information Link`
```
https://github.com/in28minutes/spring-microservices-v3/blob/main/v3-upgrade.md
```


`Micrometer`
```
Micrometer - Vendor-neutral application observability facade. 
Instrument your JVM-based application code without vendor lock-in.  
Observation (Metrics & Logs) + Tracing.

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-observation</artifactId>
</dependency>
```

`Open Telemetry as Bridge`
```
Simplified Observability (metrics, logs, and traces)


<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-otel</artifactId>
</dependency>

<dependency>
    <groupId>io.opentelemetry</groupId>
    <artifactId>opentelemetry-exporter-zipkin</artifactId>
</dependency>
```



`OPTION 2: Brave as Bridge`

```
Only supports tracing not supports metrics and logs so we use open telemetry as bridge

<dependency>
    <groupId>io.micrometer</groupId>
    <artifactId>micrometer-tracing-bridge-brave</artifactId>
</dependency>

<dependency>
    <groupId>io.zipkin.reporter2</groupId
    <artifactId>zipkin-reporter-brave</artifactId>
</dependency>
```

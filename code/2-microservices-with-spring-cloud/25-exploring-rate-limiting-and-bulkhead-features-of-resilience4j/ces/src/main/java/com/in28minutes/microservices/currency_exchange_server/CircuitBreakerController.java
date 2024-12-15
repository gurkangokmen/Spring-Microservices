package com.in28minutes.microservices.currency_exchange_server;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CircuitBreakerController {
    private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);


    // http://localhost:8001/sample-api
    @GetMapping("/sample-api")
    //@Retry(name = "default-sample-api", fallbackMethod = "hardcodedResponse")
    //@CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")

    // 10s => 2 calls to the sampleApi (in 10s only 2 calls are allowed)
    @RateLimiter(name = "default")

    // Configure how many concurrent calls are allowed.
    @Bulkhead(name = "default")
    public String samleApi() {
        logger.info("Sample API call received");
        //ResponseEntity<String> forEntity = new RestTemplate().getForEntity("http://localhost:8080/some-dummy-url", String.class);
        //return forEntity.getBody();

        return "Sample API";
    }

    //
    // It must have Exception as the parameter, otherwise it will not work
    //
    public String hardcodedResponse(Exception ex) {
        return "fallback-response";
    }
}

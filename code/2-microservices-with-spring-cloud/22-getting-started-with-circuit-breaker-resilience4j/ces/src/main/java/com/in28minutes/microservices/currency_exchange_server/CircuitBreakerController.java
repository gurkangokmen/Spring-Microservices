package com.in28minutes.microservices.currency_exchange_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerController {

    @GetMapping("/sample-api")
    public String samleApi() {
        return "Sample API";
    }
}

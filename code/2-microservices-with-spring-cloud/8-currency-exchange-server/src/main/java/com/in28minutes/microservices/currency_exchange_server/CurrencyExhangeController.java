package com.in28minutes.microservices.currency_exchange_server;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExhangeController {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
        return new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(65));
    }
}

package com.in28minutes.microservices.currency_exchange_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.math.BigDecimal;

@RestController
public class CurrencyExhangeController {

    private Logger logger = LoggerFactory.getLogger(CurrencyExhangeController.class);

    @Autowired
    private Environment environment;


    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {

        //Trace ID: 9804a825b41e428febc9c60ea17a79f5
        //Span ID: 4020a211ab88ac54
        //2024-08-22T12:12:25.298+03:00  INFO [currency-exchange-server,9804a825b41e428febc9c60ea17a79f5,4020a211ab88ac54] 25624 --- [currency-exchange-server] [nio-8001-exec-1] [9804a825b41e428febc9c60ea17a79f5-4020a211ab88ac54] c.i.m.c.CurrencyExhangeController        : retrieveExchangeValue called with USD to INR
        logger.info("retrieveExchangeValue called with {} to {}", from, to);


        CurrencyExchange currencyExchange = new CurrencyExchange(1000L, "USD", "INR", BigDecimal.valueOf(65));
        String port = environment.getProperty("local.server.port");
        //currencyExchange.setEnvironment("8080"); //v1
        currencyExchange.setEnvironment(port); //v2
        return currencyExchange;
    }
}

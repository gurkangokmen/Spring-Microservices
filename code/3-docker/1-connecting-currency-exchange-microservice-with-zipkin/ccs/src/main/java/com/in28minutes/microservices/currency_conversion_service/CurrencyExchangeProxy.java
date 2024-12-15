package com.in28minutes.microservices.currency_conversion_service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-server",url = "localhost:8000")
@FeignClient(name = "currency-exchange-server") //WE REMOVE URL FOR LOAD BALANCE,  WE CANNOT SAY SPECIFIC URL! BECAUSE URLS=8000,8001
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion retrieveExchangeValue(
            @PathVariable String from,
            @PathVariable String to);

}

/**
 @FeignClient(name = "currency-exchange-server2") ====> [503] during [GET] to [http://currency-exchange-server2/currency-exchange/from/USD/to/INR]
 */

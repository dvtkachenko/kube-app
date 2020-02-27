package com.dvt.kube.app.currency.conversion.service;

//import org.springframework.cloud.netflix.ribbon.RibbonClient;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name="currency-exchange-service", url="localhost:8001")
//@FeignClient(name="currency-exchange-service")
//@FeignClient(name="zuul-api-gateway-server")
//@RibbonClient(name="currency-exchange-service")
public interface CurrencyExchangeServiceProxy {
//    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    // with using ZUUL service name appended
    @GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversionHolder getExchangeValue(@PathVariable("from") String from, @PathVariable("to") String to);
}

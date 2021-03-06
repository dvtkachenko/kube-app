package com.dvt.kube.app.currency.exchange.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment environment;

    @Autowired
    private ExchangeValueRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);
        // env property as VM parameter  e.g. -Dserver.port=8001
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        logger.info("Currency exchanged from {} to {} -> exchange value {}", from, to, exchangeValue);
        return exchangeValue;
    }

    @GetMapping("/execute")
    public String execute() {
        logger.info("endpoint '/execute' is executed");
        return "It is executed";
    }
}

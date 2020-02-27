package com.dvt.kube.app.currency.exchange.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CurrencyExchangeServiceApplicationFullIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private CurrencyExchangeController controller;

	TestRestTemplate restTemplate = new TestRestTemplate();
	HttpHeaders headers = new HttpHeaders();

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	void shouldExchange() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<ExchangeValue> response = restTemplate.exchange(
				createURLWithPort("/currency-exchange/from/{from}/to/{to}"),
				HttpMethod.GET, entity, ExchangeValue.class,"USD","UAH");

		ExchangeValue responseBody = response.getBody();

		assertThat(responseBody.getFrom()).isEqualTo("USD");
		assertThat(responseBody.getTo()).isEqualTo("UAH");
		assertThat(responseBody.getExchangeRate().intValue()).isEqualTo(24);
	}

	@Test
	void shouldExecuted() throws Exception {
		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		ResponseEntity<String> response = restTemplate.exchange(
				createURLWithPort("/execute"), HttpMethod.GET, entity, String.class);
		String expected = "It is executed";

		assertThat(response.getBody()).isEqualTo(expected);
	}

	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
}

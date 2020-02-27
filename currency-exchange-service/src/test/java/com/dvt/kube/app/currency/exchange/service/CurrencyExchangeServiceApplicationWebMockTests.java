package com.dvt.kube.app.currency.exchange.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {"local.server.port=8000"})
class CurrencyExchangeServiceApplicationWebMockTests {

	@Autowired
	private CurrencyExchangeController controller;

	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

	@Test
	public void shouldExchange() throws Exception {
		this.mockMvc.perform(get("/currency-exchange/from/{from}/to/{to}", "USD", "UAH"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().json("{\n \"id\": 1001,\n  \"from\": \"USD\"," +
						"\n  \"to\": \"UAH\",\n  \"exchangeRate\": 24.00,\n  \"port\": 8000\n}"));
	}

	@Test
	public void shouldExecute() throws Exception {
		this.mockMvc.perform(get("/execute"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().string(containsString("It is executed")));
	}
}

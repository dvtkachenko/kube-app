package com.dvt.kube.app.datasource.service;

import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DatasourceServiceApplicationTests {

	@Autowired
	private DatasourceController controller;

	@Test
	void contextLoads() {
	}

	@Test
	public void shouldBeSuccessful() {
		DatasourceResponse response = controller.executeRequest();
		Assertions.assertNull(response);
	}
}

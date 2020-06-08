package com.dvt.kube.app.datasource.service;

import com.dvt.kube.app.datasource.service.configuration.ClientConfiguration;
import com.dvt.kube.app.datasource.service.connector.client.DatasourceClient;
import com.dvt.kube.app.datasource.service.connector.client.DatasourceHttp11Client;
import com.dvt.kube.app.datasource.service.domain.DatasourceRequest;
import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.Duration;

class DatasourceClientTests {

	private static DatasourceClient datasourceClient;

	@BeforeAll
	public static void setup() {
//		datasourceClient = new DatasourceHttp11Client(new ClientConfiguration().getDatasourceHttp11Client());
		datasourceClient = new DatasourceHttp11Client(createDatasourceHttp11Client());
	}

	@Test
	public void shouldBeSuccessful() {

		String requestBody = "<Parameters>\r\n    <FirstName>John</FirstName>\r\n    <LastName>Smith</LastName>\r\n    " +
				"<PhoneNumber1>22222222</PhoneNumber1>\r\n    <PhoneNumber2>22222222</PhoneNumber2>\r\n</Parameters>";

		DatasourceRequest request = new DatasourceRequest();
		request.setUrl("https://identity");
		request.setPayload(requestBody);

		DatasourceResponse response = datasourceClient.callDatasource(request);
		Assertions.assertNull(response);
	}

	private static HttpClient createDatasourceHttp11Client() {

		return HttpClient.newBuilder()
				.version(HttpClient.Version.HTTP_1_1)
				.connectTimeout(Duration.ofMillis(5000))
//                .followRedirects(HttpClient.Redirect.NORMAL)
				.proxy(getProxy())
				.build();
	}

//    private static ProxySelector getProxy() {
//        return ProxySelector.of(new InetSocketAddress("127.0.0.1", 8888));
//    }

	private static ProxySelector getProxy() {
		return ProxySelector.getDefault();
	}
}

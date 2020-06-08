package com.dvt.kube.app.datasource.service.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLParameters;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Optional;

@Configuration
public class ClientConfiguration {

    @Value("${https.protocols:}")
    private String[] protocolsProperty;

    @Value("${https.cipherSuites:}")
    private String[] cipherSuitesProperty;

    @Bean
    public HttpClient getDatasourceHttp11Client() {

//        String[] ciphers = { "TLS_RSA_WITH_DES_CBC_SHA" }; // TLSv1.0
////        String[] protocols = { "TLSv1.0" };
////        String[] ciphers = { "TLS_AES_256_GCM_SHA384" };
////        String[] protocols = { "TLSv1.3" };
//            protocols = new String [1];
//            protocols[0] = "TLSv1.2";

        if (protocolsProperty.length == 0 || cipherSuitesProperty.length == 0) {
            return HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
//                    .connectTimeout(Duration.ofMillis(5000))
                    .connectTimeout(Duration.ofMillis(50000))
                    .proxy(getProxy())
                    .build();
        } else {
            SSLParameters sslParameters = new SSLParameters();
            String[] protocols = protocolsProperty;
            String[] ciphers = cipherSuitesProperty;
            sslParameters.setProtocols(protocols);
            sslParameters.setCipherSuites(ciphers);

            return HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .connectTimeout(Duration.ofMillis(5000))
                    .sslParameters(sslParameters)
                    .proxy(getProxy())
                    .build();
        }
    }

//    private ProxySelector getProxy() {
//        return ProxySelector.of(new InetSocketAddress("127.0.0.1", 8888));
//    }

    private ProxySelector getProxy() {
        return ProxySelector.getDefault();
    }

    @Bean
    public RestTemplate getRestTemplate() {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setReadTimeout(50000);
        RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
        return restTemplate;
    }

//    @Bean
    public RestTemplate getHttpUrlConnectionRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}

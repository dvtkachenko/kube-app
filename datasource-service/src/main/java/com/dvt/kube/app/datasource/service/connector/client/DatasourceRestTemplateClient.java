package com.dvt.kube.app.datasource.service.connector.client;

import com.dvt.kube.app.datasource.service.domain.DatasourceRequest;
import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;
import com.dvt.kube.app.datasource.service.exception.DatasourceServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

// This new RestTemplate() uses java.net.HttpUrlConnection and his implementation is sun/net/www/protocol/http/HttpURLConnection.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/net/www/protocol/http/HttpURLConnection.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/net/www/http/HttpClient.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/net/NetworkClient.java
//  protected Socket doConnect (String server, int port)
//      protected Socket createSocket() throws IOException {
//        return new java.net.Socket(Proxy.NO_PROXY);  // direct connection
//    }
// https://github.com/openjdk-mirror/jdk7u-jdk/blob/master/src/share/classes/java/net/Socket.java
// https://github.com/openjdk-mirror/jdk7u-jdk/blob/f4d80957e89a19a29bb9f9807d2a28351ed7f7df/src/solaris/classes/java/net/PlainSocketImpl.java
//
//    On Unix systems we simply delegate to native methods
//    native void socketCreate(boolean isServer) throws IOException;
//    native void socketConnect(InetAddress address, int port, int timeout) throws IOException;
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/windows/native/libnet/PlainSocketImpl.c
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/unix/native/libnet/PlainSocketImpl.c

//  new RestTemplate(new HttpComponentsClientHttpRequestFactory()) is Apache HttpClient based
// https://github.com/apache/httpcomponents-client/tree/master/httpclient5/src/main/java/org/apache/hc/client5/http/socket

@Component
@RequiredArgsConstructor
@Slf4j
public class DatasourceRestTemplateClient implements DatasourceClient {

    private final RestTemplate restTemplate;

    @Override
    public DatasourceResponse callDatasource(DatasourceRequest datasourceRequest) {

        DatasourceResponse datasourceResponse = new DatasourceResponse();

//        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.setAccept(MediaType.APPLICATION_JSON);
        HttpEntity<String> request =
                new HttpEntity<String>((String)datasourceRequest.getPayload(), headers);

        String rawResponse =
                restTemplate.postForObject(getURI(datasourceRequest), request, String.class);

        if(rawResponse != null) {
            datasourceResponse.setPayload(rawResponse);
        }
        return datasourceResponse;
    }

    private URI getURI(DatasourceRequest datasourceRequest) {
        try {
            return new URI(datasourceRequest.getUrl());
        } catch (URISyntaxException e) {
            throw new DatasourceServiceException("URL parsing error", e);
        }
    }
}

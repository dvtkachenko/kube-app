package com.dvt.kube.app.datasource.service.connector.client;

import com.dvt.kube.app.datasource.service.domain.DatasourceRequest;
import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;
import com.dvt.kube.app.datasource.service.exception.DatasourceServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/java/net/http/HttpClient.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/HttpClientImpl.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/Http2ClientImpl.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/Http2Connection.java
// * Http2Connections belong to a Http2ClientImpl, (one of) which belongs to a HttpClientImpl.
// https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/HttpConnection.java
// https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.net.http/share/classes/jdk/internal/net/http/PlainHttpConnection.java
// https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/java/nio/channels/SocketChannel.java
// https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/nio/ch/SocketChannelImpl.java
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/nio/ch/Net.java
// Net.java  private static native int connect0 (boolean preferIPv6 ...
// Net.java  private static native int connect0 (boolean preferIPv6 ...
//  private static native int socket0
//  https://github.com/AdoptOpenJDK/openjdk-jdk11/blob/master/src/java.base/share/classes/sun/nio/ch/IOUtil.java
//  read/write operation


@Component
@RequiredArgsConstructor
@Slf4j
public class DatasourceHttp11Client implements DatasourceClient {

    private final HttpClient httpClient;

    @Override
    public DatasourceResponse callDatasource(DatasourceRequest datasourceRequest) {

        DatasourceResponse datasourceResponse = new DatasourceResponse();

        URI requestURI = getURI(datasourceRequest);
        HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofString((String) datasourceRequest.getPayload());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(requestURI)
                .POST(requestBody)
//                .header(HttpHeaders.AUTHORIZATION, "Basic RUDIXWZaHJvZFJU6")
                .header(HttpHeaders.AUTHORIZATION, "IdentityApiKey fb1b403bdf84-e80c-4dee")
//                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_XML_VALUE)
//                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML_VALUE)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .timeout(Duration.ofMillis(5000))
                .build();

        try {
            HttpResponse<String> rawResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if(rawResponse != null) {
                datasourceResponse.setPayload(rawResponse.body());
            }
        } catch (IOException | InterruptedException e) {
            log.error("Executing request error - {}", e.fillInStackTrace());
            throw new DatasourceServiceException("Executing request error", e);
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

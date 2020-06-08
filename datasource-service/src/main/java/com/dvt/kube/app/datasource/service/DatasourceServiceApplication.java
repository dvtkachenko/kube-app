package com.dvt.kube.app.datasource.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class DatasourceServiceApplication {

	public static void main(String[] args) {

		//        String[] ciphers = { "TLS_RSA_WITH_DES_CBC_SHA" }; // TLSv1.0
//        String[] protocols = { "TLSv1.0" };

		String[] ciphers = { "TLS_AES_256_GCM_SHA384" };
		String[] protocols = { "TLSv1.3" };

//		System.setProperty("jdk.tls.client.protocols", "TLSv1.3");
//		jdk.tls.client.cipherSuites

//		System.setProperty("https.cipherSuites","TLS_RSA_WITH_DES_CBC_SHA");
//		System.setProperty("https.cipherSuites","TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
//		System.setProperty("https.protocols","TLSv1.0");

//		System.setProperty("https.cipherSuites","TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA");
//		System.setProperty("https.protocols","TLSv1.1");

//		System.setProperty("https.cipherSuites","TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256");
//		System.setProperty("https.protocols","TLSv1.2");

//		System.setProperty("https.cipherSuites","TLS_AES_256_GCM_SHA384");
//		System.setProperty("https.protocols","TLSv1.3");

		// to handle certificate we car run with JVM options
		// -Djavax.net.debug=all -Djavax.net.ssl.trustStore="d:\temp\identity_cacerts"
		SpringApplication.run(DatasourceServiceApplication.class, args);
	}
}

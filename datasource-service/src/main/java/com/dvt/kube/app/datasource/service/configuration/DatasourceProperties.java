package com.dvt.kube.app.datasource.service.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "datasource.identity")
public class DatasourceProperties {

    private String url;
    private String username;
    private String password;

//    @Value("${properties.path}")
    private String propertiesPath;

//    @Value("${simulation.url}")
    private String simulationUrl;

    @PostConstruct
    public void init() {
        log.info("App properties : {}, url: {}, username: {}, password: {}", this.getClass().getName(), url, username, password);
//        log.info("App properties : properties.path: {}, simulationUrl: {}", propertiesPath, simulationUrl.get());
        log.info("App properties : properties.path: {}", propertiesPath);
    }
}

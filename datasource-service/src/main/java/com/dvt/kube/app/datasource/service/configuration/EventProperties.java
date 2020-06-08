package com.dvt.kube.app.datasource.service.configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@Data
@Configuration
@ConfigurationProperties(prefix = "event.management")
public class EventProperties {

    boolean enabled;
    private String name;

    @PostConstruct
    public void init() {
        log.info("App properties : {}, enabled: {}, name: {},", this.getClass().getName(), enabled, name);
    }
}

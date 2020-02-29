package com.dvt.kube.app.currency.exchange.service.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class PropertyInjector {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${currency.dir.path}")
    private String pathAsString;

    @Value("${currency.dir.path}")
    private Resource pathAsResource;

//    @Value("${currency.dir.url.path}")
    private Resource pathAsUrlResource;

    @Value("${currency.dir.class.path}")
    private Resource pathAsClassPathResource;

//    @Value("#{pathProperties.getUrlPath()}")
//    @Value("#{propertyInjector.getPreparedPathValue()}")
//    @Value("#{#this.getPreparedPathValue()}")
//    @Value("#{#root.getPreparedPathValue()}")
//    @Value("#root.getPreparedPathValue()")
    private Resource pathAsUrlResourceBySpel;

//    @Autowired
    private ResourceLoader resourceLoader;

    public PropertyInjector(@Autowired ResourceLoader resourceLoader, @Value("${currency.dir.url.path}") Resource pathAsUrlResource) {
        this.resourceLoader = resourceLoader;
        this.pathAsUrlResource = pathAsUrlResource;
    }
//    AnnotationConfigServletWebServerApplicationContext
    @PostConstruct
    private void init() throws IOException {
        logger.info("PropertyInjector -> currencyDirPath as String = {} ", pathAsString);
        logger.info("PropertyInjector -> currencyDirPath as Resource = {} -> {} ", pathAsResource.getClass(),
                pathAsResource /*, pathAsResource.getFile().getAbsolutePath() */);
        logger.info("PropertyInjector -> currencyDirPath as UrlResource = {} -> {} -> {} ", pathAsUrlResource.getClass(),
                pathAsUrlResource, pathAsUrlResource.getFile().getAbsolutePath());
        logger.info("PropertyInjector -> currencyDirPath as ClassPathResource = {} -> {} -> {} ", pathAsClassPathResource.getClass(),
                pathAsClassPathResource, pathAsClassPathResource.getFile().getAbsolutePath());
    }

    public String getPreparedPathValue() {
        String preparedPath = this.pathAsString;

        if(!pathAsString.startsWith("file:")) {
            preparedPath = "file:" + this.pathAsString;
        }
        return preparedPath;
    }
}

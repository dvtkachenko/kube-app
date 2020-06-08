package com.dvt.kube.app.currency.exchange.service.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

//@Component
public class PropertyInjector implements InitializingBean {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PathProperties pathProperties;
//    private PathProperties paths;

    @Value("${currency.dir.path}")
    private String pathAsString;

    @Value("${currency.dir.path}")
    private Resource pathAsResource;

//    @Value("${currency.dir.url-path}")
    private Resource pathAsUrlResource;

    @Value("${currency.dir.class-path}")
    private Resource pathAsClassPathResource;

//    @Value("#{pathProperties.getUrlPath()}")
//    @Value("#{propertyInjector.getPreparedPathValue()}")
//    @Value("#{propertyInjector.convertToResourceFitType('${currency.dir.class.path}')}")
//    @Value("#{propertyInjector.convertToResourceFitFormat('${currency.dir.path}')}")
    @Value("#{pathProperties.getUrlPath()}")
//    @Value("#{paths.getUrlPath()}")
//    @Value("#{new com.dvt.kube.app.currency.exchange.service.property.PathFormatConverter().convertToResourceFitFormat('${currency.dir.path}')}")
    private Resource pathAsUrlResourceBySpel;


    @Value("#{#root}")
    private Object value;

//    @Autowired
    private ResourceLoader resourceLoader;

//    public PropertyInjector(@Autowired ResourceLoader resourceLoader, @Value("${currency.dir.url-path}") Resource pathAsUrlResource) {
//        this.resourceLoader = resourceLoader;
//        this.pathAsUrlResource = pathAsUrlResource;
//    }
//    AnnotationConfigServletWebServerApplicationContext
    @PostConstruct
    private void init() throws IOException {
        logger.info("PropertyInjector -> currencyDirPath as String = {} ", pathAsString);
        logger.info("PropertyInjector -> currencyDirPath as Resource = {} -> {} ", pathAsResource.getClass(),
                pathAsResource /*, pathAsResource.getFile().getAbsolutePath() */);
//        logger.info("PropertyInjector -> currencyDirPath as UrlResource = {} -> {} -> {} ", pathAsUrlResource.getClass(),
//                pathAsUrlResource, pathAsUrlResource.getFile().getAbsolutePath());
        logger.info("PropertyInjector -> currencyDirPath as ClassPathResource = {} -> {} -> {} ", pathAsClassPathResource.getClass(),
                pathAsClassPathResource, pathAsClassPathResource.getFile().getAbsolutePath());
    }

    public String convertToResourceFitFormat(String propertyPath) {
        return new PathFormatConverter().convertToResourceFitFormat(propertyPath);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet()");
    }
}

package com.dvt.kube.app.currency.exchange.service.property;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Configuration
@ConfigurationProperties(prefix = "currency.dir")
@Validated
public class PathProperties {

    @Autowired
    private ResourceLoader resourceLoader;

    private String path;

    @NotBlank
    private String urlPath;

    @NotNull
    private Resource urlPathResource;

    String classPath;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getUrlPath() {
        return urlPath;
    }

    public void setUrlPath(String urlPath) {
        this.urlPath = new PathFormatConverter().convertToResourceFitFormat(urlPath);
        this.urlPathResource = resourceLoader.getResource(this.urlPath);
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Resource getUrlPathResource() {
        return urlPathResource;
    }
}

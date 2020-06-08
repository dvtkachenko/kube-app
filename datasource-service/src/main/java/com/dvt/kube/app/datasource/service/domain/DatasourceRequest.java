package com.dvt.kube.app.datasource.service.domain;

import lombok.Data;

@Data
public class DatasourceRequest {
    private String url;
    private Object payload;
}

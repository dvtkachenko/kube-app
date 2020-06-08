package com.dvt.kube.app.datasource.service.connector.client;

import com.dvt.kube.app.datasource.service.domain.DatasourceRequest;
import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;

public interface DatasourceClient {
    DatasourceResponse callDatasource(DatasourceRequest datasourceRequest);
}

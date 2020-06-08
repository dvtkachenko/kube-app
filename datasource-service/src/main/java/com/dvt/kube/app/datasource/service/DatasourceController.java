package com.dvt.kube.app.datasource.service;

import com.dvt.kube.app.datasource.service.connector.client.DatasourceClient;
import com.dvt.kube.app.datasource.service.connector.client.DatasourceHttp11Client;
import com.dvt.kube.app.datasource.service.connector.client.DatasourceRestTemplateClient;
import com.dvt.kube.app.datasource.service.domain.DatasourceRequest;
import com.dvt.kube.app.datasource.service.domain.DatasourceResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class DatasourceController {

//    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${datasource.identity.url}")
    private String datasourceUrl;

//    @Autowired
//    private DatasourceClient datasourceClient;

    @Autowired
    private DatasourceHttp11Client datasourceHttp11Client;

    @Autowired
    private DatasourceRestTemplateClient datasourceRestTemplateClient;

    @GetMapping("/api/datasource/request/execute")
    public DatasourceResponse executeRequest() {
        DatasourceRequest datasourceRequest = createDatasourceRequest();

        log.info("Datasource request execution over HttpClient 11 started : {}", datasourceRequest);

//        DatasourceResponse datasourceResponse = datasourceClient.callDatasource(datasourceRequest);
        DatasourceResponse datasourceResponse = datasourceHttp11Client.callDatasource(datasourceRequest);

        log.info("Datasource request execution over HttpClient 11 finished : {} : payload -> {}", datasourceResponse, datasourceResponse.getPayload());

        return datasourceResponse;
    }

    @GetMapping("/api/datasource/request/executeRestTemplate")
    public DatasourceResponse executeRestTemplateRequest() {
        DatasourceRequest datasourceRequest = createDatasourceRequest();

        log.info("Datasource request execution over RestTemplate started : {}", datasourceRequest);

//        DatasourceResponse datasourceResponse = datasourceClient.callDatasource(datasourceRequest);
        DatasourceResponse datasourceResponse = datasourceRestTemplateClient.callDatasource(datasourceRequest);

        log.info("Datasource request execution over RestTemplate finished : {} : payload -> {}", datasourceResponse, datasourceResponse.getPayload());

        return datasourceResponse;
    }
    private DatasourceRequest createDatasourceRequest() {
//        String requestBody = "<Parameters>\r\n    <FirstName>John</FirstName>\r\n    <LastName>Smith</LastName>\r\n    " +
//                "<PhoneNumber1>22222222</PhoneNumber1>\r\n    <PhoneNumber2>22222222</PhoneNumber2>\r\n</Parameters>";

        String requestBody = "{}";

        DatasourceRequest datasourceRequest = new DatasourceRequest();
        datasourceRequest.setUrl(datasourceUrl);
        datasourceRequest.setPayload(requestBody);

        return datasourceRequest;
    }
}

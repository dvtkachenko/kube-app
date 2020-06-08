package com.dvt.kube.app.datasource.service.exception;

public class DatasourceServiceException extends RuntimeException {
    public DatasourceServiceException(String message) {
        super(message);
    }

    public DatasourceServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

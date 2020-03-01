package com.dvt.kube.app.currency.exchange.service.property;

public class PathFormatConverter {

    public static String FILE_URL_PREFIX = "file:";
    public static String CLASSPATH_URL_PREFIX = "classpath:";

    public String convertToResourceFitFormat(String sourcePath) {
        if(sourcePath.isEmpty() || isUrlPathType(sourcePath) || isClassPathType(sourcePath)) {
            return sourcePath;
        }
        return FILE_URL_PREFIX + sourcePath;
    }

    private boolean isClassPathType(String checkedPath) {
       return checkedPath.startsWith(CLASSPATH_URL_PREFIX);
    }

    private boolean isUrlPathType(String checkedPath) {
        return checkedPath.startsWith(FILE_URL_PREFIX);
    }
}

package com.dvt.kube.app.currency.exchange.service.property;

public class PathFormatConverter {

    public static String URL_PREFIX = "file:";
    public static String CLASSPATH_PREFIX = "classpath:";

    public String convertToResourceFitFormat(String sourcePath) {
        if(isUrlPathType(sourcePath) || isClassPathType(sourcePath)) {
            return sourcePath;
        }
        return URL_PREFIX + sourcePath;
    }

    private boolean isClassPathType(String checkedPath) {
       return checkedPath.startsWith(CLASSPATH_PREFIX);
    }

    private boolean isUrlPathType(String checkedPath) {
        return checkedPath.startsWith(URL_PREFIX);
    }
}

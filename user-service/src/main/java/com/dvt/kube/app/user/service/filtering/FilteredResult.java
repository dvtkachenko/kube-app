package com.dvt.kube.app.user.service.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonIgnoreProperties(value={"field2"})
@JsonFilter("ResultFilter")
public class FilteredResult {

    private String field1;
    private String field2;
    private String field3;

    @JsonIgnore
    private String field4;

    public FilteredResult(String field1, String field2, String field3, String field4) {
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public String getField3() {
        return field3;
    }

    public String getField4() {
        return field4;
    }
}

package com.dvt.kube.app.user.service.versioning;

public class PersonV1 {
    private String fullName;

    public PersonV1() {
    }

    public PersonV1(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}

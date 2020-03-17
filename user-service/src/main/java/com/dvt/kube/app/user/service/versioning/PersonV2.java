package com.dvt.kube.app.user.service.versioning;

public class PersonV2 {
    private FullName fullName;

    public PersonV2() {
    }

    public PersonV2(FullName fullName) {
        this.fullName = fullName;
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }
}

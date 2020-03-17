package com.dvt.kube.app.user.service.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PersonVersioningController {

    @GetMapping("/v1/person")
    public PersonV1 getPersonV1() {
        return new PersonV1("Orest Vovk");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2() {
        return new PersonV2(new FullName("Orest", "Vovk"));
    }

    @GetMapping(value="/person/param", params="version=1")
    public PersonV1 getPersonByParamV1() {
        return new PersonV1("Orest Vovk");
    }

    @GetMapping(value="/person/param", params="version=2")
    public PersonV2 getPersonByParamV2() {
        return new PersonV2(new FullName("Orest", "Vovk"));
    }

    @GetMapping(value="/person/header", headers="X-API-VERSION=1")
    public PersonV1 getPersonByHeaderV1() {
        return new PersonV1("Orest Vovk");
    }

    @GetMapping(value="/person/header", headers="X-API-VERSION=2")
    public PersonV2 getPersonByHeaderV2() {
        return new PersonV2(new FullName("Orest", "Vovk"));
    }
}

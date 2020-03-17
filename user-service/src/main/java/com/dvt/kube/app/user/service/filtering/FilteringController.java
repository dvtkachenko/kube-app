package com.dvt.kube.app.user.service.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FilteringController {

    @GetMapping("/filtering-static")
    public FilteredResult getStaticFilteredResult() {
        return new FilteredResult("value 1", "value 2", "value 3", "value 4");
    }

    @GetMapping("/filtering-dynamic")
    public MappingJacksonValue getDynamicFilteredResult() {

        FilteredResult filteredResult = new FilteredResult("value 1", "value 2", "value 3", "value 4");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1");

        FilterProvider filters = new SimpleFilterProvider().addFilter("ResultFilter", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(filteredResult);
        mapping.setFilters(filters);

        return mapping;
    }
}

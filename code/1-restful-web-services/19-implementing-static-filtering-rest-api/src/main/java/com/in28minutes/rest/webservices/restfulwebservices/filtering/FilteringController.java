package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    //
    // http://localhost:8080/filtering
    //

    @GetMapping("/filtering")
    public SomeBean filtering(){
        return new SomeBean("value1","value2","value3");
    }

    //
    //http://localhost:8080/filtering-list
    //

    // Filtering works even Lists
    @GetMapping("/filtering-list")
    public List<SomeBean> filteringList(){
        return Arrays.asList(new SomeBean("value1","value2","value3"),new SomeBean("value4","value5","value6"));
    }
}

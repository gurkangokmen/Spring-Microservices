package com.in28minutes.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
//@JsonIgnoreProperties({"field1","field3"}) //If you don't anything, it doesn't affect!

@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;

    //@JsonIgnore
    private String field2;
    private String field3;

}

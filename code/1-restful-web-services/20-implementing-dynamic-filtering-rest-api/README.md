```java
@JsonFilter("SomeBeanFilter")
MappingJacksonValue 
SimpleBeanPropertyFilter
FilterProvider
```

```java
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter("SomeBeanFilter")
public class SomeBean {
    private String field1;
    private String field2;
    private String field3;

}
```

```java
@GetMapping("/filtering")
public MappingJacksonValue filtering(){
    SomeBean someBean = new SomeBean("value1", "value2", "value3");
    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);


    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
}

// Filtering works even Lists
@GetMapping("/filtering-list")
public MappingJacksonValue filteringList(){
    List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

    MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
    mappingJacksonValue.setFilters(filters);

    return mappingJacksonValue;
}
```


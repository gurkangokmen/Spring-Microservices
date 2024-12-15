package com.in28minutes.rest.webservices.restfulwebservices.helloworld;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//REST API
@RestController
public class HelloWorldController {

    // GET
    // Way1: @RequestMapping(method = RequestMethod.GET, path = "/hello-world")
    // Way2: @GetMapping(path = "/hello-world")
    // http://localhost:8080/hello-world
    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}

package com.in28minutes.rest.webservices.restfulwebservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
	
	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World"; 
	}
	
	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World"); 
	}

	//Path Parameters
	// /users/{id}/todos/{id} => /users/1/todos/100
	// /hello-world/path-variable/{name}

	// http://localhost:8080/hello-world/path-variable/hilal
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable  String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}

	// http://localhost:8080/hello-world/path-variable-2/hilal
	@GetMapping(path = "/hello-world/path-variable-2/{name}")
	public HelloWorldBean helloWorldPathVariable2(@PathVariable("name")  String theName) {
		return new HelloWorldBean(String.format("Hello World 2, %s", theName));
	}

	
}

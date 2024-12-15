
```
// http://localhost:8080/hello-world/path-variable/hilal
@GetMapping(path = "/hello-world/path-variable/{name}")
public HelloWorldBean helloWorldPathVariable(@PathVariable  String name) {
return new HelloWorldBean(String.format("Hello World, %s", name));
}
```

```
// http://localhost:8080/hello-world/path-variable-2/hilal
@GetMapping(path = "/hello-world/path-variable-2/{name}")
public HelloWorldBean helloWorldPathVariable2(@PathVariable("name")  String theName) {
    return new HelloWorldBean(String.format("Hello World 2, %s", theName));
}
```
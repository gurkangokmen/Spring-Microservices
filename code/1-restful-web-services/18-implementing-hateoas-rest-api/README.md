
<H1>HATEOAS</H1>

https://docs.spring.io/spring-hateoas/docs/current/reference/html/

```java
@GetMapping("/users")
    public List<User> retrieveAllUsers() {
        return service.findAll();
    }

    // http://localhost:8080/users/

    // EntityModel
    // WebMvcLinkBuilder
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);

        if (user==null){throw new UserNotFoundException("id-"+ id);}

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }
```

`http://localhost:8080/users/1`

```json
{
    "id": 1,
    "name": "Adam",
    "birthDate": "1994-10-25",
    "_links": {
        "all-users": {
            "href": "http://localhost:8080/users"
        }
    }
}
```



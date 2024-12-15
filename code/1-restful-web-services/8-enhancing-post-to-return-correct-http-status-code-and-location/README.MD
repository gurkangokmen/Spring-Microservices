```
[POST]

http://localhost:8080/users

REQUEST BODY:
{
    "name": "Asha",
    "birthDate": "1999-10-12"
}
```

```
@PostMapping("/users")
public ResponseEntity<User> createUser(@RequestBody User user) {
service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(user.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
```

![Screenshot 2024-10-18 073934](https://github.com/user-attachments/assets/48e511fe-2858-4c8b-8caf-b8ee0a4769a0)

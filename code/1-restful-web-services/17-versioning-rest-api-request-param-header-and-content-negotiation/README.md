
# Note

```
File names affect result!
```
## They should be named as follows:
```
messages.properties
messages_nl.properties
```

<H1>URI VERSIONING</H1>
```
@GetMapping("/v1/person")
public PersonV1 getFirstVersionOfPerson() {
    return new PersonV1("Bob Charlie");
}
    
http://localhost:8080/v1/person


RESPONSE:
{
    "name": "Bob Charlie"
}
```

```
@GetMapping("/v2/person")
public PersonV2 getSecondVersionOfPerson() {
    return new PersonV2(new Name("Bob", "Charlie"));
}
    
http://localhost:8080/v2/person


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```


<H1>PARAM VERSIONING</H1>
```
@GetMapping(path="/person", params="version=1")
public PersonV1 getFirstVersionOfPersonRequestParameter() {
    return new PersonV1("Bob Charlie");
}
    
http://localhost:8080/person?version=1


RESPONSE:
{
    "name": "Bob Charlie"
}
```

```
@GetMapping(path="/person", params="version=2")
public PersonV2 getSecondVersionOfPersonRequestParameter() {
    return new PersonV2(new Name("Bob", "Charlie"));
}
    
http://localhost:8080/person?version=2


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```

<H1>HEADER VERSIONING</H1>

```
@GetMapping(path="/person/header", headers="X-API-VERSION=1")
public PersonV1 getFirstVersionOfPersonRequestHeader() {
    return new PersonV1("Bob Charlie");
}
    
http://localhost:8080/person/header [HEADER → X-API-VERSION:1]


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```

```
@GetMapping(path="/person/header", headers="X-API-VERSION=2")
public PersonV2 getSecondVersionOfPersonRequestHeader() {
    return new PersonV2(new Name("Bob", "Charlie"));
}
    
http://localhost:8080/person/header [HEADER → X-API-VERSION:2]


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```


<H1>PRODUCE VERSIONING</H1>

```
GetMapping(path="/person/accept", produces="application/vnd.company.app-v1+json")
public PersonV1 getFirstVersionOfPersonAcceptHeader() {
    return new PersonV1("Bob Charlie");
}
    
http://localhost:8080/person/accept [HEADER → Accept:application/vnd.company.app-v1+json]


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```

```
@GetMapping(path="/person/accept", produces="application/vnd.company.app-v2+json")
public PersonV2 getSecondVersionOfPersonAcceptHeader() {
    return new PersonV2(new Name("Bob", "Charlie"));
}
    
http://localhost:8080/person/accept [HEADER → Accept:application/vnd.company.app-v2+json]


RESPONSE:
{
    "name": {
        "firstName": "Bob",
        "lastName": "Charlie"
    }
}
```

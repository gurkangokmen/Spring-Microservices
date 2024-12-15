# Let's handle why is NOT work username&password in POST,PUT REQUESTS!

`1-) Spring security would intercept this request`

`2-) and, it would execute a series of filters (Filter Chains)`

`3-) There are a series of checks that are done in your filter chains.`
```
3.1) All requests should be authenticated
3.2) Check CSRF ➡️ post,put
3.3) etc...
```


## SOLUTİON: We change filter chain and disable CSRF
```
Configuration
Bean
```


<h1>🌸🌸🌸🌸🌸🌸🌸🌸</h1>

To Start Project:

1. Run MySQL in docker
```
docker start 47fb2f2bc9608e90aa5f4d5c0dee1d670e43a6a058c1435d8c5f5f5a60e03cea
```

2. Run Spring Boot Application


<h1>🌸🌸🌸🌸🌸🌸🌸🌸</h1>

URL:


http://localhost:8080/users [GET] Fetch All Users

http://localhost:8080/jpa/users [GET] Fetch All Users

http://localhost:8080/users/{id} [GET] Fetch Specific Id User

http://localhost:8080/jpa/users [GET] Save User
```json
{
    "name":"Asha",
    "birthDate":"1999-11-06"
}
```
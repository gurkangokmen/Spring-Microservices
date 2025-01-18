```
docker build -t in28min/hello-world-docker:v2 .
                                              ↓
                                            Current Context
```

```
Steps:
- Upper Docker Command read Dockerfile in given context (. -> current folder)
```


```
=========== IMPORTANT ===========

we have done earlier is that the jar file creation was done separately.

We ran Mvn clean install on our local machine and then we copied the jar into the Docker image.

The best practice is to build everything that is needed inside the docker image.

It is not recommended to build something on your local machine and copy it into a docker image.

If you do something on the local machine and copy it into a local image, then there is every chance

that if you run it on somebody else's machine, the output would be a little different.

And that's the reason why whenever we create Docker images, the entire build process should happen

inside a Docker image.

And for that reason, what we will do now is we will build the jar file as part of the Docker image.

and then we will run it as part of the Docker image as well.

It takes long time (it will be fixed later).
```

```
=========== FOR ERRORS ===========
https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud/learn/lecture/45814351#questions/22735159
```
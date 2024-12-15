# Docker

## Table of Contents

* [Getting Start](#getting-start)
* [Difference between Docker Image and Docker Container](#difference-between-docker-image-and-docker-container)
* [First Test](#first-test)
* [Docker Registry](#docker-registry)
* [Docker Run Ports](#docker-run-ports)
* [Docker Ps](#docker-ps)


## Getting Start

```
➖Create Docker images for each microservice

➖Docker image contains everything a microservice needs to run: 
    * Application Runtime ( JDK or Python or NodeJS ) 
    * Appliction code Dependencies
```

## Difference between Docker Image and Docker Container

```
➖Docker Image:

Definition: A Docker image is a read-only template that contains the instructions for creating a Docker container. It includes all the necessary dependencies, configurations application
code, libraries, and runtime environment.


➖Docker Container:

Definition: A Docker container is a running instance of a Docker image. It is a live, executable version of the image.


➖Summary:

Image: Static, read-only, and used to create containers.
Container: Running, live instance of the image, capable of performing tasks and holding a temporary state.
```

## First Test

```bash
1- Open Docker Desktop
2- docker run in28min/todo-rest-api-h2:1.0.0.RELEASE
```

## Docker Registry

```bash
hub.docker.com → Docker Registry

https://hub.docker.com/r/in28min/todo-rest-api-h2 → Repository


docker run in28min/todo-rest-api-h2:1.0.0.RELEASE
http://localhost:5000/hello-world
```

## Docker Run Ports

## Docker Ps

`Show all running containers`
```
docker ps
```
![Screenshot 2024-11-01 092952](https://github.com/user-attachments/assets/77d83aaa-0dda-4626-bd89-2c435c661a0d)

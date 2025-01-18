# Docker

## Table of Contents

* [Getting Start](#getting-start)
* [Version](#version)
* [Docker Terminology](#docker-terminology)
* [Docker Architecture](#docker-architecture)
    * [Docker Engine](#docker-engine)
    * [Docker Daemon](#docker-daemon)
    * [Docker CLI](#docker-cli)
    * [How works?](#how-works)
* [Difference between Docker Image and Docker Container](#difference-between-docker-image-and-docker-container)
* [First Test](#first-test)
* [Docker Commands](#docker-commands)
    * [Docker Run](#docker-run)
    * [Docker Container Pause](#docker-container-pause)
    * [Docker Container Unpause](#docker-container-unpause)
    * [Docker Run Ports](#docker-run-ports) 
    * [Docker Ps](#docker-ps)
    * [Docker Container Ls](#docker-container-ls)
    * [Docker Container Ls -a](#docker-container-ls--a)
    * [Docker Images (local)](#docker-images-local)
    * [Docker Container Stop ae423](#docker-container-stop-ae423)
    * [Create a Image with Different Tag](#create-a-image-with-different-tag)
    * [Docker Search Mysql](#docker-search-mysql)
    * [Docker History ](#docker-history)
    * [Docker Image Inspect](#docker-image-inspect)
    * [Docker Image Remove](#docker-image-remove)
    * [Docker Container Prune (Remove Stopped Containers)](#docker-container-prune-remove-stopped-containers)
    * [Docker Container Stop](#docker-container-stop)
    * [Docker Container Kill](#docker-container-kill)
    * [Auto Restart](#auto-restart)
    * [Docker Events](#docker-events)
* [Docker Registry](#docker-registry)
* [Tag](#tag)
* [Detached Mode](#detached-mode)
* [Docker Daemon](#docker-daemon)

## Getting Start

```
➖Create Docker images for each microservice

➖Docker image contains everything a microservice needs to run: 
    * Application Runtime ( JDK or Python or NodeJS ) 
    * Appliction code Dependencies
```

## Version

```
docker --version
```
![Image](https://github.com/user-attachments/assets/39495c48-39fd-4ef5-8e7d-8b2a04d1a22e)

## Docker Terminology

![Image](https://github.com/user-attachments/assets/1f2ddd36-ace9-4f19-b72f-1d836085275b)

## Docker Architecture

### Docker Engine
```
Core component for building, running and managing containers.
```

### Docker Daemon
```
It manages Docker images, containers, networks, and volumes.

Processes requests from the Docker CLI or API.

It runs in the system background without a visual interface.
```

![Screenshot 2024-12-30 014309](https://github.com/user-attachments/assets/4436f505-e33f-4f9f-90cc-67501608a47a)


```
Docker Daemon is responsible for managing the containers. It's responsible for managing the locally images and 
it is responsible for pulling something from the image registry. If something is not available on our local or pushing a locally crated image to a image registry.
```

### Docker CLI
```
Docker CLI is a command-line interface that allows users to interact with Docker.
```

### How works?

```
How It Works

User (CLI):
Runs a command, for example 'docker run'.

Docker CLI:
Command is passed to Docker Daemon via Docker API.

Docker Daemon:
Processes the command, creates the container, and runs it.

Docker Engine:
This is the entire infrastructure that manages these operations.
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

## Docker Commands

### Docker Run

```
docker run -p 5000:5000 -d in28min/todo-rest-api-h2:1.0.0.RELEASE

🟰

docker container run -p 5000:5000 -d in28min/todo-rest-api-h2:1.0.0.RELEASE
```

### Docker Container Pause


```
docker container pause 25c9
```

![Screenshot 2024-12-30 021423](https://github.com/user-attachments/assets/28ccdc68-9354-4827-be4d-7f7df367b710)

### Docker Container Unpause


```
docker container unpause 25c9
```

![Screenshot 2024-12-30 021545](https://github.com/user-attachments/assets/ce3cf465-e0f9-4e32-961b-4d5c1d0658fd)


### Docker Run Ports

```
-p 5000:5000 → -p {HostPort}:{ContainerPort}
```

```bash
docker run -p 5000:5000 in28min/todo-rest-api-h2:1.0.0.RELEASE
```

### Docker Ps

`Show all running containers`
```
docker ps
```
![Screenshot 2024-11-01 092952](https://github.com/user-attachments/assets/77d83aaa-0dda-4626-bd89-2c435c661a0d)

### Docker Container Ls

`Show all running containers`


```
docker container ls 
```

![Screenshot 2024-12-30 005548](https://github.com/user-attachments/assets/3fde42c2-13ae-4f59-8261-a8879808fa9b)

### Docker Container Ls -a

`Show all containers (running + stopped)`

![Screenshot 2024-12-30 010721](https://github.com/user-attachments/assets/129913fb-6753-4590-9a9d-5669f9ecde31)


### Docker Images (local)

![Screenshot 2024-12-30 010853](https://github.com/user-attachments/assets/9723b265-8048-456b-865c-bfa9423e45fb)


### Docker Container Stop ae423

![Screenshot 2024-12-30 012544](https://github.com/user-attachments/assets/4295e37e-8997-4107-aee7-fed7b0de65e8)


### Create a Image with Different Tag

```
docker tag in28min/todo-rest-api-h2:1.0.0.RELEASE in28min/todo-rest-api-h2:1.0.0.latest
```

![Screenshot 2024-12-30 014814](https://github.com/user-attachments/assets/e1439a98-527d-45af-88ef-7e5404121abb)


### Docker Search Mysql

```
Check image is official or not
```

![Screenshot 2024-12-30 015004](https://github.com/user-attachments/assets/e64a886e-0f9e-421d-914a-a83364f5c7fb)

### Docker History 

```
docker history 9d05dd98f4a4
```

![Screenshot 2024-12-30 015912](https://github.com/user-attachments/assets/b828ef40-322d-45ce-91a9-0c28fb30f9a5)


### Docker Image Inspect

```
docker image inspect 9d05dd98f4a4
```

![Screenshot 2024-12-30 020103](https://github.com/user-attachments/assets/74b1324e-a98c-4ac4-9208-af843903f2f6)
![Screenshot 2024-12-30 020227](https://github.com/user-attachments/assets/dabae651-b583-4ff4-8c15-30db63299f5d)


### Docker Image Remove

```
docker image remove 7ce93a845a8a
```

![Screenshot 2024-12-30 020810](https://github.com/user-attachments/assets/ffaf0122-5350-45d9-be83-fa9b301db613)

### Docker Container Prune (Remove Stopped Containers)

```
docker container prune
```

![Screenshot 2024-12-30 021814](https://github.com/user-attachments/assets/20d35c63-c8de-4d69-8f8f-a628388be0fd)

### Docker Container Stop


`stop => sigterm => graceful shutdown`

`It takes a bit time like *7s or *10s`

```
docker container stop 25c
```

![Screenshot 2024-12-30 022258](https://github.com/user-attachments/assets/3a3c776b-13c5-43bb-9733-d30c6863c485)

### Docker Container Kill

`kill =>  sigkill => immediately terminates the process`

```
docker container kill aee
```

![Screenshot 2024-12-30 022644](https://github.com/user-attachments/assets/71a6a5c1-5f47-489a-b36b-dbef564c52f3)

### Auto Restart

`The container automatically start when docker is restart.`

`It is usefull for databases.`

```
docker container run -p 5000:5000 --restart=always -d in28min/todo-rest-api-h2:1.0.0.RELEASE
```

![Screenshot 2024-12-30 023148](https://github.com/user-attachments/assets/4fd0cc43-1c1b-460f-89e0-1428a25649b5)

```
Default:
docker container run -p 5000:5000 --restart=no -d in28min/todo-rest-api-h2:1.0.0.RELEASE
```

### Docker Events

![Screenshot 2024-12-30 023612](https://github.com/user-attachments/assets/ff0f41c0-8fde-49b0-a034-f0975bf2ba16)

## Docker Registry

```bash
hub.docker.com → Docker Registry

https://hub.docker.com/r/in28min/todo-rest-api-h2 → Repository


docker run in28min/todo-rest-api-h2:1.0.0.RELEASE
http://localhost:5000/hello-world
```


## Tag

```
In Docker, a tag is a label used to identify a specific
version of a docker image. It helps you specify and 
manage different versions of the same image.


💥 If no tag is specified, Docker defaults to the latest tag.
```

```
repository:tag
```

## Detached Mode

```
--detach or -d
```

```
🌼Detached Mode → Docker run your container in detached mode in the backgroud.

🌼Work in background if you make ctrl+c in terminal or close terminal
```


```bash
docker run -p 5000:5000 -d in28min/todo-rest-api-h2:1.0.0.RELEASE
```















## Launch Zipkin Container Using Docker

```
docker run -p 9411:9411 openzipkin/zipkin:2.23
```

![Screenshot 2024-12-30 023929](https://github.com/user-attachments/assets/0f2bcb04-670e-4b12-acb9-cf2c8dbf9299)

![Screenshot 2024-12-30 024001](https://github.com/user-attachments/assets/bcb4131a-5844-4da0-a31c-647c1c89187f)

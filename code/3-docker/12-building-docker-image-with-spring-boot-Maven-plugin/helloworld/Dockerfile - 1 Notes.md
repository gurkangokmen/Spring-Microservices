```
docker build -t in28min/hello-world-docker:v1 .
                                              ↓
                                            Current Context
```

```
Steps:
- run maven install commands to create jar (jar files created in target folder)
- Upper Docker Command read Dockerfile in given context (. -> current folder)
```
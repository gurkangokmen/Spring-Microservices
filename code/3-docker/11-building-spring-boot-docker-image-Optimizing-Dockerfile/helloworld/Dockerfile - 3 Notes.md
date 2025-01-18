```
docker build -t in28min/hello-world-docker:v3 .
                                              ↓
                                            Current Context
                                            
                                            
                                            
docker container run -p 5000:5000 -d in28min/hello-world-docker:v3                                            
```

```
Steps:
- Upper Docker Command read Dockerfile in given context (. -> current folder)
```


```
=========== IMPORTANT ===========
In the last step, we went to multi-stage approach and we saw that the build took a long time.

Even if I was making a small code change, it was causing the entire application to be rebuilt again.

How can we stop that from happening?

One of the most important things that you need to understand is that Docker uses something called layering.

Each command you execute in here can create a separate layer.

And what Docker tries to do is to reuse layers as much as possible.

For example, if nothing has changed in this layer, then Docker would try to reuse this layer in the next build.


🪻 Docker caches every layer and tries to reuse it
🪻 Let's make use of this feature to make our build efficient
```

```
Try change code and rebuild.

first 120 seconds
now 20 seconds because it is cached
```
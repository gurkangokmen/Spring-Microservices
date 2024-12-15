`Note: data.sql automaticly executed (if you are using h2 database, otherwise in real databases will not be executed like mysql)`

`data.sql are only executed if you're connecting to an in-memory database.`

`data.sql will not be executed if you're connecting to a real database.`

# how can we be sure that we are actually getting the data from MySQL database back?
## Solution: mysqlsh (mysql shell)
`Firstly we enable/convert console mysql shell available`
```
mysqlsh
```
`We connect database`
```
\connect social-media-user@localhost:3307
```
`We set social-media-database default schema`
```
\use social-media-database
```
`Switch Sql Mode (You were in sql mode before, it gives an error when you writes sql queries)`
```
\sql
```
`If you see results, no problem 💘💘`
```
select * from user_details;
```
```
select * from post;
```



# <h1>Docker run ... (Creates and starts)

`Launch MySQL as Docker Container`
```
docker run --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=social-media-user --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=social-media-database --name mysql --publish 3307:3307 mysql:8-oracle
```


```
docker run --detach // container will run in the background rather than attaching to your terminal session
--env MYSQL_ROOT_PASSWORD=dummypassword // environment variable - admin
--env MYSQL_USER=social-media-user  
--env MYSQL_PASSWORD=dummypassword 
--env MYSQL_DATABASE=social-media-database 
--name mysql  //container name
--publish 3307:3306 // port 
mysql:8-oracle //complete name (8-oracle -> can run any os)
```

# <h1>Docker start ... (Starts an existing, stopped container (created earlier using docker run))
```
docker start 47fb2f2bc9608e90aa5f4d5c0dee1d670e43a6a058c1435d8c5f5f5a60e03cea
```
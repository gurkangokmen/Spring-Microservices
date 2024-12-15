`URL`
```
http://localhost:8000/currency-exchange/from/USD/to/INR

`Response Structure`
{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "environment": "8000"
}
```

```
http://localhost:8001/currency-exchange/from/USD/to/INR

`Response Structure`
{
  "id": 1000,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "environment": "8001"
}
```



`VM arguments ( Eclipse )`
```
-Dserver.port=8001
```

`Environment arguments ( IntelliJ )`
```
server.port=8001
```
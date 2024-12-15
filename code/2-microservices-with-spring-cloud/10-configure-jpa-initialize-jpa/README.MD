```
http://localhost:8000/currency-exchange/from/USD/to/INR

Response:
{
  "id": 10001,
  "from": "USD",
  "to": "INR",
  "conversionMultiple": 65,
  "environment": "8000"
}
```

```
http://localhost:8000/currency-exchange/from/EUR/to/INR

Response:
{
  "id": 10002,
  "from": "EUR",
  "to": "INR",
  "conversionMultiple": 75,
  "environment": "8000"
}
```

```
http://localhost:8000/currency-exchange/from/AUD/to/INR

Response:
{
  "id": 10003,
  "from": "AUD",
  "to": "INR",
  "conversionMultiple": 25,
  "environment": "8000"
}
```


`data.sql ➡️ (src/main/resources)`
```
If you do not write in this location, does not work! (JDBC is not necessary!)
```


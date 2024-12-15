18-setting-up-spring-cloud-api-gateway
```
Spring Cloud Gateway
```
http://localhost:8761

![Screenshot 2024-07-18 233118](https://github.com/user-attachments/assets/4dea3198-d7b3-44d2-9146-1c2d705b6350)


19-enabling-discovery-locator-with-eureka-for-spring-cloud-gateway

Initial

- http://localhost:8765/CURRENCY-EXCHANGE-SERVER/currency-exchange/from/USD/to/INR

- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/USD/to/INR/quantity/10



Lower Case

- http://localhost:8765/currency-exchange/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion/currency-conversion-feign/from/USD/to/INR/quantity/10



Custom Routes

- http://localhost:8765/currency-exchange/from/USD/to/INR

- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

- http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10

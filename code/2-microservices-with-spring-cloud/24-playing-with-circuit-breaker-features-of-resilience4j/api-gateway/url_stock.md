Currency Exchange

🌐http://localhost:8001/currency-exchange/from/USD/to/INR

Currency Conversion

🌐http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

🌐http://localhost:8100/currency-conversion-feign/from/USD/to/INR/quantity/10

Eureka

🌐http://localhost:8761/

Api Gateway

🌐http://localhost:8765/CURRENCY-EXCHANGE-SERVER/currency-exchange/from/USD/to/INR

🌐http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion/from/USD/to/INR/quantity/10 (This link is static 8001 now: if you run ces on 8000, you have to go ccs project and change link url manually in CurrencyConversionController.java)

🌐http://localhost:8765/CURRENCY-CONVERSION-SERVICE/currency-conversion-feign/from/USD/to/INR/quantity/10

Api Gateway - Lower Case

🌐http://localhost:8765/currency-exchange-server/currency-exchange/from/USD/to/INR

🌐http://localhost:8765/currency-conversion-service/currency-conversion/from/USD/to/INR/quantity/10 (This link is static 8001 now: if you run ces on 8000, you have to go ccs project and change link url manually in CurrencyConversionController.java)

🌐http://localhost:8765/currency-conversion-service/currency-conversion-feign/from/USD/to/INR/quantity/10

Api Gateway - Custom Url

🌐 http://localhost:8765/currency-exchange/from/USD/to/INR

🌐 http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10

🌐 http://localhost:8765/currency-conversion-feign/from/USD/to/INR/quantity/10

🌐 http://localhost:8765/currency-conversion-new/from/USD/to/INR/quantity/10
# TraderAuto+
#### Connecting Car Buyers with Lower Credit Scores with Subprime Lenders
## Motivation
With the current user-journey, consumers with lower credit scores aiming to purchase used cars struggle to both communicate and obtain loans from subprime lenders. The existing gap between subprime lenders and consumers acts as an obstacle in the formation of mutually beneficial partnerships. TraderAuto+ is designed to create a smooth and convenient purchasing experience for car buyers with lower credit scores by providing them information on loan options while recommending suitable cars. This product has the potential to greatly improve customer convenience while expanding the market share and valuation of both AutoTrader and AutoCapital Canada.
## Build Status
The server is running and working properly!
## Code Style
The project follows standard Java code style, SOLID and Clean Architecture principles.
## Tech/Framework used
- Java Springboot framework is used to construct the backend server for the web application. 
- Amazon EC2 is used to host the server
- Amazon RDS is used to host the backend SQL database
- Amazon Amplify is used to host the web application (You can find the frontend repo here: https://github.com/autotrader-plus/traderautoplus-app)
## Features
- Provide users a list of loans for specific cars, and the likelihood of getting approval for each loan based on monthly income, credit score, employment status, homeowner status, monthly budget, car price and a variety of other factors.
- Allow user to select car preferences so that the loans are only calculated for the cars that they are interested in buying.
- Provide opportunities through embedded links to directly connect with AutoCapital Canada to move forward with the purchase journey. 
## Build & Installation
This gradle project officially runs on an EC2 instance. However, if you wish to run and test the server on localhost, you can run the following commands:
```
./gradlew bootRun
```
If you wish to build a runnable JAR file, use the following commands:
```
./gradlew build
```
You can find the built JAR file in build/libs/ folder.
## Tests
All the unit and integration test codes are in src/test/java/packages. You can run the tests by the following command:
```
./gradlew test
```
Mockito is used to mocked the database connection for unit testing.
## How to Use?
Go to our web application: , and start exploring!
## Contribute
Feel free to clone the project to make any changes, and submit issues and pull requests for any changes you made.
## Credits
- Senso.AI for providing SENSO API for calculating car loans and prepayment score.
- AutoCapital Canada for providing us guidance and ideas on some important factors to take into account when calculating loan approval rates.
## Copyright Information
Copyright (c) 2021 by TraderAuto+ (Jia Hao Choo, Ameen Parthab, Sophie Sun, Elizabeth Li, Daniel Xu)
## License
MIT License

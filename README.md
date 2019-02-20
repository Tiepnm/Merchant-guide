##GALAXY MERCHANT TRADING GUIDE

- Input for testing from a file that has a name is input.txt
- Run File StartUp.java
##Solution approach

I have applied Chain of Responsibility Design Pattern with 2 processor is InputProcess and OutputProcess
#####InputProcess class has response is :
- Read data from a file and push each line of text to list
- Check the type of each line and push it to storage(Map), It has 3 sentence types ( ASSIGNED, CREDITS,QUESTION), MappingData file has response store them

After done task in Input process, the program put mapping data to Output process

#####OutputProcess class :
Base on type of each question -> we have class handle business logic corresponding (We have apply Apply Facade Pattern )

Please see diagram image bellow

![Alt text](https://doc-14-c0-docs.googleusercontent.com/docs/securesc/j0cnh0rjqvil8d21ekenkf1ofg6gnp0t/vjg69koi1nvpt1fct12su6lef2fndeuj/1550649600000/01781326887403023996/01781326887403023996/1TlfekLRco6NHLEPEik7BYKcOuGNGqNtC?e=view&nonce=2djsjn8brn27o&user=01781326887403023996&hash=7rgg618p724vv5n9efgkl03hvv4i31cp)



##Compile and running Require
Java 8 installation require
Maven 3 installation require
To run application in IDE please start GalaxyMerchantApplication.java.

Use the below commands to build and run application:

mvn package

A jar package name Merchant-Galaxy-1.0-SNAPSHOT.jar will be place in target/ folder

Command to start application:

java -jar Merchant-Galaxy-1.0-SNAPSHOT.jar

To execute all the test cases: mvn test
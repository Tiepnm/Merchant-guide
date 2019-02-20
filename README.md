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
![image](https://drive.google.com/uc?export=view&id=1TlfekLRco6NHLEPEik7BYKcOuGNGqNtC)

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
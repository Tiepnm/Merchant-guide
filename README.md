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

![Alt text](1.jpg?raw=true)
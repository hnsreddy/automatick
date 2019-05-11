# Automatick

Automated ticketing system code challenge.

## Getting Started

This is a very simple scaled down app and hence getting the application fired up is a very simple process. 

### Prerequisites

Java version 11+
Maven version 4+
The application was authored and tested on a unix based system; I am not quite sure if this application will run as seamlessly as it does on unix.

### Installing

Clone this repo anywhere you want to and run the following command after navigating to the application base folder (normally this would be a folder named "automatic")

bin/setup.sh

This command will build and application, run all the tests and start up the application.

Output of the command if everything works fine:
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
[INFO] Total time:  4.479 s
[INFO] Finished at: 2019-05-11T16:24:32+05:30
[INFO] ------------------------------------------------------------------------
Starting up parking lot server for you...
Using <User folder>/automatick/parking_lot/target/automatick-1.0-SNAPSHOT.jar

## Test run

Once the application comes up run the following command to test the commands application supports. An input file for the test will be available with this distribution to facilitate this. Run this command from the applications base folder.

bin/parking_lot.sh inputfile.txt

### Sample output
Executing commands from input file inputfile.txt
Creating parking lot with 4 slots
Created a parking lot with 4 slots
Executing park command
Allocated slot number:0
Executing park command
Allocated slot number:1
Executing park command
Allocated slot number:2
Executing park command
Allocated slot number:3
Registration numbers for cars with color white
ka51mg511,ka51mc2665
Cars of white color are parked in slots:
0,3
Car with registration number is parked in: 
0
Executing leave  command
Slot number 1 is free
Exiting parking lot application; bye bye!

## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

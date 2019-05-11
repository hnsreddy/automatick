# Automatick

Automated ticketing system code challenge.

## Getting Started

This is a very simple app and hence getting the application fired up is a very simple process. 

### Prerequisites

Java version 11+
Maven version 4+
The application was authored and tested on a unix based system; I am not quite sure if this application will run as seamlessly as it does on unix.

### Installing

Clone this repo anywhere you want to and run the following command after navigating to the application base folder (normally this would be a folder named "automatic")

```
bin/setup.sh
```

## Test run

Once the application comes up run the following command to test the commands application supports. An input file for the test will be available with this distribution to facilitate this. Run this command from the applications base folder.

```
bin/parking_lot.sh inputfile.txt
```

### Sample output
```
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
```
## Supported commands
The application once started using the ####bin/setup.sh command will continue to run and serve responses to supported commands. Below is a list of commands supported by this application.

###Creating a parking lot
```
bin/create_lot.sh <number of slots>
```

This command initialises the parking lot application with a pre-defined number of slots. The only argument needed to run this command is an integer (number of slots)

###Park a car
```
bin/park.sh <vehicle registration number> <vehicle color>
```

This command will be used to issue a ticket, it figures out which slot is free and allocates it to the vehicle. Registration number and color of the vehicle are the 2 arguments that needs to be passed to this command.

###Un-park a car
```
bin/leave.sh <slot number>
```

This command is used when the vehicle is leaving the parking lot; it basically frees up a specified slot number and makes it available for parking.

##Reporting
###List down registration numbers of all cars of a specified color
```
bin/registration_numbers_for_cars_with_colour.sh <color>
```
###List down all slot numbers of a car of specified color
```
bin/slot_numbers_for_cars_with_colour.sh <color>
```

###Show slot number of a car whose registration number is given
```
bin/slot_number_for_registration_number.sh <vehicle registration number>
```
## Exit the application
``` 
bin/exit.sh
```


## Built With
* [Maven](https://maven.apache.org/) - Dependency Management

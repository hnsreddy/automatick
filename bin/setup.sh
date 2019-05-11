#!/bin/sh -u
umask 022
Echo "Setting up parking lot, this may take a while please be patient"

Echo "I am assuming you have maven installed, going ahead with the build using maven"
cd parking_lot
mvn clean install

Echo "Starting up parking lot server for you..."
workingDirectory="$PWD"
nohup java -jar $workingDirectory/target/automatick-1.0-SNAPSHOT.jar &

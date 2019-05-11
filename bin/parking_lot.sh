#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Executing commands from input file $1"
while read line
do
	tokens=( $line )
	#Echo "Command: ${tokens[0]} Arguments: ${tokens[@]:1}"
	./bin/${tokens[0]}.sh ${tokens[@]:1}
done < $1


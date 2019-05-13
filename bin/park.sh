#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Executing park command"
Echo "park $1 $2"| nc localhost 56060
printf "\n"

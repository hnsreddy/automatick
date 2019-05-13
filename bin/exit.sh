#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Exiting parking lot application; bye bye!"
Echo "exit" | nc localhost 56060
printf "\n"

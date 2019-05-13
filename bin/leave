#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Executing leave  command"
Echo "leave $1"| nc localhost 56060
printf "\n"

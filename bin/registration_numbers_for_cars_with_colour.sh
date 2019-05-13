#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Registration numbers for cars with color $1"
Echo "registration_numbers_for_cars_with_colour $1" | nc localhost 56060
printf "\n"

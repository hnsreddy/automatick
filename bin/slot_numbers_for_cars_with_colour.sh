#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Cars of $1 color are parked in slots:"
Echo "slot_numbers_for_cars_with_colour $1" | nc localhost 56060
printf "\n"

#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Car with registration number is parked in: "
Echo "slot_number_for_registration_number $1" | nc localhost 56060

#!/bin/sh -u
PATH=$PATH:/bin; export PATH
umask 022

Echo "Creating parking lot with $1 slots"
Echo "create_lot $1"| nc localhost 56060

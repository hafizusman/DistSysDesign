#!/bin/bash

rm -rf storage
rm -f *.log
rm -f *.replay
#./execute.pl -s -n FileServerNode -f 0 -c scripts/FileServerTest
./execute.pl -s -n FacebookNode -f 0 -c scripts/FacebookTest

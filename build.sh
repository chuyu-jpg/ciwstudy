#!/bin/bash
OUTPUT_DIR=`pwd`/out
docker build  --shm-size=1gb -t study21 . 


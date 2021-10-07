#!/bin/bash
docker run --rm -it --shm-size=1gb -v `pwd`/out:/out:z --env-file env.list study21 ./scripts/print-included.sh



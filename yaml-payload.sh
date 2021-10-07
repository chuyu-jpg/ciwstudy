#!/bin/bash

echo "Press Ctrl-C to abort process"
read -p "Press enter to load YAML payload with snakeyaml ..."
cd payloads
javac -cp ../analysis/dataset/snakeyaml.jar:. Main.java 
java -cp ../analysis/dataset/snakeyaml.jar:. Main lol50.yaml

cd ..




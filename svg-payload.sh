#!/bin/bash

echo "Press Ctrl-C to abort process"
read -p "Press enter to load SVG payload with batik..."
java -cp analysis/dataset/batik.jar  org.apache.batik.apps.rasterizer.Main -scriptSecurityOff ./payloads/depth50.svg

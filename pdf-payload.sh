#!/bin/bash

echo "Press Ctrl-C to abort process"
read -p "Press enter to load PDF payload with pdfbox ..."

java -jar analysis/dataset/pdfbox.jar PDFReader payloads/doscos.pdf


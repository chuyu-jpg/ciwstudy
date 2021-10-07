# Replication package for paper


This package includes a getting started guide and step-by-step instructions to replicate the results in the paper. It also includes an explained listing of the contents of this repository.
This package has been tested on Ubuntu release 18.04. The system must already contain a working instance of Docker (it was tested on Docker client/engine version 18).
The Docker image is about 1GB in size, and this repository is nearly 600MB. After finishing the experiment you may use Docker to remove the images.


# Getting Started Guide

## Cloning repository and building Docker image

Use git to obtain the contents of this repository.

Then change to the cloned directory:

Then you can use Docker to build an image, which has the required dependencies: 
[Doop](https://bitbucket.org/yanniss/doop/src/master/), [Souffle](https://souffle-lang.github.io/) datalog engine / compiler, OpenJDK1.8 and Apache Maven. Execute the provided script to build the image:

`./build.sh
`

The scripts for executing the commands mount the directory `./out` in the container using Docker volumes, so that the results persist after the execution of the scripts.

# Step-by-Step Instructions

We have shell scripts in the root of the repository that can be used to execute the commands required to:

* Print tabulated results from an old analysis.
* Execute the analysis for a single project.
* Execute the analysis for all projects.
* Print tabulated results for a recently run analysis, which has stored the output files in the `./out` directory.
* Evaluate the payloads discussed in the paper.



## Producing tables in the paper using data files from an analysis executed by the authors

Previously generated results from an old execution of the analysis is included in this repository. The analysis was run with the input libraries (listed in Table I of the paper).

The resulting output files are in:

`./data` as csv files.

The input libraries used for this analysis (the libraries in Table I of the paper) are included in the repository at: 

`./analysis/dataset`

Each library is a self-contained jar file that follows the same names as used in the paper.

The results from the old analysis can be converted into a table and printed on the console using this command:

`./print-results.sh`


## Executing a fresh analysis

A fresh analysis can be executed with this script:

`./run.sh <library> <mainClass>
`

The first parameter is the library (e.g. _pdfbox_), the second parameter, the main class for the application, is required if the library needs a driver for the analysis.

For example, to execute it for the _pdfbox_ library, the command is:

`./run.sh pdfbox
`

Which executes the analysis for the _pdfbox_ project and saves the resulting output files in the `./out` directory.

For _pdfbox_ that takes about 5 minutes to finish.


If a project requires a driver, it must be added as an extra parameter.

For example, for _SnakeYAML_, the command is:

`./run.sh  snakeyaml drivers.snakeyaml.Main
`

The list of driver names are available for examination in `./analysis/script/run-all.sh`, which is the script invoked within the container by `./run.sh`


The results can be printed for this analysis using this script:

`./fresh-results.sh 
`

It prints the results on the console. The columns are displayed in the same order as Table II in the paper.

You can use the following script to execute the analysis on all libraries:

`./run-all.sh`


## Evaluating Payloads

The PDF, SVG and YAML payloads can be evaluated on how they perform with the respective libraries. Note that the resulting processes can be CPU/memory intensive and will either run indefinitely or exhaust resources.
Also note that these are executed on the host machine and not in the container. They require Java runtime to run, and the SnakeYAML example require a Java compiler to compile the main class for loading YAML files.

Attempts to load the PDF file in PDFBox:

`./pdf-payload.sh`

Attempts to load the SVG file in Batik:

`./svg-payload.sh`

Attempts to load the YAML file in SnakeYAML:

`./yaml-payload.sh`

# Directory contents for the repository

## Datalog files used to extend Doop

`./analysis/datalog-souffle
`

## Dataset

This directory holds the self-contained jar files for the libraries listed in Table 1.

`./analysis/dataset/*jar
`

## Docker

The base image is pulled from Docker hub using :

`./build.sh
`

It is about 1GB in size.

`
./Dockerfile
`

## Scripts for running the tasks

Scripts for running the tasks are in the root of the repo. i.e. `./run.sh`, `./print-results.sh` and `fresh-results.sh`

These wrapper scripts invoke scripts from the following directory in the repository:

`./analysis/scripts
`

## Payloads (Payloads for PDF, YAML and SVG vulnerabilities)

`./payloads
`

## Data

`./data
`

Previously generated output files from the analysis, with results for all projects. The files are in csv format.

## Pre-analysis (Java maven) for extra fact extraction and reporting

`./analysis/ and ./analysis/pom.xml
`



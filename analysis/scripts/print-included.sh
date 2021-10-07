#!/usr/bin/env bash
mvn compile
mvn exec:java -Dexec.mainClass=acar.reporting.Report -Dexec.args="Results /data"

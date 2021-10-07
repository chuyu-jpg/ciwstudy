#!/usr/bin/env bash
mvn exec:java -Dexec.mainClass=acar.reporting.Report -Dexec.args="Results /out"

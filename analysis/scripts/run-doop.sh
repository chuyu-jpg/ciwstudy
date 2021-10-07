#!/usr/bin/env bash


PROJECT_HOME=/analysis
INPUT_HOME=/analysis/dataset
INPUT=/analysis/dataset/$1.jar
OUTPUT_DIR=/out/$1
LOG_FILE=${OUTPUT_DIR}/log.txt
MACRO_FILE=./datalog-souffle/custom/recursion_macros.h

PROJECT=$1

rm $MACRO_FILE
touch $MACRO_FILE
touch $LOG_FILE

THIS=YES
PARAM=YES
SIMPLE=YES
INDIRECT=NO
INDIRECT2=NO
TRAVERSAL=YES
TRIGGER=YES

echo "#define TEST_PACKAGE \"\"" | tee -a $MACRO_FILE

if [ $THIS == YES ]; then
  echo "#define THIS 1" | tee -a $MACRO_FILE
fi

if [ $PARAM == YES ]; then
  echo "#define PARAM 1" | tee -a $MACRO_FILE
fi

if [ $SIMPLE == YES ]; then
  echo "#define SIMPLE 1" | tee -a $MACRO_FILE
fi

if [ $INDIRECT == YES ]; then
  echo "#define INDIRECT 1" | tee -a $MACRO_FILE
fi

if [ $INDIRECT2 == YES ]; then
  echo "#define INDIRECT2 1" | tee -a $MACRO_FILE
fi

if [ $TRAVERSAL == YES ]; then
  echo "#define TRAVERSAL 1" | tee -a $MACRO_FILE
fi

if [ $TRIGGER == YES ]; then
  echo "#define TRIGGER 1" | tee -a $MACRO_FILE
fi

./scripts/sync.sh

function save_start_time() {
 start=$(date +%s)
}

function save_end_time() {
 end=$(date +%s)
}

function log_duration() {
 echo $((end-start)) " seconds " $1 | tee -a $LOG_FILE
}

cd $DOOP_HOME

save_start_time


## Additional libs not required as we would like all classes in classpath in. option for libs:  -l ${ADDITIONAL_LIBS}
if [ -z $2 ];
then
eval "${DOOP_HOME}/doopOffline -i ${INPUT}  ${EXTRA_ARG} -a context-insensitive -id ${PROJECT}  --Xstats-none --souffle-jobs 4"
else
cp $INPUT ${INPUT_HOME}/input.jar
cd ${PROJECT_HOME}/target/classes
jar -fu ${INPUT_HOME}/input.jar drivers/*
cd -
eval "${DOOP_HOME}/doopOffline -i ${INPUT_HOME}/input.jar  ${EXTRA_ARG} -a context-insensitive -id ${PROJECT}  --main $2 --Xstats-none --souffle-jobs 4"
rm ${INPUT_HOME}/input.jar
fi



echo "Exit code $?" | tee -a $LOG_FILE 

save_end_time

log_duration "points-to"

cd -

cp $DOOP_HOME/out/context-insensitive/$1/database/Composite.csv /out/$1
cp $DOOP_HOME/out/context-insensitive/$1/database/Simple*.csv /out/$1
rm -rf $DOOP_HOME/out/*
rm -rf $DOOP_HOME/cache/*



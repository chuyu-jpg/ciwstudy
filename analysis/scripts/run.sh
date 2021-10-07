#!/usr/bin/env bash


if [ -f "$TARGET_DIR/$1.jar" ]
then
    echo "Found: $TARGET_DIR/$1.jar"
else
    echo "$TARGET_DIR/$1.jar not found. Aborting!" >&2
    exit
fi

CD=`pwd`
cp $TARGET_DIR/$1.jar  dataset/fat.jar
cp dataset/rt.jar dataset/test-fat.jar
cd dataset
cp fat.jar tmp/
cd tmp
unzip fat.jar
rm fat.jar
jar -fu ../test-fat.jar .
cd ..
rm -rf tmp/*
cd ..
rm /out/*.csv
rm /out/*.txt
mvn exec:java -Dexec.mainClass=acar.analysis.Analysis -Dexec.args="fat.jar"

if [ $? -eq 0 ]
then
  echo "Successfully executed pre-analysis"
else
  echo "Pre-analysis failed!" >&2
  cd $CD
  exit
fi

mkdir /out/$1
cp /out/fields.txt /out/fields.fact
cp /out/fields.fact /tmp/fields.fact
cp /out/looped.txt /out/looped.fact
cp /out/looped.fact /tmp/looped.fact

cp /out/*.txt /out/$1
cp /out/*.csv /out/$1
./scripts/run-doop.sh $1 $2
cd $CD

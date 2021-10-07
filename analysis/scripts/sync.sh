#/bin/bash

cd datalog-souffle
cp -r ./custom $DOOP_HOME/souffle-logic/addons/
cp  ./open-programs/rules-ser.dl $DOOP_HOME/souffle-logic/addons/open-programs/
cp  ./open-programs/entry_points.dl $DOOP_HOME/souffle-logic/addons/open-programs/

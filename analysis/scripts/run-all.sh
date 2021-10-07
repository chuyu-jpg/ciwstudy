#!/usr/bin/env bash
./scripts/run.sh  xbean drivers.xmlbean.Main
./scripts/run.sh  batik org.apache.batik.apps.rasterizer.Main
./scripts/run.sh jfxrt drivers.imageio.Main
./scripts/run.sh  mongo drivers.bson.Main
./scripts/run.sh  mvel2
./scripts/run.sh ognl drivers.ognl.Main
./scripts/run.sh  pdfbox
./scripts/run.sh  pdfxstream
./scripts/run.sh  protobuf drivers.protobuf.Main
./scripts/run.sh  sanselan drivers.sanselan.Main
./scripts/run.sh  snakeyaml drivers.snakeyaml.Main
./scripts/run.sh  stringtemplate drivers.stringtemplate.Main
./scripts/run.sh  gson drivers.gson.Main
./scripts/run.sh jettison drivers.jettison.Main
./scripts/run.sh jackson drivers.jackson.Main
./scripts/run.sh xstream drivers.xstream.Main

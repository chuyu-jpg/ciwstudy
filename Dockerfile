# Dockerfile for replication package 

FROM joebloggz/doop:feb20
COPY tmp /analysis/dataset/tmp
COPY data /data
COPY analysis /analysis
COPY analysis.dl /doop/souffle-logic/analyses/context-insensitive/analysis.dl
WORKDIR /analysis
RUN mvn compile

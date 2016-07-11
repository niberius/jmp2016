#!/bin/bash
mvn clean install
cd application/
mvn assembly:single
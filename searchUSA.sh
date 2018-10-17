#!/bin/bash
javac -d ./bin/ -cp ./src/ src/map/*.java
java -cp ./bin map.SearchUSA $1 $2 $3

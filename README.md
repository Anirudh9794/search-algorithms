The project is built in java using eclipse IDE.
* Pre-requisites: default-jdk
```
	sudo apt-get install default-jdk
```

To compile and run the program:
* in LINUX environment searchUSA.sh script can be used to run the program in linux environment:
```
	./searchUSA.sh <ALGORITHM> <SOURCE> <DESTINATION>
```
* else run the following commands to run the program:
```
	javac -d bin/ -cp src/ src/map/*.java
	java -cp bin/ map.SearchUSA <ALGORITHM> <SOURCE> <DESTINATION>
```
* output.png shows the recorded output obtained by running on a remote linux machine(ubuntu16.04)
	Since the image did not have javac installed, it should be installed using the following command:

		sudo apt-get install default-jdk

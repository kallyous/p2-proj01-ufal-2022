
all:
	javac -classpath ".:sqlite-jdbc-3.39.3.0.jar:json-simple-1.1.1.jar" -d . src/projetator/*.java

test:
	make all
	java -classpath ".:sqlite-jdbc-3.39.3.0.jar:json-simple-1.1.1.jar" projetator.Main

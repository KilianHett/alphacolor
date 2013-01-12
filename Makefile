

default:
	javac src/*
	mv src/* bin

exec:
	java -cp bin AlphaColor


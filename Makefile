BIN = ./bin
SRC = ./src


default:
	@javac -d $(BIN) $(SRC)/*.java

exec:
	@java -cp bin AlphaColor


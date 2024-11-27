#Makefile

program: main.o
	gcc main.o -o program

main.o: main.c asm.h
	gcc -g -Wall -Wextra -fanalyzer -c main.c -o main.o

clean:
	rm *.o program

run: program
	./program

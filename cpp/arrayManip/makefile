# File:    makefile
# Author:  HouPha Vang
# Program: Assignment 5

# This makefile will compile and link the program and produce
# an executable file called prog

#macros
EXE=prog
OBJECTS=main.o average.o biggest.o count.o getOddEven.o output.o read.o smallest.o
CFLAGS=-Wall
LFLAGS= 

$(EXE):		$(OBJECTS)
		g++ -o $(EXE) $(OBJECTS) $(LFLAGS)

average.o:	average.cpp 
	g++ $(CFLAGS) -c average.cpp
			
biggest.o:	biggest.cpp
	g++ $(CFLAGS) -c biggest.cpp
			
count.o:	count.cpp
	g++ $(CFLAGS) -c count.cpp

getOddEven.o:	getOddEven.cpp
	g++ $(CFLAGS) -c getOddEven.cpp

main.o:	main.cpp constants.h
	g++ $(CFLAGS) -c main.cpp
		
output.o:	output.cpp
	g++ $(CFLAGS) -c output.cpp
			
read.o:	read.cpp constants.h
	g++ $(CFLAGS) -c read.cpp
		
smallest.o:	smallest.cpp
	g++ $(CFLAGS) -c smallest.cpp

clean:	
	rm *.o
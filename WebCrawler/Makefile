webSearch: main.o index.o query.o
	gcc -g -o webSearch main.o index.o query.o -lm
main.o: main.c main.h
	gcc -g -c main.c
index.o: index.c index.h
	gcc -g -c index.c
query.o: query.c query.h
	gcc -g -c query.c
#ifndef QUERY_H_
#define QUERY_H_
#include "index.h"
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>


void treeSearch(struct node * subTree, char * word, int length, int * count);

int webScore(char ** wordArr, struct tree * arrTree [], int MAX_N, int numberOfWords);

int startQuery(struct tree * arrTree [], int MAX_N);

int calculations(struct tree * top, char * word, int numberPages, struct tree * arrTree [], int index);

int webPagesContain(struct tree * arrTree [], char * word, int numberPages);

int countURLS(struct tree ** arrTree, int MAX_N);

#endif

#ifndef INDEX_H_
#include INDEX_H_

#endif
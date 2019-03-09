#ifndef INDEX_H_
#define INDEX_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MAX_ADDR_LENGTH 1000
#define MAX 30000
#define CHILD 26

struct tree{
  char * addr;
  int totalTermCount;
  struct node *root;
  int * wordScore;
  double * frequency;
  double score;
};

struct node{
  int count;
  struct node * children[CHILD];
  char data;
};

int indexPage(char * url, struct tree * node);

int crawler(char * filename, int MAX_N);

int getLink(const char* srcAddr, char* link, const int maxLinkLength);

int getText(const char* srcAddr, char* buffer, const int bufSize);

void wordCount(struct tree ** treeArr, int size);

void count(struct node* root, int * total);

void printTrieContents(struct node* root);

int freeTrieMemory(struct node* root);

#endif
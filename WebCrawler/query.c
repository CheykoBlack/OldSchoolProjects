#include "index.h"
#include "query.h"




/*
This file is in charge of taking user input for a web query and then returning the top  3 results
*/

//Continually asks user for query which is then displayed back to console. Passes inputs to webScore for calculations
//Takes array of trees and the number of trees for parameters
int startQuery(struct tree * arrTree [], int MAX_N) {
    
    //Holds strings to be passed to webScore()
    char ** wordArr = malloc(sizeof(char *) * 20);
    
    if(wordArr == NULL) {
        
        printf("Could not malloc wordArr");
        return -1;
        
    }
    
    int i;
    
    for(i = 0; i < 20; i++) {
        
        wordArr[i] = malloc(sizeof(char) * 100);
        
        
        if(wordArr[i] == NULL) {
            
            printf("Failed to malloc wordArr at %d", i);
        }
        
    }
    
    char * searchWords;
    char delimit [] = " ";
    size_t length = 0;
    size_t read = 0;
    int continueQuery = 1;
    
//Continues until user enters a blank query    
    while(continueQuery) {
        
        int foundUpper = 0;
        printf("\nEnter a web query: ");
    
        read=getline(&searchWords,&length,stdin);
        
        if (read > 0 && searchWords[read-1] == '\n') {
            searchWords[--read] = '\0';
        }
        
        //Checks for empty query
        if( read == 0) {
            
            printf("Exiting the program\n");
            continueQuery = 0;
            break;
        }
        
        //Checks to make sure input is correctly formatted
        for (i = 0; searchWords[i] != '\0'; i++) {
        
            if(isupper(searchWords[i]) || (!isalpha(searchWords[i]) && !isspace(searchWords[i]))) {
            
                foundUpper = 1;
            
            }
        
        }
        
        //Prints error if incorrect format from user
        if(foundUpper) {
        
            printf("Please enter a query containing only lower-case letters.\n");
            continue;
        
        }else{
        
            printf("Query is \"%s\".", searchWords);
            printf("\nIDF scores are");
        
            char * word;
            word = strtok(searchWords, delimit);
            int k = 0;
            int numberOfWords = 0;
            
            while(word != NULL) {
                strcpy(wordArr[k], word);
                
                k++;
                numberOfWords++;
                word = strtok(NULL, delimit);
            }
            
//Passes search words, array of trees, number of trees and the number of search words to webScore which does the calculations for
//all trees in the array
            webScore(wordArr, arrTree, MAX_N, numberOfWords);
            
            
            int i;
            
            for(i = 0; i < numberOfWords; i++) {
                printf("\nIDF(%s): %.7f", wordArr[i], arrTree[0]->frequency[i]);
                
            }

//Figures out the top scores for all tree and stores them in array to be printed            
            struct tree * topScores [3];
            topScores[0] = malloc(sizeof(struct tree));
            topScores[1] = malloc(sizeof(struct tree));
            topScores[2] = malloc(sizeof(struct tree));
            
            if(topScores[0] == NULL || topScores[1] == NULL || topScores[2] == NULL) {
                
                printf("Coud not malloc topScores");
                exit(1);
            }
            
            topScores[0]->addr = malloc(sizeof(char) * 1000);
            topScores[1]->addr = malloc(sizeof(char) * 1000);
            topScores[2]->addr = malloc(sizeof(char) * 1000);
            
            topScores[0]->score = 0;
            
            topScores[1]->score = 0;
            
            topScores[2]->score = 0;
            
            for(i = 0; i < MAX_N; i++) {
                
                if(arrTree[i]->score >= topScores[0]->score) {
                    
                    strncpy(topScores[2]->addr, topScores[1]->addr, 1000);
                    strncpy(topScores[1]->addr, topScores[0]->addr, 1000);
                    topScores[2]->score = topScores[1]->score;
                    topScores[1]->score = topScores[0]->score;
                    strncpy(topScores[0]->addr, arrTree[i]->addr, 1000);
                    topScores[0]->score = arrTree[i]->score;

                }else if(arrTree[i]->score > topScores[1]->score) {
                    
                    strncpy(topScores[2]->addr, topScores[1]->addr, 1000);
                    topScores[2]->score = topScores[1]->score;
                    strncpy(topScores[1]->addr, arrTree[i]->addr, 1000);
                    topScores[1]->score = arrTree[i]->score;

                }else if(arrTree[i]->score > topScores[2]->score) {
                    
                    strncpy(topScores[2]->addr, arrTree[i]->addr, 1000);
                    topScores[2]->score = arrTree[i]->score;
                }
            }
            
            printf("\nWeb pages:");
            if(topScores[0]->score > 0){
                printf("\n1. %s (score: %0.4f)\n", topScores[0]->addr, topScores[0]->score);
            }
            if(topScores[1]->score > 0){
                printf("2. %s (score: %0.4f)\n", topScores[1]->addr, topScores[1]->score);
            }
            if(topScores[2]->score > 0){
                printf("3. %s (score: %0.4f)\n", topScores[2]->addr, topScores[2]->score);
            }
            if(topScores[0]->score <= 0 && topScores[1]->score <= 0 && topScores[2]->score <= 0){
                printf("\n");
            }
            
            free(topScores[0]->addr);
            free(topScores[1]->addr);
            free(topScores[2]->addr);
            free(topScores[0]);
            free(topScores[1]);
            free(topScores[2]);
        }
        
        int i = 0;
        for(i = 0; i < MAX_N; i++){
                free(arrTree[i]->wordScore);
                free(arrTree[i]->frequency);
        }
        
    }
    for(i = 0; i < 20; i++) {
            free(wordArr[i]);
    }
    free(wordArr);
    free(searchWords);

    return 0;
}

//Takes words to be searched for, array of trees, number of trees and the number of search words for parameters.
//Iterates through tree sending each one to calculations for score computation
int webScore(char ** wordArr, struct tree * arrTree [], int MAX_N, int numberOfWords) {
    
    if(wordArr == NULL || arrTree == NULL) {
        
        fprintf(stderr, "%s", "Empty Query\n");
        exit(1);
    }
    
    int i = 0;
//Iterates through all trees to malloc their wordScore and frequency pointers    
    for(i; i < MAX_N; i++) {
        
       arrTree[i]->wordScore = malloc(sizeof(double) * numberOfWords);
       arrTree[i]->frequency = malloc(sizeof(double) * numberOfWords);
        
       if(arrTree[i]->wordScore == NULL || arrTree[i]->frequency == NULL) {
           
           printf("Could not malloc wordScore/frequency");
           exit(1);
       } 
       arrTree[i]->score = 0;
    }

    
    int j;
//Sends each tree to calculations to get their wordScore (for each word) and their frequency. Multiplies them to get total web page score    
    for(i = 0; i < MAX_N; i++) {
        
        for(j = 0; j < numberOfWords; j++) {
            
            calculations(arrTree[i], wordArr[j], countURLS(arrTree, MAX_N), arrTree, j);
        }
    }
    
    
    
}

int countURLS(struct tree ** arrTree, int MAX_N){
    int count = 0;
    int index = 0;
    for(index; index < MAX_N; index++){
        if(arrTree[index]->addr != NULL){
            count++;
        }
    }
    return count;
}

//Uses helper methods treeSearch and webPageContain to get the count for each word and how many web pages it occurs on. Then stores them in
//tree to be printed later
int calculations(struct tree * top, char * word, int numberPages, struct tree * arrTree [], int index) {

    int wordCount = 0;
    treeSearch(top->root, word, strlen(word), &wordCount);
    int numPagesContain = webPagesContain(arrTree, word, numberPages);
    
    double termFrequency = (wordCount * 1.0)/(top->totalTermCount * 1.0);
    double inverseDF = log((1.0 + numberPages)/(1.0 + numPagesContain));
    
    top->wordScore[index] = termFrequency;
    top->frequency[index] = inverseDF;
    
    top->score += (termFrequency * inverseDF);
}

         
void treeSearch(struct node * subTree, char * word, int length, int * count) {
    int index = *word - 'a';
    if(length == 0){
        *count = subTree->count;
        return;
    }
    else{
        if(subTree == NULL){
            *count = 0;
            return;
        }
        if(subTree->children[index] != NULL){
            treeSearch(subTree->children[index], ++word, --length, count);
        }
    }
        
    
}

//Modified treeSearch that iterates through all trees searching for the target word. If it was found then the count is increased.
//Count is needed in calculations method to get the number of webPages that contain the target word
int webPagesContain(struct tree * arrTree [], char * word, int numberPages) {
    
    int count = 0;
    int i;
    
    for(i = 0; i < numberPages; i++) {
        int contains = 0;
        treeSearch(arrTree[i]->root, word, strlen(word), &contains);
        if(contains > 0) {
            
            count++;
        }
    }
    
    return count;
}


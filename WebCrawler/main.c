#include "main.h"
/* main.c
 * Only responsible for taking the required command-line arguments and putting them where they need to go,
 *      and then calling the starting function in index.c
 */
 
int main(int argc, char ** argv){
    long seed;
    char fileName[MAX_LENGTH];
    int maxPages;
    
    if(argc != 4){
        printf("USAGE: %s fileName numHops seed\n", argv[0]);
        return -1;
    }
    
    seed = atol(argv[3]);
    strncpy(fileName, argv[1], MAX_LENGTH);
    maxPages = atoi(argv[2]);
    
    srand(seed);
    
    if(!crawler(fileName, maxPages)){
        printf("Failed to open file.\n");
        return -1;
    }
}
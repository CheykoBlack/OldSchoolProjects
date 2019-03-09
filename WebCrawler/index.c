#include "index.h"
/* index.c
 * Handles all of the indexing phase of the program, and calls the starting function in query.c when ready.
 */

// getLink function from crawler project
int getLink(const char* srcAddr, char* link, const int maxLinkLength){
    const int bufSize = 1000;
    char buffer[bufSize];

    int numLinks = 0;

    FILE *pipe;

    snprintf(buffer, bufSize, "curl -s \"%s\" | python getLinks.py", srcAddr);

    pipe = popen(buffer, "r");
    
    if(pipe == NULL){
        fprintf(stderr, "ERROR: could not open the pipe for command %s\n", buffer);
        return 0;
    }

    fscanf(pipe, "%d\n", &numLinks);

    if(numLinks > 0){
        int linkNum;
        double r = (double)rand() / ((double)RAND_MAX + 1.0);

        for(linkNum=0; linkNum<numLinks; linkNum++){
            fgets(buffer, bufSize, pipe);
      
            if(r < (linkNum + 1.0) / numLinks){
		    break;
            }
        }

        /* copy the address from buffer to link */
        strncpy(link, buffer, maxLinkLength);
        link[maxLinkLength-1] = '\0';
    
        /* get rid of the newline */
        {
        char* pNewline = strchr(link, '\n');
            if(pNewline != NULL){
		        *pNewline = '\0';
            }
        }   
    }

    pclose(pipe);

    if(numLinks > 0){
        return 1;
    }
    else{
        return 0;
    }
}

// The indexing function, 
int indexPage(char * url, struct tree * node){
    node->addr = (char*)malloc(MAX_ADDR_LENGTH);
    strcpy(node->addr,url);
    printf("%s\n",node->addr);
    
    const int bufSize = MAX;
    char buffer[bufSize];
    char * letters;
    char * delim = "1234567890_.,!?#$%^&*  \n";
    
    if(!getText(url, buffer, bufSize)){
        printf("Failed to populate the buffer with characters.\n");
    }
    
    int index = 0;
    int whitespace = 0;
    
    struct node * root = calloc(1, sizeof(struct node)); // Like this!
    
    if(root == NULL){
        printf("Failed to calloc root of tree.\n");
        exit(1);
    }
    
    int count = 0;
    char word[256];
    
    letters = strtok(buffer, delim);
    do{
        int index = 0;
        int str = strlen(letters);
        while(index < str){
            letters[index] = tolower(letters[index]);
            index++;
        }
        if(letters != NULL){
            if(letters != ""){
                printf("\t%s\n",letters);
                str = strlen(letters);
                addWordOccurrence(letters, str, root);
            }
        }
        letters = strtok(NULL, delim);
    }while(letters != NULL);
    
    node->root = root;
    return 1;
}

int searchTree(struct tree ** root, int size, char * url){
    
    int index = 0;
    for(index; index < size; index++){
        if(root[index]->addr!=NULL){
            
            if(strcmp(root[index]->addr,url) == 0){
                return 0;
            }
        }
        else{
            return 1;
        }
    }
    return 1;
}


// Populates the buffer with text from the url
int getText(const char* srcAddr, char* buffer, const int bufSize){
  FILE *pipe;
  int bytesRead;

  snprintf(buffer, bufSize, "curl -s \"%s\" | python getText.py", srcAddr);

  pipe = popen(buffer, "r");
  if(pipe == NULL){
    fprintf(stderr, "ERROR: could not open the pipe for command %s\n", buffer);
    return 0;
  }

  bytesRead = fread(buffer, sizeof(char), bufSize-1, pipe);
  buffer[bytesRead] = '\0';

  pclose(pipe);

  return bytesRead;
}


int crawler(char * filename, int MAX_N){
    FILE * file = fopen(filename,"r");
    
    if(file == NULL){
        fprintf(stderr, "Error opening file: %s\n",filename);
        return 0;
    }
    
    int hopNumber;  // Number of pages currently read
    int n = 0;
    char url[MAX_ADDR_LENGTH]; // first line of file read
    int hopsLimit; // next line of file read
    
    struct tree * arrTree[MAX_N];
    int index = 0;
    
    for(index; index < MAX_N; index++){
        
        arrTree[index] = (struct tree*)malloc(sizeof(struct tree));
        
    }
    
    index = 0;
 
    printf("Indexing...\n");
    while(!feof(file) && url != NULL && n < MAX_N){
        fscanf(file, "%s %i", url, &hopsLimit);
        hopNumber = 0;
        
        while(1){
            if( searchTree(arrTree, index, url) ){
                
                indexPage(url, arrTree[index]);
                n++;
                index++;
            }
            hopNumber++;
            
            if(hopNumber <= hopsLimit && n < MAX_N){
                int links = getLink(url, url, 1000);
                
                if(!links){
                    break;
                }
            }
            else{
                break;
            }
        }
    }
    
    fclose(file);
    wordCount(arrTree,MAX_N);
    
    int i = 0;
    
    startQuery(arrTree, MAX_N);

    for(i = 0; i < MAX_N; i++) {
        freeTrieMemory(arrTree[i]->root);
        free(arrTree[i]->addr);
        free(arrTree[i]);
    }
    
    return 1;
}

int freeTrieMemory(struct node* root){
    int i;
    if(root != NULL){
        for(i=0; i < CHILD; i++){
            if(root->children[i] !=NULL){
                freeTrieMemory(root->children[i]);
            }
        }
        free(root);
        root = NULL;
        return 1;
    }  
    return 0;
}

int addWordOccurrence(const char* word, int wordLength, struct node* root){
    int index = *word - 'a';
    if(strlen(word) == 0){
        root->count += 1;
        return 0;
    }
    else{
        if(!root->children[index]){
            struct node* child = calloc(1,sizeof(struct node));
            if(child == NULL){
                printf("Failed to calloc child node.\n");
                exit(1);
            }
            child->count = 0;
            child->data = *word;
            root->children[index] = child;
            addWordOccurrence(++word, wordLength, root->children[index]);
        }
        else{
            addWordOccurrence(++word, wordLength, root->children[index]);
        }
    }
    return 0;
}

void wordCount(struct tree ** treeArr, int size){
    int index  = 0;
    
    for(index; index < size; index++){
            if(treeArr[index] != NULL && treeArr[index]->root != NULL){
                count(treeArr[index]->root, &treeArr[index]->totalTermCount);
            }
    }
}

void count(struct node* root, int * total){
    if (root -> count > 0){
         
         *total += root->count;
    }
    
    int i;
    for (i = 0; i < 26; i++) {
        if (root -> children[i] != NULL) {
            count(root -> children[i], total);
        }
    }
}

char temp[256];
void printTrieContents(struct node* root){
    if (root -> count > 0){
         printf("%s: %d\n", temp, root -> count);
    }
    
    int i;
    for (i = 0; i < 26; i++) {
        if (root -> children[i] != NULL) {
            int j = strlen(temp);
            temp[j] = root -> children[i] -> data;
            temp[j + 1] = '\0';
            printTrieContents(root -> children[i]);
        }
    }
    temp[strlen(temp) - 1] = '\0';
}
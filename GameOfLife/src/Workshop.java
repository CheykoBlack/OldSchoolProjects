package martcd08_cs161_project2;
import java.util.ArrayList;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 2: The Game of Life
*
* Description:
    Workshop contains the logic of the game without any GUI elements.
*/
public class Workshop {
    /* Data Fields */
    protected String message;                  // Stores result of game
    protected boolean finished;                // True when the game has ended
    protected boolean[][] mirror;              // Represents the current generation, stores true for living grids and false for dead grids
    protected int rows;                        // The number of rows in the grid
    protected int columns;                     // The number of columns in the grid
    protected final boolean[][] allFalse;      // Named constant that stores the array of false values to use as a comparison
    protected ArrayList<boolean[][]> history;  // Used to store generations as the game runs
    protected int minLifeToLife;               // Minimum number of 'true' required for life
    protected int maxLifeToLife;               // Maximum number of 'true' required for life
    protected int minDeadToLife;               // Minimum number of 'true' required to bring false to true
    protected int maxDeadToLife;               // Maximum number of 'true' required to bring false to true
    // End Data Fields //
    
    /* Methods */
    /**
     * Workshop constructor, takes two int parameters for the dimensions of the 2D array used throughout the game to store 'true' and 'false' values.
     * @param r int for rows
     * @param col int for columns
     */
    public Workshop(int r, int col){
        rows = r;       // Sets rows to r
        columns = col;  // Sets columns to col
        
        mirror = new boolean[rows][columns]; // Creates a 2D boolean array using rows and columns
        
        allFalse = new boolean[rows][columns];  // Instanciates allFalse to the same size as mirror
        
        history = new ArrayList<boolean[][]>();
        
        /* For loop to populate allFalse with false in every index */
        for(int row = 0; row < rows; row++){
            for(int cols = 0; cols < columns; cols++){
                allFalse[row][cols] = false;
                //System.out.print(allFalse[row][cols] + " ");
            }
            //System.out.println("");
        }
    }
    // End Workshop(int r, int col) //
    
    /**
     * setParameters sets the minLifeToLife, maxLifeToLife, minDeadtoLife, and maxDeadToLife variables to those chosen by the user in settings
     * @param minLife int, number of alive required to keep a cell alive
     * @param maxLife int, max number of alive required to keep a cell alive
     * @param minDeath int, min number of alive required to bring a cell to life
     * @param maxDeath int, max number of alive required to bring a cell to life
     */
    public void setParameters(int minLife, int maxLife, int minDeath, int maxDeath){
        minLifeToLife = minLife;
        maxLifeToLife = maxLife;
        minDeadToLife = minDeath;
        maxDeadToLife = maxDeath;
    }
    // End setParamters //
    
    /**
     * resertMirror() sets each index in mirror to false
     */
    public void resetMirror(){
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                mirror[r][c] = false;
            }
        }
    }
    // End resetMirror() //
    
    /**
     * resetHistory() clears the ArrayList history of all previous generations
     */
    public void resetHistory(){
        history.clear();
    }
    // End resetHistory() //
    
    /**
     * isAlive returns false if k or j is negative, or if k is greater than rows or j is greater than columns.
     * If k and j are both valid indices then isAlice returns the boolean stored in mirror[k][j]
     * @param k row
     * @param j column
     * @return boolean stored in mirror at the specified index
     */
    private boolean isAlive(int k, int j){
        if(k < 0 || k > rows || j < 0 || j > columns){
            return false;
        }
        else{
            return mirror[k][j];
        }
    }
    // End isAlive() //
    
    /**
     * neighborCount(int k, int j) counts the number of alive neighbors around each index in the 2-D array of boolean values.
     * @param k int for row
     * @param j int for column
     * @return int (number of alive neighbors)
     */
    private int neighborCount(int k, int j){
        int count = 0;
        if(k >= 0 && k < rows && j >= 0 && j < columns){
            /* Case 1: Index in a corner */
            // Top left corner
            if(k == 0 && j == 0){
                if(isAlive(k, j+1)){
                    count++;
                }
                if(isAlive(k+1, j)){
                    count++;
                }
                if(isAlive(k+1, j+1)){
                    count++;
                }
            }
            
            // Top right corner
            else if(k == 0 && j == columns -1){
                if(isAlive(k, j-1)){
                    count++;
                }
                if(isAlive(k+1, j-1)){
                    count++;
                }
                if(isAlive(k+1, j)){
                    count++;
                }
            }
            
            // Bottom left corner
            else if(k == rows - 1 && j == 0){
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j+1)){
                    count++;
                }
                if(isAlive(k, j+1)){
                    count++;
                }
            }
            
            // Bottom right corner
            else if(k == rows - 1 && j == columns -1){
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j-1)){
                    count++;
                }
                if(isAlive(k, j-1)){
                    count++;
                }
            }
            
            /* Case 2: Index at an edge */
            // Column 0
            else if(k > 0 && k < rows-1 && j == 0){
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j+1)){
                    count++;
                }
                if(isAlive(k, j+1)){
                    count++;
                }
                if(isAlive(k+1, j+1)){
                    count++;
                }
                if(isAlive(k+1, j)){
                    count++;
                }
            }
            
            // Row 0
            else if(k == 0 && j > 0 && j < columns-1){
                if(isAlive(k, j-1)){
                    count++;
                }
                if(isAlive(k+1, j-1)){
                    count++;
                }
                if(isAlive(k+1, j)){
                    count++;
                }
                if(isAlive(k+1, j+1)){
                    count++;
                }
                if(isAlive(k, j+1)){
                    count++;
                }
            }
            
            // Index in final column
            else if(k > 0 && k < rows-1 && j == columns-1){
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j-1)){
                    count++;
                }
                if(isAlive(k, j-1)){
                    count++;
                }
                if(isAlive(k+1, j-1)){
                    count++;
                }
                if(isAlive(k+1, k)){
                    count++;
                }
            }
            
            // Index is final row
            else if(k == rows-1 && j > 0 && j < columns-1){
                if(isAlive(k, j-1)){
                    count++;
                }
                if(isAlive(k-1, j-1)){
                    count++;
                }
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j+1)){
                    count++;
                }
                if(isAlive(k, j+1)){
                    count++;
                }
            }
            
            /* Case 3: All inner indices */
            else{
                if(isAlive(k-1, j-1)){
                    count++;
                }
                if(isAlive(k-1, j)){
                    count++;
                }
                if(isAlive(k-1, j+1)){
                    count++;
                }
                if(isAlive(k, j-1)){
                    count++;
                }
                if(isAlive(k, j+1)){
                    count++;
                }
                if(isAlive(k+1, j-1)){
                    count++;
                }
                if(isAlive(k+1, j)){
                    count++;
                }
                if(isAlive(k+1, j+1)){
                    count++;
                }
            }
        }
        return count;
    }
    // End neighborCount() //
    
    /**
     * equals(boolean[][] arrA, boolean[][] arrB) checks the values of one 2-D array of boolean values against another.
     *      Returns true if they are equal, returns false if they are not.
     * @param arrA boolean[][]
     * @param arrB boolean[][]
     * @return boolean
     */
    private boolean equals(boolean[][] arrA, boolean[][] arrB){
        // If statement to check lengths, if their lengths don't match they cannot be equal
        if((arrA.length != arrB.length) || (arrA[0].length != arrB[0].length)){
            return false;
        }
        
        // For loop to iterate through each index and check it against each index in the same spot in the other array
        for(int r = 0; r < arrA.length ; r++){
            for(int c = 0; c < arrA[r].length; c++){
                if(arrA[r][c] != arrB[r][c]){
                    return false;
                }
            }
        }
        
        // Return true if and only if the arrays pass each of the previous tests
        return true;
    }
    // End equals() //
    
    /**
     * listPositionOf() returns the position of a target in a ArrayList that stores boolean 2-D arrays.
     * @param target 2-D array that contains boolean values
     * @return int
     */
    private int listPositionOf(boolean[][] target){
        int count = 1;
        
        // For loop to iterate through the ArrayList searching for the target 2-D boolean array, the counter increases each time the target isn't found and once it is found the count is returned
        for(int index = 0; index < history.size(); index++){
            if(equals((history.get(index)),target)){
                return count;
            }
            else{
                count++;
            }
        }
        
        // If the array is never found in the ArrayList then 0 is returned
        return 0;
    }
    // End listPositionOf() //
    
    /**
     * nextGeneration() uses the listPositionOf() method and the countNeighbors() methods to calculate the boolean values of the next generation in the game.
     *      This method also contains the messages displayed on the game board throughout each round, including the two possible final messages
     */
    public void nextGeneration(){
        int position;
        history.add(mirror);
        finished = false;
        
        // Instanciates next using rows and columns
        boolean[][] next = new boolean[rows][columns];
        
        
        // For loop to iterate through the previously instanciated boolean 2-D array to determine which indexes are false and which are true
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                if(isAlive(r, c)){
                    if(minLifeToLife <= neighborCount(r, c) && maxLifeToLife >= neighborCount(r, c)){
                        next[r][c] = true;
                    }
                    else{
                        next[r][c] = false;
                    }
                }
                else{
                    if(minDeadToLife <= neighborCount(r, c) && maxDeadToLife >= neighborCount(r, c)){
                        next[r][c] = true;
                    }
                    else{
                        next[r][c] = false;
                    }
                }
            }
        }
        // End for loop
        
        String extinctMsg = "After " + history.size() + " generations life is extinct in Island";
        
        // If statement to check which message is displayed and whether the game will continue
        /* If the 2-D array next is entirely false */
        if(equals(allFalse, next)){
            message = extinctMsg;   // message is assigned the extinctMsg, informing players that their entire island is dead (T-T)
            mirror = next;          // mirror is assigned next
            finished = true;        // finished is assigned true
            return;                 // method terminates
        }
        /* If the 2-D array next isn't entirely false */
        else{
           position = listPositionOf(next); // position is assigned the position of the array in the ArrayList
        }
        
        String cycleMsg = "After " + (position-1) + " generations life is cyclic of length " + (history.size()-position+1);
        
        // If the position of the array is greater than 0, or positive...
        if(position > 0){
            message = cycleMsg;     // message is assigned cycleMsg to inform players the information regarding their cycle
            mirror = next;          // mirror is assigned next
            finished = true;        // finished is assigned true
            return;                 // method terminates
        }
        // If the position of the array is less than 0, or negative...
        else{
            mirror = next;      // mirror is assigned next
            message = "Go on!"; // message is assigned the String "Go on!"
        }
    }
    // End nextGeneration()
    /* End Methods */
}
/* End Workshop */

package martcd08_cs161_project1;
import java.util.ArrayList;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 1: MyStringBuilder
*
* Description: MyStringBuilder class is my own attempt at a StringBuilder class is order to help understand and practice some of the various String methods as well as ArrayList objects.
*/
public class MyStringBuilder {
    /* Fields */
    private ArrayList<Character> cList;     // Stores String objects as an ArrayList of characters.
    // End Fields
    
    /* Methods */
    /**
     * Constructor which assigns the passed ArrayList object list to the ArrayList object cList.
     * @param list Character ArrayList which initializes cList
     */
    public MyStringBuilder(ArrayList<Character> list){
        cList = list;
    }
    /* End MyStringBuilder(ArrayList<Character> list) */
    
    /**
     * Constructor which creates an ArrayList object with length of String passed as argument,
     *      then stores each character from that String in a character array, and then runs
     *      a for loop through that character array to add each element to the ArrayList object.
     * @param text String passed to be added, character-by-character, to cList.
     */
    public MyStringBuilder(String text){
        // Instanciates cList to the size of the String supplied
        cList = new ArrayList<Character>(text.length());
        
        // Declares and instanciates a char array to the size of the String supplied
        char[] characters = new char[text.length()];
        
        // Adds the String, character-by-character, to the char array
        text.getChars(0, text.length(), characters, 0);
        
        // For loop to add each index of the char array to cList
        for(int index = 0; index < characters.length; index++){
            cList.add(characters[index]);
        }
    }
    /* End MyStringBuolder(String text) */
    
    /**
     * Constructor which creates an ArrayList object with the length of the supplied char array,
     *      then adds each element of the char array to cList.
     * @param text 
     */
    public MyStringBuilder(char[] text){
        // Instanciates cList to the size of the char array passed
        cList = new ArrayList<Character>(text.length);
        
        // For loop to add each element of the char array to cList.
        for(int index = 0; index < text.length; index++){
            cList.add(text[index]);
        }
    }
    /* End MyStringBuilder(char[] text) */
    
    /**
     * Constructor which takes another MyStringBuilder object and copies each element into another
     *      MyStringBuilder object by copying each element from other's cList and adding it to
     *      the new cList.
     * @param other 
     */
    public MyStringBuilder(MyStringBuilder other){
        // Instanciates an ArrayList object
        cList = new ArrayList<Character>();
        
        // For loop to run through the supplies MyStringBuilder object's cList and add each element one by one to the new MyStringBuilder's cList
        for(int index = 0; index < other.cList.size(); index++){
            cList.add(other.cList.get(index));
        }
    }
    /* End MyStringBuilder(MyStringBuilder other) */
    
    /**
     * Constructor which instantiates cList to the length of size. 
     * @param size 
     */
    public MyStringBuilder(int size){
        // Instanciates an ArrayList object to the size specified, if size is a positive number
        if(size >= 0){
            cList = new ArrayList<Character>(size);
        }
        else{
            cList = new ArrayList<Character>();
        }
    }
    /* End MyStringBuilder(int size) */
    
    /**
     * Default constructor.
     */
    public MyStringBuilder(){
        cList = new ArrayList<>();
    }
    /* End default constructor */
    
    /**
     * toArray() takes the MyStringBuilder object and returns the elements in a char[] array.
     * @return char[] array
     */
    public char[] toArray(){
        // Declare and instanciates a char[] array with a size equal to the size of the cList ArrayList object
        char[] charArray = new char[cList.size()];
        
        // For loop to run through the cList ArrayList object and populate each element on-by-one into each index of the char[] array
        for(int index = 0; index < cList.size(); index++){
            charArray[index] = cList.get(index);
        }
        
        // Returns the char[] array now populated with the elements from the cList ArrayList object
        return charArray;
    }
    /* End toArray() */
    
    /**
     * convertToString() returns the MyStringBuilder object as a String
     * @return 
     */
    public String convertToString(){
        // String calls the valueOf() method, passing MyStringBuilder's method toArray(), in order to return the char[] array created by toArray() as a String
        return String.valueOf(toArray());
    }
    /* End convertToString */
    
    /**
     * toString() calls toString() with respect to the field, cList.
     * @return 
     */
    public String toString(){
        return cList.toString();
    }
    /* End toString() */
    
    /**
     * equals() calls equals with respect to each object's fields
     * @param other MyStringBuilder object being compared with the MyStringBuilder object calling the method
     * @return true if their fields are the same, false otherwise
     */
    public boolean equals(MyStringBuilder other){
        // if check to make sure the object being compared is an instance of the MyStringBuilder class, if not false is returned, else cList calls equals and passes other.cList as the argument
        if(other instanceof MyStringBuilder){
            return cList.equals(other.cList);
        }
        return false;
    }
    /* End equals() */
    
    /**
     * append(char item) adds a character to the cList ArrayList object
     * @param item character being added to the end of cList
     */
    public void append(char item){
        cList.add(item);
    }
    /* End append(char item) */
    
    /**
     * append(String item) adds a String to the MyStringBuilder object's cList field
     * @param item 
     */
    public void append(String item){
        MyStringBuilder tempString = new MyStringBuilder(item);
        cList.addAll(tempString.cList);
    }
    /* End append(String item) */
    
    /**
     * append(char[] item) adds a char[] array to the cList ArrayList object of the calling MyStringBuilder object
     * @param item 
     */
    public void append(char[] item){
        MyStringBuilder tempChar = new MyStringBuilder(item);
        cList.addAll(tempChar.cList);
    }
    /* End append(char[] item) */
    
    /**
     * append(MyStringBuilder item) adds the cList of another MyStringBuilder object to the cList of the calling MyStringBuilder object
     * @param item 
     */
    public void append(MyStringBuilder item){
        cList.addAll(item.cList);
    }
    /* End append(MyStringBilder item) */
    
    /**
     * insert(int position, char item) inserts the given character into the position specified
     * @param position index at which to insert item
     * @param item character being inserted
     */
    public void insert(int position, char item){
        // If/else to make sure the position specified is a valid index
        if(position >= 0 && position < cList.size()){
            cList.add(position, item);
        }
        // If the position isn't a valid index, add the chracter to the end of cList
        else{
            cList.add(item);
        }
    }
    /* End inter(int position, char item) */
    
    /**
     * insert(int position, MyStringBuilder item) inserts the supplied MyStringBuilder object into the MyStringBuilder object that is calling this method into the position supplied
     * @param position index at which to insert item
     * @param item MyStringBuilder object being inserted
     */
    public void insert(int position, MyStringBuilder item){
        // If/else to check that position is a valid index
        if(position >= 0 && position < cList.size()){
            cList.addAll(position,item.cList);
        }
        // If position is not a valid index, adds the MyStringBuilder object's cList to the end of the calling object's cList
        else{
            cList.addAll(item.cList);
        }
    }
    /* End insert(int position, MyStrightBuilder item) */
    
    /**
     * insert(int position, String item) inserts the supplied string into the cList of the calling MyStringBuilder object.
     * @param position index at which to insert item
     * @param item String being inserted
     */
    public void insert(int position, String item){
        // Declare and instanciate a MyStringBuilder object which contains the supplied String
        MyStringBuilder tempString = new MyStringBuilder(item);
        
        // If/else statement to verify that position is a valid index, if it is adds the String into cList at the specified position
        if(position >= 0 && position < cList.size()){
            cList.addAll(position, tempString.cList);
        }
        // If the position is not a valid index, adds the String to the end of cList.
        else{
            cList.addAll(tempString.cList);
        }
    }
    /* End insert(int position, String item) */
    
    /**
     * insert(int position, char[] item) inserts the supplied char[] array into the cList of the calling MyStringBuilder at the specified position
     * @param position index at which to insert the char[] array
     * @param item char[] array being inserted
     */
    public void insert(int position, char[] item){
        // Declares and instanciates a MyStringBuilder object to create a cList containing the supplied char[] array
        MyStringBuilder tempCharArray = new MyStringBuilder(item);
        
        // If/else statement to validate that position is a valid index, if it is the cList from the previously created MyStringBuilder object is inserted at the index specified
        if(position >= 0 && position < cList.size()){
            cList.addAll(position, tempCharArray.cList);
        }
        // If position isn't a valid index, the char[] array is inserted into the end of cList.
        else{
            cList.addAll(tempCharArray.cList);
        }
    }
    /* End insert(int position, char[] item) */
    
    /**
     * insert(int position, ArrayList<Character> item) inserts the supplied Character ArrayList into the MyStringBuilder object at the position specified.
     * @param position index at which to begin insertion of item
     * @param item ArrayList<Character> being inserted
     */
    public void insert(int position, ArrayList<Character> item){
        // If/Else statement to validate that position is a valid index, if it is the supplied ArrayList object is inserted into cList starting at the supplied position
        if(position >= 0 && position < cList.size()){
            cList.addAll(position, item);
        }
        // If position is not a valid index, the ArrayList object is inserted at the end of cList
        else{
            cList.addAll(item);
        }
    }
    /* End insert(int position, ArrayList<Character> item) */
    
    /**
     * delete(int start, int end) removes the characters in the MyStringBuilder object starting at start and ending with end (end not included)
     * @param start the starting index, removed
     * @param end the ending index, not removed
     */
    public void delete(int start, int end){
        // if statement to make sure that start and end are both greater than or equal to 0, start and end are both less than the length of cList, and start is less than end
        if(start >= 0 && start < cList.size() && end >= 0 && end < cList.size() && start < end){
            // For loop to iterate through cList backwards, starting at the end index and removing each item from the end back to the starting index
            for(int index = end-1; index >= start ; index--){
                cList.remove(index);
            }
        }
    }
    /* End delete(int start, int end) */
    
    /**
     * deleteCharAt(int position) removes the character at the index supplied
     * @param position 
     */
    public void deleteCharAt(int position){
        // If statement to verify that position is greater than zero and less than the size of cList
       if(position >= 0 && position < cList.size()){
           cList.remove(position);
       } 
    }
    /* End deleteCharAt(int position) */
    
    /**
     * deleteAll removes every instance of item from the MyStringBuilder object from start to end not including the end point.
     * @param start beginning index to search, removed if the element matches item
     * @param end which index to stop the search, not removed even if the element matches item
     * @param item the element to search for, in this case a character
     */
    public void deleteAll(int start, int end, char item){
        // If statement to verify that start and end are both valid indices and that start is smaller than end
        if(start >= 0 && end >= 0 && start < end && start < cList.size() && end < cList.size()){
            // For loop to iterate backwards through cList starting at the index just before end and ending with the starting index
            for(int index = end-1; index >= start; index--){
                // If statement to verify that only the elements which match item are removed
                if(cList.get(index).equals(item)){
                    cList.remove(index);
                }
            }
        }
    }
    /* End deleteAll(int start, int end, char item) */
    
    /**
     * replace(int position, char item) replaces the element at the supplied index with the item passed as an argument.
     * @param position index to replace with item
     * @param item item to be put into cList
     */
    public void replace(int position, char item){
        // If statement to verify that position is a valid index
        if(position >= 0 && position < cList.size()){
            cList.set(position, item);
        }
    }
    /* End replace(int position, char item) */
    
    /**
     * replace(int start, int end, MyStringBuilder item) replaces the elements from start to end with the MyStrightBuilder object passed as an argument.
     * @param start index at which to start the replacement, included in the replacement
     * @param end index at which to end the replacement, not included in the replacement
     * @param item MyStrightBuilder object to be inserted
     */
    public void replace(int start, int end, MyStringBuilder item){
        // If statement to validate the start and end indices
        if(start >= 0 && end >= 0 && start < cList.size() && end < cList.size() && start < end){
            delete(start, end);
            insert(start, item);
        }
    }
    /* End replace(int start, int end, MyStringBuilder item) */
    
    /**
     * replace(int start, int end, String item) replaces the elements from start to end with the supplied String.
     * @param start index to start the replacement, included
     * @param end index to end the replacement, not included
     * @param item String to be inserted
     */
    public void replace(int start, int end, String item){
        // If statement to verify that both start and end are valid indices
        if(start >= 0 && end >= 0 && start < cList.size() && end < cList.size() && start < end){
            delete(start, end);
            insert(start, item);
        }
    }
    /* End replace(int start, int end, String item) */
    
    /**
     * replace(int start, int end, char[] item) replaces the elements from start to end with the supplied char[] array.
     * @param start index at which to begin, included
     * @param end index at which to end, not included
     * @param item char[] array that is inserted
     */
    public void replace(int start, int end, char[] item){
        // If statement to verify that start and end are valid indices
        if(start >= 0 && end >= 0 && start < cList.size() && end < cList.size() && start < end){
            delete(start, end);
            insert(start, item);
        }
    }
    /* End replace(int start, int end, char[] item) */
    
    /**
     * replace(int start, int end, ArrayList<Character> item) replaces the elements from start to end with the supplied ArrayList<Character> object.
     * @param start index where replacement begins, included
     * @param end   index where replacement ends, not included
     * @param item  ArrayList<Character> object inserted
     */
    public void replace(int start, int end, ArrayList<Character> item){
        // If statement to verifiy that start and end are valid indices
        if(start >= 0 && end >= 0 && start < cList.size() && end < cList.size() && start < end){
            delete(start, end);
            insert(start, item);
        }
    }
    /* End replace(int start, int end, ArrayList<Character> item) */
    
    /**
     * setCharAt(int position, char item) is identical to replace(int position, char item). Replaces the element at the specified index with the character supplied.
     * @param position Index to be replaced
     * @param item Character to be inserted into the supplied index
     */
    public void setCharAt(int position, char item){
        // If statement to verify that position is a valid index
        if(position >= 0 && position < cList.size()){
            cList.set(position, item);
        }
    }
    /* End setCharAt(int position, char item) */
    
    /**
     * charAt(int position) returns the character at the index specified by position.
     * @param position index returned
     * @return character at position in cList
     */
    public char charAt(int position){
        // If statement to verify that position is a valid index
        if(position >= 0 && position < cList.size()){
            return cList.get(position);
        }
        // If position isn't a valid index, returns 1, which prints an empty box on my console
        return 1;
    }
    /* End charAt(int position) */
    
    /**
     * indexOf(char target) returns the index of the target element if the element is found in the MyStrightBuilder element, else returns -1.
     * @param target the character being searched for
     * @return the index where the target is located, or -1 if the target isn't found
     */
    public int indexOf(char target){
        // For loop to iterate through the cList
        for(int index = 0; index < cList.size(); index++){
            // If statement to check whether or not the element at the index of the loop equals the target, if it does it returns the index.
            if(cList.get(index).equals(target)){
                return index;
            }
        }
        // If the target character isn't found, returns -1
        return -1;
    }
    /* End indexOf(char target) */
    
    /**
     * indexOf(String target) returns the index of the String passed as an argument.
     * @param target String being searched for
     * @return index where the target String begins
     */
    public int indexOf(String target){
        return convertToString().indexOf(target);
    }
    /* End indexOf(String target) */
}

import java.util.Random;
/*
* Chelsey Martin
* CS160-03 Fall 2017
* Project 4: Deal or No Deal
*
* Description. The BriefcaseCollection class handles the collection of cases. This class deals with setting up the cases with their values, randomizing those values, and dealing with user input when
*       the user picks a case.
*
*/
public class BriefcaseCollection {
    /* Data Fields */
    private Briefcase[] cases;  // A Briefcase type array that stores the collection of briefcases for the game.
    // End Data Fields //
    
    /* Methods */
    /**
     * permute() creates a random permutation of the elements in the cases briefcase array.
     */
    private void permute(){
        int q = 0;
        int m = 0;
        Random rd = new Random();
        for(int k = 0; k < cases.length; k++){
            q = rd.nextInt(cases.length);
            m = (k + q) % cases.length;
            double case1 = cases[k].getValue();
            double case2 = cases[m].getValue();
            cases[k].setValue(case2);
            cases[m].setValue(case1);
        }
    }
    // End permute() //
    
    /**
     * chooseThisCase() sets the chosen field of the case with an index 1 less than the parameter which to true.
     *          Returns the value stored in that index.
     * @param which
     * @return
     */
    public Briefcase chooseThisCase (int which){
        cases[(which-1)].setChosen(true);
        return cases[(which-1)];
    }
    // End chooseThisCase() //
    
    /**
     * showRemainingCases() prints to the console the serial numbers of the remaining cases.
     *      Serial numbers are the index number plus one.
     *      A chosen case will have a dollar value of 0, so anything that isn't a 0 is a remaining case.
     */
    public void showRemainingCases(){
        for(int k = 0; k < cases.length; k++){
            if(!(cases[k].isChosen())){
                System.out.print((k+1) + " ");
            }
        }
    }
    // End showRemainingCases() //
    
    /**
     * invalidChoice() checks to make sure the user's desired serial number hasn't already been used for the current game.
     * @param index
     * @return 
     */
    public boolean invalidChoice (int index){
        if(index < 1 || index > cases.length || cases[index-1].isChosen()){
            return true;
        }
        else{
            return false;
        }
    }
    // End invalidChoice() //
    
    /**
     * computeAverage() checks each index of cases and assigns it to tempValue if the current value stored doesn't equal 0.
     *      As each value is stored to tempValue it is also added up with all the previously stored values and a counter increases by 1 each time a non-empty case is found.
     *      Returns the average by dividing tempValue and counter.
     * @return 
     */
    public double computeAverage(){
        double tempValue = 0;
        double counter = 0;
        for(int k = 0; k < cases.length; k++){
            if(!(cases[k].isChosen())){
                counter++;
                tempValue += cases[k].getValue();
            }
        }
        return tempValue/counter;
    }
    // End computeAverage() //
    
    /**
     * getLastCase() runs a for loop to check each index of cases and find the last remaining briefcase.
     *      Returns the last remaining briefcase.
     * @return 
     */
    public Briefcase getLastCase(){
        Briefcase chosen = cases[0];
        for(int k = 0; k < cases.length; k++){
            if(!(cases[k].isChosen())){
                chosen = cases[k];
            }
        }
        return chosen;
    }
    // End getLastCase() //
    
    /**
     * Default Constructor. Takes a double type array parameter to instantiate and populate the Briefcase type array called cases,
     *      then randomizes the order of the briefcase objects in the cases array with the permute() method.
     * @param values 
     */
    public BriefcaseCollection(double[] values){
        // Instantiates cases to the length of the values array.
        cases = new Briefcase[values.length];
        
        // For loop to populate the cases array with Briefcase objects instantiated with the value located at values[k].
        for(int k = 0; k < cases.length; k++){
            cases[k] = new Briefcase(values[k]);
        }
        
        // Call permute() to randomly rearrange the briefcases in the array.
        permute();
    }
    // End BriefcaseCollection() //
    // End Methods //
}

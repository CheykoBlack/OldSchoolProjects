/*
* Chelsey Martin
* CS160-03 Fall 2017
* Project 4: Deal or No Deal
*
* Description. The Briefcase class handles each individual case that is stored in the BriefcaseCollection. This class contains methods that allow us to look at the values in the cases, change the values,
*       flag a case as being chosen, and check whether a case has been chosen or not.
*
*/
public class Briefcase {
    /* Data Fields */
    private double $value;      // Dollar value in the briefcase.
    private boolean chosen;     // True if the player has chosen a briefcase, false otherwise.
    // End Data Fields //
    
    /* Methods */
    /**
     * Getter method that returns the value stored in $value.
     * @return 
     */
    public double getValue(){
        return $value;
    }
    // End getValue() //
    
    /**
     * Setter method that assigns value to $value.
     * @param value 
     */
    public void setValue(double value){
        $value = value;
    }
    // End setValue() //
    
    /**
     * Getter method that returns the value stored in chosen.
     * @return 
     */
    public boolean isChosen(){
        return chosen;
    }
    // End isChosen() //
    
    /**
     * Setter method that assigns the parameter chosen to the class data field chosen.
     * @param chosen 
     */
    public void setChosen(boolean chosen){
        this.chosen = chosen;
    }
    // End setChosen() //
    
    /**
     * Default constructor, initializes %value.
     * @param value 
     */
    public Briefcase(double value){
        $value = value;
    }
    // End Briefcase() //
    // End Methods //
}

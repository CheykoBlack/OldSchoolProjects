package martcd08_cs161_project3;
import java.awt.event.*;
import javax.swing.*;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 3: MVC on the Clock
*
* Description:
    A non-GUI class that handles all the application data required for the working clock.
*/
public class ClockWork {
    /* Data Fields */
    private int longArmIndex;                       // Stores the current position of the minute arm
    private int shortArmIndex;                      // Stores the current position of the hour arm
    private int secondsArmIndex;                    // Stores the current position of the second arm
    private int radius;                             // Value passed by the GUI when the frame is built
    private int centerX;                            // Value passed by the GUI when the frame is built
    private int centerY;                            // Value passed by the GUI when the frame is built
    private int[] x = new int[60];                  // Coordinates of the end of the tacks on the clock face
    private int[] y = new int[60];                  // Coordinates of the end of the tacks on the clock face
    private int[] xx = new int[60];                 // Coordinates of the end of the tacks on the clock face
    private int[] yy = new int[60];                 // Coordinates of the end of the tacks on the clock face
    private int tickTime;                           // Stores the delay value in milliseconds, how long each second takes to 'tick'
    private Timer timer;                            // Controls the hand movement
    private ActionListener listener;                // Executes actionPerformed in the GUI
    private ActionEvent event;                      // The parameter passed to listener's actionPerformed
    /* End Data Fields */
    
    /* Methods*/
    /**
     * Constructor, instantiates event.
     */
    public ClockWork(){
        event = new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "clock");
    }
    // End constructor
    
    /**
     * getArmIndexValues() creates an array containing the current int stores in longArmIndex, shortArmIndex, and secondsArmIndex and returns the array.
     * @return int[]
     */
    public int[] getArmIndexValues(){
        // Create an int array called armIndexValues and fill the array with the locations of the short arm, long arm, and seconds arm
        int[] armIndexValues = {longArmIndex, shortArmIndex, secondsArmIndex};
        // return the array 
        return armIndexValues;
    }
    // End getArmIndexValues()
    
    /**
     * getCoordinates() creates a 2D array called coordinates and using four separate for loops, populates the 2D array as follows;
     *      - The first column is populated with the int[] x's values.
     *      - The second column is populated with the int[] y's values.
     *      - The third column is populated with the int[] xx's values.
     *      - The fourth column is populated with the int[] yy's values.
     * @return int[][]
     */
    public int[][] getCoordinates(){
        // Creates a 2-D int array with 60 rows and 4 columns. Each column holds a single x, y, xx, or yy value.
        int[][] coordinates = new int[60][4];
        
        // For loop to load the 2-D array. Loops runs through each column and fills the rows of each column with either x, y, xx, or yy values.
        for(int row = 0; row < coordinates.length; row++){
            coordinates[row][0] = x[row];
            coordinates[row][1] = y[row];
            coordinates[row][2] = xx[row];
            coordinates[row][3] = yy[row];
        }
        
        // Return the 2-D array
        return coordinates;
    }
    // End getCoordinates()
    
    /**
     * setCircle sets the dimensions for the clock face. Used to determine where the tick marks go as well as where all the clock hands start from the center.
     * @param radius int for radius
     * @param centerX int for centerX
     * @param centerY int for centerY
     */
    public void setCircle(int radius, int centerX, int centerY){
        this.radius = radius;       // Sets the current ClockWork object's radius to the value supplied
        this.centerX = centerX;     // Sets the current ClockWork object's centerX to the value supplied
        this.centerY = centerY;     // Sets the current ClockWork object's centerY to the value supplied
    }
    // End setCircle()
    
    /**
     * setTickTime() sets the speed at which the clock 'ticks' based on the value supplied in the ControlDevices class by the user manipulating the slider on the GUI
     * @param tick int
     */
    public void setTickTime(int tick){
        tickTime = tick;    // Sets the current ClockWork object's tickTime to the passed tick
    }
    // End setTickTime()

    /**
     * addActionListener sets the parameter passed as the listener object for the ClockWork object calling the method
     * @param watch ActionListener
     */
    public void addActionListener(ActionListener watch){
        listener = watch;   // Sets the current ClockWork object's listener to the supplied watch
    }
    // End addActionListener()
    
    /**
     * loadTacks() Runs two for loops, one to load the x,y int[] and another to load the xx,yy int[]. These will be used for creating the individual dash marks on the clock face.
     */
    public void loadTacks(){
        // For loop to run through the x and y array and load them with the point at which they begin on the clock face
        for(int index = 0; index < x.length; index++){
            x[index] = (int)(centerX + radius*Math.sin(2*index*Math.PI/60));
            y[index] = (int)(centerY - radius*Math.cos(2*index*Math.PI/60));
        }
        
        // For loop to run through the xx and yy array and load them with the point at which they end on the clock face
        for(int index = 0; index < xx.length; index++){
            // If statement to set the length of the longer tick marks
            if(index%5 == 0){
                xx[index] = (int)(centerX + (radius+15)*Math.sin(2*index*Math.PI/60));
                yy[index] = (int)(centerY - (radius+15)*Math.cos(2*index*Math.PI/60));
            }
            else{
                xx[index] = (int)(centerX + (radius+10)*Math.sin(2*index*Math.PI/60));
                yy[index] = (int)(centerY - (radius+10)*Math.cos(2*index*Math.PI/60));
            }
        }
    }
    // End loadTacks()
    
    /**
     * makeTimer() instantiates timer, passing tickTime as the delay and creating an anonymous TimerListener object as a listener. The method then starts the timer.
     */
    public void makeTimer(){
        // Creates a new timer object with the delay of tickTime and a new anonymous listener from the inner class TimerListener
        timer = new Timer(tickTime, new TimerListener());
        // Starts the timer
        timer.start();
    }
    // End makeTimer()
    
    /**
     * stop() stops timer
     */
    public void stop(){
        // Stops the timer
        timer.stop();
    }
    // End stop()
    
    /**
     * reset() stops the timer, sets the values stored in longArmIndex, shortArmIndex, and secondsArmIndex to 0, and calls connect()
     */
    public void reset(){
        stop(); // Calls stop to stop the timer
        longArmIndex = 0;       // Sets the current position of the long arm to 0 which is equivalent to noon, 12:00, or midnight on the clock face.
        shortArmIndex = 0;      // Sets the current poisition of the short arm to 0, which is the equivalent to noon, 12:00, or midnight on the clock face.
        secondsArmIndex = 0;    // Sets the current position of the seconds arm to 0, which is the equivalent to noon, 12:00, or mightnight on the clock face.
        connect();      // Calls connect to inform FaceGUI class that something has changed in the clock.
    }
    // End reset()
    
    /**
     * timeFormat verifies the String input passed as an argument. The string must contain up to 4 integers, two integers before : and two integers after. The first 'pair' of integers must be between 1 and 24, 
     *      and the second pair of integers must be between 00 and 59.
     * @param inp String
     * @return boolean
     */
    private boolean timeFormat(String inp){
        // Creates a String array called numbers
        String[] numbers;
        // If the supplied String contains ':' continue...
        if(inp.contains(":")){
            // Fill numbers with the split String, using : as the divider
            numbers = inp.split(":");
            
            // If the length of numbers is greater than two or less than two, immediately return false
            if(numbers.length > 2 || numbers.length < 2){
                return false;
            }
            
            // If the length of numbers is equal to two...
            // Create a character array called hour and fill it with the characters in the first index of the numbers array.
            char[] hour = numbers[0].toCharArray();
            // If the length of hour is equal to 1 and the character stored is not a number, return false
            if(hour.length == 1 && !(Character.isDigit(hour[0]))){
                return false;
            }
            // If the length of hour is equal to 2 and either of the characters stored inside are not digits, return false
            if(hour.length == 2 && (!(Character.isDigit(hour[0])) || !(Character.isDigit(hour[1])))){
                return false;
            }
            
            // Create a character array called minute and store the characters in the second index of the numbers array.
            char[] minute = numbers[1].toCharArray();
            // If the length of minute is greater than 2 or either of the characters are not digits, return false
            if(minute.length > 2 || !(Character.isDigit(minute[0])) || !(Character.isDigit(minute[1]))){
                return false;
            }
            
            // If all of the previous checks have succecced...
            int hours = Integer.parseInt(numbers[0]);   // Stores the values from the first index of numbers (the hours)
            int minutes = Integer.parseInt(numbers[1]); // Stores the values from the second index of numbers (the minutes)
            
            // If statement to verify that the values of hours and minutes are valid
            if(hours > 0 && hours < 24 && minutes >= 0 && minutes < 60){
                // Return true only if hours is positive and less than 24 and minutes is greater than or equal to 0 and minutes is less than 60.
                return true;
            }
            // If hours equals 24, true is returned only if minutes is also 0.
            else if(hours == 24 && minutes == 0){
                return true;
            }
            // If any of those checks fail, return false
            else{
                return false;
            }
        }
        // If the supplied String does not contain ':' return false immediately.
        return false;
    }
    // End timeFormat()
    
    /**
     * setClock(String timeToSet) first validates that timeToSet is a valid time for a clock by passing timeToSet to timeFormat, then a String[] is created using : as a delimeter for
     *      timeToSet, then each 'half' is assigned to a different integer variable using parseInt. Finally, shortArmIndex is set to hours, longArmIndex is set to minutes,
     *      and secondsArmIndex is set to 0.
     * @param timeToSet String
     */
    public void setClock(String timeToSet){
        // If the supplied String is not a valid input for the time, 'Invalid input!' prints to the console and the method returns so nothing else happens and user input can continue.
        if(!timeFormat(timeToSet)){
            System.out.println("Invalid input!");
            return;
        }
        
        String[] numbers = timeToSet.split(":");        // Stores the supplied string once split using the delimeter ':'
        int hours = Integer.parseInt(numbers[0]);       // Stores the first index of numbers in hours
        int minutes = Integer.parseInt(numbers[1]);     // Stores the second index of numbers in minutes
        
        // If hours is less than 12, set the position of the short arm to hours*5.
        if(hours < 12){
            shortArmIndex = hours*5;
        }
        // Else if hours is greater than or equal to 12 and less than 24, set the position of the short arm to (hours-12) times 5.
        else if(hours >= 12 && hours < 24){
            shortArmIndex = (hours-12)*5;
        }
        // Else if hours is equal to 24, set the position of the short arm to 0.
        else if(hours == 24){
            shortArmIndex = 0;
        }
        
        longArmIndex = minutes; // Set the position of the long arm to minutes
        secondsArmIndex = 0;    // Set the position of the seconds arm to zero
        connect();  // Call connect to inform the FaceGUI class that a change has occured in the clock and show that change on the clock face
    }
    // End setClock(String timeToSet)
    
    /**
     * connect() has listener call actionPerformed and passes event as a parameter
     */
    private void connect(){
        // listener calls action performed and passed event, this creates an event object and signals to the FaceGUI, who is listening for an event object, that something has changed in the clock
        listener.actionPerformed(event);
    }
    // End connect()
    
    /* End Methods */
    
    /* Inner Classes */
    /**
     * TimerListner is a private inner class that implements ActionListener and takes care of updating secondsArmIndex, and shortArmIndex. Calls connect().
     */
    private class TimerListener implements ActionListener{
        /**
         * actionPerformed(ActionEvent event) updates secondsArmIndex and shortArmIndex then calls connect().
         * @param event 
         */
        public void actionPerformed(ActionEvent event){
            // Each time actionPerformed is called an event object is created and the clock face is updated by the FaceGUI. Each 'action' represents a 'tick' of the clock face.
            secondsArmIndex = (++secondsArmIndex)%60;   // The position of the seconds arm is increased and then divided by 60. The remainder is stored in secondsArmIndex.
            
            // If the position of the second arm is 0..
            if(secondsArmIndex == 0){
                longArmIndex = ++longArmIndex%60;   // The position of the long arm is increased by the increased by one position modulus 60
                
                // If the position of the long arm divided by 12 returns 0...
                if(longArmIndex%12 == 0){
                    shortArmIndex = ++shortArmIndex%60; // The position of the short arm is created by the increased by one position modulus 60
                }
            }
            connect();  // Connect is called to start the cycle over until the user stops the clock or resets the clock.
        }
        // End actionPerformed(ActionEvent event)
    }
    // End TimerListener Class
    /* End Inner Classes */
}
/* End ClockWork */

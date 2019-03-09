package martcd08_cs161_project3;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 3: MVC on the Clock
*
* Description:
    A GUI class that provides the visual appearance of the clock.
*/
public class FaceGUI extends JFrame implements ActionListener{
    /* Data Fields */
    private ClockWork work; // To aggregate ClockWork to FaceGUI
    private Face face;      // To hold the inner class object
    private int radius;     // Stores the radius of the clock
    private int centerX;    // Stores the centerX value of the clock
    private int centerY;    // Stores the centerY value of the clock
    /* End Data Fields */
    
    /* Methods*/
    /**
     * Constructor. Passes cw as work to instantiate a ClockWork object. Adds this as an action listener to work, and then calls buildClock()
     * @param cw ClockWork
     */
    public FaceGUI(ClockWork cw){
        super("Grandpa's Clock");       // Calls the constructor of JFrame and sets the title of the clock window to 'Grandpa's Clock'
        work = cw;                      // Sets work to the supplied ClockWork object
        work.addActionListener(this);   // Assigns the actionListener from this object to work
        buildClock();                   // Calls buildClock() to create the clock window
    }
    // End Constructor
    
    private void buildClock(){
        Container pane = getContentPane();                  // Stores the content pane of the main window in pane
        setSize(800,800);                                   // Sets the size of the clock window to 800 by 800
        setLocation(50,100);                                // Sets the location of the clock window to 50(x) and 100(y)
        setVisible(true);                                   // Makes the clock window visisble on the monitor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Sets the default close operation to shut the program down when the user clicks the X on the clock window
        centerX = 385;                                      // Sets the center of the clock to 385 (x)
        centerY = 370;                                      // Sets the center of the clock to 370 (y)
        radius = 300;                                       // Sets the radius of the clock to 300
        work.setCircle(radius, centerX, centerY);           // Has work call setCircle with the center x, y, and radius values
        work.loadTacks();                                   // Has work call loadTacks to use the circle data for the clock to create the x, y, xx, and yy values of the tacks that will print around the clock face
        face = new Face();                                  // Instanciates a new Face object and stores it in face
        add(face);                                          // Adds face to the center region (by default) of the clock window
    }
    
    /**
     * actionPerformed(ActionEvent event) has face call repaint()
     * @param event ActionEvent
     */
    public void actionPerformed(ActionEvent event){
        face.repaint(); // face calls repaint() every time an ActionEvent is created by the ClockWork object (occurs every time a change happens in the clock) to paint the new face of the clock in the window
    }
    // End actionPerformed(ActionEvent event)
    /* End Methods */
    
    /* Inner Classes */
    private class Face extends JPanel{
        /* Methods */
        /**
         * paintComponent(Graphics g) handles the movement of the clock face
         * @param g Graphics
         */
        public void paintComponent(Graphics g){
            super.paintComponent(g);                            // Has JPanel call paintComponent and pass the graphics object g
            int[] armIndex = work.getArmIndexValues();          // Creates an int array called armIndex that stores the arm index values from the work object
            int longArm = armIndex[0];                          // Stores the first index of armIndex. Contains the long arm position
            int shortArm = armIndex[1];                         // Stores the second index of armIndex. Contains the short arm position
            int secondArm = armIndex[2];                        // Stores the third index of armIndex. Contains the seconds arm position
            int[][] coordinates = work.getCoordinates();        // Has work call getCorrdinates() to return the 2-D array that stores the coordinates of the tack locations (x, y being where they start and xx, yy being where they end)
            int[] x = new int[60];                              // Creates an int array called x with 60 indices
            int[] y = new int[60];                              // Creates an int array called y with 60 indices
            int[] xx = new int[60];                             // Creates an int array called xx with 60 indices
            int[] yy = new int[60];                             // Creates an int array called yy with 60 indices
            
            // For loop to populate each int array with the appropriate values from the coordinates 2-D int array
            for(int row = 0; row < 60; row++){
                x[row] = coordinates[row][0];   // The row number index of x is filled with the coorindate[row] from column 0 of coordinate
                y[row] = coordinates[row][1];   // The row number index of y is filled with the coorindate[row] from column 1 of coordinate
                xx[row] = coordinates[row][2];  // The row number index of xx is filled with the coorindate[row] from column 2 of coordinate
                yy[row] = coordinates[row][3];  // The row number index of yy is filled with the coorindate[row] from column 3 of coordinate
            }
            
            setBackground(Color.YELLOW);        // Sets the background of the clock window to yellow
            g.setColor(Color.WHITE);            // Sets the color of the graphics object to white
            g.fillOval(85, 70, 600, 600);       // Draws a filled oval
            g.setColor(Color.CYAN);             // Sets the color of the graphics object to cyan
            g.fillOval(135, 120, 500, 500);     // Draws a filled oval inside the previously drawn oval
            g.setColor(Color.BLACK);            // Sets the color of the graphics object to black
            g.fillOval(365, 350, 40, 40);       // Draws a filled oval inside the previously drawn oval
            
            // For loop to draw each tack around the clock face using x and y as the starting point and xx and yy as the ending point of the tack.
            for(int tack = 0; tack < 60; tack++){
                g.drawLine(x[tack], y[tack], xx[tack], yy[tack]);
            }
            
            Font font = new Font("TimesRoman", Font.BOLD, 25);      // Creates a bold font of Times New Roman with size 25
            g.setFont(font);                                        // Sets the font of the graphics object to the created font
            g.drawString("12", 372, 50);                            // Draws a "12" at the outside of the clock face
            g.drawString("1", 542, 93);                             // Draws a "1" at the outside of the clock face
            g.drawString("2", 662, 210);                            // Draws a "2" at the outside of the clock face
            g.drawString("3", 706, 378);                            // Draws a "3" at the outside of the clock face
            g.drawString("4", 663, 542);                            // Draws a "4" at the outside of the clock face
            g.drawString("5", 543, 665);                            // Draws a "5" at the outside of the clock face
            g.drawString("6", 378, 710);                            // Draws a "6" at the outside of the clock face
            g.drawString("7", 208, 668);                            // Draws a "7" at the outside of the clock face
            g.drawString("8", 90, 543);                             // Draws a "8" at the outside of the clock face
            g.drawString("9", 50, 378);                             // Draws a "9" at the outside of the clock face
            g.drawString("10", 78, 215);                            // Draws a "10" at the outside of the clock face
            g.drawString("11", 200, 92);                            // Draws a "11" at the outside of the clock face
            
            int[] x1 = new int[60];         // Creates an int array called x1 with 60 indices
            int[] y1 = new int[60];         // Creates an int array called y1 with 60 indices
            int[] xx1 = new int[60];        // Creates an int array called xx1 with 60 indices
            int[] yy1 = new int[60];        // Creates an int array called yy1 with 60 indices
            
            // For loop to run through xx1, yy1, x1, and y1 and populate them with an edited formula from the tick marks to draw the seconds hand from the center of the clock out
            for(int k = 0; k < 60; k++){
                xx1[k] = ((int)(centerX + (radius-15)*Math.sin(2*k*Math.PI/60)));   // Ending x value for the seconds hand
                yy1[k] = ((int)(centerY - (radius-15)*Math.cos(2*k*Math.PI/60)));   // Ending y value for the seconds hand
                x1[k] = ((int)(centerX + (radius-300)*Math.sin(2*k*Math.PI/60)));   // Starting x value for all arms, located in the center of the clock face
                y1[k] = ((int)(centerY - (radius-300)*Math.cos(2*k*Math.PI/60)));   // Starting y value for  all arms, located in the center of the clock face
            }
            
            // For loop to draw the seconds hand as it rotates around the clock face with each tick of the clock
            for(int k = 0; k < 60; k++){
                if(secondArm == k){
                    g.setColor(Color.RED);  // Sets color of the graphics object to red
                    g.drawLine(x1[k], y1[k], xx1[k], yy1[k]);       // Draws the line of the seconds hand
                }
            }
            
            
            // For loop to run through xx1 and yy1 and populate them with an edited formula from the tick marks to draw the long arm from the center of the clock out
            for(int k = 0; k < 60; k++){
                xx1[k] = ((int)(centerX + (radius-40)*Math.sin(2*k*Math.PI/60)));       // Ending x value for the long arm
                yy1[k] = ((int)(centerY - (radius-40)*Math.cos(2*k*Math.PI/60)));       // Ending y value for the long arm
            }
            
            // For loop to draw the long arm with each tick of the clock
            for(int k = 0; k < 60; k++){
                if(longArm == k){
                    g.setColor(Color.BLUE); // Sets the color of the graphics object to blue
                    g.drawLine(x1[k], y1[k], xx1[k], yy1[k]);       // Draws the long arm
                    g.drawLine(x1[k]-1, y1[k]-1, xx1[k]-1, yy1[k]-1);   // Draws a second long arm 1-px away from the first long arm line.
                }
            }
            
            // For loop to run through xx1 and yy1 of the hour arm with an extended formula from the tick marks
            for(int k = 0; k < 60; k++){
                xx1[k] = ((int)(centerX + (radius-200)*Math.sin(2*k*Math.PI/60)));  // The ending x point for the hour arm
                yy1[k] = ((int)(centerY - (radius-200)*Math.cos(2*k*Math.PI/60)));  // The ending y point for the hour arm
            }
            
            // For loop to draw the hour arm with each tick of the clock
            for(int k = 0; k < 60; k++){
                if(shortArm == k){
                    g.setColor(Color.BLUE); // Sets the color of the graphics object to blue
                    g.drawLine(x1[k], y1[k], xx1[k], yy1[k]);   // Draws the hour arm
                    g.drawLine(x1[k]-1, y1[k]-1, xx1[k]-1, yy1[k]-1);   // Draws a second hour arm 1-px away from the first hour arm
                    g.drawLine(x1[k]+1, y1[k]+1, xx1[k]+1, yy1[k]+1);   // Draws a third hour arm 1+px away from the first hour arm
                }
            }
        }
        // End paintComponent
        /* End Methods */
    }
    /* End Inner Classes */
}
/* End FaceGUI */

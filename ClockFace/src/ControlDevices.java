package martcd08_cs161_project3;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 3: MVC on the Clock
*
* Description:
    A GUI class that represents the control of the clock.
*/
public class ControlDevices extends JFrame implements ActionListener, ChangeListener{
    /* Data Fields */
    private ClockWork work;                                                     // Aggregates ClockWork to ControlDevices.
    private JButton run = new JButton("Run the Clock");                         // JButton to click for clock to start
    private JButton stop = new JButton("Stop the Clock");                       // JButton to click for clock to stop
    private JButton reset = new JButton("Reset the Clock");                     // JButton to click to reset the clock
    private JButton set = new JButton("Set the Clock");                         // JButton to click to set the time on the clock
    private JSlider speed = new JSlider(JSlider.VERTICAL, 0, 1000, 200);        // JSlider to allow user to set tick time
    private JLabel runLabel = new JLabel("Click to start the clock");           // JLabel for the run button
    private JLabel stopLabel = new JLabel("Click to stop the clock");           // JLabel for the stop button
    private JLabel resetLabel = new JLabel("Click to reset the clock");         // JLabel for the reset button
    private JLabel setLabel = new JLabel(" Enter time, use format 15:42 ");     // JLabel for the set button
    private JLabel speedLabel = new JLabel("Select tick time");                 // JLabel for the speed slider
    private JTextField setField = new JTextField(5);                            // JTextField to allow users to enter the time they wish to set the clock to
    /* End Data Fields */
    
    /* Methods */
    /**
     * Constructor. Initializes work with the supplied parameter. Calls makeSlider() and buildControlGUI().
     * @param cw 
     */
    public ControlDevices(ClockWork cw){
        super("Control of Clockwork");  // Calls the constructor of JFrame and passes 'Control of Clockwork' to set as the title of the window that contains user controls
        work = cw;  // Stores the supplied ClockWork object in work
        makeSlider();   // Calls makeSlider to create the slider that allows the user to control the clock tick speed
        buildControlGUI();  // Calls buildControlGUI to create the window that contains user controls
    }
    // End ControlDevices()
    
    /**
     * makeSlider() creates the tick marks on the JSlider speed
     */
    private void makeSlider(){
        speed.setMajorTickSpacing(100);     // Sets the major tick spacing of the slider to 100
        speed.setMinorTickSpacing(10);      // Sets the minor tick spacing of the slider to 10
        speed.setPaintTicks(true);          // Shows ticks
        speed.setPaintLabels(true);         // Shows labels
        speed.setBackground(Color.CYAN);    // Sets the background of the slider to cyan
        work.setTickTime(speed.getValue()); // Has work call setTickTime to set the speed of the timer object to the value selected by the user on this slider
    }
    // End makeSlider()
    
    /**
     * buildControlGUI() creates the window that will allow users to control the functions of the clock
     */
    private void buildControlGUI(){
        Container pane = getContentPane();      // Grabs and stores the main window
        setSize(600,500);                       // Sets the size of the user control window to 600 width and 500 height
        setLocation(900,250);                   // Sets the location of the user control window on the monitor to 900(x) and 250(y)
        setLayout(new BorderLayout(15,0));      // Sets the layout of the user control window to a BorderLayout with vertical spacing of 15
        pane.setBackground(Color.DARK_GRAY);    // Sets the background of the user control window to dark gray, this will show in the spacing between the regions.
        setVisible(true);                       // Makes the user control window visible on the monitor
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Sets the default close operation of the user control window to shut the program down when the user pressed the X
        
        // Center Region
        JPanel center = new JPanel();                                           // Creates a new JPanel called center
        JPanel row1 = new JPanel();                                             // Creates a new JPanel called row1
        JPanel row2 = new JPanel();                                             // Creates a new JPanel called row2
        JPanel row3 = new JPanel();                                             // Creates a new JPanel called row3
        JPanel row4 = new JPanel();                                             // Creates a new JPanel called row4
        row4.setBackground(Color.PINK);                                         // Sets the background of row4 to pink
        center.setLayout(new GridLayout(4, 0));                                 // Sets the layout of center to a GridLayout with 4 rows and 0 columns
        row1.add(run);                                                          // Adds run button to row1
        run.addActionListener(this);                                            // Registers the actionListener from this object to run
        row1.add(runLabel);                                                     // Adds runLabel to row1
        row2.add(stop);                                                         // Adds stop button to row2
        stop.addActionListener(this);                                           // Registers the actionListener from this object to stop
        row2.add(stopLabel);                                                    // Adds stopLabel to row2
        row3.add(reset);                                                        // Adds reset button to row3
        reset.addActionListener(this);                                          // Registers the actionListener from this object to reset
        row3.add(resetLabel);                                                   // Adds resetLabel to row3
        row4.add(setLabel);                                                     // Adds setLabel to row4
        setLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));     // Sets a border 2 px wide and black around setLabel
        row4.add(setField);                                                     // Adds setField to row4
        setField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));     // Sets a black border 2 px wide around setField
        row4.add(set);                                                          // Adds set button to row4
        set.addActionListener(this);                                            // Registers the actionListener from this object to set
        center.add(row1);                                                       // Adds row1 to center, fills in row 1
        center.add(row2);                                                       // Adds row2 to center, fills in row 2
        center.add(row3);                                                       // Adds row3 to center, fills in row 3
        center.add(row4);                                                       // Adds row4 to center, fills in row4
        add(center, BorderLayout.CENTER);                                       // Adds center to the center region of the main user control window
        
        // East Region
        JPanel east = new JPanel();                     // Creates a new JPanel called east
        JPanel grid = new JPanel();                     // Creates a new JPanel called grid
        east.setLayout(new BorderLayout(20,0));         // Sets the layout of east to a BorderLayout with a 20 px vertical gap
        grid.setLayout(new GridLayout(0,2,10,0));       // Sets the layout of grid to a GridLayout with 2 columns and a 10 px vertical gap
        grid.add(speed);                                // Adds speed, the slider, to grid, fills up the first column
        speed.addChangeListener(this);                  // Registers this eventListener to speed
        grid.add(speedLabel);                           // Adds speedLabel to grid
        east.add(grid, BorderLayout.CENTER);            // Adds grid to the center region of east
        east.add(new JPanel(), BorderLayout.EAST);      // Adds an anonymous JPanel to the east region of east
        east.add(new JPanel(), BorderLayout.WEST);      // Adds an anonymous JPanel to the west region of east
        east.add(new JPanel(), BorderLayout.NORTH);     // Adds an anonymous JPanel to the north region of east
        east.add(new JPanel(), BorderLayout.SOUTH);     // Adds an anonymous JPanel to the south region of east
        add(east, BorderLayout.EAST);                   // Adds east to the east region of the main user control window
    }
    // End buildControlGUI()
    
    /**
     * stateChanged(ChangeEvent event) sets the tickTime in the ClockWork object work to the value set on the speed slider by the user
     * @param event ChangeEvent
     */
    public void stateChanged(ChangeEvent event){
        work.setTickTime(speed.getValue());     // Whenever a ControlDevices object calls stateChanged with a ChangeEvent object work calls setTickTime with the value on the slider
    }
    // End stateChanged(ChangeEvent event)
    
    /**
     * actionPerformed(ActionEvent event) does the following;
     *      If stop is pressed by the user, run is enabled and the clock is stopped.
     *      If run is pressed by the user, run is disabled and the clock is started.
     *      If reset is pressed by the user, run is enabled and the clock is reset to Midnight 00:00.
     *      If set is pressed by the user, the time entered into the text field is set on the clock.
     * @param event ActionEvent
     */
    public void actionPerformed(ActionEvent event){
        // If the event was generated by stop...
        if(event.getSource() == stop){
            run.setEnabled(true);   // run is enabled
            work.stop();            // The clock is stopped
        }
        // If the event was generated by run...
        if(event.getSource() == run){
            run.setEnabled(false);  // run is disabled
            work.makeTimer();       // The clock is started
        }
        // If the event was generated by reset...
        if(event.getSource() == reset){
            run.setEnabled(true);   // run is enabled
            work.reset();           // The clock is set back to the default time
        }
        // If the event was generated by set...
        if(event.getSource() == set){
            work.setClock(setField.getText());  // The clock is set to the time supplied by the user
            setField.setText("");               // The field where a user types the time they wish to set is emptied
        }
    }
    // End actionPerformed(ActionEvent event)
    /* End Methods */
}
/* End ControlDevices */

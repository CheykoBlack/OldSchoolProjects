package martcd08_cs161_project2;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
/*
* Chelsey Martin
* CS161 – 01 Spring 2018
* Project 2: The Game of Life
*
* Description:
    Island contains the GUI elements for the Game of Life as well as the ActionListeners and the GUI for the game settings and associated ActionListeners.
*/
public class Island extends JFrame implements ActionListener{
    /* Data Fields */
    protected Workshop shop;            // Stores the Island button's which contain the boolean value 'true' if alive and 'false' if dead
    protected JButton[][] population;   // JButton 2-D array that stores each grid of the Island
    protected JButton mark;             // JButton to click to enable the game board
    protected JButton next;             // JButton to click to continue to the next generation
    protected JButton play;             // JButton to click to advance the game automatically
    protected JButton reset;            // JButton to click to reset the game board to start a new game
    protected JTextField northField;    // JTextField that will print the results of the game
    protected JLabel initial;           // JLabel for the mark button
    protected JLabel nextGen;           // JLabel for the next button
    protected JLabel resetGame;         // JLabel for the reset button
    protected JTextField minLife;       // JTextField for minLife
    protected JTextField maxLife;       // JTextField for maxLife
    protected JTextField minDeath;      // JTextField for minDeath
    protected JTextField maxDeath;      // JTextField for maxDeath
    protected JTextField redField;      // JTextField for R in RGB
    protected JTextField greenField;    // JTextField for G in RGB
    protected JTextField blueField;     // JTextField for B in RGB
    protected JPanel right;             // JPanel that displays the color chosen by user input of R,G, and B
    protected JTextField rowsField;     // JTextField for number of rows
    protected JTextField columnField;   // JText Field for number of columns
    protected JButton random;           // JButton to click to randomize the color selection
    protected JButton ok;               // JButton for 'OK' on the settings GUI
    protected JButton stop;             // JButton to click to stop the auto play
    protected boolean autoBoolean;
    protected Play auto = new Play();
    protected Timer delay = new Timer(250,auto);
    protected Color alive = Color.YELLOW;
    // End Data Fields //
    
    /* Methods */
    /**
     * Default constructor. This isn't required by the project outline, however it allows the client to create the entire game board
     *      by only calling this constructor and following the on-screen prompts. 
     */
    public Island(){
        // Creates a window
        super(">>>>>>GAME OF LIFE SETTINGS<<<<<<");
        
        // Instantiate the object for the ActionListener class
        Settings listener = new Settings();
        
        // Window Parameters
        setSize(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        /* North Region */
        JPanel title = new JPanel();
        JLabel titleLabel = new JLabel("Game of Life Settings");
        title.add(titleLabel);
        add(title,BorderLayout.NORTH);
        // End North Region
        
        /* West Region */
        JPanel west = new JPanel();
        west.setLayout(new GridLayout(0,1));
        JLabel parameters = new JLabel("Life Parameters");
        minLife = new JTextField(1);
        minLife.setText("1");
        minLife.addActionListener(listener);
        maxLife = new JTextField(1);
        maxLife.setText("4");
        maxLife.addActionListener(listener);
        minDeath = new JTextField(1);
        minDeath.setText("1");
        minDeath.addActionListener(listener);
        maxDeath = new JTextField(1);
        maxDeath.setText("4");
        maxDeath.addActionListener(listener);
        
        JPanel parametersTitle = new JPanel();
        parametersTitle.add(parameters);
        
        JPanel westBox = new JPanel();
        JPanel options = new JPanel();
        options.setLayout(new GridLayout(0,1));
        options.add(parametersTitle);
        JPanel minLifePanel = new JPanel();
        JLabel minLifeLabel = new JLabel("Min Life");
        minLifePanel.add(minLifeLabel);
        minLifePanel.add(minLife);
        options.add(minLifePanel);
        
        JPanel maxLifePanel = new JPanel();
        JLabel maxLifeLabel = new JLabel("Max Life");
        maxLifePanel.add(maxLifeLabel);
        maxLifePanel.add(maxLife);
        options.add(maxLifePanel);
        
        JPanel minDeathPanel = new JPanel();
        JLabel minDeathLabel = new JLabel("Min Death");
        minDeathPanel.add(minDeathLabel);
        minDeathPanel.add(minDeath);
        options.add(minDeathPanel);
        
        JPanel maxDeathPanel = new JPanel();
        JLabel maxDeathLabel = new JLabel("Max Death");
        maxDeathPanel.add(maxDeathLabel);
        maxDeathPanel.add(maxDeath);
        options.add(maxDeathPanel);
        
        westBox.add(options);
        west.add(westBox);
        
        add(west, BorderLayout.WEST);
        // End West Region
        
        /* Center Region */
        JPanel center = new JPanel();
        JPanel centerChoices = new JPanel();
        centerChoices.setLayout(new GridLayout(0,2));
        
        JPanel left = new JPanel();
        left.setLayout(new GridLayout(0,1));
        JPanel centerTitlePanel = new JPanel();
        JLabel centerTitle = new JLabel("Color choice");
        centerTitlePanel.add(centerTitle);
        left.add(centerTitlePanel);
        
        JPanel red = new JPanel();
        JLabel redLabel = new JLabel("Red");
        redField = new JTextField(2);
        redField.setText("255");
        redField.addActionListener(listener);
        red.add(redLabel);
        red.add(redField);
        left.add(red);
        
        JPanel green = new JPanel();
        JLabel greenLabel = new JLabel("Green");
        greenField = new JTextField(2);
        greenField.setText("255");
        greenField.addActionListener(listener);
        green.add(greenLabel);
        green.add(greenField);
        left.add(green);
        
        JPanel blue = new JPanel();
        JLabel blueLabel = new JLabel("Blue");
        blueField = new JTextField(2);
        blueField.setText("0");
        blueField.addActionListener(listener);
        blue.add(blueLabel);
        blue.add(blueField);
        left.add(blue);
        
        random = new JButton("random");
        random.addActionListener(listener);
        left.add(random);
        
        right = new JPanel();
        right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        right.setBackground(Color.YELLOW);
        
        centerChoices.add(left);
        centerChoices.add(right);
        center.add(centerChoices);
        add(center, BorderLayout.CENTER);
        // End Center Region
        
        /* East Region */
        JPanel east = new JPanel();
        JPanel eastTitle = new JPanel();
        JLabel eastTitleLabel = new JLabel("Grid Size");
        
        eastTitle.add(eastTitleLabel);
        
        JPanel eastChoice = new JPanel();
        eastChoice.setLayout(new GridLayout(0,1));
        eastChoice.add(eastTitle);
        
        JPanel rowsPanel = new JPanel();
        JLabel rows = new JLabel("Rows");
        rowsField = new JTextField(1);
        rowsField.setText("5");
        rowsField.addActionListener(listener);
        rowsPanel.add(rows);
        rowsPanel.add(rowsField);
        eastChoice.add(rowsPanel);
        
        JPanel columnPanel = new JPanel();
        JLabel columns = new JLabel("Columns");
        columnField = new JTextField(1);
        columnField.setText("5");
        columnField.addActionListener(listener);
        columnPanel.add(columns);
        columnPanel.add(columnField);
        eastChoice.add(columnPanel);
        
        east.add(eastChoice);
        add(east, BorderLayout.EAST);
        // End East Region
        
        /* South Region */
        JPanel south = new JPanel();
        ok = new JButton("OK");
        ok.addActionListener(listener);
        south.add(ok);
        add(south, BorderLayout.SOUTH);
        // End South Region
    }
    // End Island() //
    
    /**
     * Settings is a private class which contains the action listeners for the settings GUI, which weren't required by the project description but optional instead
     */
    private class Settings implements ActionListener{
        public void actionPerformed(ActionEvent e){
            /* Color choice */
            int red = 255;
            int green = 255;
            int blue = 0;
            
            if(redField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Red cannot be left blank", "Red is empty", JOptionPane.ERROR_MESSAGE);
                redField.setText("255");
            }
            else if(Integer.parseInt(redField.getText()) < 0){
                JOptionPane.showMessageDialog(null, "Red cannot be negative", "Red is negative", JOptionPane.ERROR_MESSAGE);
                redField.setText("255");
            }
            else if(Integer.parseInt(redField.getText()) > 255){
                JOptionPane.showMessageDialog(null, "Red cannot be greater than 255", "Red is too large", JOptionPane.ERROR_MESSAGE);
                redField.setText("255");
            }
            else{
                red = Integer.parseInt(redField.getText());
            }
            
            if(greenField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Green cannot be left blank", "Green is empty", JOptionPane.ERROR_MESSAGE);
                greenField.setText("255");
            }
            else if(Integer.parseInt(greenField.getText()) < 0){
                JOptionPane.showMessageDialog(null, "Green cannot be negative", "Green is negative", JOptionPane.ERROR_MESSAGE);
                greenField.setText("255");
            }
            else if(Integer.parseInt(greenField.getText()) > 255){
                JOptionPane.showMessageDialog(null, "Green cannot be greater than 255", "Green is too large", JOptionPane.ERROR_MESSAGE);
                greenField.setText("255");
            }
            else{
                green = Integer.parseInt(greenField.getText());
            }
            
            if(blueField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Blue cannot be left blank", "Blue is empty", JOptionPane.ERROR_MESSAGE);
                blueField.setText("0");
            }
            else if(Integer.parseInt(blueField.getText()) < 0){
                JOptionPane.showMessageDialog(null, "Blue cannot be negative", "Blue is negative", JOptionPane.ERROR_MESSAGE);
                blueField.setText("0");
            }
            else if(Integer.parseInt(blueField.getText()) > 255){
                JOptionPane.showMessageDialog(null, "Blue cannot be greater than 255", "Blue is too large", JOptionPane.ERROR_MESSAGE);
                blueField.setText("0");
            }
            else{
                blue = Integer.parseInt(blueField.getText());
            }
            
            Color background = new Color(red, green, blue);
            
            if(red == 211 && blue == 211 && green == 211){
                JOptionPane.showMessageDialog(null, "Color choice cannot be the same color as a 'dead' cell", "Color choice cannot be light gray", JOptionPane.ERROR_MESSAGE);
                redField.setText("255");
                greenField.setText("255");
                blueField.setText("255");
                background = Color.YELLOW;
            }
            else{
                background = new Color(red, green, blue);
            }
            
            right.setBackground(background);
            
            /* Parameter Choice */
            int minLifeValue = 1;
            int maxLifeValue = 4;
            int minDeathValue = 1;
            int maxDeathValue = 4;
            
            if(minLife.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a value for Min Life", "Min Life cannot be empty", JOptionPane.ERROR_MESSAGE);
                minLife.setText("1");
            }
            else if(Integer.parseInt(minLife.getText()) < 1){
                JOptionPane.showMessageDialog(null, "Min Life cannot be less than 1", "Min Life is less than 1", JOptionPane.ERROR_MESSAGE);
                minLife.setText("1");
            }
            else if(Integer.parseInt(minLife.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Min Life cannot be greater than 4", "Min Life is greather than 4", JOptionPane.ERROR_MESSAGE);
                minLife.setText("1");
            }
            else if(Integer.parseInt(minLife.getText()) > Integer.parseInt(maxLife.getText())){
                JOptionPane.showMessageDialog(null, "Min Life cannot be greather than Max Life", "Min Life is greather than Max Life", JOptionPane.ERROR_MESSAGE);
                minLife.setText("1");
                maxLife.setText("4");
            }
            else{
                minLifeValue = Integer.parseInt(minLife.getText());
            }
            
            if(maxLife.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a value for Max Life", "Max Life cannot be empty", JOptionPane.ERROR_MESSAGE);
                maxLife.setText("4");
            }
            else if(Integer.parseInt(maxLife.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Max Life cannot be greater than 4", "Max Life is greather than 4", JOptionPane.ERROR_MESSAGE);
                maxLife.setText("4");
            }
            else if(Integer.parseInt(maxLife.getText()) < Integer.parseInt(minLife.getText())){
                JOptionPane.showMessageDialog(null, "Max Life cannot be less than Min Life", "Max Life is less than Min Life", JOptionPane.ERROR_MESSAGE);
                maxLife.setText("4");
                minLife.setText("1");
            }
            else{
                maxLifeValue = Integer.parseInt(maxLife.getText());
            }
            
            if(minDeath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a value for Min Death", "Min Death cannot be empty", JOptionPane.ERROR_MESSAGE);
                minDeath.setText("1");
            }
            else if(Integer.parseInt(minDeath.getText()) < 1){
                JOptionPane.showMessageDialog(null, "Min Death cannot be less than 1", "Min Death is less than 1", JOptionPane.ERROR_MESSAGE);
                minDeath.setText("1");
            }
            else if(Integer.parseInt(minDeath.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Min Death cannot be greater than 4", "Min Death is greather than 4", JOptionPane.ERROR_MESSAGE);
                minDeath.setText("1");
            }
            else if(Integer.parseInt(minDeath.getText()) > Integer.parseInt(maxDeath.getText())){
                JOptionPane.showMessageDialog(null, "Min Death cannot be greather than Max Death", "Min Death is greather than Max Death", JOptionPane.ERROR_MESSAGE);
                minDeath.setText("1");
                maxDeath.setText("4");
            }
            else{
                minDeathValue = Integer.parseInt(minDeath.getText());
            }
            
            if(maxDeath.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Please enter a value for Max Death", "Max Death cannot be empty", JOptionPane.ERROR_MESSAGE);
                maxDeath.setText("4");
            }
            else if(Integer.parseInt(maxDeath.getText()) > 4){
                JOptionPane.showMessageDialog(null, "Max Death cannot be greater than 4", "Max Death is greather than 4", JOptionPane.ERROR_MESSAGE);
                maxDeath.setText("4");
            }
            else if(Integer.parseInt(maxDeath.getText()) < Integer.parseInt(minDeath.getText())){
                JOptionPane.showMessageDialog(null, "Max Death cannot be less than Min Death", "Max Death is less than Min Death", JOptionPane.ERROR_MESSAGE);
                maxDeath.setText("4");
                minDeath.setText("1");
            }
            else{
                maxDeathValue = Integer.parseInt(maxDeath.getText());
            }
            
            /* Rows and Columns */
            int rows = 5;
            int columns = 5;
            
            if(rowsField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Rows cannot be empty", "Rows is empty", JOptionPane.ERROR_MESSAGE);
                rowsField.setText("5");
            }
            else if(Integer.parseInt(rowsField.getText()) < 1){
                JOptionPane.showMessageDialog(null, "The number of rows cannot be less than 1", "Rows less than 1", JOptionPane.ERROR_MESSAGE);
                rowsField.setText("5");
            }
            else{
                rows = Integer.parseInt(rowsField.getText());
            }
            
            if(columnField.getText().isEmpty()){
                JOptionPane.showMessageDialog(null, "Columns cannot be empty", "Columns is empty", JOptionPane.ERROR_MESSAGE);
                columnField.setText("1");
            }
            else if(Integer.parseInt(rowsField.getText()) < 1){
                JOptionPane.showMessageDialog(null, "The number of columns cannot be less than 1", "Columns less than 1", JOptionPane.ERROR_MESSAGE);
                 columnField.setText("1");
            }
            else{
                columns = Integer.parseInt(columnField.getText());
            }
            
            if(rows > 10 && columns > 10){
                JOptionPane.showMessageDialog(null, "The number of rows and columns cannot both be greater than 10", "Too many rows and columns", JOptionPane.ERROR_MESSAGE);
                rowsField.setText("10");
                columnField.setText("10");
            }
            
            if(e.getSource() == ok){
                dispose();
                Workshop game = new Workshop(rows, columns);
                game.setParameters(minLifeValue, maxLifeValue, minDeathValue, maxDeathValue);
                Island newGame = new Island(rows, columns, game);
                newGame.setColor(background);
            }
            
            if(e.getSource() == random){
                Random rd = new Random();
                red = rd.nextInt(255);
                green = rd.nextInt(255);
                blue = rd.nextInt(255);
                redField.setText(String.valueOf(red));
                greenField.setText(String.valueOf(green));
                blueField.setText(String.valueOf(blue));
                background = new Color(red, green, blue);
                right.setBackground(background);
            }
        }
        // End actionPerformed() //
    }
    // End Settings Class //
    
    public void setColor(Color color){
        alive = color;
    }
    
    /**
     * Island(int rows, int columns, Workshop shop) creates the Game of Life game board with the supplied arguments.
     * @param rows number of rows the selectable island grid contains
     * @param columns number of columns the selectable island grid contains
     * @param shop contains the ArrayList<boolean> for the island grid which contains the 'true' (alive) and 'false' (dead) values of each cell
     */
    public Island(int rows, int columns, Workshop shop){
        // Creates a window
        super(">>>>>>LIFE GAME BOARD<<<<<<");
        // Assigns Island's shop the Workshop shop passed as an argument
        this.shop = shop;
        // Creates a JPanel to store the 
        JPanel buttons = new JPanel();
        
        // Create window parameters
        setSize(1000,800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
        // Instanciates population as an array of JButtons with int rows and int columns passed as the size
        population = new JButton[rows][columns];
        
        // For loop to iterate through each index of population and populate each index with a JButton object, register an action listener, and set the default background color
        for(int r = 0; r < rows; r++){
            for(int c = 0; c < columns; c++){
                population[r][c] = new JButton(String.valueOf(r) + ", " + String.valueOf(c));
                population[r][c].addActionListener(this);
                population[r][c].setBackground(Color.LIGHT_GRAY);
            }
        }
        // End For Loop
        
        // Calls the buildWindow() method to create the game board
        this.buildWindow();
    }
    // End Island(int rows, int columns, Workshop shop) //
    
    /**
     * enableBoard(boolean flag) iterates through the 2-D array of JButton objects (in which each one represents a cell on the Island) 
     *      and passes the value of flag to either enable or disable that cell.
     * @param flag boolean value of 'true' represents a live cell, and 'false' represents a dead cell
     */
    private void enableBoard(boolean flag){
        for(int row = 0; row < population.length; row++){
            for(int column = 0; column < population[0].length; column++){
                population[row][column].setEnabled(flag);
            }
        }
    }
    // End enableBoard(boolean flag) //
    
    /**
     * resetBoard() iterates through the 2-D array of JButton objects (in which each one represents a cell on the Island) to change
     *      each cell's background back to the default 'false' value (Light Gray)
     */
    private void resetBoard(){
        for(int row = 0; row < population.length; row++){
            for(int column = 0; column < population[0].length; column++){
                population[row][column].setBackground(Color.LIGHT_GRAY);
            }
        }
    }
    // End resetBoard() //
    
    /**
     * buildWindow() creates the Game Board that is used throughout the entire game.
     */
    public void buildWindow(){
        ButtonListener buttonAction = new ButtonListener();
        
        // Center Region
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(population.length, population[0].length));
        
        for(int r = 0; r < population.length; r++){
            for(int c = 0; c < population[0].length; c++){
                population[r][c].setEnabled(false);
                center.add(population[r][c]);
            }
        }
        
        // North Region
        JPanel north = new JPanel();
        northField = new JTextField(50);
        northField.setBackground(Color.PINK);
        north.add(northField);
        
        // South Region
        JPanel south = new JPanel();
        south.setBackground(Color.BLACK);
        south.setLayout(new GridLayout(0, 2, 4, 4));
        
        // South Region Buttons
        mark = new JButton("mark");
        mark.addActionListener(buttonAction);
        next = new JButton("next");
        next.setEnabled(false);
        next.addActionListener(buttonAction);
        play = new JButton("play");
        play.setEnabled(false);
        play.addActionListener(auto);
        stop = new JButton("stop");
        stop.setEnabled(false);
        stop.addActionListener(buttonAction);
        reset = new JButton("reset");
        reset.setEnabled(false);
        reset.addActionListener(buttonAction);
        initial = new JLabel("click for marking initial life >>>>>>>>>>");
        nextGen = new JLabel("see the next generation >>>>>>>>>>>>>>>");
        nextGen.setBackground(Color.RED);
        nextGen.setOpaque(true);
        resetGame = new JLabel("reset the game >>>>>>>>>>>>>>>>>>>>");
        
        JPanel southLabel1 = new JPanel();
        southLabel1.add(initial);
        initial.setBackground(Color.PINK);
        initial.setOpaque(true);
        south.add(southLabel1);
        
        JPanel southMark = new JPanel();
        southMark.add(mark);
        south.add(southMark);
        
        JPanel southLabel2 = new JPanel();
        southLabel2.add(nextGen);
        nextGen.setBackground(Color.CYAN);
        nextGen.setOpaque(true);
        south.add(southLabel2);
        
        JPanel southNext = new JPanel();
        southNext.add(next);
        southNext.add(play);
        southNext.add(stop);
        south.add(southNext);
        
        JPanel southLabel3 = new JPanel();
        southLabel3.add(resetGame);
        resetGame.setBackground(Color.GREEN);
        resetGame.setOpaque(true);
        south.add(southLabel3);
        
        JPanel southReset = new JPanel();
        southReset.add(reset);
        south.add(southReset);
        
        add(center, BorderLayout.CENTER);
        add(north, BorderLayout.NORTH);
        add(south, BorderLayout.SOUTH);
    }
    // End buildWindow() //
    
    /**
     * displayGeneration(boolean [][] mirror) iterates through the 2-D array of JButton's to determine the boolean value stored in each cell.
     *      If true the background of that cell/JButton is set to yellow, if false the background is set to Light Gray.
     * @param mirror 
     */
    private void displayGeneration(boolean[][] mirror){
        for(int row = 0; row < mirror.length; row++){
            for(int col = 0; col < mirror[0].length; col++){
                if(mirror[row][col]){
                    population[row][col].setBackground(alive);
                }
                else{
                    population[row][col].setBackground(Color.LIGHT_GRAY);
                }
            }
        }
    }
    // End displayGeneration(boolran[][] mirror) //
    
    /**
     * actionPerformed(ActionEvent event) is the ActionListener for the JButton's that make up the grid of the Island.
     *      If a button is clicked, that button's background is changed to the color chosen by user. If a button is clicked again, that
     *      button's background is changed to light gray. Works by checking the current background of the button and
     *      swapping it to the other available color.
     * @param event ActionEvent object generated by the JButton in the grid when clicked
     */
    public void actionPerformed(ActionEvent event){
        JButton clicked = new JButton();
        Color live = alive;
        clicked = (JButton)event.getSource();

        
        
        if(clicked.getBackground() == live){
            clicked.setBackground(Color.LIGHT_GRAY);
        }
        else{
            clicked.setBackground(live);
        }
    }
    // End actionPerformed(ActionEvent event) //
    /* End Methods */
    
    /**
     * ButtonListener inner class.
     *      Contains a single method that handles the action performed when the game buttons are clicked. (Mark, next, and reset)
     *      Implements ActionListener
     */
    private class ButtonListener implements ActionListener{
        /* Methods */
        /**
         * actionPerformed(ActionEvent event) is the Action Listener for the three buttons which control the flow of the game; mark, next, and reset.
         * @param event ActionEvent created when a button is clicked (registered to mark, next, and reset).
         */
        public void actionPerformed(ActionEvent event){
            // If/else statement to verify the source of the ActionEvent object
            /* If mark is pushed */
            if(event.getSource() == mark){
                enableBoard(true);      // enableBoard is called with true, allowing users to click the grid of the Island
                next.setEnabled(true);  // next is enabled
                mark.setEnabled(false); // mark is disabled
                play.setEnabled(true);  // play is enabled
                shop.resetHistory();    // resetHistory() is called with shop, which removes the history from the ArrayList of boolean values from previous generations
            }
            
            /* If next is pushed */
            else if(event.getSource() == next){
                enableBoard(false);     // enableBoard is called with false, preventing users from clicking the grid of the Island once the game has begun
                mark.setEnabled(false); // mark is disabled
                shop.resetMirror();     // shop called resetMirror(), 
                reset.setEnabled(true); // reset is enabled
                
                // For loop to iterate through the 2-D array of JButtons, if their background color isn't light gray (dead), assigns true to shop.mirror[][]
                for(int row = 0; row < population.length; row++){
                    for(int col = 0; col < population[0].length; col++){
                        if(population[row][col].getBackground() != Color.LIGHT_GRAY){
                            shop.mirror[row][col] = true;
                        }
                    }
                }
                // End for loop
                
                shop.nextGeneration();              // shop calls nextGeneration() to advance to the next game screen
                northField.setText(shop.message);   // the message in northField is set to shop.message
                displayGeneration(shop.mirror);     // displayGeneration is called with shop.mirror (updating the game board)
                
                
                // If/else statement to check if finished is true for shop, if so next is disabled, preventing advancement of the game
                if(shop.finished){
                    next.setEnabled(false);
                    stop.setEnabled(false);
                    play.setEnabled(false);
                }
            }
            
            /* if reset is pushed */
            else if(event.getSource() == reset){
                mark.setEnabled(true);      // mark is enabled
                reset.setEnabled(false);    // reset is disabled
                next.setEnabled(false);     // next is disabled
                play.setEnabled(false);     // play is disabled
                stop.setEnabled(false);     // stop is disabled
                delay.stop();               // timer for auto play is stopped
                autoBoolean = false;        // boolean that controls auto is set to false
                shop.resetHistory();        // shop calls resetHistory()
                northField.setText("");     // the message in northField is erased
                resetBoard();               // The game board is reset to start the game over
            }
            
            /* if stop is pushed */
            else if(event.getSource() == stop){
                autoBoolean = false;
                play.setEnabled(true);
                stop.setEnabled(false);
                delay.stop();
            }
        }
        // End actionPerformed //
        /* End Methods */
    }
    /* End inner class */
    
    public class Play implements ActionListener{
        public void actionPerformed(ActionEvent e){
            play.setEnabled(false);
            stop.setEnabled(true);
            autoBoolean = true;
            
            if(autoBoolean){
                delay.start();
                next.doClick();
            }
        }
    }
}
/* End Island */

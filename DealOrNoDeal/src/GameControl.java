import java.util.Scanner;
import javax.swing.JOptionPane;
/*
* Chelsey Martin
* CS160-03 Fall 2017
* Project 4: Deal or No Deal
*
* Description. GameControl contains methods that handle the function of the game. This class makes things happen in the game by calling on other methods in other classes.
*
*/
public class GameControl {
    /* Data Fields */
    private BriefcaseCollection collection = new BriefcaseCollection(Banker.MONEY_VALUES);     // Holds the BriefcaseCollection array used throughout the entire game.
    private Scanner kb = new Scanner(System.in);                                                                        // Scanner object to collect user input throughtout the game.
    // End Data Fields //
    
    /* Methods */
    /**
     * playOneRound() displays the current round to the console and solicits the user for a case.
     * Calls chooseACase() with collection as a parameter to get the next selected briefcase.
     * @param round 
     */
    private void playOneRound(int round){
        System.out.println("\nRound number: " + round + "\nChoose a case please");
        System.out.printf("Briefcase contains $%,.2f\n",chooseACase(collection).getValue());
    }
    // End playOneRound() //
    
    /**
     * chooseACase() solicits the user for the case of their choice after displaying all remaining
     *      cases. This method then checks the user's choice to make sure it is a valid selection.
     *      If the user's selection is valid the case is returned.
     * @param //collection
     * @return 
     */
    private Briefcase chooseACase(BriefcaseCollection array){
        System.out.print("Remaining Cases: ");
        array.showRemainingCases();
        System.out.print("\nWhich case? ");
        int choice = kb.nextInt();
        while(array.invalidChoice(choice)){
            System.out.print("That choice is invalid. Please pick another case!\nWhich case? ");
            choice = kb.nextInt();
        }
        
        //System.out.println("Briefcase contains $" + collection.chooseThisCase(choice).getValue());
        return array.chooseThisCase(choice);
    }
    // End chooseACase() //
    
    /**
     * makeDeal() offers the user the banker's offer and allows them to accept or reject the offer.
     * @param offer
     * @return 
     */
    private boolean makeDeal(double offer){
        int choice = JOptionPane.showConfirmDialog(null, String.format("The offer is now $%,.2f\nClick 'Yes' to make a deal", offer),"Time for a decision: Deal or No Deal!", JOptionPane.YES_NO_OPTION);
        if(choice == JOptionPane.YES_OPTION){
            return true;
        }
        return false;
    }
    // End makeDeal() //
    
    /**
     * play() runs the logic of the game, running through each round and printing the messages to allow the user to accept or deny banker's deals.
     */
    public void play(){
        /* Local Variables */
        Briefcase initialCase;
        double offer = 0;
        int round = 1;
        boolean deal = false;
        /* End Local Variables */
        
        System.out.println("Make your initial choice!");
        initialCase = chooseACase(collection);
        
        while(deal == false && round < 10){
            playOneRound(round);
            offer = Banker.makeOffer(round, collection.computeAverage());
            deal = makeDeal(offer);
            round++;
        }
        
        if(deal){
            System.out.printf("\nYou made a deal for $%,.2f\nYour initial case contains $%,.2f\n", offer, initialCase.getValue());
            JOptionPane.showMessageDialog(null,String.format("You made a deal and won the offer $%,.2f.\nYour initial case contains $%,.2f.",offer, initialCase.getValue()), "The game ends, we have a winner!", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        else{
            Briefcase lastCase =  collection.getLastCase();
            System.out.print("\nRemaining Cases: ");
            collection.showRemainingCases();
            System.out.println("");
            int choice = JOptionPane.showConfirmDialog(null,"Would you like to trade your case for the one left?","The last offer",JOptionPane.YES_NO_OPTION);
            
            if(choice == JOptionPane.YES_OPTION){
                JOptionPane.showMessageDialog(null,String.format("You traded your initial case and won the value in the last briefcase $%,.2f\nYour initial case contains $%,.2f.", lastCase.getValue(), initialCase.getValue()),"The game ends, we have a winner!",JOptionPane.INFORMATION_MESSAGE);
                System.out.printf("Your winning from the last case is $%,.2f.\nYour case contained $%,.2f\n", lastCase.getValue(), initialCase.getValue());
                System.exit(0);
            }
            else{
                JOptionPane.showMessageDialog(null, String.format("You rejected the last offer and won the value in your initial case $%,.2f\nThe last case contains $%,.2f",initialCase.getValue(),lastCase.getValue()), "The game ends, we have a winner!", JOptionPane.INFORMATION_MESSAGE);
                System.out.printf("Your winning from your initial case is $%,.2f.\nThe last case contained $%,.2f\n", initialCase.getValue(), lastCase.getValue());
                System.exit(0);
            }
        }
    }
    // End play() //
    // End Methods //
}

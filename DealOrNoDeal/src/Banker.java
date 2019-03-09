/*
* Chelsey Martin
* CS160-03 Fall 2017
* Project 4: Deal or No Deal
*
* Description. The Banker class handles the offers that the banker makes to the user based on the cases that have already been picked and the average and some other pretty math on what is left in the cases.
*
*/
public class Banker {
    /* Data Fields */
    final public static double[] MONEY_VALUES = {1, 10, 100, 1000, 4000, 10000, 
        40000, 100000, 300000, 700000, 1000000};                                // Stores all of the possible dollar values that can be used in each briefcase.
    // End Data Fields //
    
    /* Methods */
    /**
     * makeOffer() uses the formula described in the project description to calculate the offer the banker
     *      will make to the player at the end of each round.
     * @param round
     * @param avg
     * @return 
     */
    public static double makeOffer(int round, double avg){
        int n = round;
        double temp = 0.01 * (75+5*n-3*Math.max(0,n-5))*avg*(1+0.15*(avg-55000)/Math.abs(avg - 55000));
        double offer = Math.max(100, 1000*Math.round(temp/1000));
        return offer;
    }
    // End makeOffer() //
    // End Methods //
}

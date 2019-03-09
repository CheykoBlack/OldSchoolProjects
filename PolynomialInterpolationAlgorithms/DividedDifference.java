/*
 * Chelsey Martin
 * CS 384
 * 2/27/2019
 *
 * Class Description:
 *      Uses the divided differences algorithm to find the coefficients of an interpolation polynomial given the initial known nodes.
 */
public class DividedDifference {
    /* Data Fields */
    private double[] x;         // Stores the x values of the known nodes
    private double[] y;         // Stores the y values of the known nodes
    private double[][] values;  // Stores the coefficients calculated
    /* End Data Fields*/

    /* Methods */

    /**
     * public DividedDifference(double[][] nodes)
     *      Handles the creation of each data field, and calls the methods to calculate the values and print them to the console.
     * @param nodes, the known (x, y) values in the form [x][0] and [y][1].
     *               The nodes must be in ascending order with [0][0] containing the smallest x value and [n-1][n-1] containing the largest x value.
     */
    public DividedDifference(double[][] nodes){
        x = new double[nodes.length];
        y = new double[nodes.length];
        int n = 0;
        for(int i = 1; i <= nodes.length; i++){
            n+=i;
        }
        values = new double[n-x.length][n-x.length];

        System.out.println("\tx\t\t\tf(x)");
        for(int index = 0; index < nodes.length; index++){
            x[index] = nodes[index][0];
            y[index] = nodes[index][1];
            values[index][0] = y[index];
            System.out.printf("%8.5f\t %8.5f\n", x[index], y[index]);
        }

        calculate();
        print();
    }

    /**
     * private void calculate()
     *      Calculates the coefficients and stores them in the values[][].
     */
    private void calculate(){
        for(int i = 1; i < x.length; i++){
            for(int j = 1; j <= i; j++){
                values[i][j] = (values[i][j - 1] - values[i-1][j-1])/(x[i] - x[i - j]);
            }
        }
    }

    /**
     * private void print()
     *      prints the calculated coefficients in an attractive format to the console.
     */
    private void print(){
        System.out.println("\nNewton Triangle");
        for(int row = 0; row < values.length; row++){
            for(int col = 0; col < values.length; col++){
                if(values[row][col] != 0.0)
                System.out.printf("%8.5f\t\t", values[row][col]);
            }
            System.out.println("");
        }
    }
    /* End Methods */
}

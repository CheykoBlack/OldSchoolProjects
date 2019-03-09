import java.util.*;

/*
 * Chelsey Martin
 * CS 384
 * 2/27/2019
 *
 * Class description:
 *      The LagrangePolynomial class can construct each base Lagrange polynomial for a given number of nodes and a given degree.
 *      Each base polynomial is displayed on the console in the following format:
 *          [ (x - x0) (x - x1) ... (x - xn) ] ÷ [ (xi - x0) (xi - x1) ... (xi - xn) ]
 *          where xi is the x-value left out of the dividend, and n is the degree.
 *      The program then calculates each base Lagrange function, step by step, showing the steps on the console and the results
 *          of each step as it works.
 *      Finally, the program calculates the result of the polynomial interpolation for the given value. Once again displaying
 *          each step and result of each step on the console.
 */

public class LagrangePolynomial {
    /* Data Fields */
    private int degree;                     // The degree of the desired interpolation polynomial
    private double[][] nodes;               // The initial given known (x, y) points
    private String[] lagrange;              // The representation of each base lagrange equation in the format specified above
    private double solve;                   // The value for which P(x) is being solved for
    private ArrayList<Double> divisors;     // The value of the divisors of each base lagrange equation
    private ArrayList<Double> dividends;    // The value of the dividends of each base lagrange equation
    private double dividend = 1;            // Stores the calculating dividend value
    private double divisor = 1;             // Stores the calculating divisor value
    private double result;                  // Stores the result of the interpolation problem (Stores P(solve))
    private double[] values;                // Stores the result of each individual lagrange base function for the linear combination
    /* End Data Fields */

    /* Methods */

    /**
     * public LagrangePolynomial(int degree, double[][] nodes, double solve)
     *      Handles the bulk of the console output, outside of the final answer.
     * Preconditions:
     *      degree >= 0
     *      nodes must be in the format nodes[n][0] = x-values, nodes[n][1] = y-values
     * @param degree, the degree of the lagrange interpolation polynomial
     * @param nodes, the known points (x, y)
     * @param solve, the x for which we want to know P(x)
     */
    public LagrangePolynomial(int degree, double[][] nodes, double solve){
        this.degree = degree;
        this.nodes = new double[degree+1][2];
        lagrange = new String[degree+1];
        this.solve = solve;
        divisors = new ArrayList<>(degree);
        dividends = new ArrayList<>(degree);
        values = new double[degree+1];

        if(nodes.length <= degree){
            System.out.println("The number of nodes given must be greater than the degree.");
            return;
        }

        System.out.println("The nodes: ");
        for(int index = 0; index < (this.nodes).length; index++){
            this.nodes[index][0] = nodes[index][0];
            this.nodes[index][1] = nodes[index][1];
            System.out.println("x[" + index + "] = " + this.nodes[index][0] + " | y[" + index + "] = " + this.nodes[index][1]);
        }

        System.out.println("\nThe Lagrange Base Equations and Process for Solving Each One: ");
        for(int index = 0; index < lagrange.length; index++){
            lagrange[index] = populateEquation(index);
            System.out.println("L[" + index + "](x) = " + lagrange[index]);

            System.out.println("Step 1: Solving each (equation) in the dividend");
            for(int k = 0; k < dividends.size(); k++){
                System.out.println("\tDividend[" + k + "] = " + dividends.get(k));
            }

            System.out.println("Step 2: Solving each (equation) in the divisor");
            for(int k = 0; k < divisors.size(); k++){
                System.out.println("\tDivisor[" + k + "] = " + divisors.get(k));
            }

            System.out.println("Step 3: Solving the dividend");
            for(int k = 0; k < dividends.size(); k++){
                dividend *= dividends.get(k);
            }
            System.out.println("\tDividend of L[" + index + "] = " + dividend);

            System.out.println("Step 4: Solving the divisor");
            for(int k = 0; k < divisors.size(); k++){
                divisor *= divisors.get(k);
            }
            System.out.println("\tDivisor of L[" + index + "] = " + divisor);

            System.out.println("Step 5: Dividing the Dividend by the Divisor");
            result = dividend/divisor;
            System.out.println("\t(" + dividend + " \u00F7 " + divisor + ") = " + result);

            System.out.println("Step 6: Multiple the previous result by the corresponding y-value");
            values[index] = nodes[index][1] * result;
            System.out.println("\t(" + nodes[index][1] + " x " + result + ") = " + values[index]);

            System.out.println();
            dividends.clear();
            divisors.clear();
            dividend = 1;
            divisor = 1;
        }
        calculate();
    }

    /**
     * private String populateEquation(int start)
     *      Creates and returns the string representation of each base lagrange function, and calculates each dividend
     *          and divisor while it works.
     * @param start, the x-value to be excluded from the dividend and included in the divisor.
     * @return String representation of the lagrange base it is working on.
     */
    private String populateEquation(int start){
        String dividend = "";
        String divisor = "";

        for(int index = 0; index <= degree; index++){
            if(index != start){
                dividend += "(x - " + nodes[index][0] + ") ";
                divisor += "(" + nodes[start][0] + " - " + nodes[index][0] + ") ";
                dividends.add(solve - nodes[index][0]);
                divisors.add(nodes[start][0] - nodes[index][0]);
            }
        }

        return "[ " + dividend + "]" + " \u00F7 " + "[ " + divisor + "]";
    }

    /**
     * private void calculate()
     *      Calculates the final result of the lagrange polynomial and prints each step to the console with result.
     */
    private void calculate(){
        double answer = 0;
        System.out.println("Final Answer: Linear Combination of the Above Work");
        System.out.print("\tP(" + solve + ") = ");

        for(int index = 0; index < values.length; index++){
            if(index != values.length - 1){
                System.out.print(values[index] + " + ");
            }
            else{
                System.out.print(values[index] + " = ");
            }
            answer += values[index];
        }
        System.out.println(answer);
    }
    /* End Methods */
}

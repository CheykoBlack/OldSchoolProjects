/*
    Chelsey Martin
    CS 384
    HW 3
    2/6/2019

    The FalsePosition class contains the False position method for finding the root of a given function.
 */
public class FalsePosition {
    /**
     * public double falsePosition(double p0, double p1, int n, double tol)
     *      Finds the root of the function stored in the function(double x) method using the false position method.
     * Precondition: f(p0) and f(p1) must be alternating signs.
     * @param p0, the initial p0 value
     * @param p1, the initial p1 value
     * @param n, the number of iterations to run the false position method before returning the root
     * @param tol, the required tolerance to be used if the root meets the required tolerance before the desired number of iterations occur
     * @return the x value where f(x) = 0
     */
    public double falsePosition(double p0, double p1, int n, double tol){
        int i = 2;
        double q0 = function(p0);
        double q1 = function(p1);
        double p;
        double q;

        System.out.printf("Iteration \t\t\t p0 \t\t\t\t\t\t p1 \t\t\t\t\t\t p  \n");

        do{
            p = p1 - q1 * (p1 - p0) / (q1 - q0);

            System.out.printf("%5d \t\t %6.17f \t\t %6.17f \t\t %6.17f\n",i,p0,p1,p);

            if(Math.abs(p - p1) < tol || i == n){
                return p;
            }
            i++;
            q = function(p);

            if(q * q1 < 0){
                p0 = p1;
                q0 = q1;
            }

            p1 = p;
            q1 = q;
        }while(i <= n);

        System.out.println("Failed to find a root for the function using the false position method.");
        return p;
    }

    /**
     * private double function(double x)
     *      The function for which the false position method will be used to find the root of.
     * @param x, the x value to be used with the function
     * @return f(x)
     */
    private double function(double x){
        return Math.pow(x, 2) - 6;
    }
}

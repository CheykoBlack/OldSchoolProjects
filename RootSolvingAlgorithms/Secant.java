/*
    Chelsey Martin
    CS 384
    HW 3
    2/6/2019

    The Secant class contains the Secant method for finding the root of a function.
 */
public class Secant {
    /**
     * public double secant(double p0, double p1, double tol)
     *      Uses the secant method on the function defined in function(double x) to find the root of the function.
     * @param p0, the initial p0 starting point
     * @param p1, the initial p1 starting point
     * @param tol, the required tolerance for which the root must fall within
     * @return the root of the function or x value where f(x) = 0.
     */
    public double secant(double p0, double p1, double tol){
        double p;
        double q1 = function(p1);
        double q0 = function(p0);
        int i = 2;

        System.out.printf("Iteration \t\t\t p0 \t\t\t\t\t\t p1 \t\t\t\t\t\t p  \n");

        do {
            p = p1 - q1 * (p1 - p0) / (q1 - q0);

            System.out.printf("%5d \t\t %6.17f \t\t %6.17f \t\t %6.17f\n",i,p0,p1,p);

            if(Math.abs(p - p1) < tol){
                return p;
            }
            i++;

            p0 = p1;
            q0 = q1;
            p1 = p;
            q1 = function(p);
        } while(Math.abs(p - p1) < tol);

        System.out.println("Failed to find the root of the function with the secant method.");
        return Double.POSITIVE_INFINITY;
    }

    /**
     * public double secant(double p0, double p1, int n, double tol)
     *      Same as the secant(double p1, double p1, double tol) method with an additional parameter to control the number of
     *          iterations the secant method should run before returning the root.
     * @param p0, initial point p0
     * @param p1, initial point p1
     * @param n, the number of iterations the secant method will make before returning the root
     * @param tol, the required tolerance of the secant method assuming the tolerance is met before the required number of iterations occur
     * @return, the root of the function or the x where f(x) = 0
     */
    public double secant(double p0, double p1, int n, double tol){
        double p;
        double q1 = function(p1);
        double q0 = function(p0);
        int i = 2;

        System.out.printf("Iteration \t\t\t p0 \t\t\t\t\t\t p1 \t\t\t\t\t\t p  \n");

        do {
            p = p1 - q1 * (p1 - p0) / (q1 - q0);

            System.out.printf("%5d \t\t %6.17f \t\t %6.17f \t\t %6.17f\n",i,p0,p1,p);

            if(Math.abs(p - p1) < tol || i >= n){
                return p;
            }
            i++;

            p0 = p1;
            q0 = q1;
            p1 = p;
            q1 = function(p);
        } while(Math.abs(p - p1) > tol);

        System.out.println("Failed to find the root of the function with the secant method.");
        return Double.POSITIVE_INFINITY;
    }

    /**
     * private double function(double x)
     *      The function for which the secant method is being used to find the root of.
     * @param x, the x value to be supplied to the function
     * @return f(x)
     */
    private double function(double x){
        return Math.pow(x, 2) - 6;
    }
}

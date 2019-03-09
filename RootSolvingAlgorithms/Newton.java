/*
    Chelsey Martin
    CS 384
    HW 3
    2/6/2019

    The Newton class contains the Newton method for finding the root of a function.
 */
public class Newton {
    /**
     * public double newton(double p, double accuracy)
     *      Performs the newton method on the function defined within a private method, function, and its
     *          derivative defined within a private method, functionDerive.
     * @param p, the starting point
     * @param accuracy, the required accuracy of the value x for the root of the function
     * @return the x-value or root of the function where f(x) = 0
     */
    public double newton(double p, double accuracy){
        double pbefore = p;
        int i = 1;

        System.out.printf("Iteration \t\t\t p0 \t\t\t\t\t\t p \n");

        do{
            pbefore = p;
            p = pbefore - function(pbefore) / functionDerive(pbefore);

            System.out.printf("%5d \t\t %6.17f \t\t %6.17f\n",i,pbefore,p);

            if(Math.abs(p - pbefore) < accuracy){
                System.out.println("Iterations: " + i);
                return p;
            }
            i++;
        }while(Math.abs(pbefore - p) > accuracy);

        System.out.println("Failed to use Newton's Method to find the root");
        return Double.POSITIVE_INFINITY;
    }

    /**
     * public double newtonSteps(double p, double steps)
     *      Identical to the newton() method but with accuracy replaced by steps as the desired stopping point
     * @param p is the starting point
     * @param steps the number of iterations to be done by the newton method
     * @return the x where f(x) = 0
     */
    public double newtonSteps(double p, double steps) {
        double pbefore = p;
        int i;

        System.out.printf("Iteration \t\t p0 \t\t\t p \n");

        for (i = 1; i <= steps; i++) {
            pbefore = p;
            p = pbefore - function(pbefore) / functionDerive(pbefore);

            System.out.printf("%5d \t\t %6.5f \t\t %6.5f\n", i, pbefore, p);
        }
        return p;
    }

    /**
     * private double function(double x)
     *      The function for which the root is being calculated using the newton method.
     * @param x the x value, returns y when x = x
     * @return the y value or f(x)
     */
    private double function(double x){
        return 2 * x - 4 + 2 / Math.pow(x, 2) - 2 / Math.pow(x, 3);
    }

    /**
     * private double functionDerive(double x)
     *      The derivative of the function for which the root is being calculated using the newton method.
     * @param x the x value, returns y when x = x
     * @return the y value or f(x)
     */
    private double functionDerive(double x) {
        return 2 - 4 / Math.pow(x,3) + 6 / Math.pow(x, 4);
    }
}

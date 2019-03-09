public class Bisection2 {
    /* Methods */

    /**
     * Uses the Bisection method to find a root of the function to
     *      an accuracy passed as a parameter.
     * @param a, the starting point a
     * @param b, the starting point b
     * @param accuracy, the accuracy desired for the root
     * @return p, the root with the desired accuracy
     */
    public double bisection(double a, double b, double accuracy){
        int i = 1;
        double fa = function(a);
        double pbefore, p = a + (b-a) /2;
        double fp = function(p);

        double testA = function(a);
        double testB = function(b);

        if((testA > 0 && testB > 0) || (testA < 0 && testB < 0)){
            System.out.println("The starting points a and b fail the precondition for the Bisection method.\na and b must be opposite signs.\na is "+testA+"\nb is " + testB);
            return Double.POSITIVE_INFINITY;
        }
        else{
            System.out.println("The starting points a and b pass the precondition for the Bisection method. \na and b are opposite signs.\na is " + testA + "\nb is " + testB+"\n");
        }

        System.out.printf("Iteration \t\t\t\t a \t\t\t\t\t\t b \t\t\t\t\t\t p\n");

        do{
            pbefore = p;
            p = a+(b-a)/2;
            fp = function(p);

            System.out.printf("%5d \t\t %6.17f \t\t %6.17f \t\t %6.17f\n",i,a,b,p);

            if(fp == 0 || (Math.abs(pbefore - p) < accuracy) && pbefore-p != 0){
                System.out.println("Iterations: " + i);
                return p;
            }
            i++;

            if(fa * fp > 0){
                a = p;
                fa = fp;
            }
            else{
                b = p;
            }
        }while(Math.abs(pbefore - p) > accuracy || Math.abs(pbefore - p) == 0);
        System.out.println("Failed to find an approximation to the root using bisection method.");
        return i;
    }

    /**
     * The function for which the Bisection method is used to find the root for.
     * @param x, the missing variable in the function being solved for f(x)
     * @return the solution when x is plugged into the function with the parameter
     */
    private double function(double x){
        return Math.pow(x,2) - 3;
    }
    /* End Methods */
}

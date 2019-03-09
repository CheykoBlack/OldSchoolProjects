/*
    Chelsey Martin
    CS 384
    Spring 2019
    Fixed Point Iteration
 */

public class FixedPoint {
    public double fixedPoint(double initial, double tol){
        int count = 0;
        double p;

        System.out.println("Iteration \t\t\t p0 \t\t\t\t p \t\t\t\t\t |p-p0|");

        do{
            p = function(initial);
            //if(count%10 == 0){
                System.out.printf("%4d \t\t\t %.10f \t\t %.10f \t\t %.15f\n",count,initial,p,Math.abs(p-initial));
            //}
            if(Math.abs(p-initial) < tol){
                return p;
            }
            count++;
            initial = p;
        }while(count < 1000);

        System.out.println("Unable to find a fixed point with the desired tolerance.\n");
        return Double.POSITIVE_INFINITY;
    }

    private double function(double x){
        return (2 * Math.pow(x,3) + 25) / (3 * Math.pow(x,2));
    }
}

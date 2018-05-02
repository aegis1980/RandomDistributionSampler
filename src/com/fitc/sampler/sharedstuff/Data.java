package com.fitc.sampler.sharedstuff;

public abstract class Data {
	
	//*******************************************************************************************
	// Static statistics methods
	// I am not sure where these came from. I have had them as 'library' class for a while.

	public static double sum (double[] a){

        if (a.length > 0) {
            double sum = 0;
 
            for (int i=0;i<a.length;i++) {
                sum += a[i];
            }
            return sum;
        }
        return 0;
    }
	
    public static double variance (double[] a, double mean){
        double sum = 0;
 
        for (int i=0;i<a.length;i++)
            sum += Math.pow((a[i] - mean), 2);
        return sum / ( a.length - 1 ); 
    }
	
	
    public static double sd (double[] a, double mean){
        return Math.sqrt(variance(a,mean)); // sample
    }
    
    
    public static double mean (double[] a){
        return sum(a) / (a.length * 1.0);
    }
    
    /**
     * Return max and min values in a double array, as
     * a double array.
     * 
     * First value is the max, second the min value
     * 
     * @param a
     * @return
     */
    public static double[] maxmin (double[] a){
    	double[] maxmin = {0,0};
    	if (a.length > 0) {
            for (int i=0;i<a.length;i++) {
				if (a[i]> maxmin[0]) maxmin[0] = a[i];
				if (a[i]< maxmin[1]) maxmin[1] = a[i];
            }
        }
    	return maxmin;
    }
}

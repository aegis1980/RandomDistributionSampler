package com.fitc.sampler.sharedstuff;

/**
 * <p>Check that my log(a + b + c) from log a, log b, log c works - my attempt to avoid underflow</p> 
 * 
 * @author jrob271
 *
 */
public class Log2SumTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		double[] probs = {-10,-5,-8};
		
		double log2sum = Utils.addLog2(probs);
		
		System.out.printf("X = log2 ( xn): %f",log2sum);

		System.out.printf("\n2^X : %.10f", Math.pow(2,log2sum));
	
		double tot = 0;
		for (double p : probs){
			tot = tot + Math.pow(2, p);
		}
		
		
		System.out.printf("\nSimple sum : %.10f", tot);
	}

}

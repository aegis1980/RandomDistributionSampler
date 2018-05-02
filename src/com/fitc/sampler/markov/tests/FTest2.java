package com.fitc.sampler.markov.tests;

import com.fitc.sampler.markov.ForwardAlgorithm;
import com.fitc.sampler.sharedstuff.Utils;

public class FTest2 {

	public static final char[] STATES = {'H','E','T'};
	
	public static final char[] SYMBOLS = {'B','I','N'};

	public static final double[][] TRANSITIONS ={ 
		{15./16.	,3./160.	,7./160},
		{1./15.		,5./6.		,1./10.},
		{1./8.		,1./8.		,3./4.}};
	
	public static final double[][] EMISSIONS ={ 
		{0.3,	0.5,	0.2},
		{0.55,	0.15,	0.3},
		{0.1,	0.4,	0.5}};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] symbolSeq  ={'N','I'};
		
		ForwardAlgorithm fa = new ForwardAlgorithm(STATES, TRANSITIONS, SYMBOLS, EMISSIONS);
		fa.recordHistory(true);
		fa.setSymbolSeq(symbolSeq);
		fa.solve();
		
		System.out.println(Utils.printMultiArray(fa.getF()));
		System.out.printf("\nCalculated log2 P(x): %.3f\n",fa.getLP());

		System.out.println(Utils.printMultiArray(fa.getf()));
		System.out.printf("\nCalculated log2 P(x): %.7f\n",fa.getP());
	}
	
	

}

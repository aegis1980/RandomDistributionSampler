package com.fitc.sampler.markov.tests;

import com.fitc.sampler.markov.ForwardAlgorithm;
import com.fitc.sampler.sharedstuff.Utils;

public class FTest {

	public static final char[] STATES = {'H','L',};
	
	public static final double[][] TRANSITIONS ={ 
		{0.5	,0.5},
		{0.4	,0.6}};

	public static final char[] SYMBOLS = {'A','C','G','T'};	
	
	public static final double[][] EMISSIONS ={ 
		{0.2,	0.3,	0.3,	0.2},
		{0.3,	0.2,	0.2,	0.3}};
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		char[] symbolSeq ={'G','G','C','A'};		
		
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

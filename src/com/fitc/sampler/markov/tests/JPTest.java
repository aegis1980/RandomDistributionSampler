package com.fitc.sampler.markov.tests;

import com.fitc.sampler.markov.JointProbability;
import com.fitc.sampler.sharedstuff.Utils;

public class JPTest {

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
		
		char[] stateSeq = {'H','H'};
		char[] symbolSeq ={'N','I'};
		
		JointProbability jp = new JointProbability(STATES, TRANSITIONS, SYMBOLS, EMISSIONS);
		jp.recordHistory(true);
		jp.setSequences(stateSeq,symbolSeq);
		jp.solve();
		
		System.out.println(Utils.sequenceToString("State ", stateSeq));
		System.out.println(Utils.sequenceToString("Symbol", symbolSeq));
		
		System.out.printf("\nCalculated log2 P(x,pi): %.3f\n",jp.getLP());

		System.out.printf("\nCalculated P(x,pi): %.7f\n",jp.getP());
		

	}

}

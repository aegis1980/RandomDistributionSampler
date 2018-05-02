package com.fitc.sampler.markov.tests;

import com.fitc.sampler.markov.MarkovChainSampler;
import com.fitc.sampler.sharedstuff.Utils;

public class MarkovTest {

	private static final char START_STATE = 'H';

	private static final int SAMPLE = 1000000000;

	private static final double[][] TRANSITIONS ={ 
		{15./16.	,3./160.	,7./160},
		{1./15.	,5./6.		,1./10.},
		{1./8.	,1./8.		,3./4.}};
	
	private static final char[] STATES = {'H','E','T'};
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		MarkovChainSampler mps = new MarkovChainSampler(TRANSITIONS, STATES);
		mps.setStartState(START_STATE);
		
		System.out.print(START_STATE);
		
		final int[] counts = new int[STATES.length];
		final int[] seriesCounts = new int[STATES.length];
		
		char oldState = 'H';
		counts[mps.getIndex(START_STATE)]=1;
		

		for (int i = 0;i<SAMPLE;i++){
			final char sample = mps.nextStateSingle();
			System.out.print(sample);
			
			if (oldState!=sample) 
				seriesCounts[mps.getIndex(oldState)]++;
			
			oldState = sample;
			for (int j=0; j<STATES.length; j++){
				if (sample==STATES[j]){
					counts[j]++;
					break;
				}
				
			}
			
		}
		
		for (int j=0; j<STATES.length; j++){
			System.out.printf("\n%c: %.2f",STATES[j], ((double)counts[j]/(double)SAMPLE));
		}
		
		// Check steady state
		double[] steadyState = MarkovChainSampler.steadyState(TRANSITIONS);
		System.out.println(Utils.printArray(steadyState));
		
		//Check geometric dist
		for (int j=0; j<STATES.length; j++){
			double avgLen = ((double) counts[j]) / ((double)  seriesCounts[j]) ;
			System.out.printf("\n%c: %.2f  --  count: %d --  seq: %d",STATES[j], avgLen, counts[j],seriesCounts[j]);
		}
		
	}

}

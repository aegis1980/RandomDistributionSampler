package com.fitc.sampler.markov.tests;

import com.fitc.sampler.markov.HiddenMarkovModelSampler;

public class HMMTest {

	private static final char START_STATE = 'A';

	private static final int SAMPLE = 200;

//	private static final double[][] TRANSITIONS = {{0.5,0.5},{0.5,0.5}};
//	private static final char[] STATES = {'A','B'};
	
	private static final double[][] TRANSITIONS = {{1}};
	private static final char[] STATES = {'A'};
	
	private static final double[][] EMISSIONS = {{0,0.5,0.5}};
	private static final char[] SYMBOLS = {'X','Y','Z'};
	
	
	/**
	 * @param args
	 */
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		HiddenMarkovModelSampler hmms = new HiddenMarkovModelSampler(TRANSITIONS, STATES, EMISSIONS, SYMBOLS);
		
		hmms.setStartState(START_STATE);
		String states = ""+START_STATE;
		String symbols ="" + hmms.sampleSymbol(START_STATE);
		
		final int[] counts = new int[STATES.length];
		
		for (int i = 0;i<SAMPLE;i++){
			final char sample[] = hmms.nextStateSymbolPair();
			states = states + sample[0];
			symbols = symbols + sample[1];

/*			for (int j=0; j<STATES.length; j++){
				if (sample==STATES[j]){
					counts[j]++;
					break;
				}
				
			}*/
			
		}
		
		System.out.println(states);
		System.out.println(symbols);
		
//		for (int j=0; j<STATES.length; j++){
//			System.out.printf("\n%c: %d",STATES[j], counts[j]);
//		}

	}

}

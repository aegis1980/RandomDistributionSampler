package com.fitc.sampler.tests;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.Sampler;
import com.fitc.sampler.distributions.discrete.Uniform;

public class DataAndUniformDistTest {

	final static char[] STATES = { 'A', 'B','C','D'};
	final static double[] P = {0.4,0.3,0.3};
	private static final int SAMPLES = 100000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		DiscreteDistribution probDist = new Uniform(STATES);	

		//DiscreteDistribution probDist = new DiscreteDistributionData(STATES,P);
		
		Sampler sampler = new Sampler(probDist);
				
		final int[] counts = new int[STATES.length];
		
		for (int i = 0;i<SAMPLES;i++){
			final char sample = sampler.sampleState();
			for (int j=0; j<STATES.length; j++){
				if (sample==STATES[j]){
					counts[j]++;
					break;
				}
				
			}
			
		}
		
		for (int j=0; j<STATES.length; j++){
			System.out.printf("\n%c: %d",STATES[j], counts[j]);
		}
	}
}

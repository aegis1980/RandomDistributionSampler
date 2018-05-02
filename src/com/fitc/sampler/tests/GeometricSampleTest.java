package com.fitc.sampler.tests;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.Sampler;
import com.fitc.sampler.distributions.discrete.Bernoulli;

public class GeometricSampleTest {
	static double p = 1.0/16.0;
	final static char[] STATES = {'H','N'};
	private static final int SAMPLES = 100000;

	
	public static void main(String[] args) {
		int c =0, hcount =0;
		DiscreteDistribution probDist = new Bernoulli(p,STATES);

		Sampler sampler = new Sampler(probDist);

		int[] lengths = new int[1000];
		while (c < SAMPLES) {
			while (sampler.sampleState()=='H'){
				hcount++;
			}
			lengths[hcount]++;
			hcount=0;
			c++;
		}
		
		for (int i=0;i<lengths.length;i++){
			System.out.println(i + " " +lengths[i]);
		}
	}
	
}

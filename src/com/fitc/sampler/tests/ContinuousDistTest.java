package com.fitc.sampler.tests;

import com.fitc.sampler.ContinuousDistribution;
import com.fitc.sampler.Sampler;
import com.fitc.sampler.SamplerTest;
import com.fitc.sampler.distributions.continuous.Exponential;

public class ContinuousDistTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ContinuousDistribution probDist = new Exponential(0.03);
		
		SamplerTest s = new SamplerTest(1000000000,probDist,Sampler.RNG.MERSENNE_TWISTER);
		s.run();
				
		System.out.printf("Expt expected value: %s;", s.sampleExpectedValue());
		System.out.printf("\nTheoretical expected value: %s;", probDist.getTheoreticalE());
		
		System.out.printf("\nExpt variance: %s;", s.sampleVariance());
		System.out.printf("\nTheoretical variance: %s;", probDist.getTheoreticalVar());
	}
}

package com.fitc.sampler.tests;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.Sampler;
import com.fitc.sampler.SamplerTest;
import com.fitc.sampler.distributions.discrete.Geometric;

//import nz.ac.jrob217.sampler.distributions.discrete.Poisson;




public class DiscreteDistTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//DiscreteDistributionModel probDist = new Binomial(0.4, 10);	
		//DiscreteDistributionModel probDist = new Poisson(5);	
		//DiscreteDistributionModel probDist = new Bernoulli(0.6);
		//DiscreteDistributionModel probDist = new Uniform(5);
		DiscreteDistribution probDist = new Geometric((0.25));
		
		SamplerTest s = new SamplerTest(10000000,probDist,Sampler.RNG.MERSENNE_TWISTER);
		s.run();
		System.out.println(s.toString());

				
		System.out.printf("Expt expected value: %s;", s.sampleExpectedValue());
		System.out.printf("\nTheoretical expected value: %s;", probDist.getTheoreticalE());
		
		System.out.printf("\nExpt variance: %s;", s.sampleVariance());
		System.out.printf("\nTheoretical variance: %s;", probDist.getTheoreticalVar());
		
	}
}

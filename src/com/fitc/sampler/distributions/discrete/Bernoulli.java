package com.fitc.sampler.distributions.discrete;

import com.fitc.sampler.DiscreteDistribution;

/**
 * <p> Bernoulli discrete distribution for use with sampler
 * 
 * @author jrob217
 *
 */
public class Bernoulli extends DiscreteDistribution {

	private double p;

	public Bernoulli(double p){
		super(2);
		if (p<0 || p>1) throw new RuntimeException("p needs to be >=0 and <=1");
		this.p = p;
		init();
	}
	
	public Bernoulli(double p, char[] events){
		super(events);
		if (p<0 || p>1) throw new RuntimeException("p needs to be >=0 and <=1");
		this.p = p;
		init();
	}

	@Override
	public double getTheoreticalE() {
		return p; 
	}

	@Override
	public double getTheoreticalVar() {
		return p * (1-p); 
	}

	@Override
	protected double probabilityMassFunction(int x) {
		return (x==1)?p:1-p;
	}

}

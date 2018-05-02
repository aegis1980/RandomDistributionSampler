package com.fitc.sampler.distributions.discrete;

import com.fitc.sampler.DiscreteDistribution;


public class Geometric extends DiscreteDistribution {

	private double pe;

	public Geometric(double pe) {
		super(200);
		this.pe = pe;
		init();

	}

	@Override
	public double getTheoreticalE() {
		return 1/pe;
	}

	@Override
	public double getTheoreticalVar() {
		return (1-pe)/(pe*pe);
	}

	@Override
	protected double probabilityMassFunction(int in) {
		return in==0 ? 0 : pe*Math.pow((1-pe),((double)in-1));
	}
}

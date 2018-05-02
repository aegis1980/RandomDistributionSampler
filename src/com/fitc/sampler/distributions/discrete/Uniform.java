package com.fitc.sampler.distributions.discrete;

import com.fitc.sampler.DiscreteDistribution;


public class Uniform extends DiscreteDistribution{

	private final int mA;
	private final int mB;
	
	public Uniform(int a, int b){
		super(a,b);
		mA = a;
		mB= b;
		init();
	}
	
	public Uniform(int k) {
		super(k);
		mA = 0;
		mB = k-1;
		init();
	}

	public Uniform(char[] states) {
		super(states);
		mA = 0;
		mB = mK-1;
		init();
	}

	@Override
	protected double probabilityMassFunction(int x) {
		return (1./(double)mK);
	}

	@Override
	public double getTheoreticalE() {
		return (mA+mB)/2;
	}

	@Override
	public double getTheoreticalVar() {
		return (mK*mK-1)/12;
	}

}

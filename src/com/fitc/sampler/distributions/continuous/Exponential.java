package com.fitc.sampler.distributions.continuous;

import com.fitc.sampler.ContinuousDistribution;

public class Exponential extends ContinuousDistribution {

	final double mL;
	
	public Exponential(double lambda){
		mL = lambda;
	}
	
	@Override
	protected double CDFMap(double u) {
		/*
		 * for X ~ Exp(l)
		 * 
		 * CDF(x) = 1 - e ^(-l.x)
		 * so x = (-log (u)/l)
		 */
		double x = -StrictMath.log(u)/mL;

		return x;
	}

	@Override
	public double getTheoreticalE() {
		return (1/mL);
	}

	@Override
	public double getTheoreticalVar() {
		return (1/(mL*mL));
	}

}

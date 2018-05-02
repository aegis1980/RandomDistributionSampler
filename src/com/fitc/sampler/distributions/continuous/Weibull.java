package com.fitc.sampler.distributions.continuous;

import com.fitc.sampler.ContinuousDistribution;

public class Weibull extends ContinuousDistribution {

	final double mL;
	final double mA;

	public Weibull(double lambda, double a){
		mL = lambda;
		mA = a;
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

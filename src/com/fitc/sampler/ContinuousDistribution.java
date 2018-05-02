package com.fitc.sampler;

public abstract class ContinuousDistribution extends Distribution {

	@Override
	public double getValue(double u){
		return CDFMap(u);
	}

	/**
	 * <p>Continuous Distribution Function (CDF)</p>
	 * <p>Function that assigns a probability that X <= x</p>
	 * <p>i.e. F(x) = P (X <= x) </p>
	 * 
	 * <p>CDF is the integration of the Probability Density Function (PDF)</br>
	 * We have u and CDF in u(x) = CFD(x) and need to solve for x.
	 * 
	 * 
	 * <p>for sampling of continous distribution
	 * 
	 * @param x Discrete value X can take
	 * @return Probability x can occur. NOTE:  0 <= p(X=x) <= 1
	 */
	protected abstract double CDFMap(double x);
	
	@Override
	final public double sample(double u){
		return getValue(u);
	}
	
	@Override
	public void init(){
		// Generally dont need to do this for CD
		// might implements PDF > CDF
	}

}

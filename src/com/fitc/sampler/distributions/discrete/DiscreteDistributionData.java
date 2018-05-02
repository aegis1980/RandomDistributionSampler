package com.fitc.sampler.distributions.discrete;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.sharedstuff.Data;

/**
 * <p>Discrete Distribution for use with random sampler</p>
 * 
 * @author Jon Robinson jrob217 
 *
 */
public class DiscreteDistributionData extends DiscreteDistribution {

	/**
	 * <p>Array of states/events, x, and associated array of probabilities, p(x)
	 * 
	 * @param states
	 * @param probs
	 */
	public DiscreteDistributionData(char[] states,double[] probs){
		super(states);
		if (states.length!=probs.length) throw new RuntimeException(
		        "Need to be as many states as probabilities.");
		double tot = Data.sum(probs);
		if (Math.abs(tot-1.)>0.00000001) throw new RuntimeException(
		        "Probabilities need to add up to 1. They add up to "+ tot);
		this.p = probs;
		init();
	}
	
	@Override
	protected void buildEventsAndProbabilities(){
		// DO NOTHING : Probabilities already user-defined.
	}

	@Override
	protected double probabilityMassFunction(int x) {
		// DO NOTHING : Probabilities already user-defined. Method never called
		return 0;
	}

	@Override
	public double getTheoreticalE() {
		double e=0;
		for (int i=0;i<p.length;i++)
			e = e + (p[i] + (double)getState(i)); 
		return e;
	}

	@Override
	public double getTheoreticalVar() {
		// TODO Auto-generated method stub
		return 0;
	}


}

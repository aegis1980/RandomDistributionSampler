package com.fitc.sampler.markov;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.distributions.discrete.DiscreteDistributionData;
import com.fitc.sampler.sharedstuff.Utils;

/**
 * <p> This class is just a container for the discrete probability distributions used 
 * in Markov Chain and HMM - essentially the Markov,transition or emission matrices turned into 
 * distributions so they can be used with my sampler</p>
 * 
 * @author jrob217
 *
 */
public class ProbabilityMatrixDistribution extends DiscreteDistribution{

	DiscreteDistributionData[] rowDists; 
	double[][] mP;
	private char[] mSymbols;
	
	public ProbabilityMatrixDistribution(char[] states,double[][] markov){
		super(states);
		mSymbols = states;
		this.mP = markov;
		init();
	}
	
	
	public ProbabilityMatrixDistribution(char[] states,char[] symbols, double[][] markov){
		super(states);
		mSymbols = symbols;
		this.mP = markov;
		init();
	}
	
	
	public void init() {
		int rows = mP.length;
		int cols = mP[0].length;
		
		// create separate dd for each row.
		rowDists = new DiscreteDistributionData[rows];
		for (int r=0;r<rows;r++)
			rowDists[r] = new DiscreteDistributionData(mSymbols,Utils.extractRow(mP,r));
	}

	public DiscreteDistributionData getDist(int i){
		return rowDists[i];
	}
	
	@Override
	public char getState(int i){
		return mSymbols[i];
	}

	@Override
	protected double getValue(double i) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double sample(double u) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTheoreticalE() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getTheoreticalVar() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected double probabilityMassFunction(int x) {
		// TODO Auto-generated method stub
		return 0;
	}

}

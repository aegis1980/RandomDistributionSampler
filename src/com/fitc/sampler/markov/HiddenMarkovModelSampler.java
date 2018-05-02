package com.fitc.sampler.markov;

import com.fitc.sampler.Sampler;

/**
 * <p>Constructs an HMM and allows random sampling from it</p>
 * 
 * @author jrob217
 *
 */
public class HiddenMarkovModelSampler {

	private MarkovChainSampler mStateSampler;

	final private Sampler mSampler;
	private ProbabilityMatrixDistribution pmdSymbol;
	
	public HiddenMarkovModelSampler(double[][] transitions, char[] states, double[][] emissons, char[] symbols) {
		// Create state sample - models 'drawing' of states with a Markov chain
		mStateSampler = new MarkovChainSampler(transitions,states);
		pmdSymbol =  new ProbabilityMatrixDistribution(states,symbols,emissons);
		mSampler =  new Sampler();
	}
	
	
	public void setStartState(char startState) {
		mStateSampler.setStartState(startState);
	}
	
	public void setStartState(char[][] startState) {
		mStateSampler.setStartState(startState);
	}
	
	
	/**
	 * <p>Returns next state and symbol characters from random sample of HMM</p>
	 * 
	 * @return ret array with state and symbol in
	 */
	public char[] nextStateSymbolPair(){
		char[] ret = new char[2];
		ret[0] = mStateSampler.nextStateSingle();		
		ret[1] = sampleSymbol(ret[0]);
		return ret;
	}

	/**
	 * <p>Returns next symbol character from random sample of HMM</p>
	 * 
	 * @return ret array with state and symbol in
	 */
	public char sampleSymbol(char c) {
		int d = pmdSymbol.getIndex(c);
		mSampler.setDistribution(pmdSymbol.getDist((int)d));
		return pmdSymbol.getState((int)mSampler.sample()); 
	}
	

}

package com.fitc.sampler.markov;

import java.util.HashMap;
import java.util.Map;

import com.fitc.sampler.DiscreteDistribution;
import com.fitc.sampler.Sampler;
import com.fitc.sampler.distributions.discrete.DiscreteDistributionData;
import com.fitc.sampler.sharedstuff.Utils;

public class ForwardAlgorithmSampler extends ForwardAlgorithm {

	private final Sampler mSampler = new Sampler();
	private Map<Character, Integer> stateToIndexMap = new HashMap<Character,Integer>(); 
	
	public ForwardAlgorithmSampler(char[] states, double[][] transitions,
			char[] symbols, double[][] emissions) {
		super(states, transitions, symbols, emissions);
		
		makeStateMap(states);
		
		// Need to record log(pF) matrix
		mRecordHistory = true;
	}
	
	
	/**
	 * <p>Generates sample state sequence from given symbol sequence,
	 * follwing back-tracing method, as per course notes.</p>
	 * 
	 * @return stateSeq a likely sampled state sequence
	 */
	public char[] generateStateSeq(){
		char[] stateSeq = new char[seqLen];
		
		//Sample last state in state sequence first
		stateSeq[seqLen-1] = sampleLastState();
		
		int sym_i;
		int stt_l;
		
		DiscreteDistribution dist;
		for (int i=seqLen-1;i>0;i--){
			// (index for) symbol at i
			sym_i = getSymIndex(mSymSeq[i]);
			
			// (index for) state at l
			stt_l = getStateIndex(stateSeq[i]);
			
			// get e_l(i), probability of state l emitting pi
			double e = mEmissions[stt_l][sym_i];

			// Array for storing probabilities to feed into discrete distribution
			double[] pk_l = new double[mStates.length];

			for (int stt_k=0;stt_k<mStates.length;stt_k++){	
				// get a_kl, the prob from going from state k (in i-1) to state l (in i)
				double a = mTransitions[stt_k][stt_l];

				double log_pk_l = Utils.log2(a*e)+mFHist[stt_k][i-1] - mFHist[stt_l][i] ; 	
				pk_l[stt_k] = Math.pow(BASE, log_pk_l);
			}
			// 	create Discrete distribution for column based on calculated probabilities
			dist = new DiscreteDistributionData(mStates, pk_l);
			mSampler.setDistribution(dist);
			stateSeq[i-1] = mSampler.sampleState();	
		}
		
		return stateSeq;
	}

	public char sampleLastState() {
		double[] pk = new double[mStates.length];
		
		//Calculate
		
		double[] logs = new double[mStates.length];
		for (int stt_k=0;stt_k<mStates.length;stt_k++){
			logs[stt_k] = mFHist[stt_k][seqLen-1];
		}
		final double log2Ptot = Utils.addLog2(logs);
		
		for (int stt_k=0;stt_k<mStates.length;stt_k++){
			double log_pk = mFHist[stt_k][seqLen-1];
			pk[stt_k] = Math.pow(2, log_pk - log2Ptot);	
		}
		
		//Create Discrete distribution for column L.
		DiscreteDistribution dist = new DiscreteDistributionData(mStates, pk);
		mSampler.setDistribution(dist);
		mSampler.sampleState();
		return mSampler.sampleState();
	}
	
	//************************************************************************************
	// Getters and setters
	
	
	//************************************************************************
	
	// Store states and symbols in Map for O(1) access
	private void makeStateMap(char[] states) {
		// Fill up states bimap
		for (int i=0; i<states.length; i++){
			stateToIndexMap.put(states[i], i);
		}
	}
	
	protected int getStateIndex(char c){
		return stateToIndexMap.get(c);
	}

}

package com.fitc.sampler.markov;

import java.util.HashMap;
import java.util.Map;

import com.fitc.sampler.sharedstuff.Utils;

public class JointProbability {

	private static final double BASE = 2.;

	private Map<Character, Integer> symToIndexMap = new HashMap<Character,Integer>(); 
	private Map<Character, Integer> stateToIndexMap = new HashMap<Character,Integer>(); 
	
	private char[] mSymSeq, mStateSeq;
	
	private char[] mStates;
	
	// Length of sequence
	private int len;
	
	// Probalility matrices
	private double[][] mTransitions,mEmissions;
	
	private double logPx_pi;

	private boolean recordHistory = false;


	public JointProbability(char[] states, double[][] transitions, char[]symbols, double [][] emissions){

		// Initialise 
		mStates = states;
		mTransitions = transitions;
		mEmissions = emissions;
		makeBiMaps(states,symbols);
		
		
		// fill out 
	}
	
	public void solve(){
		int state_i, state_i1, symbol_i;

		// a_0_pi : 0.3333 (uniform dist'n on first state in Markov chain)
		
		double a = 1. / ((double) mStates.length);
		logPx_pi = Utils.log2(a);
		if (recordHistory) System.out.printf("i: -   a: %f   aL: %.3f   p: %.4f\n",a,logPx_pi,logPx_pi);
		
		for (int i = 0; i < len; i++) {

			// DO STATES FIRST
			// get indices for states i
			state_i = getStateIndex(mStateSeq[i]);
			double aL=0;
			if (i < len-1) {
				// get indices for states i+1
				state_i1 = getStateIndex(mStateSeq[i + 1]);
				// Get associated prob from tran's matrix
				a = mTransitions[state_i][state_i1];

				aL = Utils.log2(a);
				logPx_pi = logPx_pi + aL;
			}

			// .. and for symbol i
			symbol_i = getSymIndex(mSymSeq[i]);

			// Get associates probabilities from probability matrices
			double e = mEmissions[state_i][symbol_i];
			
			// do sum
			double eL = Utils.log2(e);
			logPx_pi = logPx_pi + eL;
			
			if (recordHistory) System.out.printf("i: %d   a: %f   aL: %.3f   e: %.4f   el:%.3f   p: %.4f\n",i,a,aL,e,eL,logPx_pi);
		}
		
	}
	
	//***********************************************************************
	// Public getters and setters
	
	
	public double getLP() {
		return logPx_pi;
	}

	public double getP() {
		return Math.pow(BASE, logPx_pi);
	}
	
	public void setSequences(char[] stateSeq, char[] symSeq){
		mStateSeq = stateSeq;
		mSymSeq = symSeq;
		len = symSeq.length;
	}
	
	public void recordHistory(boolean b) {
		recordHistory = b;
	}
	
	//************************************************************************
	
	//************************************************************************
	
	// Store states and symbols in Map for O(1) access
	private void makeBiMaps(char[] states, char[] symbols) {
		// Fill up states bimap
		for (int i=0; i<states.length; i++){
			stateToIndexMap.put(states[i], i);
		}
		
		// Fill up symbols bimap
		for (int i=0; i<symbols.length; i++){
			symToIndexMap.put(symbols[i], i);
		}
	}
	
	private int getSymIndex(char c){
		return symToIndexMap.get(c);
	}
	
	private int getStateIndex(char c){
		return stateToIndexMap.get(c);
	}



}

package com.fitc.sampler.markov;

import java.util.HashMap;
import java.util.Map;

import com.fitc.sampler.sharedstuff.Utils;

public class ForwardAlgorithm {
	
	protected boolean mRecordHistory;
	
	protected static final double BASE = 2.;

	private Map<Character, Integer> symToIndexMap = new HashMap<Character,Integer>(); 
	
	protected char[] mSymSeq;
	
	protected char[] mStates;
	
	private double[][] mF;
	protected double[][] mFHist,mfHist;
	protected int seqLen;
	protected double[][] mTransitions,mEmissions;

	private double mLogPx;

	public ForwardAlgorithm(char[] states, double[][] transitions, char[]symbols, double [][] emissions){
		
		// Initialise 
		mStates = states;
		mTransitions = transitions;
		mEmissions = emissions;
		makeSymbolMap(symbols);
	}
	
	public void solve(){
		int sym_i;
		double a;
		
		/*
		 * First column- 
		 * Assuming each state equally likely. 
		 */
		a = 1. / ((double) mStates.length);
		for (int r=0;r<mStates.length;r++){
			// lookup index for symbol 0
			sym_i = getSymIndex(mSymSeq[0]);
			
			// Get associated probabilities from probability matrices
			double e = mEmissions[r][sym_i];
			mF[r][0]  = Utils.log2(a) + Utils.log2(e);	
			if (mRecordHistory)  mFHist[r][0] = mF[r][0];
		}
		
		// Now rest of columns
		for (int i=1;i<seqLen;i++){
			
			for (int r=0;r<mStates.length;r++){
				// lookup index for symbol i
				sym_i = getSymIndex(mSymSeq[i]);
				
				// Get associated probabiliy from probability matrices
				// this is part (1) of Fk(i)
				double e = mEmissions[r][sym_i];
				mF[r][1] =  Utils.log2(e);	
				if (mRecordHistory)  mFHist[r][i] = mF[r][1] ;	
				
				//... now do part 2
				double tot =0;
				for (int r1=0;r1<mStates.length;r1++){
					a = mTransitions[r1][r];
					
					tot = tot + Math.pow(BASE,mF[r1][0] + Utils.log2(a));
				}
				mF[r][1] = mF[r][1] + Utils.log2(tot);
				if (mRecordHistory) mFHist[r][i] = mF[r][1];
			}
			for (int r=0;r<mStates.length;r++)
				mF[r][0] = mF[r][1];
		}
		
		/*
		 * Termination. 
		 * Calculate log2(P(x))
		 */
		double logs[] = new double [mStates.length];
		for (int r = 0;r<mStates.length;r++){
			logs[r] = mF[r][1];
		}
		mLogPx = Utils.addLog2(logs);
	}
	
	//***********************************************************************
	// Public getters and setters
	
	public double[][] getF(){
		if (!mRecordHistory) return historyError();
		return mFHist;
	}
	
	public double[][] getf(){
		if (!mRecordHistory) return historyError();
	
		if (mfHist==null){
			mfHist = new double[mStates.length][mSymSeq.length];
			for (int r=0;r<mStates.length;r++)
				for (int c=0;c<mSymSeq.length;c++)
					mfHist[r][c] = Math.pow(BASE, mFHist[r][c]);
		}
		return mfHist;
	}


	
	public double getLP() {
		return mLogPx;
	}

	public double getP() {
		return Math.pow(BASE, mLogPx);
	}
	
	/**
	 * <p>Set to true if want to store history of recursion for checking in 
	 * mFHist</p>
	 * 
	 * @param b
	 */
	public void recordHistory(boolean b){
		mRecordHistory = b;
	}
	
	public void setSymbolSeq(char[] symSeq){
		mSymSeq = symSeq;
		seqLen = symSeq.length;
		
		//initialise F matrix where all recursion numbers are stored
		if (mRecordHistory) mFHist = new double[mStates.length][symSeq.length];
		mF = new double[mStates.length][2];
	}
	
	//************************************************************************
	
	// Store states and symbols in Map for O(1) access
	private void makeSymbolMap(char[] symbols) {
		// Fill up symbols bimap
		for (int i=0; i<symbols.length; i++){
			symToIndexMap.put(symbols[i], i);
		}
	}
	
	
	protected int getSymIndex(char c){
		return symToIndexMap.get(c);
	}
	
	private static double[][] historyError() {
		System.err.println("Need to set history recording to true with 'recordHistory(boolean b)'");
		return new double[0][0];
	}
	


}

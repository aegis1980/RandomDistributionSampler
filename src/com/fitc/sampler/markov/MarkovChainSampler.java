package com.fitc.sampler.markov;

import com.fitc.sampler.Sampler;
import com.fitc.sampler.sharedstuff.Utils;


public class MarkovChainSampler {

	private Sampler mSampler;
	
	private char[][] mStateMatrix;
	private ProbabilityMatrixDistribution pmdState;
	
	public MarkovChainSampler(double[][] markov,char[] states, char startState) {
		pmdState =  new ProbabilityMatrixDistribution(states,markov);
		setStartState(startState);	
		initialise();
	}
	
	public MarkovChainSampler(double[][] markov, char[] states, char[][] startState) {
		pmdState =  new ProbabilityMatrixDistribution(states,markov);
		setStartState(startState);
		initialise();
	}
	
	public MarkovChainSampler(double[][] markov, char[] states) {
		pmdState =  new ProbabilityMatrixDistribution(states,markov);
		initialise();
	}
	
	public void setStartState(char startState) {
		mStateMatrix = new char[1][1];
		mStateMatrix[0][0] = startState;
	}
	
	public void setStartState(char[][] startState) {
		mStateMatrix = startState;
	}
		
	public char[][] nextChainState(){
		if (mStateMatrix==null) throw new RuntimeException(
		        "you need to set a start state.");
		//traverse matrix
		for (int r=0;r<mStateMatrix.length;r++)
			for (int c=0;c<mStateMatrix[0].length;c++){
				// get last state index
				int d = pmdState.getIndex(mStateMatrix[r][c]);
				
				// Set Discrete distribution to the one assigned to this state
				mSampler.setDistribution(pmdState.getDist((int)d));
				
				//Keep track of index - this gets used in next step (rather than state)
				//get new state index
				mStateMatrix[r][c] = pmdState.getState((int)mSampler.sample());
			}
		
		return mStateMatrix; 
	}
	
	public char nextStateSingle(){
		return nextChainState()[0][0];
	}
	
	public void initialise(){
		mSampler = new Sampler();
	}
	
	public int getIndex(char state){
		return pmdState.getIndex(state);
	}
	
	
	//*************************************************************************************************************
	// Static methods;
	
	private static boolean isMarkov(double[][] markov) {
		// TODO Auto-generated method stub
		return true;
	}
	
	public static double[] steadyState(double[][] markov){
		int rows = markov.length;
		int cols = markov[0].length;
		
		
		// make clone of multi-array
		double [][] ss = new double[rows][];
		for(int i = 0; i < ss.length; i++)
		{
		  double[] aMatrix = markov[i];
		  int  aLength = aMatrix.length;
		  ss[i] = new double[aLength];
		  System.arraycopy(aMatrix, 0, ss[i], 0, aLength);
		}
		
		double[][] ssold = new double[rows][cols];
		boolean[][] converge= new boolean[rows][cols];
	
		while (true){
			
			for (int r=0;r<rows;r++)
				for (int c=0;c<cols;c++)
					ssold[r][c] = ss[r][c];
			
			ss = Utils.multiply(markov, ss);
			
			
			for (int r=0;r<rows;r++)
				for (int c=0;c<cols;c++)
					if (Math.abs(ss[r][c]-ssold[r][c])<0.0000000001) converge[r][c] = true;
			
			int count=0;
			for (int r=0;r<rows;r++)
				for (int c=0;c<cols;c++)
					if (converge[r][c]==true) count++;
			
			if (count==rows*cols) break; 
			
		}
		
		double [] ret = new double [cols];
		for (int c=0;c<cols;c++)
			ret[c] = ss[0][c];

		return ret;
	}


}

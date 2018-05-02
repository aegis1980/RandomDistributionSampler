package com.fitc.sampler;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


public abstract class DiscreteDistribution extends Distribution {

	BiMap<Integer,Character> indexToStateMap = HashBiMap.create(); 
	BiMap<Character,Integer> stateToIndexMap; 
	protected int mK;
	
	protected double p[];
	double bin[];
	
	public DiscreteDistribution(int k){
		this(0, k-1);
	}
	
	public DiscreteDistribution(char[] states){
		super();
		mK = states.length;	
		makeBiMap(states);
	}
	
	public DiscreteDistribution(int a, int b){
		super();
		mK = b-a +1;
		char[] events = new char[mK];
		for (int i=0;i<mK;i++){
			events[i] = (char) (i+a);
		}
		makeBiMap(events);
	}


	/**
	 * <p>Calculates probabilities and associated 'bins'</p>
	 * 	
	 */
	@Override
	public void init() {
		
		buildEventsAndProbabilities();
		buildBins();
	}
	
	protected void makeBiMap(char[] events) {
		// Fill up events bimap
		for (int i=0; i<mK; i++){
			indexToStateMap.put(i, events[i]);
		}
		stateToIndexMap =  indexToStateMap.inverse();
	}
	
	/**
	 * <p>Probability Mass Function (PMF)<br>
	 * alt. Probability Distribution Function (PDF)</p>
	 * <p>Function that assigns a probability to each possible value of X.</p>
	 * 
	 * <p>i.e. P (X = x) = p(x)</p>
	 * @param x Discrete value X can take
	 * @return Probability x can occur. NOTE:  0 <= p(X=x) <= 1
	 */
	protected abstract double probabilityMassFunction(int x);
	
	@Override
	protected double getValue(double x){
		return probabilityMassFunction((int)x);
	}
	
	protected void buildEventsAndProbabilities(){
		p = new double[mK+1];
		
		p[0] = getValue(0);
		
		for (int i = 1; i < mK + 1; i++) {
			p[i] = getValue(i);
		}
	}
	
	
	private void buildBins() {
		bin = new double[mK];
		bin[0] = p[0];

		for (int i = 1; i < mK; i++) {
			bin[i] = bin[i - 1] + p[i];
		}
	}
	
	public int getIndex(char c){
		return stateToIndexMap.get(c);
	}
	
	public char getState(int j){
		return indexToStateMap.get(j);
	}
	
	
	public int getK(){
		return mK;
	}
	
	public double[] getProbabilites(){
		return p;
	}
	
	public double[] getBins(){
		return bin;
	}
	
	final public String binsToString(){
		String s = "";
		for (int i = 0; i < mK; i++) {
			s = s + indexToStateMap.get(i) + " " + bin[i] + "\n";
		}
		return s;
	}
	
	@Override
	public double sample(double u){
		for (int j = 0; j < bin.length; j++) {
			if (u < bin[j])	return j;
		}
		return bin[bin.length - 1];
	}
	
	final public char sampleState(double u){
		return getState((int) sample(u));
	}
	
}

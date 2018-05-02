package com.fitc.sampler;

import java.util.Date;
import java.util.Random;

import com.fitc.sampler.sharedstuff.MersenneTwister;



public class Sampler {

	protected Distribution mDist;
	protected DiscreteDistribution mDDist;
	
	private final static Random RANDOM = new Random();
	
	//MersenneTwister seeded with current date
	private final static MersenneTwister MERSENNE_TWISTER = new MersenneTwister(new Date());

	private RNG mGenerator = RNG.MERSENNE_TWISTER; //Default RNG is the Mersenne Twister

	public Sampler(DiscreteDistribution d) {
		setDistribution(d);
	}

	public Sampler(ContinuousDistribution d) {
		setDistribution(d);
	}
	
	public Sampler(){
		
	}

	public void setDistribution(ContinuousDistribution d) {
		mDist = d;
		setUp();
	}
	

	public void setDistribution(DiscreteDistribution d){
		mDist = d;
		mDDist = d;
		setUp();
	}

	protected void setUp() {
		mDist.init();
	}
	
	public char sampleState(){
		double u = (mGenerator==RNG.MERSENNE_TWISTER) ? MERSENNE_TWISTER.nextDouble(): RANDOM.nextDouble();
		return mDDist.sampleState(u);
	}
	
	public double sample() {
		double u = (mGenerator==RNG.MERSENNE_TWISTER) ? MERSENNE_TWISTER.nextDouble(): RANDOM.nextDouble();
		return mDist.sample(u);
	}

	public void setRNG(RNG rng) {
		mGenerator = rng;
	}
	
	public enum RNG {
	    JAVA_RNG, MERSENNE_TWISTER 
	}
}

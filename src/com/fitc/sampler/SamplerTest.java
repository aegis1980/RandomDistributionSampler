package com.fitc.sampler;


/**
 * <p>Class for testing sampler and discrete and continuous probability distributions.</p>
 * <p>Compares theoretical mean and variance with experimental values.</p>
 *  
 * @author jrob217
 *
 */
public class SamplerTest{
	
	// Number of samples taken
	private final static int N = 1000000;
	/**
	 * Number of samples int test
	 */
	int mN = N;
	double sampleP[];
	TestSampler mInstance;
	
	
	public SamplerTest(int numberOfSamples,DiscreteDistribution d,Sampler.RNG rng) {
		mInstance = new DiscreteSamplerTest(numberOfSamples,d);
		init(numberOfSamples, rng);
	}
	
	public SamplerTest(int numberOfSamples,ContinuousDistribution c,Sampler.RNG rng) {
		mInstance = new ContinuousSamplerTest(numberOfSamples,c);
		init(numberOfSamples, rng);
	}

	private void init(int numberOfSamples, Sampler.RNG rng) {
		mInstance.setRNG(rng);
		mN = numberOfSamples;
	}
	
	
	public double sampleExpectedValue(){
 		return mInstance.sampleExpectedValue();
	}
	
	public double sampleVariance(){
 		return mInstance.sampleVariance();
	}
	
	public void run() {
		mInstance.run();
	}
	
	public String toString(){
		return mInstance.toString();
	}

	private class DiscreteSamplerTest extends TestSampler {
		
		//DiscreteDistribution mDist;
		double sampleP[];
	
		
		private int xCount[];
		
		private DiscreteSamplerTest(int numberOfSamples,DiscreteDistribution d) {
			super(d);
		}
		
		@Override
		protected void setUp(){
			super.setUp();
			xCount = new int[((DiscreteDistribution)mDist).mK];
			
		}

		public double sampleExpectedValue(){
			double total = 0;
	 		for (int i = 0; i <mDDist.mK; i++) 
	 			total = total  + i * (double)xCount[i];
			
	 		return (ex  = total/ (double)mN);
		}
		
		@Override
		double sampleVariance() {
			double total2 = 0;
	 		for (int i = 0; i <mDDist.mK; i++) 
	 			total2 = total2  + i*i * (double)xCount[i];
			return (var = ((total2/ (double)mN)-ex*ex));
		}
		
		public void run() {
			long startTime = System.currentTimeMillis();
			for (int i = 0; i <mN; i++) {
				double x = sample();
				xCount[(int)x]++;
			}
			long runTime = System.currentTimeMillis()-startTime;
			System.out.printf("Runtime: %.2f seconds\n\n", runTime/1000f);
			sampleProbabilities();
		}
		
		protected void sampleProbabilities(){
			sampleP = new double[mDDist.mK];
			
	 		for (int i = 0; i <mDDist.mK; i++) {
				sampleP[i] = (double)xCount[i] /  (double)mN;
			}
		}
		
		@Override
		public String toString(){
			String s = "";
			for (int j = 0; j < mDDist.bin.length; j++) {
				s= s + j + "   " + xCount[j] + "   " + sampleP[j] +"   " + mDDist.p[j] +"\n";
			}
			return s;
		}



	}
	
 	private class ContinuousSamplerTest extends TestSampler {
		
		double total = 0, total2 = 0;

		
		private ContinuousSamplerTest(int numberOfSamples,ContinuousDistribution d) {
			super(d);
		}
		
		@Override
		protected void setUp(){
			super.setUp();
		}

		@Override
		public double sampleExpectedValue(){
	 		return (ex  = total/ (double)mN);
		}
		
		@Override
		public double sampleVariance(){
			//if (ex==UNDEFINED) sampleExpectedValue();
			double ex2 = total2 / (double)mN;	
			return (var = ex2 - ex*ex );
		}
		
		
		
		public void run() {
			long startTime = System.currentTimeMillis();
			for (int i = 0; i <mN; i++) {
				double x = sample();
				total = total + x;
				total2  = total2 + x*x;
			}
			long runTime = System.currentTimeMillis()-startTime;
			System.out.printf("Runtime: %.2f seconds\n\n", runTime/1000f);
		}
		
		@Override
		public String toString(){
			String s = "";
//			for (int j = 0; j < mDist.bin.length; j++) {
//				s= s + mDist.x[j] + "   " + xCount[j] + "   " + sampleP[j] +"   " + mDist.p[j] +"\n";
//			}
			return s;
		}

		@Override
		protected void sampleProbabilities() {
			// TODO Auto-generated method stub
			
		}

	}
	
	private abstract class TestSampler extends Sampler{

		protected double ex;
		protected double var;
		
		public TestSampler(DiscreteDistribution d) {
			super(d);
		}
		
		public TestSampler(ContinuousDistribution d) {
			super(d);
		}
		
		abstract double sampleExpectedValue();
		abstract double sampleVariance();
		
		abstract public void run();
		
		abstract protected void sampleProbabilities();
		
		abstract public String toString();
		
	}
	
}

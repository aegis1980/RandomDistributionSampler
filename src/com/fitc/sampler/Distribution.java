package com.fitc.sampler;

public abstract class Distribution {
	
	private String mName;
		
	public Distribution (){
		setName();
	}
	
	protected void setName() {
		mName = this.getClass().getSimpleName();
	}
	
	protected abstract double getValue(double i);
	
	public abstract void init();
	
	public abstract double sample(double u);
	
	abstract public double getTheoreticalE();
	
	abstract public double getTheoreticalVar();
}

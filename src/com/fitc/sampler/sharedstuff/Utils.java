package com.fitc.sampler.sharedstuff;

import java.text.DecimalFormat;


public class Utils {

	public static final DecimalFormat DF3 = new DecimalFormat("##########.0000");
	
	public static String sequenceToString(String name, char[] seq) {
		String out =  name+ " : ";
		for (char s:seq){
			out = out + s;
		}
		out = out + "    Length: " + seq.length;
		return out;
	}
	
	public static String printMultiArray(int[][] counts) {
		String s = "\n";
		for (int r=0;r<counts.length;r++){
			for (int c=0;c<counts[0].length;c++){
				s= s+ " " + counts[r][c];
			}
			s=s+"\n";
		}
		return s;
	}
	
	public static String  printMultiArray(String label, int[][] counts) {
		return "\n" + label + "  :" + printMultiArray(counts);
	}
	
	public static String printMultiArray(double[][] counts) {
		String s = "\n";
		for (int r=0;r<counts.length;r++){
			for (int c=0;c<counts[0].length;c++){
				double d = new Double(Utils.DF3.format(counts[r][c])).doubleValue();
				s= s+ " " + d;
			}
			s=s+"\n";
		}
		return s;
	}
	
	public static String  printMultiArray(String label, double[][] counts) {
		return "\n" + label + "  :" + printMultiArray(counts);
	}
	
	public static String printArray(double[] counts) {
		String s = "\n";

			for (int i=0;i<counts.length;i++){
				double d = new Double(Utils.DF3.format(counts[i])).doubleValue();
				s= s+ " " + d;
			}
			s=s+"\n";

		return s;
	}
	
	public static String printArray(String label,double[] counts) {
		return "\n" + label + "  :" + printArray(counts);
	}

	public static double[] rowTotals(int[][] counts){
		int rows = counts.length;
		int cols = counts[0].length;
		double[] rowTot = new double[cols];
		
		for (int r=0;r<rows;r++)
			for (int c=0;c<cols;c++)
				rowTot[r] = rowTot[r] + counts[r][c];
		
		return rowTot;
	}
	
	public static double[] colTotals(int[][] counts){
		int rows = counts.length;
		int cols = counts[0].length;
		double[] colTot = new double[rows];
		
		for (int r=0;r<rows;r++)
			for (int c=0;c<cols;c++)
				colTot[c] = colTot[c] + counts[r][c];
		
		return colTot;
	}
	
	public static double[][] normaliseRows(int[][] counts) {
		return normaliseRows(counts,null);
	}

	public static double[][] normaliseRows(int[][] counts, double[] rowTots) {
		int rows = counts.length;
		int cols = counts[0].length;
		if (rowTots == null) rowTots = rowTotals(counts);
		// Create double matrix the same size.
		double[][] normalised = new double[rows][cols];
		for (int r=0;r<rows;r++)
			for (int c=0;c<cols;c++)
				normalised[r][c] = ((double)counts[r][c])/rowTots[r];

		return normalised;
	}
	
	
	public static double[] extractRow(double[][] in, int r) {
		int rows = in.length;
		int cols = in[0].length;
		
		double[] out = new double[cols];
		for (int c=0;c<cols;c++)
			out[c] = in[r][c];
		
		return out;
	}

	public static double[] normalise(double[] in) {
		double[] normalised = new double[in.length];
		double total = Data.sum(in);
		 for (int i=0;i<in.length;i++){
			 normalised[i] = in[i]/total;
		 }
		return normalised;
	}
	
	
	

    /**
     * Array matrix multiplication</br>
     * from http://introcs.cs.princeton.edu/java/22library/Matrix.java
     * 
     * @param A
     * @param B
     * @return AB
     */
    public static double[][] multiply(double[][] A, double[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = A[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] C = new double[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }
    
    
    
    
    private static final double LN_2 = Math.log(2.);
     
    public static double log2(double x)
    {
        return Math.log(x)/LN_2 ;
    }
    
    /**
     * <p>My attempt at minimising underflow adding parameter of logs together
     * based on question I asked here:</p>
     * http://math.stackexchange.com/questions/402511/sum-of-logarithm-arguments 
     * 
     * @param log2s
     * @return log2(total of log parameters)
     */
    public static double addLog2(double[] logs){
    	// pick constant C as the negative of the most negative log2 value
    	final double C = -(Data.maxmin(logs)[1]);
    	double tot=0;
    	for (double l:logs){
    		tot = tot + Math.pow(2,(l+C));
    	}
    	return log2(tot) - C;
    	
    }
}

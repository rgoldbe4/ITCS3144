package io.github.rgoldbe4.assignment3;

public class Kernel {
	public static double[][] getIdentity() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = 0;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 1.0;
		matrix[1][2] = 0;
		matrix[2][0] = 0;
		matrix[2][1] = 0;
		matrix[2][2] = 0;
		
		return matrix;
	}
	
	public static double[][] getEdge() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = -1;
		matrix[0][1] = -1;
		matrix[0][2] = -1;
		matrix[1][0] = -1;
		matrix[1][1] = 8;
		matrix[1][2] = -1;
		matrix[2][0] = -1;
		matrix[2][1] = -1;
		matrix[2][2] = -1;
		
		return matrix;
	}
	
	public static double[][] getSharpen() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = 0;
		matrix[0][1] = -1;
		matrix[0][2] = 0;
		matrix[1][0] = -1;
		matrix[1][1] = 5;
		matrix[1][2] = -1;
		matrix[2][0] = 0;
		matrix[2][1] = -1;
		matrix[2][2] = 0;
		
		return matrix;
	}
	
	public static double[][] getBox() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = 1;
		matrix[0][1] = 1;
		matrix[0][2] = 1;
		matrix[1][0] = 1;
		matrix[1][1] = 1;
		matrix[1][2] = 1;
		matrix[2][0] = 1;
		matrix[2][1] = 1;
		matrix[2][2] = 1;
		
		matrix = applyMultiplier(1.0/9.0, matrix);
		return matrix;
	}
	
	public static double[][] getGaussian() {
		double[][] matrix = new double[5][5];
		matrix[0][0] = 1;
		matrix[0][1] = 4;
		matrix[0][2] = 6;
		matrix[0][3] = 4;
		matrix[0][4] = 1;
		
		matrix[1][0] = 4;
		matrix[1][1] = 16;
		matrix[1][2] = 24;
		matrix[1][3] = 16;
		matrix[1][4] = 4;
		
		matrix[2][0] = 6;
		matrix[2][1] = 24;
		matrix[2][2] = 36;
		matrix[2][3] = 24;
		matrix[2][4] = 6;
		
		matrix[3][0] = 4;
		matrix[3][1] = 16;
		matrix[3][2] = 24;
		matrix[3][3] = 16;
		matrix[3][4] = 4;
		
		matrix[4][0] = 1;
		matrix[4][1] = 4;
		matrix[4][2] = 6;
		matrix[4][3] = 4;
		matrix[4][4] = 1;
		
		matrix = applyMultiplier(1.0/256.0, matrix);
		return matrix;
	}
	
	public static double[][] getUnsharp() {
		double[][] matrix = new double[5][5];
		matrix[0][0] = 1;
		matrix[0][1] = 4;
		matrix[0][2] = 6;
		matrix[0][3] = 4;
		matrix[0][4] = 1;
		
		matrix[1][0] = 4;
		matrix[1][1] = 16;
		matrix[1][2] = 24;
		matrix[1][3] = 16;
		matrix[1][4] = 4;
		
		matrix[2][0] = 6;
		matrix[2][1] = 24;
		matrix[2][2] = -476;
		matrix[2][3] = 24;
		matrix[2][4] = 6;
		
		matrix[3][0] = 4;
		matrix[3][1] = 16;
		matrix[3][2] = 24;
		matrix[3][3] = 16;
		matrix[3][4] = 4;
		
		matrix[4][0] = 1;
		matrix[4][1] = 4;
		matrix[4][2] = 6;
		matrix[4][3] = 4;
		matrix[4][4] = 1;
		
		matrix = applyMultiplier(-(1.0/256.0), matrix);
		return matrix;
	}
	
	public static double[][] applyMultiplier(double multiplier, double[][] kernel) {
		double[][] finished = new double[kernel.length][kernel.length];
		for (int i = 0; i < kernel.length; i++) {
			for (int j = 0; j < kernel.length; j++) {
				finished[i][j] = multiplier * kernel[i][j];
			}
		}
		
		return finished;
	}
}

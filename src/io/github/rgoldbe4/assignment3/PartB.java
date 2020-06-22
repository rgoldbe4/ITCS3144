package io.github.rgoldbe4.assignment3;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartB {
	final static int RED_PLACEMENT = 0;
	final static int GREEN_PLACEMENT = 1;
	final static int BLUE_PLACEMENT = 2;
	static int height, width, totalSize;
	

	public static void main(String[] args) {		
		try {
			
			System.out.println("-- ASSIGNMENT #3 : PART B --");
			Scanner in = new Scanner(System.in);
			System.out.print("Please enter in 1, 2 or 3: ");
			String num = in.next();
			
			// -- Step 1: Grab all files and create new files. --
			File givenFile = new File(num + ".txt");
			
			File writeFileIdentity = new File(num + "_identity_partB.txt");
			FileWriter exportFileIdentity = new FileWriter(num + "_identity_partB.txt");
			
			File writeFileEdge = new File(num + "_edge_partB.txt");
			FileWriter exportFileEdge = new FileWriter(num + "_edge_partB.txt");
			
			File writeFileSharpen = new File(num + "_sharpen_partB.txt");
			FileWriter exportFileSharpen = new FileWriter(num + "_sharpen_partB.txt");
			
			File writeFileBox = new File(num + "_box_partB.txt");
			FileWriter exportFileBox = new FileWriter(num + "_box_partB.txt");
			
			File writeFileGauss = new File(num + "_gauss_partB.txt");
			FileWriter exportFileGauss = new FileWriter(num + "_gauss_partB.txt");
			
			File writeFileUnsharp = new File(num + "_unsharp_partB.txt");
			FileWriter exportFileUnsharp = new FileWriter(num + "_unsharp_partB.txt");
			
			writeFileIdentity.createNewFile();
			writeFileEdge.createNewFile();
			writeFileSharpen.createNewFile();
			writeFileBox.createNewFile();
			writeFileGauss.createNewFile();
			writeFileUnsharp.createNewFile();
			
			Scanner readFile = new Scanner(givenFile);	
			int counter = 0;
			
			 
			//First, skip RGB line
			readFile.nextLine();
			//Second, grab the height
			height = Integer.parseInt(readFile.nextLine());
			//Third, grab the width
			width = Integer.parseInt(readFile.nextLine());
			totalSize = ((height * width) + 1);
			
			//Create placeholders for values.
			double[][] redMatrix = new double[height][width];
			double[][] greenMatrix = new double[height][width];
			double[][] blueMatrix = new double[height][width];
			
			double[] redHolder = new double[totalSize];
			double[] greenHolder = new double[totalSize];
			double[] blueHolder = new double[totalSize];
			
			/*
			 * Ignore first line (RGB)
			 * Grab height
			 * Grab width
			 * Rest of file is RGB -> num num num
			 */
			while (readFile.hasNextLine()) {
				String line = readFile.nextLine();
				// Grab red, green, blue.
				double red = Double.parseDouble( line.split(" ")[ RED_PLACEMENT ] );
				double green = Double.parseDouble( line.split(" ")[ GREEN_PLACEMENT ]);
				double blue = Double.parseDouble( line.split(" ")[ BLUE_PLACEMENT ]);
				counter++;
				
				//Add to respective arrays.
				redHolder[counter] = red;
				greenHolder[counter] = green;
				blueHolder[counter] = blue;
			}
			
			readFile.close();
			
			//Next, convert all values into 2D arrays
			int row = 0, col = 0;
			for (int i = 0; i < (totalSize - 1); i++) { //i = column
				if (row == height) {
					break;
				}
				
				if (col == height) {
					row++;
					col = 0;
				}
				
				//Move values to 2D array.
				redMatrix[row][col] = redHolder[i];
				greenMatrix[row][col] = greenHolder[i];
				blueMatrix[row][col] = blueHolder[i];
				
				col++;
			}
			
			exportFileIdentity.write("RGB\n");
			exportFileIdentity.write(height + "\n");
			exportFileIdentity.write(width + "\n");
			
			exportFileEdge.write("RGB\n");
			exportFileEdge.write(height + "\n");
			exportFileEdge.write(width + "\n");
			
			exportFileBox.write("RGB\n");
			exportFileBox.write(height + "\n");
			exportFileBox.write(width + "\n");
			
			exportFileGauss.write("RGB\n");
			exportFileGauss.write(height + "\n");
			exportFileGauss.write(width + "\n");
			
			exportFileUnsharp.write("RGB\n");
			exportFileUnsharp.write(height + "\n");
			exportFileUnsharp.write(width + "\n");
			
			exportFileSharpen.write("RGB\n");
			exportFileSharpen.write(height + "\n");
			exportFileSharpen.write(width + "\n");
			
			//Trial run - only test for identity matrix
			List<int[][]> finishedIdentity = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getIdentity());
			List<int[][]> finishedEdge = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getEdge());
			List<int[][]> finishedSharp = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getSharpen());
			List<int[][]> finishedBox = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getBox());
			List<int[][]> finishedGauss = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getGaussian());
			List<int[][]> finishedUnsharp = getFilteredImage(redMatrix, greenMatrix, blueMatrix, getUnsharp());
			
			for (int r = 0; r < height; r++) {
				for (int c = 0; c < width; c++) {
					//exportFileIdentity.write( finishedIdentity.get(RED_PLACEMENT)[r][c] + " " + finishedIdentity.get(GREEN_PLACEMENT)[r][c] + " " + finishedIdentity.get(BLUE_PLACEMENT)[r][c] + "\n" );
					//exportFileEdge.write(finishedEdge.get(RED_PLACEMENT)[r][c] + " " + finishedEdge.get(GREEN_PLACEMENT)[r][c] + " " + finishedEdge.get(BLUE_PLACEMENT)[r][c] + "\n");
					//exportFileSharpen.write(finishedSharp.get(RED_PLACEMENT)[r][c] + " " + finishedSharp.get(GREEN_PLACEMENT)[r][c] + " " + finishedSharp.get(BLUE_PLACEMENT)[r][c] + "\n");
					//exportFileBox.write(finishedBox.get(RED_PLACEMENT)[r][c] + " " + finishedBox.get(GREEN_PLACEMENT)[r][c] + " " + finishedBox.get(BLUE_PLACEMENT)[r][c] + "\n");
					//exportFileGauss.write(finishedGauss.get(RED_PLACEMENT)[r][c] + " " + finishedGauss.get(GREEN_PLACEMENT)[r][c] + " " + finishedGauss.get(BLUE_PLACEMENT)[r][c] + "\n");
					exportFileUnsharp.write(finishedUnsharp.get(RED_PLACEMENT)[r][c] + " " + finishedUnsharp.get(GREEN_PLACEMENT)[r][c] + " " + finishedUnsharp.get(BLUE_PLACEMENT)[r][c] + "\n");
				}
			}
			
			// Finished - Close files and say done.
			exportFileIdentity.close(); 
			exportFileEdge.close();
			exportFileSharpen.close();
			exportFileBox.close();
			exportFileGauss.close();
			exportFileUnsharp.close();
			
			in.close();
			
			System.out.println("Finished writing file(s).");
		
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	/**
	 * Filter an RGB image with a kernel
	 * @param red
	 * @param green
	 * @param blue
	 * @param kernel
	 * @return
	 */
	public static List<int[][]> getFilteredImage(double[][] red, double[][] green, double[][] blue, double[][] kernel) {
		List<int[][]> finished = new ArrayList<>();
		int[][] finishedRed = new int[height][width];
		int[][] finishedGreen = new int[height][width];
		int[][] finishedBlue = new int[height][width];
		double valueRed = 0.0, valueBlue = 0.0, valueGreen = 0.0;
		
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				
				//Dynamic summation of kernel and image
				for (int kernelRow = 0; kernelRow < kernel.length; kernelRow++) {
					for (int kernelCol = 0; kernelCol < kernel.length; kernelCol++) {
						int offsetRow = row + kernelRow;
						int offsetCol = col + kernelCol;
						
						//Make sure offset does not go over matrix limits.
						offsetRow = offsetRow >= height ? row : offsetRow;
						offsetCol = offsetCol >= width ? col : offsetCol;
						
						for (int color = 0; color < 3; color++) {
							switch (color) {
							case RED_PLACEMENT: //RED
								valueRed += (kernel[kernelRow][kernelCol] * red[offsetRow][offsetCol]);
								break;
							case GREEN_PLACEMENT: //GREEN
								valueGreen += (kernel[kernelRow][kernelCol] * green[offsetRow][offsetCol]);
								break;
							case BLUE_PLACEMENT: //BLUE
								 valueBlue += (kernel[kernelRow][kernelCol] * blue[offsetRow][offsetCol]);
								break;
							}
						}
					}
				}
				System.out.println(valueRed / 256);
				//Fix colors if they are over the threshold.
				if (valueRed < 0) {
					valueRed = 0;
				}
				if (valueRed > 255) {
					valueRed = 255;
				}
				if (valueGreen < 0) {
					valueGreen = 0;
				}
				if (valueGreen > 255) {
					valueGreen = 255;
				}
				if (valueBlue < 0) {
					valueBlue = 0;
				}
				if (valueBlue > 255) {
					valueBlue = 255;
				}
				
				finishedBlue[row][col] = (int) Math.round(valueBlue);
				finishedGreen[row][col] = (int) Math.round(valueGreen);
				finishedRed[row][col] = (int) Math.round(valueRed);
				valueRed = 0;
				valueBlue = 0;
				valueGreen = 0;
				
			}
			
			finished.add(finishedRed);
			finished.add(finishedGreen);
			finished.add(finishedBlue);
		}
		
		
		return finished;
	}
	
	public static double[][] getIdentity() {
		double[][] matrix = new double[3][3];
		matrix[0][0] = 0;
		matrix[0][1] = 0;
		matrix[0][2] = 0;
		matrix[1][0] = 0;
		matrix[1][1] = 1;
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
		
		return matrix;
	}

	
}


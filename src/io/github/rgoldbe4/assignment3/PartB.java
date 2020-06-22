package io.github.rgoldbe4.assignment3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PartB {
	final static int RED_PLACEMENT = 0;
	final static int GREEN_PLACEMENT = 1;
	final static int BLUE_PLACEMENT = 2;
	static int height, width, totalSize, adjustedHeight, adjustedWidth;
	

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
			
			
			//Get all 
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getIdentity(), exportFileIdentity);
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getEdge(), exportFileEdge);
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getSharpen(), exportFileSharpen);
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getBox(), exportFileBox);
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getGaussian(), exportFileGauss);
			getFilteredImage(redMatrix, greenMatrix, blueMatrix, Kernel.getUnsharp(), exportFileUnsharp);
			
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
	public static void getFilteredImage(double[][] red, double[][] green, double[][] blue, double[][] kernel, FileWriter writer) {
		try {
			//Step 1: Update dimensions of image.
			int adjustedHeight = height - kernel.length + 1;
			int adjustedWidth = width - kernel.length + 1;
			
			System.out.println("*New* Height: " + height + " -> " + adjustedHeight);
			System.out.println("*New* Width: " + width + " -> " + adjustedWidth);
			System.out.println("Start of loops: (" + (kernel.length / 2)  + "," + (kernel.length / 2) + "); Expected: (1,1)");
			System.out.print("Bounds: ");
			for (int test = -(kernel.length / 2); test < (kernel.length / 2) + 1; test++) {
				System.out.print(test + ", ");
			}
			
			List<int[][]> finished = new ArrayList<>();
			int[][] finishedRed = new int[adjustedHeight][adjustedWidth];
			int[][] finishedGreen = new int[adjustedHeight][adjustedWidth];
			int[][] finishedBlue = new int[adjustedHeight][adjustedWidth];
			double valueRed = 0.0, valueBlue = 0.0, valueGreen = 0.0;
			System.out.println();
			System.out.println("#### Starting Image ####");
			System.out.println();
			
			int matrixRow = 0;
			int matrixCol = 0;
			//You want to start down one row, and to the right once.
			for (int row = kernel.length / 2; row < adjustedHeight; row++) {
				for (int col = kernel.length / 2; col < adjustedWidth; col++) {
					
					//Declare kernel dimensions that an array will allow
					int kernelRow = 0;
					int kernelCol = 0;
					
					for (int offsetRow = -(kernel.length / 2); offsetRow < kernel.length / 2 + 1; offsetRow++) {
						for (int offsetCol = -(kernel.length / 2); offsetCol < kernel.length / 2 + 1; offsetCol++) {
							kernelRow = kernelRow == kernel.length ? 0 : kernelRow;
							kernelCol = kernelCol == kernel.length ? 0 : kernelCol;
							int currentRow = offsetRow + row;
							int currentCol = offsetCol + col;
							
							valueRed += (kernel[kernelRow][kernelCol] * red[currentRow][currentCol]);
							valueGreen += (kernel[kernelRow][kernelCol] * green[currentRow][currentCol]);
							valueBlue += (kernel[kernelRow][kernelCol] * blue[currentRow][currentCol]);
							
							//Increment kernel.
							kernelCol++;
						}
						//Increment kernel
						kernelRow++;
					}
					
					matrixRow = matrixRow + (kernel.length / 2) == adjustedHeight ? 0 : matrixRow;
					matrixCol = matrixCol + (kernel.length / 2) == adjustedWidth ? 0 : matrixCol;
					int r = (int) Math.round(valueRed);
					int g = (int) Math.round(valueGreen);
					int b = (int) Math.round(valueBlue);
					
					r = r > 255 ? 255 : r;
					r = r < 0 ? 0 : r;
					g = g > 255 ? 255 : g;
					g = g < 0 ? 0 : g;
					b = b > 255 ? 255 : b;
					b = b < 0 ? 0 : b;
					
					finishedBlue[matrixRow][matrixCol] = b;
					finishedGreen[matrixRow][matrixCol] = g;
					finishedRed[matrixRow][matrixCol] = r;
					valueRed = 0;
					valueBlue = 0;
					valueGreen = 0;

					matrixCol++;
				}
				matrixRow++;
			}
			
			finished.add(finishedRed);
			finished.add(finishedGreen);
			finished.add(finishedBlue);
			
			writeToFile(writer, finished, adjustedHeight, adjustedWidth);
			
			System.out.println();
			System.out.println("#### Finished Image ####");
			System.out.println();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void writeToFile(FileWriter writer, List<int[][]> finished, int height, int width) {
		try {
			writeFileStart(writer, height, width);
			for (int r = 0; r < height - 1; r++) {
				for (int c = 0; c < width - 1; c++) {
					writer.write(finished.get(RED_PLACEMENT)[r][c] + " " + finished.get(GREEN_PLACEMENT)[r][c] + " " + finished.get(BLUE_PLACEMENT)[r][c] + "\n");
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void writeFileStart(FileWriter writer, int height, int width) {
		try {
			writer.write("RGB\n");
			writer.write(height - 1 + "\n");
			writer.write(width - 1 + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}


package Assignment_1;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;

//Sean Fite
//Programming Assignment 1
//CS 351
//This program reads in color codes from a text file and converts to hex using bitwise operations and 
//writes color codes to an output .png file to create an image
//Last Update 1/27/23

public class ImageDecoding {
	
	public static void main(String[] args) throws NumberFormatException, IOException 
	{  
		ArrayList<String> arr = new ArrayList<String>();					// empty array list for file input
		BufferedReader bufferedReader = new BufferedReader(new FileReader("/Users/seanfite/eclipse-workspace/CS_351/src/Assignment_1/image.dat"));
		String num = bufferedReader.readLine();								// read first line
        String [] parts = num.split(" ");									// parse string into width and height
        int[] nums = new int[2];	
        nums[0] = Integer.parseInt(parts[0]);
        nums[1] = Integer.parseInt(parts[1]);
        int width = nums[0];
        int height = nums[1];
		String outputFile = "/Users/seanfite/eclipse-workspace/CS_351/src/Assignment_1/output.png";
        BufferedImage canvas = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);  
        Scanner scanner = new Scanner(bufferedReader);						// scan bufferedImage input reader
		while(scanner.hasNext())
		{
			arr.add(scanner.next());										// add input to arrayList
		}
		bufferedReader.close();
		scanner.close();
		int x = 0;															// initialize trackers for width and height
		int y = 0;
		for(int i = 2; i < arr.size(); i++)									// loop through array
		{		
			String[] numbers = arr.get(i).split(",");						// parse each array value into red, blue, green
			int red = Integer.parseInt(numbers[0]);
			int blue = Integer.parseInt(numbers[1]);
			int green = Integer.parseInt(numbers[2]);
 			int rgb = (red << 16) | (green << 8) | blue;					// using bitwise ops to convert to hex
			canvas.setRGB(x, y, rgb);
			x++;
			if(x == width)													// logic for tracking height and width
			{
				x = 0;
				y++;
			}
		}
        ImageIO.write(canvas, "png", new File(outputFile));					// write to output file
	}
}

					
	

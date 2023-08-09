package Assignment_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

//Sean Fite
//Programming Assignment 1
//CS 351
//This program reads in binary code from a file and converts to various bases
//Last Update 1/27/23

public class MemoryManager {

	private ArrayList<String> arr = new ArrayList<String>();
	private int[] arr1 = new int[8];
	private int[] arr2 = new int[32];
	private int[] arr3 = new int[16];
	private int[] arr4 = new int[32];
	private boolean carry = true;
	private boolean isNeg = false;
	private int sum = 0;
		
	public MemoryManager(String string) 	/// defualt constructor, reads in file to array
	{			
		Scanner scanner = null;
		try {
			scanner = new Scanner(new File("/Users/seanfite/eclipse-workspace/CS_351/src/Assignment_1/memory.txt"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String num;
		while(scanner.hasNext())
		{
			num = scanner.nextLine();
			arr.add(num);
		}
	}
	
	public void twosCompliment(int a)					// two's compliment logic method
	{
		for(int i = 0; i < arr.get(a).length(); i++)  	// loop arr value 
		{
			if(arr.get(a).charAt(i) == '1')				// change 1's to 0's and 0's to 1's
			{
				arr1[i] = 0;							// and add these new numbers to a new array
			}
			else
			{
				arr1[i] = 1;
			}
		}
		for(int i = arr1.length - 1; i >= 0; i--)	// binary addition, add 1 to new array
		{
			if(arr1[i] == 1 && carry == true)		// logic for binary addition
			{
				arr1[i] = 0;
			}
			else if(arr1[i] == 1 && carry == false)
			{
				arr1[i] = 1;
			}
			else if(arr1[i] == 0 && carry == true)
			{
				arr1[i] = 1; 
				carry = false;
			}
			else
			{
				arr1[i] = 0;
			}
		}
		isNeg = true;								// track that this conversion has been done
	}
	
	public void convert_to_decimal(int a)				 // converts base 2 number to base 10
	{
		sum = 0;
		int count = 7;									 // working left to right, count is decremented
		if(isNeg == false)								 // if 2's compliment was not used ie leading num is 0
		{
			for(int i = 0; i < arr.get(a).length(); i++) // loop through arr num value
			{
				if(arr.get(a).charAt(i) == '1')			 // if value = 1, add power * 2 looped by count  
				{
					int power = 1;
					for(int j = 0; j < count; j++)
					{
						power *= 2;	
					}
					sum += power;						 // add to sum
					count--;							 // decrement count with each loop
				}
				else
				{
					count--;
				}
			}
		}
		else											// if 2's compliment was used
		{												// working with the new array instead of arr
			for(int i = 0; i < arr1.length; i++)		// same as above
			{
				if(arr1[i] == 1)						// if value = 1, add power * 2 looped by count
				{
					int power = 1;
					for(int j = 0; j < count; j++)
					{
						power *= 2;
					}
					sum += power;						// add to sum
					count--;							// decrement with each loop
				}			
				else
				{
					count--;
				}
			}
		}
		if(isNeg == true)								// formatting to show negative sign for neg numbers
		{
			sum = sum - sum * 2;
		}
	}
	
	public int readByte(int a)							// translates 8 bit array value into decimal
	{	
		if(arr.get(a).charAt(0) == '0')					// if unisgned or positive
		{
			convert_to_decimal(a);						// just convert to decimal
		}
		else
		{	
			twosCompliment(a);							// if signed and neg, 2's compliment
			convert_to_decimal(a);						// and then convert to decimal
		}	
		return sum;
	}
	
	public int readInt(int a)						// convert binary 4 byte value to decimal 
	{	
		int count = 31;
		int sum = 0;
		String word = "";
		for(int i = a; i < a + 4; i++)				// concatinate 4 byts into String word
		{
			word += arr.get(i);
		}
		if(word.charAt(0) == '0')					// if leading digit is 0 ie number is positive
		{
			for(int i = 0; i < word.length(); i++)  // binary to decimal conversion
			{
				if(word.charAt(i) == '1')			
				{
					int power = 1;
					for(int j = 0; j < count; j++)	// track sum of powers
					{
						power *= 2;
					}
					sum += power;					// add powers to sum
					count--;
				}
				else
				{
					count--;
				}
			}
		}
		else										// if leading digit is 1 ie negative number
		{
			for(int i = 0; i < word.length(); i++)  // complete 2's compliment
			{
				if(word.charAt(i) == '1')			// using new array flip 1s to 0s
				{
					arr2[i] = 0;
				}
				else
				{
					arr2[i] = 1;
				}
			}
			carry = true;
			for(int i = arr2.length - 1; i >= 0; i--)	// binary addition, add 1 to new array
			{
				if(arr2[i] == 1 && carry == true)		// logic for binary addition
				{
					arr2[i] = 0;
				}
				else if(arr2[i] == 1 && carry == false)
				{
					arr2[i] = 1;
				}
				else if(arr2[i] == 0 && carry == true)
				{
					arr2[i] = 1; 
					carry = false;
				}
				else
				{
					arr2[i] = 0;
				}
			}
			for(int i = 0; i < arr2.length; i++)		// track and sum powers of 2
			{
				if(arr2[i] == 1)
				{
					int power = 1;
					for(int j = 0; j < count; j++)
					{
						power *= 2;
					}
					sum += power;
					count--;
				}
				else
				{
					count--;
				}
			}
			isNeg = true;
		}
		if(isNeg == true)							// if leading digit is 1 apply neg sign to sum
		{
			sum = sum - sum * 2;
		}	
		return sum;
	}
	
	public char readChar(int a)						// translates binary value of arr to char
	{
		int count = 15;
		int sum = 0;
		String word = "";
		for(int i = a; i < a + 2; i++)				// concatinate 2 bytes into String word
		{
			word += arr.get(i);
		}
		if(word.charAt(0) == '0')					// if leading digit is 0 ie positive number
		{											// binary to decimal conversion
			for(int i = 0; i < word.length(); i++)
			{
				if(word.charAt(i) == '1')
				{
					int power = 1;
					for(int j = 0; j < count; j++)	// track count and power	
					{
						power *= 2;
					}
					sum += power;					// sum up powers 
					count--;
				}
				else
				{
					count--;
				}
			}
		}
		else										// if leading digit is 1 ie negative number
		{
			for(int i = 0; i < word.length(); i++)
			{
				if(word.charAt(i) == '1')			// create new array inverting each digit
				{
					arr3[i] = 0;					// 1s becomes 0s
				}
				else
				{
					arr3[i] = 1;					// 0s become 1s
				}
			}
			carry = true;
			for(int i = arr3.length - 1; i >= 0; i--)	// binary addition, add 1 to new array
			{
				if(arr3[i] == 1 && carry == true)		// logic for binary addition
				{
					arr3[i] = 0;
				}
				else if(arr3[i] == 1 && carry == false)
				{
					arr3[i] = 1;
				}
				else if(arr3[i] == 0 && carry == true)
				{
					arr3[i] = 1; 
					carry = false;
				}
				else
				{
					arr3[i] = 0;
				}
			}
			for(int i = 0; i < arr3.length; i++)		// binary to decimal conversion
			{
				int power = 1;
				if(arr3[i] == 1)
				{
					for(int j = 0; j < count; j++)		// add up power using count
					{
						power *= 2;
					}
					sum += power;						// sum the powers for result
					count--;
				}
				else
				{
					count--;
				}
			}	
		}												
		char asciiCharacter = (char) sum;				// convert decimal number to letter		
		return (char) asciiCharacter;
	}
	
	public float readFloat(int d)					    // translates IEEE 754 to floating point
	{
		String word = "";
		int k = 0;
		int count = 1;
		int exponent = 0;
		double float_sum = 0;
		double power = 1;
		double fraction = 1;
		double s = 0;
		double exponent_sum = 1;
		for(int i = d; i < d + 4; i++)					// concatinate 4 byts into String word
		{
			word += arr.get(i);
		}
		for(int i = word.length() - 1; i >= 0; i--)	    // reverse order of word and put into arr4
		{
			if(word.charAt(i) == '0')
			{
				arr4[k] = 0;
			}
			else
			{
				arr4[k] = 1;
			}
			k++;			
		}
		if(arr4[0] == 0)								// find sign value
		{
			s = 1;
		}
		else
		{
			s = -1;
		}
		for(int i = 9; i < arr4.length; i++)   		    // calc fraction + 1
		{
			if(arr4[i] == 1)
			{
				for(int j = 0; j < count; j++)			// find power ^-n
				{
					power /= 2;
				}
				fraction += power;
				count++;
			}
			else
			{
				count++;
			}
		}
		count = 7;
		for(int i = 1; i < arr4.length / 4 + 1; i++)    // calc exponent
		{
			power = 1;
			if(arr4[i] == 1)
			{
				for(int j = 0; j < count; j++)		    // find power ^n
				{
					power *= 2;
				}
				count--;
				exponent += power;
			}
			else
			{
				count--;
			}
		}
		exponent -= 127;								// subtract bias from exponent
		if(exponent > 0)
		{
			for(int i = 0; i < exponent; i++)			// if result positive
			{
				exponent_sum *= 2;  					// find power ^n
			}
		}
		else
		{
			for(int i = 0; i < Math.abs(exponent); i++)	// if resuilt is negative
			{
				exponent_sum = exponent_sum * 1/2f;     // find power ^-n
			}
		}
		float_sum = s * fraction * exponent_sum;        // plug into conversion formula 
		return (float) float_sum;			
	}	
}

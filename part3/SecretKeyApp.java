package Assignment_1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//Sean Fite
//Programming Assignment 1
//CS 351
//This program reads in an encrypted message from a txt file and decrypts to an output file to produce a poem
//Last Update 1/27/23

public class SecretKeyApp {

	public static void main(String[] args) throws IOException
	{																	// read in input file   
        Scanner scanner = new Scanner(new File("/Users/seanfite/eclipse-workspace/CS_351/src/Assignment_1/secret.txt"));
        String characters = "";
        while(scanner.hasNext())										// assing input to characters string
        {
        	characters += scanner.next();
        }
        char[] arrChar = characters.toCharArray();						// pass string to char array
        String decryptedMessage = decrypt(arrChar, 0x1668AC1F);			// call method using secrect key and array params
        File path = new File("/Users/seanfite/eclipse-workspace/CS_351/src/Assignment_1/message.txt");
        FileWriter wr = new FileWriter(path);
        wr.write(decryptedMessage);										// write decrypted message
        wr.flush();
        wr.close();
	}

	public static String decrypt(char[] arrChar, int secretKey) 			// method to pass in input file and secret key
	{
		char ch;
		String poem = "";
		for(int i = 0; i < arrChar.length; i++)								// loop through char array
	    {
			ch = (char)(arrChar[i] ^ 0x1668AC1F);							// using secrey key
	        poem += ch;														// add chars to poem string for decrypted output
	    }
		return poem;
    }	
}

package Assignment_1;

import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MemoryManagerTest {
	
	private static MemoryManager memoryManager;
	
	private void assertEquals(float expected, float actual) 			// testing method
	{
		if(expected == actual)
		{
			System.out.println("Correct");
		}
		else
		{
			System.out.println("Incorrect");
		}
	}
	
	@BeforeAll
	static void init() 
	{
		memoryManager = new MemoryManager("memory.txt");	
	}

	@Test
	void testReadByte() 
	{
		int expected = -104;
		int actual = memoryManager.readByte(1);
		assertEquals(expected, actual);
	}

	@Test
	void testReadInt() 
	{
		int expected = -1744779368;
		int actual = memoryManager.readInt(1);
		assertEquals(expected, actual);
	}
	
	@Test
	void testReadChar() 
	{
		char expected = 'A';
		char actual = memoryManager.readChar(11);
		assertEquals(expected, actual);
	}
	
	@Test
	void testReadFloat() 
	{
		float expected = 0.15625f;
		float actual = memoryManager.readFloat(28);
		assertEquals(expected, actual);
	}
}

/**
 * 
 */
package edu.ics211.h04;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author kobeyarai
 * Test class for the SymbolTable. Uses Jupiter JUnit testing. 
 */
class SymbolTableTest {
	private SymbolTable testTable;
	private SymbolTable testTable2;
	/**
	 * @throws java.lang.Exception
	 */
	
	//Created 2 SymbolTable Objects and added a few things. 
	@BeforeEach
	void setUp() throws Exception {
		testTable = new SymbolTable();
		testTable2 = new SymbolTable();
		
		testTable.add("Hello");
		testTable.add("World");
		testTable.add("Kobey");
		testTable.add("ICS211");
		//Debug
		//System.out.println(testTable);
		
		testTable2.add("University");
		testTable2.add("of");
		testTable2.add("Hawaii");
		testTable2.add("at");
		testTable2.add("Manoa");
		//Debug
		//System.out.println(testTable2);
	}
	
	private void sizeTest() {
		//Checked to see if the size of the array matched what it should be. 
		//Debug
		//System.out.println(testTable);
		//System.out.println(testTable2);
		//System.out.println("Table Size: "+testTable.size());
		//System.out.println("Table Size: "+testTable2.size());
		assert(testTable.size()==4);
		assert(testTable2.size()==5);
	}
	
	private void getTest() {
		//Gets the element at parameter and checks if it is what it should be. 
		//Debug
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable.get(0).compareToIgnoreCase("Hello")==0);
		assert(testTable.get(0).compareToIgnoreCase("Kobey")!=0);
		assert(testTable2.get(1).compareToIgnoreCase("Hawaii")==0);
		assert(testTable2.get(1).compareToIgnoreCase("Manoa")!=0);
	}
	
	private void addTest() {
		//Adds a few things to the SymbolTable, I purposely added
		//Some items that already existed in the SymbolTable to test the returns. 
		assert(testTable.add("Hawaii"));
		assert(!testTable.add("world"));
		assert(!testTable2.add("hawaii"));
		assert(testTable2.add("World"));
		
		//Debug
		//System.out.println(testTable);
		//System.out.println(testTable2);
	}
	
	private void removeTest() {
		//Removed a few things in SymbolTable, I purposely removed some 
		//items that would not be in the SymbolTable. 
		assert(testTable.remove("Hawaii"));
		assert(!testTable.remove("notInArray"));
		assert(testTable2.remove("World"));
		assert(!testTable2.remove("notInArray"));
		System.out.println(testTable);
		System.out.println(testTable2);
	}
	
	private void indexOfTest() {
		//Checked to see if the String value was at the proper index. 
		//Debug
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable2.indexOf("Hawaii")==1);
		assert(testTable.indexOf("Hello")==0);
		assert(testTable2.indexOf("notInArray")==-1);
		assert(testTable.indexOf("notInArray")==-1);
	}
	
	private void toStringTest() {
		//Tested to see if the proper toString was working. 
		//Debug
		//System.out.println(testTable.toString());
		//System.out.println(testTable2.toString());
		assert(testTable.toString().compareToIgnoreCase("should fail")!=0);
		assert(testTable.toString().compareToIgnoreCase("Hello ICS211 Kobey World")==0);
		assert(testTable2.toString().compareToIgnoreCase("should fail")!=0);
		assert(testTable2.toString().compareToIgnoreCase("at Hawaii Manoa of University")==0);
	}

	@Test
	void test() {
		//Run all test that were made when JUnit is run. 
		sizeTest();
		getTest();
		addTest();
		removeTest();
		indexOfTest();
		toStringTest();
	}

}

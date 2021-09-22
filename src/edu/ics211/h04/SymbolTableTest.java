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
 *
 */
class SymbolTableTest {
	private SymbolTable testTable;
	private SymbolTable testTable2;
	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		testTable = new SymbolTable();
		testTable2 = new SymbolTable();
		
		testTable.add("Hello");
		testTable.add("World");
		testTable.add("Kobey");
		testTable.add("ICS211");
		//System.out.println(testTable);
		
		testTable2.add("University");
		testTable2.add("of");
		testTable2.add("Hawaii");
		testTable2.add("at");
		testTable2.add("Manoa");
		//System.out.println(testTable2);
	}
	
	private void sizeTest() {
		//System.out.println(testTable);
		//System.out.println(testTable2);
		//System.out.println("Table Size: "+testTable.size());
		//System.out.println("Table Size: "+testTable2.size());
		assert(testTable.size()==4);
		assert(testTable2.size()==7);
	}
	
	private void getTest() {
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable.get(0).compareToIgnoreCase("Hello")==0);
		assert(testTable.get(0).compareToIgnoreCase("Kobey")!=0);
		assert(testTable2.get(1).compareToIgnoreCase("Hawaii")==0);
		assert(testTable2.get(1).compareToIgnoreCase("Manoa")!=0);
	}
	
	private void addTest() {
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable.add("Hawaii"));
		assert(!testTable.add("World"));
		assert(!testTable2.add("Hawaii"));
		assert(testTable2.add("World"));
	}
	
	private void removeTest() {
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable.remove("Hawaii"));
		assert(!testTable.remove("notInArray"));
		assert(testTable2.remove("World"));
		assert(!testTable2.remove("notInArray"));
	}
	
	private void indexOfTest() {
		//System.out.println(testTable);
		//System.out.println(testTable2);
		assert(testTable2.indexOf("Hawaii")==1);
		assert(testTable.indexOf("Hello")==0);
		assert(testTable2.indexOf("notInArray")==-1);
		assert(testTable.indexOf("notInArray")==-1);
	}
	
	private void toStringTest() {
		assert(testTable.toString().compareToIgnoreCase("should fail")!=0);
		assert(testTable.toString().compareToIgnoreCase("Hello ICS211 Kobey World   ")==0);
		assert(testTable2.toString().compareToIgnoreCase("should fail")!=0);
		assert(testTable2.toString().compareToIgnoreCase("at Hawaii Manoa of University  ")==0);
	}

	@Test
	void test() {
		sizeTest();
		getTest();
		addTest();
		removeTest();
		indexOfTest();
		toStringTest();
	}

}

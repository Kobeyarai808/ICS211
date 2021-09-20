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
		
		testTable2.add("University");
		testTable2.add("of");
		testTable2.add("Hawaii");
		testTable2.add("at");
		testTable2.add("Manoa");
	}
	
	private void sizeTest() {
		assert(testTable.size()==4);
		assert(testTable2.size()==7);
	}
	
	private void getTest() {
		assert(testTable.get(0).equals("Hello"));
		assert(!testTable.get(0).equals("Kobey"));
		assert(testTable2.get(1).equals("Hawaii"));
		assert(!testTable2.get(1).equals("Manoa"));
	}
	
	private void addTest() {
		assert(testTable.add("Hawaii"));
		assert(!testTable.add("World"));
		assert(!testTable2.add("Hawaii"));
		assert(testTable2.add("World"));
	}
	
	private void removeTest() {
		
	}
	
	private void indexOfTest() {
	
	}
	
	private void toStringTest() {
	
	}

	@Test
	void test() {
		sizeTest();
		getTest();
		addTest();
		removeTest();
		indexOfTest();
		toStringTest();
		//fail("Not yet implemented");
	}

}

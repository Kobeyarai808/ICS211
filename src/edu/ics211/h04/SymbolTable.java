package edu.ics211.h04;

import java.util.ArrayList;
import java.util.Arrays;

public class SymbolTable implements SymbolTableInterface{
	//Main is used only for testing before Unit Test was made. 
	public static void main(String[] args) {
		SymbolTable testing = new SymbolTable();
		testing.add("alpha");
		testing.add("charlie");
		testing.add("delta");
		testing.add("bravo");
		testing.add("foxtrot");
		testing.add("echo");
		testing.add("d");
		testing.add("a");
		testing.add("c");
		testing.add("b");
		//Debug
		//System.out.println(testing);
	}
	
	private String[] arr = new String[1];
	private int size;
	
	//Constructor creates a new instance of str that has a length of 1. 
	public SymbolTable() {
		arr[0] = "";
	}
	
	//Returns the size of the SymbolTable. 
	@Override
	public int size() {
		return size;
	}

	//Returns the element at index i. 
	@Override
	public String get(int i) {
		return arr[i];
	}

	/*Adds a element to the SymbolTable if it is not already in the SymbolTable. 
	 * If it is not in the SymbolTable, the method returns false, if it is in the 
	 * SymbolTable, it will sort the SymbolTable and return true. 
	 * Run time best: O(1)
	 * 		If size is 0, the String value will be assigned to arr[0] and return true. 
	 * Run time average: O(n)
	 * 		If adding something to the middle of an SymbolTable, it will exit midway through or 
	 * 		the for loop. This will run n/2 times. Therefore O(n) time. 
	 * Run time worst: O(n)
	 * 		If something is added to the front of the SymbolTable, it will loop through the entire
	 * 		for loop. This will run n times. Therefore O(n)
	 */
	@Override
	public boolean add(String value) {
		//Debug
		//System.out.println("\nsize: "+size);
		
		//Adds space in the SymbolTable if it is full. 
		if(size == arr.length) {
			arr = Arrays.copyOf(arr, arr.length+3);
			for(int i=size;i<arr.length;i++) {
				arr[i]="";
			}
		}
		//Debug
		//System.out.println("value: "+value);
		
		//If the SymbolTable is empty, add value to the SymbolTable, 
		//Also increments size. 
		if(size==0) {
			arr[0]=value;
			size++;
			return true;
		}
		
		//Iterates through the entire SymbolTable from highest index to the smallest. 
		//Inserts the value where it belongs and shifts all values to the right. 
		for(int i=size;i>=0;i--) {
			if(i==0) {
				arr[i]=value;
				size++;
				return true;
			}
			//If value is already in the SymbolTable, return false. 
			else if(arr[i].compareToIgnoreCase(value)==0) {
				return false;
			}
			//If currently on the final index (0), run this code to avoid Index out of bounds. 
			else if(value.compareToIgnoreCase(arr[i-1])>0) {
				arr[i] = value;
				size++;
				//Debug
				//for(int k=0; k<size();k++) {
					//System.out.print(" "+arr[k]);
				//}
				//System.out.print("\n");
				return true;
			}
			//Shift values out of the way. 
			else {
				arr[i]=arr[i-1];
			}
		}
		//Debug
		//System.out.println("Do not touch");
		return false;
	}
	
	/*Removes the String value from the SymbolTable, moves all indexes up by 1 to 
	 * Close the gap in the middle of the SymbolTable. Returns true if String value
	 * was successfully removed. Returns false if String value was not removed. 
	 * Run time best: O(1)
	 * 		If size is 0, it will not enter any loops and return false.
	 * Run time average: O(n)
	 * 		Although there are 2 loops, the second one picks up where the first one
	 * 		stopped. After the inner loop runs, it returns true. 
	 * Run time worst: O(n)
	 * 		Although there are 2 loops, the second one picks up where the first one
	 * 		stopped. After the inner loop runs, it returns true. 
	 */
	@Override
	public boolean remove(String value) {
		//Iterates through SymbolTable to find String value to remove. 
		for(int i=0;i<size;i++) {
			//Checks to see if value is in the SymbolTable. 
			if(arr[i].compareToIgnoreCase(value)==0){
				//Shifts all elements up to close the empty gap. 
				for(int j=i;j<size-1;j++) {
					arr[j]=arr[j+1];
				}
				//Sets the last element to blank string, decrements size, and returns
				//true. 
				arr[size-1]="";
				size--;
				return true;
			}
		}
		//Returns false if value was not found in the SymbolTable. 
		return false;
	}

	//Returns the index of the provided String value. 
	@Override
	public int indexOf(String value) {
		//Finds the String value in the SymbolTable and returns its index. 
		for(int i=0;i<size;i++) {
			if(arr[i].compareToIgnoreCase(value)==0){
				return i;
			}
		}
		//Returns -1 if String value was not found in the SymbolTable. 
		return -1;
	}
	
	//Returns a nicely formatted String of the SymbolTable. 
	@Override
	public String toString() {
		//Checks the edge case with size of 0. 
		if(size==0) {
			return "";
		}
		//Loops through the entire SymbolTable and returns the formatted toString. 
		String result = arr[0];
		for(int i=1;i<size;i++) {
			result+= " " + arr[i];
		}
		return result;
	}
}

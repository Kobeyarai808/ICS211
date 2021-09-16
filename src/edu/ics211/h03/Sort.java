/**
 *
 */
package edu.ics211.h03;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Kobey Arai
 *
 */
public class Sort<E> {

	int numberOfSwaps;
	int numberOfComparisons;

	/**
	 * initialize the statistics
	 */
	public Sort() {
		initStats();
	}
	
	// Resets the stats so the counter can restart for each sort instance
	private void initStats() {
		numberOfSwaps = 0;
		numberOfComparisons = 0;
	}

    // get method for the number of comparisons
	public int numComparisons() {
		return numberOfComparisons;
	}

    // get method for the number of swaps
	public int numSwaps() {
		return numberOfSwaps;
	}

	// swaps two adjacent indexes given the array of data and the index to swap
	private void swapAdjacent(E[] data, int index)
			throws java.lang.ArrayIndexOutOfBoundsException {
		numberOfSwaps++;
		E swap = data[index];
		data[index] = data[index + 1];
		data[index + 1] = swap;
	}
	
	//Bubble sorting algorithm
	public void bubbleSort(E[] data, Comparator<E> comparator) {
		//init vars
		initStats();
		int len = data.length;
		int j = 0;
		boolean didReorder=false, isReorder=false;
		
		//outer loop is do-while because it needs to run at least once
		do {
			//inner loop, subtract length and j from i because why check ending of array if its already sorted
			for(int i=0; i<len-j-1; i++) {
				//sets a boolean equal to if reorder() swapped two elements
				isReorder = reorder(data, comparator, i);
				//if reorder swapped 2 elements, set didReorder to true, allowing exit of the algorithm
				if(isReorder) {
					didReorder = true;
				}
				//System.out.println(i); //debug
			}
			//break out of algorithm when array is sorted (nothing was reordered last iteration)
			if(!didReorder) {
				break;
			}
			j++;
		}while(j<len);
	}

	//insertion sort algorithm
	public void insertionSort(E[] data, Comparator<E> comparator) {
		//init vars
		initStats();
		boolean didReorder = false, foundPosition = false;
		//outer loop
		for(int i=0; i<data.length-1; i++) {
			//System.out.println("Outer loop"); //debug
			
			//inner loop
			for(int j=i; j>0;j--) {
				//System.out.println("Inner loop"); //debug
				//sets a boolean equal to if reorder() swapped two elements, minus 1 to swap backwards and not forward
				didReorder = reorder(data, comparator, j-1);
				//if reorder swapped 2 elements, set foundPosition to true allowing exit of algorithm on next run
				if(didReorder) {
					foundPosition = true;
				}
				else if(foundPosition) {
					break;
				}
				//System.out.println(j); //debug
			}
		}
	}
	public void selectionSort(E[] data, Comparator<E> comparator) {
		initStats();
		int minimum;
		//Outer loop
		for(int i=0; i<data.length-1; i++) {
			//System.out.println("Outer Loop"); //debug
			//assigns the first index in array to minimum
			minimum = i;
			//Inner loop
			for(int j=i; j<data.length;j++) {
				//System.out.println("Inner Loop"); //debug
				//assigns the smaller number of the two to minimum
				minimum = minIndex(data, comparator, minimum, j); 
			}
			//after looping through to find smallest value being compared, swap it for the proper index
			swapDistant(data, i, minimum);
		}
	}
	
	//Checks to see if swapAdjacent() should be called
	public boolean reorder(E[] data, Comparator<E> comparator, int index) {
		//Increment numberOfComparisons
		numberOfComparisons++;
		//System.out.println(data[index]+" "+data[index+1]); //debug
		//determine if the indexes are already in order, then if not in order, swaps it using swapAdjacent()
		//Returns true if swap was done
		if(comparator.compare(data[index],data[index+1])>0) {
				swapAdjacent(data, index);
				return true;
		}
		return false;
	}
	//Swaps the two indexes in the data array
	public void swapDistant(E[] data, int index1, int index2) {
		//increment numberOfSwaps
		numberOfSwaps++;
		//Swaps the two indexes
		E temp = data[index1];
		data[index1]=data[index2];
		data[index2]=temp;
	}
	//Returns the smaller index of the two index inputs
	public int minIndex(E[] data, Comparator<E> comparator,int index1, int index2) {
		//Increment numberOfComparisions
		numberOfComparisons++;
		//compares the two and if smaller return index1, else return 
		if(comparator.compare(data[index1], data[index2])<0) {
			return index1;
		}
		return index2;
	}
}
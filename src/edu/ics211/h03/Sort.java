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

	private void swapAdjacent(E[] data, int index)
			throws java.lang.ArrayIndexOutOfBoundsException {
		numberOfSwaps++;
		E swap = data[index];
		data[index] = data[index + 1];
		data[index + 1] = swap;
	}

	public void bubbleSort(E[] data, Comparator<E> comparator) {
		initStats();
		int len = data.length;
		int j = 0;
		boolean didReorder=false, isReorder=false;
		do {
			for(int i=0; i<len-j-1; i++) {
				isReorder = reorder(data, comparator, i);
				if(isReorder) {
					didReorder = true;
				}
				//System.out.println(i);
			}
			if(!didReorder) {
				break;
			}
			j++;
		}while(j<len);
	}

	public void insertionSort(E[] data, Comparator<E> comparator) {
		initStats();
		boolean didReorder = false, foundPosition = false;
		for(int i=0; i<data.length-1; i++) {
			//System.out.println("Outer loop");
			for(int j=i; j>0;j--) {
				//System.out.println("Inner loop");
				didReorder = reorder(data, comparator, j-1);
				if(didReorder) {
					foundPosition = true;
				}
				else if(foundPosition) {
					break;
				}
				//System.out.println(j);
			}
		}
	}
	public void selectionSort(E[] data, Comparator<E> comparator) {
		initStats();
		int minimum;
		for(int i=0; i<data.length-1; i++) {
			//System.out.println("Outer Loop");
			minimum = i;
			for(int j=i; j<data.length;j++) {
				//System.out.println("Inner Loop");
				minimum = minIndex(data, comparator, minimum, j); 
			}
			swapDistant(data, i, minimum);
		}
	}
	public boolean reorder(E[] data, Comparator<E> comparator, int index) {
		numberOfComparisons++;
		//System.out.println(data[index]+" "+data[index+1]);
		if(comparator.compare(data[index],data[index+1])>0) {
				swapAdjacent(data, index);
				return true;
		}
		return false;
	}
	public void swapDistant(E[] data, int index1, int index2) {
		numberOfSwaps++;
		E temp = data[index1];
		data[index1]=data[index2];
		data[index2]=temp;
	}
	public int minIndex(E[] data, Comparator<E> comparator,int index1, int index2) {
		numberOfComparisons++;
		if(comparator.compare(data[index1], data[index2])<0) {
			return index1;
		}
		return index2;
	}
}
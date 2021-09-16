/**
 * 
 */
package edu.ics211.h03;

/**
 * @author Kobey Arai
 *
 */
public class IntegerComparator implements java.util.Comparator<Integer> {
	
	public int compare(Integer int1, Integer int2) {
		return int1.compareTo(int2);
	}

	/**
	 * basic test for functionality
	 * @param args (ignored)
	 */
	public static void main(String[] args) {
		IntegerComparator c = new IntegerComparator();
		System.out.println("1 compared to 2 returns " + c.compare(1, 2));
		System.out.println("2 compared to 1 returns " + c.compare(2, 1));		
		System.out.println("2 compared to 2 returns " + c.compare(2, 2));		
	}

}
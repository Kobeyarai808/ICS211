package edu.ics211.h99;

//Reversible driver code. Count should be 120 after everything works
public class ReversibleDriver {
	public static void main(String[] args) {
		int maxValue = 1000;
		int count = 0;
		
		//Find and print the first reversible numbers under 1000
		for (int i = 1; i < maxValue; i++) {
			try {
				Reversible reverse = new Reversible(i);
				count += 1;
			}catch(InvalidConstructorException e) {}
		}
		
		System.out.println("Found " + count + " numbers that meet the criteria");
	}
}

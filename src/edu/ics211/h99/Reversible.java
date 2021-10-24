package edu.ics211.h99;



/* Author: Cristian
 * Class: ICS 211
 * Description: This is the definition of a reversible object. The premise of the problem is as follows:
 * 
 * Some positive integers n have the property that the sum [ n + reverse(n) ] consists entirely of odd
 * (decimal) digits. For instance, 36 + 63 = 99 and 409 + 904 = 1313. We will call such numbers reversible;
 * so 36, 63, 409, and 904 are reversible. Leading zeroes are not allowed in either n or reverse(n).
 * Leading zeroes in this context basically refer to numbers that end with 0's.
 * Source: Premise from Project Euler Project, but edited
 * 
 */

public class Reversible {
	int n;
	int n_reverse;
	int sum;
	
	//Constructor
	public Reversible(int input) throws InvalidConstructorException {
		
		//Helpful variables
		int tempInt = input;
		boolean done = false;
		String temp = "";
		n = input;
		
		//Evaluate and store n_reverse
		while(!done) {
			
			//These next two lines work, but review what they are doing
			temp = temp + (tempInt%10);
			tempInt = tempInt/10;
			
			if (tempInt == 0) {
				done = true;
			}
		}
		//System.out.println(temp);
		n_reverse = Integer.parseInt(temp);
		//System.out.println(n_reverse);
		sum = n + n_reverse;
		
		//Verify that result is all odds. Throw exception otherwise
		//System.out.println(sum);
		if (verifyOdds(sum)&&(input%10!=0)) {
			System.out.println("The input " + input + " succeeded");
		}
		else {
			throw new InvalidConstructorException("The input " + input + " fails the constructor");
		}
	}
	
	//Helper function, verify that all digits are odds, return true or false depending on the result
	private boolean verifyOdds(int input) {
		boolean done = false;
		
		//Remove numbers divisible by 10, as their reverse would have a leading 0
		if (input%10 == 0) {
			return false;
		}
		
		//This checks one digit at a time, and verifies that each digit is odd. Please
		// review the % operator to understand what this block of code does
		while (!done) {
			//Is the next digit odd?
			if ((input%10)%2 == 1) {
				input = input/10;
			}
			
			else {
				return false;
			}
			
			//Break out of while loop when you're done checking digits
			if (input == 0) {
				done = true;
			}
		}
		
		return true;
	}
	
	
	//Main method, just used for testing. No debugging needed here
	public static void main(String[] args) {
		
		//int n = 41;
		//int n = 203;
		int n = 317;
		//int n = 908;
		
		try {
			Reversible reverse1 = new Reversible(n);
		}
		
		catch (InvalidConstructorException e) {
			System.out.println("Caught exception with input: " + n);
		}
		
	}
}


/*
	The purpose of this assignment is for you to essentially fix this code. Your task, however, is to
	explain to me your debugging process. In fact, I am more concerned about your debugging process than
	you actually fixing the code. Due to the nature of this assignment, we will not be offering any specific
	help debugging the code. However, we (the various assistants) can offer you general debugging advice.
	
	I intentionally left bad comments on this code as well, so I suggest you go through the code and try to
	add more meaningful comments.
*/


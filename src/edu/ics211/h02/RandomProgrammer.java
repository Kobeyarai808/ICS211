package edu.ics211.h02;

import java.util.Random;
/*
 * Working: RandomProgrammer is a method that 
 * generates randomly generated code. 
 * 
 * */
public class RandomProgrammer implements Programmer{
	//Variable declarations
	private final static String[] variables = { "a", "b", "c", "i", "n", "x", "y", "z", "w", "count" };
	private final static String[] methods = { "makeManoaYours", "anAppleADayKeepsTheDocAway", "pressF", "drinkCoffee", "eatCracker", "setHouse", "huntDeer", "sprint", "hop", "skip" };
	private static int maximumDepth = 5;
	Random rand = new Random();
	
	//Empty constructor because nothing needs to done
	//When objects get created. 
	public RandomProgrammer() {
		// TODO Auto-generated constructor stub
	}
	
	//main method, calls the required functions to generate
	//random code. 
	public static void main(String[] args) {
		RandomProgrammer test = new RandomProgrammer();
		for(int i=0;i<10;i++) {
			System.out.println(test.makeStatement(maximumDepth));
		}
	}
	@Override
	//generates real code that sets a random number 
	//to a random expression. 
	public Assignment makeAssignment(int maxDepth) {
		if(maxDepth<=0) {
			try {
				return new Assignment("x=0;\n");
			} catch (InvalidStatementException e) {
				System.exit(0);
			}
		}
		String varChoosen = variables[rand.nextInt(10)];
		try {
			return new Assignment(varChoosen+"="+randomExpression()+";\n");
		} catch (InvalidStatementException e) {
			System.exit(0);
			return null;
		}
	}
	//generates real code that calls a random made up method.  
	@Override
	public MethodCall makeMethodCall(int maxDepth) {
		if(maxDepth<=0) {
			try {
				return new MethodCall("theLastCall();\n");
			} catch (InvalidStatementException e) {
				System.exit(0);
			}
		}
		String methdChoosen = methods[rand.nextInt(10)];
		int amtParams = rand.nextInt(4);
		String methodCall = methdChoosen+"(";
		for(int i=0; i<amtParams-1;i++) {
			methodCall+=randomExpression()+",";
		}
		if(amtParams>0) {
			methodCall+=randomExpression()+");\n";
		}
		else {
			methodCall+=");\n";
		}
		try {
			return new MethodCall(methodCall);
		} catch (InvalidStatementException e) {
			System.exit(0);
		}
		return null;
	}

	@Override
	//generates real code that initializes a
	//while loop. 
	public WhileLoop makeWhileLoop(int maxDepth) {
		if(maxDepth<=1) {
			try {
				return new WhileLoop("while(end=true) {\n    System.exit(0);\n}");
			} catch (InvalidStatementException e) {
				System.exit(0);
			}
		}
		int numTimesLoop = rand.nextInt(10);
		String whileLoopCode = "while("+variables[rand.nextInt(10)]+">"+numTimesLoop+") {\n";
		for(int i=0; i<rand.nextInt(9)+1; i++) {
			whileLoopCode+="    "+makeStatement(maxDepth-1);
		}
		whileLoopCode+="}\n";
		try {
			return new WhileLoop(whileLoopCode);
		} catch (InvalidStatementException e) {
			System.exit(0);
			return null;
		}
	}

	@Override
	//generates real code that creates a conditional
	//statement and a random number of else ifs along
	//with an occasional else statement. 
	public Conditional makeConditional(int maxDepth) {
		if(maxDepth<=0) {
			try {
				return new Conditional("if(end=true) {\n    System.exit(0);\n}");
			} catch (InvalidStatementException e) {
				System.exit(0);
			}
		}
		int conditionNum = rand.nextInt(10);
		String conditionalCode = "if("+variables[rand.nextInt(10)]+">"+conditionNum+") {\n    "+makeStatement(maxDepth-1) + "}\n";
		int amtConditions = rand.nextInt(3);
		for(int i=0; i<amtConditions; i++) {
			conditionalCode+="else if("+variables[rand.nextInt(10)]+">"+conditionNum+") {\n    " +makeStatement(maxDepth-1) + "}\n";
		}
		if(rand.nextInt(1)==1) {
			conditionalCode+="else("+variables[rand.nextInt(10)]+">"+conditionNum+") {\n    " +makeStatement(maxDepth-1) + "}\n";
		}
		try {
			return new Conditional(conditionalCode);
		} catch (InvalidStatementException e) {
			System.exit(0);
			return null;
		}
	}

	@Override
	//Generates random code from one of the other methods. 
	public Statement makeStatement(int maxDepth) {
		if(maxDepth<=0) {
			try {
				return new Assignment("end=0;\n");
			} catch (InvalidStatementException e) {
				System.exit(0);
			}
		}
		int selector = rand.nextInt(4);  
		switch(selector) {
			case 0:
				return makeMethodCall(maxDepth);
			case 1: 
				return makeWhileLoop(maxDepth);
			case 2: 
				return makeConditional(maxDepth);
			default: 
				return makeAssignment(maxDepth);
		}
	}
	
	//Helper method to return a completely random expression. 
	//Either a number, a variable, or method. 
	private String randomExpression() {
		int choice = rand.nextInt(20);
		switch(choice){
		case 0:
			return variables[0];
		case 1: 
			return variables[1];
		case 2: 
			return variables[2];
		case 3: 
			return variables[3];
		case 4:
			return variables[4];
		case 5:
			return variables[5];
		case 6: 
			return variables[6];
		case 7: 
			return variables[7];
		case 8:
			return variables[8];
		case 9: 
			return variables[9];
		case 10: 
			return methods[0];
		case 11:
			return methods[1];
		case 12:
			return methods[2];
		case 13:
			return methods[3];
		case 14: 
			return methods[4];
		case 15: 
			return methods[5];
		case 16: 
			return methods[6];
		case 17: 
			return methods[7];
		case 18:
			return methods[8];
		case 19:
			return methods[9];
		default:
			return String.valueOf(rand.nextInt());
		}
	}

}

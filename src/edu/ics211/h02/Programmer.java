package edu.ics211.h02;

/*
 * Working: This is a Programmer Class interface. This
 * is a blueprint of the RandomProgrammer class. 
 * */
public interface Programmer {
	  Assignment makeAssignment(int maxDepth);   // create an assignment statement
	  MethodCall makeMethodCall(int maxDepth);   // create a conditional statement
	  WhileLoop makeWhileLoop(int maxDepth);     // create a while loop
	  Conditional makeConditional(int maxDepth); // create a conditional statement
	  Statement makeStatement(int maxDepth);     // create a statement of one of the above types
	}

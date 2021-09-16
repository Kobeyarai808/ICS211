package edu.ics211.h02;
/*
 * Working: This assignment method checks to see if the 
 * input text is an assignment. If it is not an assignment, 
 * Not an assignment will print. getVariable is a method 
 * that gets the variable being assigned. 
 * */
public class Assignment extends BasicStatement{
	private String[] tokens;
    public Assignment(String text) throws InvalidStatementException{
        super(text);
        tokens = textTokens();
        if(!(Character.isJavaIdentifierStart(this.text.charAt(0)) && tokens[1].equals("="))){
            System.out.println("Not an assignment");
        }

    }
    public String getVariable(){
    	return tokens[0];
    }
}

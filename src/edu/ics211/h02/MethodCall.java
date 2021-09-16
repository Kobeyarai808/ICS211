package edu.ics211.h02;

/*
 * Working: MethodCall class checks to see if the string input is a valid MethodCall. 
 * getMethodName will allow the method name to be called and used. 
 * */
public class MethodCall extends BasicStatement{
	private String[] tokens;
    public MethodCall(String text) throws InvalidStatementException{
        super(text);
        tokens = textTokens();
        if(!(Character.isJavaIdentifierStart(this.text.charAt(0)) && tokens[1].equals("("))){
            System.out.println("Not an assignment");
        }
    }
    public String getMethodName(){
    	return tokens[0];
    }
}

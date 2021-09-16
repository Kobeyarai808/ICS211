package edu.ics211.h02;

/*
 * Working: This Conditional method checks to see if the input text 
 * is a condition. If it is not a condition, Not an Assignment will
 * pop up to the user. Number of parts tells us how many bodies of code
 * we will have. isCompound will return true since there is more than 1 body. 
 * */
public class Conditional extends CompoundStatement{
    String[] tokens;
    public Conditional(String text) throws InvalidStatementException{
        this.text = text;
        tokens = textTokens();
        if(!(Character.isJavaIdentifierStart(this.text.charAt(0)) && tokens[1].equals("("))){
            System.out.println("Not an assignment");
        }
    }
    @Override
    public int numberOfParts() {
        int i = 0;
        for(String token: tokens){
            if(token.equals("else")){
                i++;
            }
        }
        return i+1;
    }

    @Override
    public boolean isCompound() {
        return true;
    }
}

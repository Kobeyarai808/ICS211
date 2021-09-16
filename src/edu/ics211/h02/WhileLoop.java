package edu.ics211.h02;
/*
 * Working: This WhileLoop method checks to see if the 
 * input text is a WhileLoop. If it is not a WhileLoop, 
 * Not a WhileLoop will print. numberOfParts will return
 * 1 because a while loop only has 1 code body. 
 * */
public class WhileLoop extends CompoundStatement{
    public WhileLoop(String text) throws InvalidStatementException{
        this.text=text;
        String[] tokens = textTokens();
        if(!(Character.isJavaIdentifierStart(this.text.charAt(0)) && tokens[1].equals("("))){
            System.out.println("Not a WhileLoop");
        }
    }
    @Override
    public int numberOfParts() {
        return 1;
    }

    @Override
    public boolean isCompound() {
        return true;
    }
}

package edu.ics211.h02;

import java.util.ArrayList;
import java.util.Arrays;

/*Working: The statement class is the parent class 
 * to both the BasicStatement and the CompoundStatement 
 * */
public abstract class Statement {
    protected String text;
    public String getText(){
        return text;
    }

    public String[] textTokens(){
        //Variable declarations
        String strOut = "";
        ArrayList<String> output = new ArrayList<String>();
        boolean isFirstChar = true;
        boolean inComment = false;
        boolean inSingleComment = false;

        //Loop through every char in the in String
        for(int i=0;i<text.length();i++){
            //Check to see if a multi-line comment was started
            if((i<text.length()-2)&&(text.substring(i,i+2).equals("/*"))&&!inSingleComment){
                inComment=true;
            }
            //Check to see if a single-line comment was started
            else if((i<text.length()-2)&&text.substring(i,i+2).equals("//")){
                inSingleComment=true;
            }
            //Check to see if a single-line comment was ended by a new line char
            else if(inSingleComment&&text.substring(i,i+1).equals("\n")) {
                inSingleComment=false;
            }
            //Check to see if a multi-line comment was ended by a closing multi-line char marker
            else if((i<text.length()-2)&&(inComment&&text.substring(i-1,i+1).equals("*/"))){
                inComment=false;
            }
            //Check to see if current char is the first char in a token
            else if(Character.isJavaIdentifierStart(text.charAt(i))&&isFirstChar&&!inComment&&!inSingleComment){
                strOut=""+text.charAt(i);
                isFirstChar = false;
            }
            //Check to see if current char is a java identifier part
            else if(Character.isJavaIdentifierPart(text.charAt(i))&&!inComment&&!inSingleComment){
                strOut+=text.charAt(i);
            }
            //Check to see if current char is a whitespace, if so it will end the current token and start a new one.
            else if(Character.isWhitespace(text.charAt(i))&&!inComment&&!inSingleComment){
                output.add(strOut);
                isFirstChar = true;
                strOut = "";
            }
            //If the above conditions are not met, it will end the current token,
            //create a new token that only contains the current char and start a new token.
            else if(!inComment&&!inSingleComment){
                output.add(strOut);
                output.add(text.substring(i,i+1));
                isFirstChar = true;
                strOut = "";
            }
        }
        //Removes blank indexes from the arraylist
        output.removeAll(Arrays.asList(""));
        //Convert arraylist to array
        String[] finalArray = new String[output.size()];
        for(int i=0;i<output.size();i++){
            finalArray[i] = output.get(i);
        }
        //returns the final array
        return finalArray;
    }

    public abstract boolean isCompound();
    
    @Override
    public String toString() {
    	return text;
    }
    
    @Override
    public boolean equals(Object compareStatement) {
    	String thisTokens[] = this.textTokens();
    	String compareTokens[] = ((Statement) compareStatement).textTokens();
    	if(thisTokens.length == compareTokens.length) {
    		for(int i=0; i<compareTokens.length; i++) {
    			if(!thisTokens[i].equals(compareTokens[i])) {
    				return false;
    			}
    		}
    		return true;
    	}
    	return false;
    }
}

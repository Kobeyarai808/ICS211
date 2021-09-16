package edu.ics211.h01;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
/*
 * ICS 211 
 * Lex.java
 * Date 09/03/2021
 * @author Kobey Arai
 * Separates file into tokens using this homebrewed Lexer
 *
 * */
public class Lex implements Lexer{


	/**
	 * Runs when program enters runtime. Mainly used for testing
	 * before the unit testing was formally working and code could pass the 
	 * unit tests.
	 * @return nothing, this is the main method
	 * @param args, default args
	 */
    public static void main(String[] args) {
    	//Experimental file that was taken from LexTest.java.
        String fileContents = "/**\n"
                + " * \n"
                + " */\n"
                + "package edu.ics211.h01;\n"
                + "\n"
                + "import java.io.InputStream;\n"
                + "\n"
                + "/**\n"
                + " * @author esb 2021/08/24\n"
                + " *\n"
                + " */\n"
                + "public interface Lexer {\n"
                + "  String[] lexicalTokens(String in);\n"
                + "  String[] lexicalTokens(InputStream in);\n"
                + "}";
        //Create a test Lex object.
        Lexer test = new Lex();
        
        //Grabs the output of the lexicalTokens and prints it out. 
        String[] whoDis = test.lexicalTokens(fileContents);
        for(String i: whoDis){
            System.out.println(i);
        }
    }

    /*
     * Returns the input string as a array with lexical chars sorted
     * @return finalArray, 
     * @param in, Input string that will be processed by the lexer
     */
    @Override
    public String[] lexicalTokens(String in) {
        //Variable declarations
        String strOut = "";
        ArrayList<String> output = new ArrayList<String>();
        boolean isFirstChar = true;
        boolean inComment = false;
        boolean inSingleComment = false;

        //Loop through every char in the in String
        for(int i=0;i<in.length();i++){
        	//Check to see if a multi-line comment was started
            if((i<in.length()-2)&&(in.substring(i,i+2).equals("/*"))&&!inSingleComment){
                inComment=true;
            }
            //Check to see if a single-line comment was started
            else if((i<in.length()-2)&&in.substring(i,i+2).equals("//")){
            	inSingleComment=true;
            }
            //Check to see if a single-line comment was ended by a new line char
            else if(inSingleComment&&in.substring(i,i+1).equals("\n")) {
            	inSingleComment=false;
            }
            //Check to see if a multi-line comment was ended by a closing multi-line char marker
            else if((i<in.length()-2)&&(inComment&&in.substring(i-1,i+1).equals("*/"))){
                inComment=false;
            }
            //Check to see if current char is the first char in a token
            else if(Character.isJavaIdentifierStart(in.charAt(i))&&isFirstChar&&!inComment&&!inSingleComment){
                strOut=""+in.charAt(i);
                isFirstChar = false;
            }
            //Check to see if current char is a java identifier part
            else if(Character.isJavaIdentifierPart(in.charAt(i))&&!inComment&&!inSingleComment){
                strOut+=in.charAt(i);
            }
            //Check to see if current char is a whitespace, if so it will end the current token and start a new one.
            else if(Character.isWhitespace(in.charAt(i))&&!inComment&&!inSingleComment){
                output.add(strOut);
                isFirstChar = true;
                strOut = "";
            }
            //If the above conditions are not met, it will end the current token,
            //create a new token that only contains the current char and start a new token.
            else if(!inComment&&!inSingleComment){
                output.add(strOut);
                output.add(in.substring(i,i+1));
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

    /*
     * Returns an Array of tokens that was processed by the lexer
     * given the InputStream
     * @param in, input from file specified. 
     * @return outputArray, an array of strings that has been through the lexer
     */
    @Override
    public String[] lexicalTokens(InputStream in) {
    	try {
    		//Create a byte array and read in the InputStream in
    		byte[] byteArray = in.readAllBytes();
    		//Place the byte data into a string temporarily
    		String contentOfByte = new String(byteArray);
    		//Debugging line
    		//System.out.println("this is what the content of bytes are\n\n"+ contentOfByte);
    		//Create a string array and provides the content of bytes
    		String[] outputArray = lexicalTokens(contentOfByte);
    		//Debugging Line
    		//System.out.println("the output array has a lenght of "+outputArray.length);
    		//Returns the outputArray
    		return outputArray;
    		//Catch any exceptions that may pop up
        }catch(IOException e) {
        	//An error has occurred, please revisit the code to fix
    		System.out.println("file not found. ");
    		//Returns null since this is trying to catch any errors instead of running
    		return null;
    	}
    }
}

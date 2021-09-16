package edu.ics211.h02;

/*
 * Working: This BasicStatement class is a parent class 
 * for the Assignment class and  and the MethodCall class. 
 * Basic Statements are not compound because they only have 
 * 1 code body. 
 * */
public class BasicStatement extends Statement{
    public BasicStatement(String text){
        this.text=text;
    }
    public boolean isCompound(){
        return false;
    }
}

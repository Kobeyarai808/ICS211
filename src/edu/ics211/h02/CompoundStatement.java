package edu.ics211.h02;

/*
 * Working: CompoundStatement class is the parent class 
 * of the WnileLoop and Conditional Class. 
 * */
public abstract class CompoundStatement extends Statement{
    public boolean isCompound(String text){
        return true;
    }
    public abstract int numberOfParts();
}

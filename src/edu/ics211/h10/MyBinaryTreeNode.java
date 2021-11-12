package edu.ics211.h10;

public class MyBinaryTreeNode {

    private double value;
    private MyBinaryTreeNode leftReference;
    private MyBinaryTreeNode rightReference;

    public MyBinaryTreeNode(double value){
        this.value = value;
    }
    public MyBinaryTreeNode(){
    }

    public boolean addLeftRef(MyBinaryTreeNode leftRef){
        leftReference = leftRef;
        return true;
    }

    public boolean addRightRef(MyBinaryTreeNode rightRef){
        rightReference = rightRef;
        return true;
    }

    public double getValue(){
        return value;
    }

    public void setValue(double val){
        value = val;
    }
}

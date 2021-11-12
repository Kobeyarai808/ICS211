package edu.ics211.h10;

public class DoubleBinarySearchTree implements DoubleBinarySearchTreeInterface{

    private MyBinaryTreeNode head;

    public static void main(String[] args) {

    }

    // constructor, creates an empty binary search tree
    public DoubleBinarySearchTree(){

    }

    // constructor, creates a binary search tree containing all the given values
    public DoubleBinarySearchTree(double[] initialValues){

    }

    @Override
    public boolean add(double value) {
        return false;
    }

    @Override
    public DoubleBinarySearchTree helperAdd(MyBinaryTreeNode root, double value){
        return new DoubleBinarySearchTree();
    }

    @Override
    public boolean has(double value) {
        return false;
    }

    @Override
    public boolean hasInRange(double first, double last) {
        return false;
    }

    @Override
    public int depth() {
        return 0;
    }

    @Override
    public double[] all() {
        return new double[0];
    }
}

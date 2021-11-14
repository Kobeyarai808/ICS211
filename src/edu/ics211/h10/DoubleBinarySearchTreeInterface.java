package edu.ics211.h10;

public interface DoubleBinarySearchTreeInterface {
    //public DoubleBinarySearchTree();   // constructor, creates an empty binary search tree
    //public DoubleBinarySearchTree(double[] initialValues);   // constructor, creates a binary search tree containing all the given values
    public boolean add(double value);   // add the given value to the binary search tree, or return false if the value is already in the tree
    public MyBinaryTreeNode helperAdd(MyBinaryTreeNode root, double value);
    public boolean has(double value);   // return true if the given value is in the binary search tree, false otherwise
    public boolean hasInRange(double first, double last);   // optional method to return true if the binary search tree has at least one value in the range first..last, false otherwise (and returns false if first > last)
    public int depth();   // returns 0 for an empty three, 1 for a tree with exactly one value, and the depth of the tree (2..) for larger trees
    public double[] all();              // returns an array containing all the values in sorted order
}

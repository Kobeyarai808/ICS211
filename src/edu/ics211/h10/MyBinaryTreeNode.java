package edu.ics211.h10;

/**
 * MyBinaryTreeNode, A binary search tree node that holds the current value, and the nodes for the left and right child.
 * @author Kobey Arai: ICS 211 Professor Edo Biagioni Fall 2021
 * @date 11/19/2021
 * @assignmentLink http://www2.hawaii.edu/~esb/2021fall.ics211/hw10.html
 *
 * Nothing special to note, works as intended.
 */
public class MyBinaryTreeNode {

    private double value;
    private MyBinaryTreeNode leftReference;
    private MyBinaryTreeNode rightReference;

    /**
     * Constructor that constructs a new node given the value of the node.
     * @param value is the value that will be held in the node.
     */
    public MyBinaryTreeNode(double value){
        this.value = value;
    }

    /**
     * Constructor that constructs a new node that has no value and no children. Intended to be blank.
     */
    public MyBinaryTreeNode(){
    }

    /**
     * addLeftRef, adds a left reference to the binary search tree node.
     * @param leftRef the child node to add to the current node.
     * @return if the node was sucessfully added. In this implementation will always return true.
     */
    public boolean addLeftRef(MyBinaryTreeNode leftRef){
        leftReference = leftRef;
        return true;
    }

    /**
     * hasLeftRef, checks to see if there is a left reference.
     * @return boolean that states if there is a left reference.
     */
    public boolean hasLeftRef(){
        return leftReference!=null;
    }

    /**
     * getLeftRef, gets the left reference.
     * @return the left child node of the parent.
     */
    public MyBinaryTreeNode getLeftRef(){
        return leftReference;
    }

    /**
     * addRightRef, adds a right reference to the binary search tree node.
     * @param rightRef the child node to add to the current node.
     * @return if the node was sucessfully added. In this implementation will always return true.
     */
    public boolean addRightRef(MyBinaryTreeNode rightRef){
        rightReference = rightRef;
        return true;
    }

    /**
     * hasRightRef, checks to see if there is a right reference.
     * @return boolean that states if there is a right reference.
     */
    public boolean hasRightRef(){
        return rightReference!=null;
    }

    /**
     * getRightRef, gets the right reference.
     * @return the right child node of the parent.
     */
    public MyBinaryTreeNode getRightRef(){
        return rightReference;
    }

    /**
     * getValue, gets the value of the node.
     * @return the boolean value of the node.
     */
    public double getValue(){
        return value;
    }

    /**
     * setValue, sets the value of the node.
     */
    public void setValue(double val){
        value = val;
    }
}

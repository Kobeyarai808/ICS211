package edu.ics211.h10;

public class DoubleBinarySearchTree implements DoubleBinarySearchTreeInterface{

    private MyBinaryTreeNode head;

    public static void main(String[] args) {
        DoubleBinarySearchTree testing = new DoubleBinarySearchTree(new double[]{4.0, 3.0, 5.0});
        testing.add(1.0);
        testing.add(2.0);
        System.out.println(testing.head.getValue());
        System.out.println(testing.head.getLeftRef().getLeftRef().getValue());
        System.out.println(testing.head.getLeftRef().getLeftRef().getRightRef().getValue());
    }

    // constructor, creates an empty binary search tree
    public DoubleBinarySearchTree(){

    }

    // constructor, creates a binary search tree containing all the given values
    public DoubleBinarySearchTree(double[] initialValues){
        head = new MyBinaryTreeNode(initialValues[0]);
        for(int i=1;i<initialValues.length;i++){
            add(initialValues[i]);
        }
    }

    @Override
    public boolean add(double value) {
        MyBinaryTreeNode theNode = helperAdd(head, value);
        return theNode!=null;
    }

    @Override
    public MyBinaryTreeNode helperAdd(MyBinaryTreeNode root, double value){
        MyBinaryTreeNode valNode = new MyBinaryTreeNode(value);
        if(value>root.getValue()){
            if(!root.hasRightRef()){
                root.addRightRef(valNode);
                return valNode;
            }
            else{
                return helperAdd(root.getRightRef(),value);
            }
        }
        else if(value<root.getValue()){
            if(!root.hasLeftRef()){
                root.addLeftRef(valNode);
                return valNode;
            }
            else{
                return helperAdd(root.getLeftRef(),value);
            }
        }
        return null;
    }

    @Override
    public boolean has(double value) {
        return hasHelper(value, head);
    }

    public boolean hasHelper(double value, MyBinaryTreeNode head) {
        if(head.getValue()>value){
            return hasHelper(value, head.getLeftRef());
        }
        else if(head.getValue()<value){
            return hasHelper(value, head.getRightRef());
        }
        else if(head.getValue()==value){
            return true;
        }
        return false;
    }

    @Override
    public boolean hasInRange(double first, double last) {
        return false;
    }

    @Override
    public int depth() {
        return depthHelper(head);
    }

    private int depthHelper(MyBinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        else if(root.hasRightRef()){
            return depthHelper(root.getRightRef())+1;
        }
        else if(root.hasLeftRef()){
            return depthHelper(root.getLeftRef())+1;
        }
        return 1;
    }

    @Override
    public double[] all() {
        return allHelper(new double[10], head, countNodes(head));
    }

    public double[] allHelper(double[] arr, MyBinaryTreeNode head, int index){
        if(head==null){

        }
        else{
            allHelper(arr, head.getLeftRef(),index);
            arr[index+1] = head.getValue();
            allHelper(arr, head.getRightRef(),index+2);
        }
        return arr;
    }

    public int countNodes(MyBinaryTreeNode head){
        if(head==null) {
            return 0;
        }
        else if(head.hasLeftRef()){
            return 1+countNodes(head.getLeftRef());
        }
        else if(head.hasRightRef()){
            return 1+countNodes(head.getRightRef());
        }
        return 1;
    }
}

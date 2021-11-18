package edu.ics211.h10;

public class DoubleBinarySearchTree implements DoubleBinarySearchTreeInterface{

    private MyBinaryTreeNode head;

    public static void main(String[] args) {
        /*DoubleBinarySearchTree testing = new DoubleBinarySearchTree(new double[]{4.0, 3.0, 5.0});
        testing.add(1.0);
        testing.add(2.0);
        System.out.println(testing.head.getValue()+" is the head");
        System.out.println(testing.head.getLeftRef().getLeftRef().getValue());
        System.out.println(testing.head.getLeftRef().getLeftRef().getRightRef().getValue());
        double [] testingArr = testing.all();
        for (int i = 0; i < testingArr.length; i++ ) {
            System.out.print(testingArr[i] + " ");
        }*/


        System.out.println("\n\n");
        DoubleBinarySearchTree created = new DoubleBinarySearchTree();
        created.add(10.0);
        created.add(2.0);
        created.add(3.0);
        created.add(4.0);
        created.add(6.0);
        created.add(5.0);
        System.out.println("\n\n");
        System.out.println(created.head.getValue()+" is the head");
        System.out.println(created.head.getLeftRef().getValue());
        System.out.println(created.head.getLeftRef().getRightRef().getValue());
        System.out.println(created.head.getLeftRef().getRightRef().getRightRef().getValue());
        System.out.println(created.head.getLeftRef().getRightRef().getRightRef().getRightRef().getValue());
        System.out.println(created.head.getLeftRef().getRightRef().getRightRef().getRightRef().getLeftRef().getValue());
        double [] stored = created.all();
        for (int i = 0; i < stored.length; i++ ) {
            System.out.print(stored[i] + " ");
        }
        System.out.print(created.depth());
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
        if(head==null) head = new MyBinaryTreeNode(value);
        MyBinaryTreeNode theNode = helperAdd(head, value);
        return theNode!=null;
    }

    @Override
    public MyBinaryTreeNode helperAdd(MyBinaryTreeNode root, double value){
        MyBinaryTreeNode valNode = new MyBinaryTreeNode(value);
        if(root==null){
            //System.out.println(valNode.getValue());
            root = valNode;
            return root;
        }
        else if(value>root.getValue()){
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
        else if(root.hasRightRef()&&root.hasLeftRef()){
            int rightDepth = depthHelper(root.getRightRef())+1;
            int leftDepth = depthHelper(root.getLeftRef())+1;
            if(rightDepth>leftDepth){
                return rightDepth;
            }
            return leftDepth;
        }
        else if(root.hasLeftRef()){
            return depthHelper(root.getLeftRef())+1;
        }
        else if(root.hasRightRef()){
            return depthHelper(root.getRightRef())+1;
        }
        return 1;
    }

    @Override
    public double[] all() {
        return allHelper(new double[countNodes(head)], head, 0);
    }

    public double[] allHelper(double[] arr, MyBinaryTreeNode head, int index){
        if(head==null){

        }
        else{
            System.out.println("Thing iterating next: "+head.getLeftRef());
            System.out.println("Thing iterating next: "+head.getRightRef());
            allHelper(arr, head.getLeftRef(),index);
            arr[index++] = head.getValue();
            allHelper(arr, head.getRightRef(),index);
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

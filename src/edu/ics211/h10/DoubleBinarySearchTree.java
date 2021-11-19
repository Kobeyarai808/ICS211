package edu.ics211.h10;

/**
 * WORKING CODE FOR ALL CASES EXCEPT ONE, Please see notes for error I have caught but have not fixed.
 *
 * DoubleBinarySearchTree, a binary search tree data structure of double values. Implemented from scratch with no libraries used for imports.
 * @author Kobey Arai: ICS 211 Professor Edo Biagioni Fall 2021
 * @date 11/19/2021
 * @assignmentLink http://www2.hawaii.edu/~esb/2021fall.ics211/hw10.html
 *
 * Note: My implementation breaks when adding 0.0 into the DoubleBinarySearchTree because of the way the all method is implemented. I use a
 * helper method named allFindNextIndex that finds the next occurrence of a default value (for a double its 0.0). I did this because I could
 * not find a way to keep track of the index using a parameter value index. Per TA, this is not subject for docking any points.
 *
 * Note: Instead of Part 2, I forwent this section for the optional research IDE project. It is attached in the folder as WorkingV2_0.
 */
public class DoubleBinarySearchTree implements DoubleBinarySearchTreeInterface{

    private MyBinaryTreeNode head;

    /**
     * main, used for testing purposes.
     * @param args
     */
    public static void main(String[] args) {
        //Test case number 1
        DoubleBinarySearchTree testing = new DoubleBinarySearchTree(new double[]{4.0, 3.0, 5.0});
        testing.add(1.0);
        testing.add(2.0);
        System.out.println(testing.head.getValue()+" is the head");
        System.out.println(testing.head.getLeftRef().getLeftRef().getValue());
        System.out.println(testing.head.getLeftRef().getLeftRef().getRightRef().getValue());
        double [] testingArr = testing.all();
        for (int i = 0; i < testingArr.length; i++ ) {
            System.out.print(testingArr[i] + " ");
        }

        //Test case number 2
        System.out.println("\n\n");
        DoubleBinarySearchTree testing2 = new DoubleBinarySearchTree();
        testing2.add(10.0);
        testing2.add(2.0);
        testing2.add(3.0);
        testing2.add(4.0);
        testing2.add(6.0);
        testing2.add(5.0);
        System.out.println("\n\n");
        System.out.println(testing2.head.getValue()+" is the head");
        System.out.println(testing2.head.getLeftRef().getValue());
        System.out.println(testing2.head.getLeftRef().getRightRef().getValue());
        System.out.println(testing2.head.getLeftRef().getRightRef().getRightRef().getValue());
        System.out.println(testing2.head.getLeftRef().getRightRef().getRightRef().getRightRef().getValue());
        System.out.println(testing2.head.getLeftRef().getRightRef().getRightRef().getRightRef().getLeftRef().getValue());
        double [] stored = testing2.all();
        for (int i = 0; i < stored.length; i++ ) {
            System.out.print(stored[i] + " ");
        }
        System.out.print(testing2.depth());
    }

    /**
     * constructor, creates an empty binary search tree, Note: Intended to be blank.
     */
    public DoubleBinarySearchTree(){
    }

    /**
     * constructor, creates a binary search tree, initilizing the tree with initialValues.
     * @param initialValues is an array that is given as input, all these values will be added to the tree durring construction.
     */
    public DoubleBinarySearchTree(double[] initialValues){
        //Sets value at index 0 of array to the head
        head = new MyBinaryTreeNode(initialValues[0]);
        //loops through the rest of the array, adding each value.
        for(int i=1;i<initialValues.length;i++){
            add(initialValues[i]);
        }
    }

    /**
     * add, adds a value to the binary search tree. Duplicates are not allowed so if the value is already in the array, it will return false.
     * @param value is the value to add to the binary search tree.
     * @return a boolean that tells us if the value was successfully added to the array.
     */
    @Override
    public boolean add(double value) {
        if(head==null) head = new MyBinaryTreeNode(value);
        MyBinaryTreeNode theNode = helperAdd(head, value);
        return theNode!=null;
    }

    /**
     * helperAdd, helper to the add method. Iterates through the binary search tree finding the proper location for the value.
     * @param parent is the parent node that we are using to determine where the value belongs (left child, right child, or none).
     * @param value is the value that is to be be added to the binary search tree.
     * @return returns a MyBinaryTreeNode object that contains all information about the node.
     */
    @Override
    public MyBinaryTreeNode helperAdd(MyBinaryTreeNode parent, double value){
        MyBinaryTreeNode valNode = new MyBinaryTreeNode(value);
        if(parent==null){
            //System.out.println(valNode.getValue());
            parent = valNode;
            return parent;
        }
        else if(value>parent.getValue()){
            if(!parent.hasRightRef()){
                parent.addRightRef(valNode);
                return valNode;
            }
            else{
                return helperAdd(parent.getRightRef(),value);
            }
        }
        else if(value<parent.getValue()){
            if(!parent.hasLeftRef()){
                parent.addLeftRef(valNode);
                return valNode;
            }
            else{
                return helperAdd(parent.getLeftRef(),value);
            }
        }
        return null;
    }

    /**
     * has, checks to see if the binary search tree has the value specified.
     * @param value is the value we are determining inclusion in the binary search tree.
     * @return a boolean saying if the value was found in the binary search tree. True if its inside the tree, false if otherwise.
     */
    @Override
    public boolean has(double value) {
        return hasHelper(value, head);
    }

    /**
     * hasHelper, helper method for the has method.
     * @param value is the value we are determining inclusion in the binary search tree.
     * @param parent
     * @return
     */
    public boolean hasHelper(double value, MyBinaryTreeNode parent) {
        if(parent.getValue()>value){
            return hasHelper(value, parent.getLeftRef());
        }
        else if(parent.getValue()<value){
            return hasHelper(value, parent.getRightRef());
        }
        else if(parent.getValue()==value){
            return true;
        }
        return false;
    }

    /**
     * depth, finds the depth of the binary serach tree.
     * @return the most amount of iterations that is required to find any value in the binary search tree.
     */
    @Override
    public int depth() {
        return depthHelper(head);
    }

    /**
     * depthHelper, helper method for the depth method.
     * @param parent is the node we are using to access children nodes.
     * @return the number of iterations required to reach bottom if parent was the head node.
     */
    private int depthHelper(MyBinaryTreeNode parent) {
        if (parent == null) {
            return 0;
        }
        else if(parent.hasRightRef()&&parent.hasLeftRef()){
            int rightDepth = depthHelper(parent.getRightRef())+1;
            int leftDepth = depthHelper(parent.getLeftRef())+1;
            if(rightDepth>leftDepth){
                return rightDepth;
            }
            return leftDepth;
        }
        else if(parent.hasLeftRef()){
            return depthHelper(parent.getLeftRef())+1;
        }
        else if(parent.hasRightRef()){
            return depthHelper(parent.getRightRef())+1;
        }
        return 1;
    }

    /**
     * all, an in-order traversal of the binary search tree.
     * @return an array of values in the binary search tree in sorted order.
     */
    @Override
    public double[] all() {
        return allHelper(new double[countNodes(head)], head, 0);
    }

    /**
     * allHelper, helper method for the all method.
     * @param arr the array will store sorted data to return.
     * @param parent is the node being used to access child nodes.
     * @param index not currently being used for anything. Currently, working on another implementation that would require an index parameter.
     * @return the array that has the sorted data.
     */
    public double[] allHelper(double[] arr, MyBinaryTreeNode parent, int index){
        if(parent==null){
        }
        else{
            //System.out.println("Thing iterating next: "+head.getLeftRef());
            if(parent.getLeftRef()!=null){
                allHelper(arr, parent.getLeftRef(),index);
            }
            arr[allFindNextIndex(arr)] = parent.getValue();
            //System.out.println("VALUE: "+parent.getValue());
            //System.out.println("Thing iterating next: "+parent.getRightRef());
            if(parent.getRightRef()!=null){
                allHelper(arr, parent.getRightRef(),index);
            }
        }
        return arr;
    }

    /**
     * allFindNextIndex, a helper method for the allHelper method that finds the next empty index.
     * @param arr array that we are trying to find next empty index.
     * @return next empty index that can store a value.
     */
    public int allFindNextIndex(double arr[]){
        for(int i=0;i<arr.length;i++){
            if(arr[i]==0.0) return i;
        }
        return -1;
    }

    /**
     * countNodes, counts the number of nodes in the binary search tree.
     * @param parent is the node that is being used to access child nodes.
     * @return the total number of nodes in the binary search tree (Does not include null nodes).
     */
    public int countNodes(MyBinaryTreeNode parent){
        if(parent==null) {
            return 0;
        }
        else if(parent.hasLeftRef()){
            return 1+countNodes(parent.getLeftRef());
        }
        else if(parent.hasRightRef()){
            return 1+countNodes(parent.getRightRef());
        }
        return 1;
    }
}

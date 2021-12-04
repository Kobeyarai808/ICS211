package edu.ics211.h11;

import java.util.Iterator;

public class Huffman {
    // your instance variables and inner class definitions go here
    HuffmanNode tree;

    private class HuffmanNode {
        int frequency;
        // a node in a Huffman tree has either a value or a left and right subtree
        //
        // for the implementation of the priority queue we also keep a next
        // reference.
        char value;
        HuffmanNode next;
        HuffmanNode left;
        HuffmanNode right;

        public HuffmanNode(int f, char v, HuffmanNode n) {
            frequency = f;
            value = v;
            next = n;
            left = null;
            right = null;
        }

        public HuffmanNode(HuffmanNode l, HuffmanNode r) {
            frequency = l.frequency + r.frequency;
            value = l.value;  // never used, but might as well initialize
            next = null;
            left = l;
            right = r;
        }

        // swap the values while not changing the next field
        private void swapNext() {
            int f = frequency;
            char v = value;
            HuffmanNode l = left;
            HuffmanNode r = right;

            frequency = next.frequency;
            value = next.value;
            left = next.left;
            right = next.right;

            next.frequency = f;
            next.value = v;
            next.left = l;
            next.right = r;
        }

        public String findChar(char c) {
            if (left == null) {
                if (value == c) {
                    return "";
                } else {
                    return null;
                }
            }
            String l = left.findChar (c);
            if (l != null) {
                return "0" + l;
            }
            String r = right.findChar (c);
            if (r != null) {
                return "1" + r;
            }
            return null;
        }

        public String toString() {
            if (left == null) {
                return value + ": " + frequency + "\n";
            } else {
                return "---: " + frequency + "\n" + left + right;
            }
        }
    }

    private boolean addIfThere(HuffmanNode start, char v) {
        HuffmanNode current = start;
        while (current != null) {
            if (current.value == v) {
                current.frequency = current.frequency + 1;
                while ((current.next != null) &&
                        (current.next.frequency < current.frequency)) {
                    current.swapNext();
                    current = current.next;
                }
                return true;
            }
            current = current.next;
        }
        return false;
    }

    private HuffmanNode add(HuffmanNode start, char v) {
        // have to traverse the linked list up to twice, once to see if it
        // is there, then to add it if it isn't already there
        if (addIfThere(start, v)) {
            return start;
        }
        return new HuffmanNode(1, v, start);
    }

    private HuffmanNode add(HuffmanNode start, HuffmanNode newNode) {
        if (start == null) {
            return newNode;
        }
        if (newNode.frequency <= start.frequency) {
            newNode.next = start;
            return newNode;
        }
        HuffmanNode current = start;
        while ((current.next != null) &&
                (newNode.frequency > current.next.frequency)) {
            current = current.next;
        }   // current.next is null or newNode.frequency <= current.next.frequency,
        // insert after the current node
        newNode.next = current.next;
        current.next = newNode;
        return start;
    }

    public Huffman(String letters) {
        HuffmanNode queue = null;
        for (int i = 0; i < letters.length(); i++) {
            queue = add(queue, letters.charAt(i));
        }
        java.util.ArrayList<Character> chars = new java.util.ArrayList<Character>();
        HuffmanNode collect = queue;
        while (collect != null) {
            chars.add(collect.value);
            // System.out.print(collect.value + ": " + collect.frequency + ", ");
            collect = collect.next;
        }
        // System.out.println();
        // makes this a Huffman tree with the letter queue from the string
        while (queue.next != null) {
            // take the first two values and make them into a new node
            queue = add(queue.next.next, new HuffmanNode (queue, queue.next));
        }
        // System.out.println("Huffman tree is: " + queue);
        // for (Character c: chars) {
        // System.out.println(c + " = " + queue.findChar(c));
        // }
        tree = queue;
    }

    /**
     * encode, a method that compresses a string by using creating a huffman tree.
     * @oaram s is the string to be encoded
     * @return a BitString with the representation of the string s
     */
    public BitStringInterface encode(String s) {
        //Variable declarations
        Huffman huffman = new Huffman(s);
        BitString bitString = new BitString();
        String str = "";
        //Loop through all elements in the tree and call bitToBool()
        for (int i=0; i<s.length(); i++) {
            str = huffman.tree.findChar(s.charAt(i));
            Boolean[] boolArr = bitToBool(new Boolean[str.length()],0,str);
            bitString.addBits(boolArr);
        }
        return bitString;
    }

    /**
     * bitToBool, a helper method that converts the array of bits (1s and 0s) to an array of booleans.
     * @param bitArr is the boolean output array being created.
     * @param str is the string that is being encoded.
     * @param index is the current index being converted to a boolean.
     * @return the boolean array after working on the index 'index'.
     */
    public Boolean[] bitToBool(Boolean[] bitArr, int index, String str) {
        //Check to see if at the end of the String
        if(index == str.length()) return bitArr;
        //If the current index is a 1, set the boolean value to true
        else if (str.charAt(index) == '1') {
            bitArr[index++] = true;
            return bitToBool(bitArr, index, str);
        }
        //If the current indexis a 0, set the boolean value to false
        else if(str.charAt(index) == '0') {
            bitArr[index++] = false;
            return bitToBool(bitArr, index, str);
        }
        //When done, return the boolean array
        return bitArr;
    }

    /**
     * decode, a method that decodes a BitString to a readable String.
     * @param data is the BitString to decode.
     * @return the String output.
     */
    public String decode(BitStringInterface data) {
        //Variable declarations
        HuffmanNode node = tree;
        String output = "";
        Iterator<Boolean> iterator = data.iterator();
        //Loop through the entire Huffman Tree
        while(true) {
            //Check to see if at a leaf node, adds the value to the output String.
            if(node.left == null && node.right == null) {
                output+=node.value;
                node = tree;
            }
            //Checks to see if done iterating through the tree, exit
            if(!iterator.hasNext()) {
                return output;
            }
            //If there is a left node, advance to it
            if (!iterator.next()) {
                node = node.left;
            }
            //If there is a right node, advance to it
            else{
                node = node.right;
            }
        }
    }

    public static void testHuffman(String textOrFileName) {
        String text = textOrFileName;
        String filename = "";
        try {   // read from the file if textOrFileName is the name of a file
            StringBuilder sb = new StringBuilder();
            java.nio.file.Path p =
                    java.nio.file.FileSystems.getDefault().getPath(textOrFileName);
            for (String s: java.nio.file.Files.readAllLines(p)) {
                sb.append(s + "\n");
            }
            text = sb.toString();
            filename = textOrFileName;
        } catch (Exception e) {
            // nothing to do, text and filename are already initialized
        }
        // System.out.println ("text " + text);
        Huffman key = new Huffman(text);
        BitStringInterface encoded = key.encode(text);
        // System.out.println("encoded bitstring is " + encoded);
        String decoded = key.decode(encoded);
        // System.out.println ("decoded " + decoded);
        assert (text.equals(decoded)) : "decoded != text";
        System.out.println("text has " + text.length() + " bytes = " +
                (text.length() * 8) + " bits, encoded text has " +
                encoded.length() + " bits");
        System.out.println((filename.equals("") ? "" : (filename + ": ")) +
                "encoded size is " +
                (double)encoded.length() / (text.length() * 8.0) +
                " of original");
    }

    public static void main(String[] args) {
        String test = "These classes create a Huffman Tree data structure that is ultimately used to encode ASCII characters. The BitString is able to convert between bits and bytes. In addition, it also allows adding bits to the BitString up until a maximum of 8 bits in a byte. The Huffman class adds characters into the Huffman tree to be encoded, it implements Huffman nodes which is used to store the value along with references to the left and right children. Frequency of the entire sub-tree is also stored in the Huffman node. ";
        testHuffman(test);

        //for (String s: args) {
            //System.out.println(s);
            //testHuffman(s);
        //}
    }
}
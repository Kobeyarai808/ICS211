package edu.ics211.h11;

import java.io.*;

public class Compress {

    /**
     * Compress, creates a compression object that either compresses a text file or decompresses a Huffman Encoded File
     * @param file is the file name to compress/decompress
     * @throws IOException
     */
    public Compress(String file) throws IOException {
        //Variable declarations
        FileInputStream in = null;
        StringBuilder stringBuilder = new StringBuilder();
        //Read in the txt file to create a Huffman tree
        try {
            in = new FileInputStream(file.substring(0,file.length()-4)+".txt");
            int c;
            while ((c = in.read()) != -1) {
                stringBuilder.append((char)c);
            }
        }finally {
            in.close();
        }
        Huffman huffman = new Huffman(stringBuilder.toString());

        //Check to see if the file being inputted is a txt file, if so encode it
        if(file.substring(file.length()-4,file.length()).equals(".txt")){
            FileOutputStream out = null;
            try{
                out = new FileOutputStream(file.substring(0,file.length()-4)+".huf");
                BitStringInterface bitStringInterface = huffman.encode(stringBuilder.toString());
                byte[] byteArr = bitStringInterface.toBytes();
                System.out.println("Encoded: "+stringBuilder.toString());
                out.write(byteArr);

                /*BitStringInterface testBitStringInterface = new BitString(byteArr);
                System.out.println(testBitStringInterface.toString());
                System.out.println("Testing: "+huffman.decode(testBitStringInterface));*/
            }finally {
                out.close();
            }
        }
        //If the file is not a txt file, it must be a huf file, if so decode it
        else{
            FileInputStream in2 = new FileInputStream(file);
            BufferedWriter out2 = new BufferedWriter(new FileWriter(file.substring(0,file.length()-4)+".211.txt"));
            byte[] testingByteArr = in2.readAllBytes();
            //for(byte i: testingByteArr){System.out.printf("%02X ",i);}
            //System.out.println();
            BitStringInterface bitStringInterface = new BitString(testingByteArr);
            //System.out.println(bitStringInterface.toString());
            String decoded = huffman.decode(bitStringInterface);
            System.out.println("Decoded: "+decoded);
            out2.write(decoded);
            in2.close();
            out2.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Compress test = new Compress("CodeReview.huf");
    }
}
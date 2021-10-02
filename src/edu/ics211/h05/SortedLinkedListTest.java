package edu.ics211.h05;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

class SortedLinkedListTest {
    private SortedLinkedList<Double> doubleLL;
    private SortedLinkedList<String> stringLL;
    StringComparator sc;
    DoublesComparator dc;

    @BeforeEach
    void setUp() {
        dc = new DoublesComparator();
        sc = new StringComparator();
        StringComparator sc = new StringComparator();
        doubleLL = new SortedLinkedList<Double>(dc);
        stringLL = new SortedLinkedList<String>(sc);
    }
        @Test
        void testSortedLinkedListString() {
            stringLL.add("banana");
            assert(stringLL.get(0).equals("banana"));
            stringLL.add("squash");
            stringLL.add("microsoft");
            stringLL.add("quick");
            stringLL.add("google");
            stringLL.add("dog");
            stringLL.add("plumeria");
            stringLL.add("dolphin");
            stringLL.add("picture");
            stringLL.add("zoo");
            stringLL.add("flower");
            stringLL.add("yellow");
            assert(stringLL.toString().equals("banana ==> dog ==> dolphin ==> flower ==> google ==> microsoft ==> "
                    + "picture ==> plumeria ==> quick ==> squash ==> yellow ==> zoo"));
            stringLL.add("apple");
            stringLL.add("apple");
            //System.out.println(stringLL.toString());
            assert(stringLL.toString().equals("apple ==> banana ==> dog ==> dolphin ==> flower ==> google ==> microsoft ==> picture ==> plumeria ==> "
                    + "quick ==> squash ==> yellow ==> zoo"));
            stringLL.remove("microsoft");
            assert(stringLL.toString().equals("apple ==> banana ==> dog ==> dolphin ==> flower ==> google ==> picture ==> plumeria ==> "
                    + "quick ==> squash ==> yellow ==> zoo"));
            assert(stringLL.size() == 12);
            assert(stringLL.indexOf("apple") == 0);

            java.util.Iterator<String> it = stringLL.iterator();
            String prev = stringLL.get(0);
            String created = "";
            int count = 0;
            for(int i=0;i<stringLL.size();i++){
                assert(it.hasNext());
                assert(sc.compare(prev, prev=it.next())<=0);
                count+=prev.length();
                created+= " " + prev;
            }
            System.out.println(created);
            System.out.println("Count Total: "+count);

            java.util.Iterator<String> oIt = stringLL.oddIterator();
            String prevO = stringLL.get(1);
            String createdO = "";
            int countO = 0;
            for(int i=0;i<stringLL.size()/2;i++){
                assert(oIt.hasNext());
                assert(sc.compare(prevO, prevO=oIt.next())<=0);
                countO+=prevO.length();
                createdO += " " + prevO;
            }
            System.out.println(createdO);
            System.out.println("Count Odd: "+countO);

            java.util.Iterator<String> eIt = stringLL.evenIterator();
            String prevE = stringLL.get(0);
            String createdE = "";
            int countE = 0;
            int index;
            if(stringLL.size()%2==1){
                index = (stringLL.size()/2)+1;
            }
            else{
                index = stringLL.size()/2;
            }
            for(int i=0;i<index;i++){
                assert(eIt.hasNext());
                assert(sc.compare(prevE, prevE=eIt.next())<=0);
                countE+=prevE.length();
                createdE += " " + prevE;
            }
            System.out.println(createdE);
            System.out.println("Count Even: "+countE);
        }
        public void testSortedLinkedListDouble () {

            /***SortedLinkedList<Double> doubLL = new SortedLinkedList<Double>(new DoublesComparator());
            doubLL.add(1.0);
            doubLL.add(2.0);
            doubLL.add(1.0);
            doubLL.add(2.0);
            doubLL.add(5.0);
            doubLL.add(3.0);
            doubLL.add(5.0);
            System.out.println(doubLL.toString());***/



            double doubleValues[] = { 0.0, 1.0, 2.0, Math.E, Math.PI, 10.0 };

            Random rand = new Random();
            //System.out.println(dc.compare(doubleValues[5],doubleValues[5]));

            for( int i = 0; i < 10 ; i++ ) {
                int randomGenerator = rand.nextInt(6);
                doubleLL.add(doubleValues[randomGenerator]);
            }
            //System.out.println(doubleLL);
            for(int i=0;i<doubleLL.size()-1;i++){
                assert(dc.compare(doubleLL.get(i), doubleLL.get(i+1))<=0);
            }

            java.util.Iterator<Double> it = doubleLL.iterator();
            double prev = doubleLL.get(0);
            String created = "";
            int count =0;
            for(int i=0;i<doubleLL.size();i++){
                assert(it.hasNext());
                assert(dc.compare(prev, prev=it.next())<=0);
                count+=prev;
                created += " " + prev;
            }
            System.out.println(created);
            System.out.println("Count Total: "+count);

            java.util.Iterator<Double> oIt = doubleLL.oddIterator();
            double prevO = doubleLL.get(1);
            String createdO = "";
            int countO=0;
            for(int i=0;i<doubleLL.size()/2;i++){
                assert(oIt.hasNext());
                assert(dc.compare(prevO, prevO=oIt.next())<=0);
                createdO+=prevO;
                createdO += " " + prevO;
                
            }
            System.out.println(createdO);
            System.out.println("Count Odd: "+countO);

            java.util.Iterator<Double> eIt = doubleLL.evenIterator();
            double prevE = doubleLL.get(0);
            String createdE = "";
            int countE=0;
            int index;
            if(doubleLL.size()%2==1){
                index = (doubleLL.size()/2)+1;
            }
            else{
                index = doubleLL.size()/2;
            }
            for(int i=0;i<index;i++){
                assert(eIt.hasNext());
                assert(dc.compare(prevE, prevE=eIt.next())<=0);
                createdE += " " + prevE;
                countE+=prevE;
            }
            System.out.println(createdE);
            System.out.println("Count Even: "+countE);
        }
        @Test
        void test () {
            testSortedLinkedListString();
            testSortedLinkedListDouble();
        }
    }
package edu.ics211.h05;

public class DoublesComparator implements java.util.Comparator<Double> {

    public DoublesComparator(){

    }

    @Override public int compare(Double o1, Double o2) {


        /***if(o1 == o2 + 0.01) {
            second = o1;
        }
        else if (o2 == o1 + 0.01) {
            first = o2;
        }
        else if (o2 - 0.01 == o1) {
            second = o1;
        }
        else if (o1 - 0.01 == o2) {
            first = o2;
        }***/

        if(Math.abs(o1-o2)<0.01) return 0;

        if (o1 < o2) {
            return -1;
        } else if (o2 < o1) {
            return 1;
        } else if (o2 == o1){
            return 0;
        }


    return 0;
    }
    public static void main(String[] args) {
        DoublesComparator c = new DoublesComparator();
        System.out.println("2.01 compared to 3.01 returns " + c.compare(2.01, 3.01));
        System.out.println("3.01 compared to 2.01 returns " + c.compare(3.01, 2.01));
        System.out.println("2.01 compared to 2.01 returns " + c.compare(2.01, 2.01));
        System.out.println("2 compared to 2.01 returns " + c.compare(2.00, 2.01));
        System.out.println("2 compared to 2 retunrns " + c.compare(10.00,10.00));
    }
}

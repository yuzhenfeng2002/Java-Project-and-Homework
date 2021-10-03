package com.company;

import java.io.*;
public class Main {

    public static void main(String[] args) {
        final int MAX = 100;
//        byte factorial = 1;
//        short factorial = 1;
//        int factorial = 1;
//        long factorial = 1L;
//        float factorial = 1f;
        double factorial = 1d;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("factorial.csv"));
            for (int n = 1; n < MAX; ++n)
            {
                factorial = factorial * n;
                out.write(String.valueOf(n) + ',' + String.valueOf(factorial) + '\n');
            }
            out.close();
            System.out.println("Succeed to create the file.");
        } catch (IOException e)
        {
            System.out.println("Fail to create the file.");
        }
    }
}

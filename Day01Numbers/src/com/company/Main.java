package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Integer> arr = new ArrayList<>();
        System.out.println("How many numbers to generate?");
        Scanner scan = new Scanner(System.in);

        int input = Integer.parseInt(scan.nextLine());
        for (int i = 0; i < input; i++) {
            int rand = (int) (Math.random() * (100 - -100 + 1)) + -100;
            arr.add(rand);
        }
        for (Integer num: arr
             ) {
            if (num <= 0) System.out.println(num);
        }
    }
}

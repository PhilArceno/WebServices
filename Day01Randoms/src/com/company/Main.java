package com.company;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code
        System.out.println("How many random numbers you want to generate?");

        Scanner scan = new Scanner(System.in);
        try {
            int num = Integer.parseInt(scan.nextLine());
            if (num < 0) throw new Error("negative number.");
            System.out.println("Enter minimum integer:");
            int min = Integer.parseInt(scan.nextLine());
            System.out.println("Enter maximum integer:");
            int max = Integer.parseInt(scan.nextLine());

            if (min > max) throw new Error("min is greater than max.");
            for (int i = 0; i < num; i++) {
                System.out.println((int) (Math.random() * (max - min)) + min);
            }
        } catch (Exception err) {
            System.out.println("Error: " + err);
        }

    }
}

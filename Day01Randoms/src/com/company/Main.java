package com.company;

import java.sql.SQLOutput;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code
        System.out.println("How many random numbers you want to generate?");

        Scanner scan = new Scanner(System.in);
        try {
            int num = scan.nextInt();
            if (num < 0) {
                System.out.println("Error: This value can't be a negative.");
                System.exit(1);
            }
            System.out.println("Enter minimum integer:");
            int min = scan.nextInt();
            System.out.println("Enter maximum integer:");
            int max = scan.nextInt();

            if (min > max) {
                System.out.println("Error: The min can't be greater than the max");
                System.exit(1);
            }
            for (int i = 0; i < num; i++) {
                int generated = (int) (Math.random() * (max - min)) + min;
                System.out.printf("%s%d", i == 0 ? "" : ", ", generated);
            }
        } catch (InputMismatchException ex) {
            System.out.println("Error: unable to parse the value");
            System.exit(1);
        }

    }
}

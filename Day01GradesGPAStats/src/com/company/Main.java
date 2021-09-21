package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> grades = getGradesArray();
        System.out.println("You've entered the following data:");
        for (String grade: grades) {
            System.out.println(
                    grade + " = " + letterGradeToNum(grade)
            );
        }
    }

    static ArrayList getGradesArray() {
        ArrayList<String> arr = new ArrayList<String>();
        System.out.println("Please enter letter grades, empty line to finish:");

        Scanner scan = new Scanner(System.in);
        String grade = scan.nextLine();
        while (grade != "") {
            arr.add(grade);
            grade = scan.nextLine();
        }
        return arr;
    }
    static double letterGradeToNum(String letterGrade) {
        switch (letterGrade) {
            case "D":
                return 1;
            case "C-":
                return 1.67;
            case "C":
                return 2;
            case "C+":
                return 2.33;
            case "B-":
                return 2.67;
            case "B":
                return 3;
            case "B+":
                return 3.33;
            case "A-":
                return 3.67;
            case "A":
                return 4;
            case "A+":
                return 4.33;
            case "F":
                return 0;
            default:
                return -1;
        }
    }
}

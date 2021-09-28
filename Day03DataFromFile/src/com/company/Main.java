package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static ArrayList<String> namesList = new ArrayList<>();
    static ArrayList<Double> numsList = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        // write your code here
        double totalLength = 0;
        readFile();
        Collections.sort(namesList);

        //print names array
        for (int i = 0; i < namesList.size(); i++) {
            System.out.println(namesList.get(i));
        }
        //print numbers array
        for (int i = 0; i < numsList.size(); i++) {
            System.out.println(numsList.get(i));
        }
        //print names array formatted
        for (int i = 0; i < namesList.size(); i++) {
            System.out.printf("%s%s", namesList.get(i), i != namesList.size() - 1 ? ", " : "");
        }
        //print nums array formatted
        System.out.println();
        for (int i = 0; i < numsList.size(); i++) {
            System.out.printf("%s%s", numsList.get(i), i != numsList.size() - 1 ? ", " : "");
        }
        for (int i = 0; i < namesList.size(); i++) {
            totalLength += namesList.get(i).length();
        }
        System.out.println();
        System.out.println(totalLength / namesList.size());

        ArrayList<String> duplicates = new ArrayList<>();
        for (int i = 0; i < namesList.size(); i++) {
            boolean printed = false;
            for (int j = i + 1; j < namesList.size(); j++) {
                String thisCheck = namesList.get(j);
                if (namesList.get(i).equals(thisCheck)) {
                    for (int k = 0; k < duplicates.size(); k++) {
                        if (thisCheck.equals(duplicates.get(k))) {
                            printed = true;
                             break;
                        } else {
                            printed = false;
                        }
                    }
                    if (printed) {
                        break;
                    }
                    System.out.println("Duplicate: " + thisCheck);
                    duplicates.add(thisCheck);
                    break;
                }
            }
        }
        writeDupes(duplicates);
    }
    static void writeDupes(ArrayList<String> dupes) {
        try (PrintWriter printWriter = new PrintWriter(new File("duplicates.txt"))) {
            for (int i = 0; i < dupes.size(); i++) {
                printWriter.println(dupes.get(i));
            }
        }catch (FileNotFoundException ex) {
            System.out.println(ex);
        }
    }
    static void readFile() {
        try (Scanner file = new Scanner(new File("input.txt"))) {
            while (file.hasNextLine()) {
                String thisLine = file.nextLine();
                if (isDouble(thisLine)) {
                    numsList.add(Double.parseDouble(thisLine));
                } else {
                    namesList.add(thisLine);
                }
            }
        } catch (IllegalStateException | FileNotFoundException|  NoSuchElementException | NullPointerException | NumberFormatException ex) {
            System.out.println(ex);
        }
    }
    static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}


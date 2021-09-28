package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Main {
    static ArrayList<Person> people = new ArrayList<Person>();
    static final String FILE_NAME = "people.txt";

    public static void main(String[] args) {
        // write your code here
        //read the file and fill the people Array
        readFile();
        for (Person p: people
             ) {
            p.print();
        }
    }

    static void readFile() {
        try (Scanner scanFile = new Scanner(new File(FILE_NAME))) {
            while (scanFile.hasNextLine()) {
                //separate the line's values into an array
                String line =  scanFile.nextLine();
                if (line.split(";", -1).length-1 != 1) {
                    throw new InvalidDataException("Line does not contain 1 semi-colon.");
                }
                String[] splitLine = line.split(";");
                if (splitLine[0].equals("") || splitLine[0].equals(null) || splitLine[1].equals("") || splitLine[1].equals(null)) {
                    throw new InvalidDataException("Line contains empty information");
                }
                people.add(new Person(splitLine[0], Integer.parseInt(splitLine[1])));
            }
        } catch (FileNotFoundException | IllegalStateException | PatternSyntaxException | NumberFormatException | InvalidDataException ex) {
            System.out.println("Error: " + ex);
        }
    }
}

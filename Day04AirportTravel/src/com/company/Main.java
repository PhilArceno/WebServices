package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
static ArrayList<Airport> airportList = new ArrayList<>();
    public static void main(String[] args) {
	// write your code here
        readFile();
        print();
    }

    public static void readFile() {
        try (Scanner scanFile = new Scanner(new File("airports.txt"))) {
            while (scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                Airport airport = new Airport(line);
                airportList.add(airport);
            }
        } catch (ParameterInvalidException | FileNotFoundException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void print() {
        for (int i = 0; i < airportList.size(); i++) {
            System.out.println(airportList.get(i).toDataLine());
        }
    }
}

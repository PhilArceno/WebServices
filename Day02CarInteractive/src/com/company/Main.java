package com.company;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.PatternSyntaxException;

public class Main {
    static ArrayList<Car> parking = new ArrayList<>();
    static Scanner scan = new Scanner(System.in);
    final static File FILE_NAME = new File("data.txt");

    public static void main(String[] args) {
        // write your code here
        boolean loop = true;
        readDataFromFile();
        while (loop) {
            System.out.println("1. Add a Car\n" +
                    "2. List all Cars (numbered)\n" +
                    "3. Modify a Car\n" +
                    "4. Delete a Car\n" +
                    "5. Compute and display statistics\n" +
                    "6. Exit\n");
            int input = Integer.valueOf(scan.nextLine());
            switch (input) {
                case 1:
                    AddCar();
                    break;
                case 2:
                    ListAllCars();
                    break;
                case 3:
                    ModifyCar();
                    break;
                case 4:
                    DeleteCar();
                    break;
                case 5:
                    ComputeAndDisplayStatistics();
                    break;
                case 6:
                    loop = false;
                    break;
                default:
                    System.out.println("Please choose a valid option.");
                    break;
            }
        }
        saveDataToFile();
    }
    static void readDataFromFile() {
        try (Scanner scanFile = new Scanner(FILE_NAME)) {
        while (scanFile.hasNextLine()) {
            String line = scanFile.nextLine();
            String[] data = line.split(";");
            parking.add(new Car(data[0], Integer.parseInt(data[1]), Double.parseDouble(data[2])));
        }
        }catch (FileNotFoundException | IllegalStateException | PatternSyntaxException ex) {
            System.out.println("Error : " + ex);
        }
    }
    static void saveDataToFile() {
        try (PrintWriter printWriter = new PrintWriter(new File("data.txt"))) {
            for (int i = 0; i < parking.size(); i++) {
                Car car = parking.get(i);
                printWriter.println(
                        car.getMakeModel() + ';' + car.getYearOfProd() + ';' + car.getEngSizeL()
                );
            }
        }catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

    static void AddCar() {
        System.out.println("Enter car's make & model:");
        String makeModel = scan.nextLine();
        System.out.println("Enter car's year of production:");
        int year = Integer.valueOf(scan.nextLine());
        System.out.println("Enter Engine size:");
        double engineSize = Double.valueOf(scan.nextLine());
        parking.add(new Car(makeModel,year,engineSize));
        saveDataToFile();
    }
    static void ListAllCars() {
        for (int i = 0; i < parking.size(); i++) {
            Car car = parking.get(i);
            System.out.println(car);
        }
    }
    static void ModifyCar() {
        try {
            System.out.println("Enter car # to modify:");
            int input = Integer.valueOf(scan.nextLine());
            if (input <= 0) {
                System.out.println("Error: Invalid input option");
                return;
            }
            input -= 1;
            Car car = parking.get(input);
            System.out.println(car + "\n");
            System.out.println("Enter new make model:");
            String makeModel = scan.nextLine();
            car.setMakeModel(makeModel);
            System.out.println("Enter new production year:");
            int year = Integer.valueOf(scan.nextLine());
            car.setYearOfProd(year);
            System.out.println("Enter new Engine Size:");
            double engineSize = Double.valueOf(scan.nextLine());
            car.setEngSizeL(engineSize);

            System.out.println("Car updated.");
            System.out.println("Car: " + car);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }
    static void DeleteCar() {
        try {
            System.out.println("Enter car # to be deleted:");
            int input = Integer.valueOf(scan.nextLine());
            if (input <= 0) {
                System.out.println("Error: Invalid input option");
                return;
            }
            Car car = parking.remove(input - 1);
            System.out.println("Car: " + car);
            System.out.printf("Car #%d: deleted.\n", input);
        } catch (NumberFormatException | IndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }
    static void ComputeAndDisplayStatistics() {
        int totalAges = 0;
        int oldest = Integer.MAX_VALUE;
        int oldestPos = 0;
        int engPos = 0;
        double largestEngine = Double.MIN_VALUE;
        for (int i = 0; i < parking.size(); i++) {
            Car car = parking.get(i);
            int year = car.getYearOfProd();
            double engine = car.getEngSizeL();
            totalAges += 2020 - year;
            if (year < oldest) {
                oldest = year;
                oldestPos = i;
            }
            if (engine > largestEngine) {
                largestEngine = engine;
                engPos = i;
            }
        }
        System.out.println("Oldest Car: " + parking.get(oldestPos));
        System.out.println("Largest Engine: " + parking.get(engPos));
        System.out.println("Average Age : " + (double) (totalAges / parking.size()));
    }
}

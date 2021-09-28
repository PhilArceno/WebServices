package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    public static final String FILE_NAME = "employees.txt";
    public static ArrayList<EmployeeSchedule> employeesSchedulesList = new ArrayList<>();

    public static void main(String[] args) {
	// write your code here
        loadDataFromFile();
        displayMenu();
    }

    public static void displayMenu() {
        while (true) {
        try {
            System.out.println("1. List all employees, sorted by Name\n" +
                    "2. Hire a new employee\n" +
                    "3. Display employees scheduled on each weekday\n" +
                    "0. Exit"
            );
            Scanner scanner = new Scanner(System.in);
            int input = scanner.nextInt();
            switch (input) {
                case 0:
                    //saveDataToFile();
                    System.exit(1);
                case 1:
                    listAllEmployeesByName();
                    break;
                case 2:
                    hireNewEmployee();
                    break;
                case 3:
                    displayWorkingOnEachWeekday();
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    break;
            }
        }catch (NoSuchElementException ex) {
            System.out.println("Error: " + ex);
            System.exit(1);
        }
        }
    }

    public static void displayWorkingOnEachWeekday() {
        try {
            System.out.println("Displaying names of employees who work each of the 7 workdays: Monday to Sunday");
            String[] daysOfTheWeek = {
                    "Sunday: ", "Monday: ", "Tuesday: ", "Wednesday: ", "Thursday: ", "Friday: ", "Saturday: "
            };
            for (EmployeeSchedule employee: employeesSchedulesList) {
                Weekday[] daysWorking = employee.getWorkdays();
                for (Weekday day: daysWorking) {
                    if (employee.isWorkingOn(day)) {

                    }
                }
            }
        }catch (Error ex) {
            System.out.println("Error: " + ex);
        }
    }
    public static void hireNewEmployee() {
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Adding a new employee.");
            System.out.println("Please enter name");
            String name = scan.nextLine();
            isEmptyString(name);

            System.out.println("Date hired in YYYY-MM-DD format (leave empty to assume today):");
            String date = scan.nextLine();
            isEmptyString(date);
            String[] dateArr = date.split("-");
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
            Date hiredDate = cal.getTime();
            System.out.println("Date hired will be " + hiredDate);

            System.out.println("Enter department name: ");
            String departmentName = scan.nextLine();
            isEmptyString(departmentName);

            String daysWorking = "";
            while(true) {
                System.out.println("Enter day of week when they work, empty to finish list: ");
                String day = scan.nextLine();
                if (day.isEmpty()) {
                    //delete last comma & space.
                    daysWorking = daysWorking.substring(0, daysWorking.length() - 1);
                    break;
                }
                daysWorking += day + ",";
            }
            EmployeeSchedule employee = new EmployeeSchedule(name, false, departmentName, hiredDate, daysWorking);
            employeesSchedulesList.add(employee);
            System.out.println("Created employee schedule: ");
            System.out.println(employee);
        } catch (InvalidValueException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void isEmptyString(String str) throws InvalidValueException {
        if (str.isEmpty()) {
            throw new InvalidValueException("Empty String");
        }
    }

    public static void listAllEmployeesByName() {
        try {
            //Comparator to compare and sort names
            Comparator<EmployeeSchedule> compareNames = (EmployeeSchedule e1, EmployeeSchedule e2) -> {
                return e1.getName().compareTo(e2.getName());
            };
            //sort it using the comparator we just created
            Collections.sort(employeesSchedulesList, compareNames);
            for (int i = 0; i < employeesSchedulesList.size(); i++) {
                System.out.println(
                        employeesSchedulesList.get(i)
                );
            }
        } catch (NullPointerException | ClassCastException | IndexOutOfBoundsException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void loadDataFromFile() {
        //try opening a scanner for employees.txt
        try (Scanner scanFile = new Scanner(new File(FILE_NAME))) {
            while(scanFile.hasNextLine()) {
                String line = scanFile.nextLine();
                if (line.isEmpty()) {
                    System.out.println("Error: Invalid line in " + FILE_NAME);
                    continue;
                }
                //create a new employee by sending the line to the constructor
                EmployeeSchedule employee = new EmployeeSchedule(line);
                employeesSchedulesList.add(employee);
            }
        } catch (FileNotFoundException | IllegalStateException | NoSuchElementException
                | InvalidValueException | UnsupportedOperationException | ClassCastException
                | NullPointerException | IllegalArgumentException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public static void saveDataToFile() {
        try (PrintWriter printWriter = new PrintWriter(new File(FILE_NAME))) {
            for (int i = 0; i < employeesSchedulesList.size(); i++) {
                printWriter.println(employeesSchedulesList.get(i));
            }
        }catch (FileNotFoundException | IndexOutOfBoundsException ex) {
            System.out.println(ex);
        }
    }
}

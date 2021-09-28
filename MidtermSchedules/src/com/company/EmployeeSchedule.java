package com.company;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.PatternSyntaxException;

class InvalidValueException extends Exception {
    InvalidValueException(String msg) {
        super(msg);
    }
}

enum Weekday { Sunday, Monday, Tuesday, Wednesday, Thursday, Friday, Saturday }


public class EmployeeSchedule {
    private String name; // 2-50 characters, not permitted are: ;^?@!~*
    private String department; // 2-50 characters, not permitted are: ;^?@!~*
    private Date dateHired; // year between 1900 and 2100
    private boolean isManager; //True or false
    private ArrayList<Weekday> workdaysList = new ArrayList<>(); // no duplicates allowed

    public EmployeeSchedule(String name, boolean isManager, String department, Date dateHired, String workweek) throws InvalidValueException {
        setName(name);
        setManager(isManager);
        setDepartment(department);
        setDateHired(dateHired);
        addWorkday(workweek);
    }

    public EmployeeSchedule(String dataLine) throws InvalidValueException {
        //Split string into String Array
        String[] data = dataLine.split(";");
        if (data.length != 5) {
            throw new InvalidValueException("Invalid number of values inputted");
        }
        setName(data[0]);
        setManager(Boolean.valueOf(data[1]));
        setDateHired(data[2]);
        setDepartment(data[3]);
        addWorkday(data[4]);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidValueException {
        checkString(name, "name");
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) throws InvalidValueException {
        checkString(department, "department");
        this.department = department;
    }

    private void checkString(String str, String input) throws InvalidValueException {
        //check string if empty
        if (str.isEmpty()) {
            throw new InvalidValueException("Empty String in " + input);
        }
        //check if name contains valid characters
        if (str.contains("^(?!.*;\\^?@!~\\*).*")) {
            throw new InvalidValueException("Invalid character inputted in " + input);
        }
    }

    public Date getDateHired() {
        return dateHired;
    }

    public void setDateHired(Date dateHired) throws InvalidValueException {
        Calendar min = Calendar.getInstance();
        //set the minimum year
        min.set(1900,1,1);
        Calendar max = Calendar.getInstance();
        //set the maximum year
        max.set(2100,12,31);
        if (!(dateHired.after(min.getTime()) && dateHired.before(max.getTime()))) {
            throw new InvalidValueException("Date is not between the years 1900 to 2100");
        }
        this.dateHired = dateHired;
    }

    public void setDateHired(String data) throws InvalidValueException {
        try {
            String[] date = data.split("-");
            //Create calendar to convert string, later to be converted to date
            Calendar cal = Calendar.getInstance();
            cal.set(Integer.parseInt(date[0]), Integer.parseInt(date[1]), Integer.parseInt(date[2]));
            //pass the correct params
            setDateHired(cal.getTime());
        } catch (PatternSyntaxException | NumberFormatException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public ArrayList<Weekday> getWorkdaysList() {
        return workdaysList;
    }

    public void addWorkday (Weekday weekday) throws InvalidValueException {
        try {
            for (int i = 0; i < workdaysList.size(); i++) {
                for (int j = i + 1; j < workdaysList.size(); j++) {
                    //if value already exists in array, throw an error
                    if (workdaysList.get(i).equals(workdaysList.get(j))) {
                        throw new InvalidValueException("Duplicate value found");
                    }
                }
            }
            //value doesn't exist in array, add it in
            this.workdaysList.add(weekday);
        } catch (InvalidValueException | IndexOutOfBoundsException | UnsupportedOperationException | ClassCastException
                | NullPointerException | IllegalArgumentException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public void addWorkday (String data) throws InvalidValueException {
        try {
            //split string containing weekday data
            String[] days = data.split(",");
            //check if string
            for (String day: days) {
                for (Weekday weekday: Weekday.values()) {
                    if (day.equals(weekday.toString())) {
                        addWorkday(weekday);
                    }
                }
            }
        } catch (PatternSyntaxException ex) {
            System.out.println("Error: " + ex);
        }
    }

    public Weekday[] getWorkdays() {
        return (Weekday[]) this.workdaysList.toArray();
    }

    public boolean isWorkingOn(Weekday weekday) {
        try {
            for (int i = 0; i < workdaysList.size(); i++) {
                //return true if they work on this weekday
                if (workdaysList.get(i).equals(weekday)) {
                    return true;
                }
            }
            return false;
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error: " + ex);
        }
        return false;
    }


    @Override
    public String toString() {
        String workweeks = "";
        try {
            //define initial string and add all workweeks into string.
        for (int i = 0; i < workdaysList.size(); i++) {
            if (i == workdaysList.size() - 1) {
                workweeks += workdaysList.get(i);
                break;
            }
            workweeks += workdaysList.get(i) + ", ";
        }
        } catch (IndexOutOfBoundsException ex) {
            System.out.println("Error: " + ex);
        }
        return String.format("%s, %s of %s department, hired on %s works on %s",
                this.name,
                this.isManager ? "manager" : "employee",
                this.department,
                this.dateHired.toString(),
                workweeks
        );
    }
}


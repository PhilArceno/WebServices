package com.company;

public class Person {
    private String name;
    private int age;

    public Person (String name, int age) throws InvalidDataException {
        if (checkName(name) && checkAge(age)) {
            this.name = name;
            this.age = age;
        };
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws InvalidDataException {
        if (checkName(name)) {
            this.name = name;
        }
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws InvalidDataException {
        if (checkAge(age)) {
            this.age = age;
        }
    }

    public void print() {
        System.out.printf("Person %s is %d y/o.\n", this.name, this.age);
    }

    private boolean checkName(String name) throws InvalidDataException {
            if (name == "") {
                throw new InvalidDataException("String is empty.");
            }
            if (!name.matches("^[A-Za-z\\s-.]+")) {
                throw new InvalidDataException("Name contains illegal characters.");
            }
            return true;
    }
    private boolean checkAge(int age) throws InvalidDataException {
            if (!(age >= 0 && age <= 150)) {
                throw new InvalidDataException("Age is not within the range of 0-150 years old.");
            }
        return true;
    }
}

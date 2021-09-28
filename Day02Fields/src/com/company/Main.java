package com.company;

import java.util.ArrayList;
import java.util.Scanner;

class Person {
    private final String name;
    private final int age;

    public Person (String name, int age) {
        this.name = name;
        this.age = age;
    }


    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }

    @Override
    public String toString() {
        return this.name + ", " + this.age + " y/o";
    }
}

public class Main {

    public static void main(String[] args) {
	// write your code here
        ArrayList<Person> people = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        for (int i = 0; i < 3; i++) {
            try {
                System.out.printf("Enter name of Person %d: ", i + 1);
                String name = input.nextLine();

                System.out.printf("Enter age of Person %d: ", i + 1);
                int age = Integer.valueOf(input.nextLine());
                Person p = new Person(name, age);
                people.add(p);
            } catch (NumberFormatException ex) {
                System.out.println("Error: " + ex);
            }
        }
        for (int i = 0; i < people.size(); i++) {
            int age = people.get(i).getAge();
            System.out.printf("Person #%d is %s who is %d y/o", i+1, people.get(i).getName(), age);
            System.out.println();
        }

        int oldestAge = Integer.MIN_VALUE;
        int oldestPosition = 0;
        int shortestLength = Integer.MAX_VALUE;
        int shortestPos = 0;
        for (int i = 0; i < people.size(); i++) {
            if (people.get(i).getAge() > oldestAge) {
                oldestAge = people.get(i).getAge();
                oldestPosition = i;
            }
            if (people.get(i).getName().length() < shortestLength) {
                shortestLength = people.get(i).getName().length();
                shortestPos = i;
            }
        }
        System.out.println("The oldest is " + people.get(oldestPosition));
        System.out.println("The one with the shortest name is " + people.get(shortestPos));
    }
}

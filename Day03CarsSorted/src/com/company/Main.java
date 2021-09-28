package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    static ArrayList<Car> parking = new ArrayList<>();

    public static void main(String[] args) {
	// write your code here
    addToParking();
    System.out.println("Sorted by Make/Model:");
    System.out.println("---------------------\n");
    Collections.sort(parking, new SortByMakeModel());
    print();

    System.out.println("Sorted by Engine Size:");
    System.out.println("---------------------\n");
    Collections.sort(parking, new SortByEngineSizeL());
    print();

    System.out.println("Sorted by Production Year:");
    System.out.println("---------------------\n");
    Collections.sort(parking, new SortByProdYear());
    print();

        System.out.println("Sorted by Production Year and make/model:");
        System.out.println("---------------------\n");
        Comparator<Car> SortByProdYearMakeModel = (c1,c2) -> {
            return c1.getProdYear() > c2.getProdYear() ? 1 : c1.getProdYear() < c2.getProdYear() ? -1 : c1.getMakeModel().compareTo(c2.getMakeModel());
        };
        Collections.sort(parking, SortByProdYearMakeModel);
        print();
    }
    static void addToParking() {
        Car car1 = new Car("Honda Civic", 2.1, 2008);
        Car car2 = new Car("Toyota Corolla", 2.3, 2015);
        Car car3 = new Car("Hyundai Elantra", 2.5, 2012);
        Car car4 = new Car("Honda Accord", 2.0, 2018);
        Car car5 = new Car("Mazda CX-5", 3.0, 2021);
        Car car6 = new Car("Honda Crv", 2.8, 2008);

        parking.add(car1);
        parking.add(car2);
        parking.add(car3);
        parking.add(car4);
        parking.add(car5);
        parking.add(car6);
    }
    static void print() {
        for (int i = 0; i < parking.size(); i++) {
            Car car = parking.get(i);
            System.out.printf("Car %d: %s, %.1f, %d\n",
                    i+1,
                    car.getMakeModel(),
                    car.getEngineSizeL(),
                    car.getProdYear());
        }
    }
}

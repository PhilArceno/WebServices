package com.company;

import java.util.Comparator;

public class SortByMakeModel implements Comparator<Car> {
    public int compare(Car c1, Car c2) {
        return c1.getMakeModel().compareTo(c2.getMakeModel());
    }
}

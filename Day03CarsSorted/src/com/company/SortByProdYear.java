package com.company;

import java.util.Comparator;

public class SortByProdYear implements Comparator<Car> {
    public int compare(Car c1, Car c2) {
        int compared = c1.getProdYear() - c2.getProdYear();
        if (compared == 0) {
            return c1.getMakeModel().compareTo(c2.getMakeModel());
        }
        return compared;
    }
}

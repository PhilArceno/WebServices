package com.company;

import java.util.Comparator;

public class SortByEngineSizeL implements Comparator<Car> {
    public int compare(Car c1, Car c2) {
        return Double.compare(c1.getEngineSizeL(),c2.getEngineSizeL());
    }
}

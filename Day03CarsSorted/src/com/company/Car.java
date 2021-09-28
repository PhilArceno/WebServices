package com.company;

public class Car {
    private String makeModel;
    private double engineSizeL;
    int prodYear;

    public Car (String makeModel, double engineSizeL, int prodYear) {
        this.makeModel = makeModel;
        this.engineSizeL = engineSizeL;
        this.prodYear = prodYear;
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public double getEngineSizeL() {
        return engineSizeL;
    }

    public void setEngineSizeL(double engineSizeL) {
        this.engineSizeL = engineSizeL;
    }

    public int getProdYear() {
        return prodYear;
    }

    public void setProdYear(int prodYear) {
        this.prodYear = prodYear;
    }
}

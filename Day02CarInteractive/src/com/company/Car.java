package com.company;

public class Car {
    String makeModel;
    int yearOfProd;
    double engSizeL;

    public Car (String makeModel, int yearOfProd, double engSizeL) {
        if (validateInputs(makeModel,yearOfProd,engSizeL)) {
            this.makeModel = makeModel;
            this.yearOfProd = yearOfProd;
            this.engSizeL = engSizeL;
        }
    }

    private boolean validateInputs(String makeModel, int yearOfProd, double engSizeL) {
        if (makeModel.length() >= 2 &&
                (yearOfProd >= 1900 && yearOfProd <= 2100) &&
                (engSizeL >= 0 && engSizeL <= 10)) {
            return true;
        } else {
            return false;
        }
    }

    public String getMakeModel() {
        return makeModel;
    }

    public void setMakeModel(String makeModel) {
        this.makeModel = makeModel;
    }

    public int getYearOfProd() {
        return yearOfProd;
    }

    public void setYearOfProd(int yearOfProd) {
        this.yearOfProd = yearOfProd;
    }

    public double getEngSizeL() {
        return engSizeL;
    }

    public void setEngSizeL(double engSizeL) {
        this.engSizeL = engSizeL;
    }

    @Override
    public String toString() {
        return makeModel + " made in " + yearOfProd + " with engine " + engSizeL + "L";
    }
}

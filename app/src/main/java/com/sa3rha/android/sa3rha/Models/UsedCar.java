package com.sa3rha.android.sa3rha.Models;

/**
 * Created by cz on 20/08/17.
 */

public class UsedCar extends Car {
    String carName,carPrice,carImage;



    public UsedCar(String carName, String carPrice, String carImage) {
        this.carName = carName;
        this.carPrice=carPrice;
        this.carImage=carImage;


    }

    public String getCarName() {
        return carName;
    }

    public String getCarPrice() {
        return carPrice;
    }
    public String getCarImage() {
        return carImage;
    }
}

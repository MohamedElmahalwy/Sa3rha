package com.sa3rha.android.sa3rha.Models;

/**
 * Created by cz on 20/08/17.
 */

public class UsedCar  {
    String carName;
    String carPrice;
    String carImage;

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public void setCarImage(String carImage) {
        this.carImage = carImage;
    }

    public String getUsedId() {
        return UsedId;
    }

    public void setUsedId(String usedId) {
        UsedId = usedId;
    }

    String UsedId;





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

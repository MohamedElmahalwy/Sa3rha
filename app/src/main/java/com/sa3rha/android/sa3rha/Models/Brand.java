package com.sa3rha.android.sa3rha.Models;

/**
 * Created by Mohamed Hefny on 8/20/2017.
 */

public class Brand {
    int brandId;
    String brandTitele, brandLogo;

    public Brand (int brandId, String brandTitele, String brandLogo){
        this.brandId = brandId;
        this.brandTitele = brandTitele;
        this.brandLogo = brandLogo;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrandTitele() {
        return brandTitele;
    }

    public String getBrandLogo() {
        return brandLogo;
    }
}

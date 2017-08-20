package com.sa3rha.android.sa3rha.Models;

/**
 * Created by Mohamed Hefny on 8/20/2017.
 */

public class Brand {
    int brandId;
    String brandTitele, brandLogo;

    public Brand (int brandId, String brandTitle, String brandLogo){
        this.brandId = brandId;
        this.brandTitele = brandTitle;
        this.brandLogo = brandLogo;
    }

    public int getBrandId() {
        return brandId;
    }

    public String getBrandTitle() {
        return brandTitele;
    }

    public String getBrandLogo() {
        return brandLogo;
    }
}

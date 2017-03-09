package com.example.muhammadimran.minimarathon.Models;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class AddProductModel {
    private String productName;
    private String productPrise;
    private String productDescription;

    public AddProductModel() {
    }

    public AddProductModel(String productName, String productPrise, String productDescription) {
        this.productName = productName;
        this.productPrise = productPrise;
        this.productDescription = productDescription;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrise() {
        return productPrise;
    }

    public void setProductPrise(String productPrise) {
        this.productPrise = productPrise;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}

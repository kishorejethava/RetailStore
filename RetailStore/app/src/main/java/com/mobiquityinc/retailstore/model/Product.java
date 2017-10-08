package com.mobiquityinc.retailstore.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by KK on 10/5/2017.
 */

public class Product implements Serializable{
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @SerializedName("productName")
    private String productName;
    @SerializedName("productImage")
    private String productImage;
    @SerializedName("productPrice")
    private String productPrice;
    @SerializedName("category")
    private String category;
}

package com.example.shoponline;

import android.widget.ImageView;

import java.io.Serializable;

public class ProductModel implements Serializable {
    private String productName;
    private int imageView;
    private String price;
    private String reteta;

    public String getReteta() {
        return reteta;
    }

    public void setReteta(String reteta) {
        this.reteta = reteta;
    }

    public ProductModel(String productName, int imageView, String price) {
        this.productName = productName;
        this.imageView = imageView;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}

package com.example.yuan.myapplication.bean;

import android.widget.TextView;

public class User {
    private double price;
    private String title;


    public User(double price, String title) {
        this.price = price;
        this.title = title;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

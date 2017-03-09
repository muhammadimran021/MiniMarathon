package com.example.muhammadimran.minimarathon.Models;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class AddStoreModel {
    private String logo;
    private String name;
    private String address;
    private String ratings;
    private String deliveryfee;
    private String orderamount;

    public AddStoreModel() {
    }

    public AddStoreModel(String logo, String name, String address, String ratings, String deliveryfee, String orderamount) {
        this.logo = logo;
        this.name = name;
        this.address = address;
        this.ratings = ratings;
        this.deliveryfee = deliveryfee;
        this.orderamount = orderamount;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRatings() {
        return ratings;
    }

    public void setRatings(String ratings) {
        this.ratings = ratings;
    }

    public String getDeliveryfee() {
        return deliveryfee;
    }

    public void setDeliveryfee(String deliveryfee) {
        this.deliveryfee = deliveryfee;
    }

    public String getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(String orderamount) {
        this.orderamount = orderamount;
    }
}

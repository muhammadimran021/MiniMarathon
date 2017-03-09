package com.example.muhammadimran.minimarathon.Models;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class PlaceOrder {
    private String fname;
    private String lname;
    private String mobile;
    private String company;
    private String street;
    private String floor;
    private String area;
    private String city;
    private String comment;

    public PlaceOrder() {
    }

    public PlaceOrder(String fname, String lname, String mobile, String company, String street, String floor, String area, String city, String comment) {
        this.fname = fname;
        this.lname = lname;
        this.mobile = mobile;
        this.company = company;
        this.street = street;
        this.floor = floor;
        this.area = area;
        this.city = city;
        this.comment = comment;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

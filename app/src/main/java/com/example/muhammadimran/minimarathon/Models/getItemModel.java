package com.example.muhammadimran.minimarathon.Models;

/**
 * Created by muhammad imran on 3/7/2017.
 */

public class getItemModel {
    private String ItemName;
    private String ItemPrise;

    public getItemModel() {
    }

    public getItemModel(String itemName, String itemPrise) {
        ItemName = itemName;
        ItemPrise = itemPrise;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getItemPrise() {
        return ItemPrise;
    }

    public void setItemPrise(String itemPrise) {
        ItemPrise = itemPrise;
    }
}

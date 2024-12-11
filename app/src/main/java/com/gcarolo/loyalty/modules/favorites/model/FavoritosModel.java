package com.gcarolo.loyalty.modules.favorites.model;

public class FavoritosModel {

    private String title;
    private String phone;
    private String address;
    private String image;


    public FavoritosModel(String title, String phone, String address, String image) {
        this.title = title;
        this.phone = phone;
        this.address = address;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getImage() {
        return image;
    }

}

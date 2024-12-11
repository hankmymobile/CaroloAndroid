package com.gcarolo.loyalty.modules.notification.model;

public class NotificationsModel {

    private String type;
    private String date;
    private String message;
    private String image;


    public NotificationsModel(String type, String date, String message, String image) {
        this.type = type;
        this.date = date;
        this.message = message;
        this.image = image;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public String getImage() {
        return image;
    }

}

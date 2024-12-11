package com.gcarolo.loyalty.modules.restaurants.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RestaurantsModel implements Parcelable {

    private String title;
    private String phone;
    private String address;
    private ArrayList<String> images;

    private String url;


    public RestaurantsModel(String title, String phone, String address, ArrayList<String> image, String url) {
        this.title = title;
        this.phone = phone;
        this.address = address;
        this.images = image;
        this.url = url;
    }

    protected RestaurantsModel(Parcel in) {
        title = in.readString();
        phone = in.readString();
        address = in.readString();
        images = in.createStringArrayList();
        url = in.readString();
    }

    public static final Creator<RestaurantsModel> CREATOR = new Creator<RestaurantsModel>() {
        @Override
        public RestaurantsModel createFromParcel(Parcel in) {
            return new RestaurantsModel(in);
        }

        @Override
        public RestaurantsModel[] newArray(int size) {
            return new RestaurantsModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.title);
        parcel.writeString(this.phone);
        parcel.writeString(this.address);
        parcel.writeList(this.images);
        parcel.writeString(this.url);
    }
}

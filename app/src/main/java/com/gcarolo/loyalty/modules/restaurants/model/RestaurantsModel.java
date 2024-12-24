package com.gcarolo.loyalty.modules.restaurants.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class RestaurantsModel implements Parcelable {

    private String title;
    private String phone;
    private String address;
    private String comments;
    private String openTable;
    private ArrayList<String> images;
    private String url;
    private int favorite = 0;


    public RestaurantsModel(int favorite, String title, String phone, String address, String comments, String openTable, ArrayList<String> image, String url) {
        this.favorite = favorite;
        this.title = title;
        this.phone = phone;
        this.address = address;
        this.comments = comments;
        this.openTable = openTable;
        this.images = image;
        this.url = url;
    }

    protected RestaurantsModel(Parcel in) {
        favorite = in.readInt();
        title = in.readString();
        phone = in.readString();
        address = in.readString();
        comments = in.readString();
        openTable = in.readString();
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOpenTable() {
        return openTable;
    }

    public void setOpenTable(String openTable) {
        this.openTable = openTable;
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

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite(int favorite) {
        this.favorite = favorite;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeInt(this.favorite);
        parcel.writeString(this.title);
        parcel.writeString(this.phone);
        parcel.writeString(this.address);
        parcel.writeString(this.comments);
        parcel.writeString(this.openTable);
        parcel.writeList(this.images);
        parcel.writeString(this.url);
    }
}

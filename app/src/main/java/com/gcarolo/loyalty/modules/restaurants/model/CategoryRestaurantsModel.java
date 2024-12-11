package com.gcarolo.loyalty.modules.restaurants.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CategoryRestaurantsModel implements Parcelable {

    private String categoryName;
    private ArrayList<RestaurantsModel> restaurantsModels;

    public CategoryRestaurantsModel(ArrayList<RestaurantsModel> restaurantsModels) {
        this.restaurantsModels = restaurantsModels;
    }

    protected CategoryRestaurantsModel(Parcel in) {
        categoryName = in.readString();
        restaurantsModels = in.createTypedArrayList(RestaurantsModel.CREATOR);
    }

    public static final Creator<CategoryRestaurantsModel> CREATOR = new Creator<CategoryRestaurantsModel>() {
        @Override
        public CategoryRestaurantsModel createFromParcel(Parcel in) {
            return new CategoryRestaurantsModel(in);
        }

        @Override
        public CategoryRestaurantsModel[] newArray(int size) {
            return new CategoryRestaurantsModel[size];
        }
    };

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ArrayList<RestaurantsModel> getRestaurantsModels() {
        return restaurantsModels;
    }

    public void setRestaurantsModels(ArrayList<RestaurantsModel> restaurantsModels) {
        this.restaurantsModels = restaurantsModels;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(this.categoryName);
        parcel.writeList(this.restaurantsModels);
    }
}

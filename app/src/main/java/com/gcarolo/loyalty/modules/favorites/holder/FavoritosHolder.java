package com.gcarolo.loyalty.modules.favorites.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.favorites.model.FavoritosModel;

public class FavoritosHolder extends RecyclerView.ViewHolder {

    private FavoritosModel model;
    private TextView tvTitle;
    private TextView tvPhone;
    private TextView tvAddress;
    private ImageView imgRestaurant;

    public FavoritosHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvPhone = itemView.findViewById(R.id.tv_phone);
        this.tvAddress = itemView.findViewById(R.id.tv_address);
        this.imgRestaurant = itemView.findViewById(R.id.img_restaurant);
    }

    public void setModel(FavoritosModel model) {
        this.model = model;
    }

    public void bindHolder() {
        if (null != this.model) {
            this.tvTitle.setText(this.model.getTitle());
            this.tvPhone.setText(this.model.getPhone());
            this.tvAddress.setText(this.model.getAddress());

        }
    }

}

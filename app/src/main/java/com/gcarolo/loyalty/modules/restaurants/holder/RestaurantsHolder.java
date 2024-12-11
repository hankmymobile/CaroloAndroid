package com.gcarolo.loyalty.modules.restaurants.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.utilities.AppContext;

public class RestaurantsHolder extends RecyclerView.ViewHolder {

    private RestaurantsModel model;
    private TextView tvTitle;
    private TextView tvPhone;
    private TextView tvAddress;
    private ImageView imgRestaurant;

    private View itemViewS;

    private IRestauranteHolderView listener;

    public RestaurantsHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvPhone = itemView.findViewById(R.id.tv_phone);
        this.tvAddress = itemView.findViewById(R.id.tv_address);
        this.imgRestaurant = itemView.findViewById(R.id.img_restaurant);
        this.itemViewS = itemView;
    }

    public void setListener(IRestauranteHolderView listener) {
        this.listener = listener;
    }

    public void setModel(RestaurantsModel model) {
        this.model = model;
    }

    public void bindHolder() {
        if (null != this.model) {
            this.tvTitle.setText(this.model.getTitle());
            this.tvPhone.setText(this.model.getPhone());
            this.tvAddress.setText(this.model.getAddress());
            //this.imgRestaurant.setImageResource(AppContext.getInstance().getContext().getResources().getIdentifier(this.model.getImage(), "drawable", AppContext.getInstance().getContext().getPackageName()));

            Glide
                    .with(AppContext.getInstance().getContext())
                    .load(AppContext.getInstance().getContext().getResources().getIdentifier(this.model.getImages().get(0), "drawable", AppContext.getInstance().getContext().getPackageName()))
                    .centerInside()
                    .into(imgRestaurant);

            this.itemViewS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.pressDetail(model);
                }
            });
        }
    }

}

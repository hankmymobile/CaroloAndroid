package com.gcarolo.loyalty.modules.restaurants.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.restaurants.holder.IRestauranteHolderView;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.modules.restaurants.holder.RestaurantsHolder;

import java.util.List;

public class RestaurantsAdapter extends RecyclerView.Adapter {
    private List<RestaurantsModel> myRestaurants;

    private IRestauranteHolderView listener;

    public RestaurantsAdapter(List<RestaurantsModel> myRestaurants) {
        this.myRestaurants = myRestaurants;
    }

    public void setListener(IRestauranteHolderView listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.restaurantes_holder, parent, false);

        return new RestaurantsHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RestaurantsModel model = this.myRestaurants.get(position);
        RestaurantsHolder holderItem = (RestaurantsHolder) holder;
        holderItem.setListener(listener);
        holderItem.setModel(model);
        holderItem.bindHolder();
    }

    @Override
    public int getItemCount() {
        return this.myRestaurants.size();
    }

    public void setMyRestaurants(List<RestaurantsModel> myRestaurants) {
        this.myRestaurants = myRestaurants;
    }


}

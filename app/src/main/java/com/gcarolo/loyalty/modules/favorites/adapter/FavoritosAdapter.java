package com.gcarolo.loyalty.modules.favorites.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.favorites.holder.FavoritosHolder;
import com.gcarolo.loyalty.modules.favorites.model.FavoritosModel;

import java.util.List;

public class FavoritosAdapter extends RecyclerView.Adapter {
    private List<FavoritosModel> myRestaurants;

    public FavoritosAdapter(List<FavoritosModel> myRestaurants) {
        this.myRestaurants = myRestaurants;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.favoritos_holder, parent, false);

        return new FavoritosHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FavoritosModel model = this.myRestaurants.get(position);
        FavoritosHolder holderItem = (FavoritosHolder) holder;
        holderItem.setModel(model);
        holderItem.bindHolder();
    }

    @Override
    public int getItemCount() {
        return this.myRestaurants.size();
    }

    public void setMyRestaurants(List<FavoritosModel> myRestaurants) {
        this.myRestaurants = myRestaurants;
    }


}

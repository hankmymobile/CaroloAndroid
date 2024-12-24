package com.gcarolo.loyalty.modules.restaurants.holder;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.utilities.AppContext;
import com.gcarolo.loyalty.utilities.PropertiesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RestaurantsHolder extends RecyclerView.ViewHolder {

    private RestaurantsModel model;
    private TextView tvTitle;
    private TextView tvPhone;
    private TextView tvAddress;
    private ImageView imgRestaurant;
    private ImageView imgFavorite;

    private View itemViewS;

    private IRestauranteHolderView listener;

    private PropertiesManager propertiesManager;

    public RestaurantsHolder(@NonNull View itemView) {
        super(itemView);
        this.propertiesManager = new PropertiesManager();
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvPhone = itemView.findViewById(R.id.tv_phone);
        this.tvAddress = itemView.findViewById(R.id.tv_address);
        this.imgRestaurant = itemView.findViewById(R.id.img_restaurant);
        this.imgFavorite = itemView.findViewById(R.id.img_favoritos);
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
            if (model.getFavorite() == 0){
                imgFavorite.setColorFilter(ContextCompat.getColor(AppContext.getInstance().getContext(), R.color.white), android.graphics.PorterDuff.Mode.MULTIPLY);
            }else{
                imgFavorite.setColorFilter(ContextCompat.getColor(AppContext.getInstance().getContext(), R.color.warning_color), android.graphics.PorterDuff.Mode.MULTIPLY);
            }
            this.tvTitle.setText(this.model.getTitle());
            this.tvPhone.setText(this.model.getPhone());
            this.tvAddress.setText(this.model.getAddress());
            Glide
                    .with(AppContext.getInstance().getContext())
                    .load(AppContext.getInstance().getContext().getResources().getIdentifier(this.model.getImages().get(0), "drawable", AppContext.getInstance().getContext().getPackageName()))
                    .apply(new RequestOptions()
                            .fitCenter()
                            .format(DecodeFormat.PREFER_ARGB_8888)
                            .override(Target.SIZE_ORIGINAL))
                    .centerInside()
                    .into(imgRestaurant);

            this.itemViewS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.pressDetail(model);
                }
            });

            this.imgFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Gson gson = new Gson();
                    String jsonStored = propertiesManager.readProperty(PropertiesManager.StoredProperty.Favorites);
                    Type listType = new TypeToken< ArrayList<RestaurantsModel> >(){}.getType();
                    List<RestaurantsModel> restaurantsModels = gson.fromJson(jsonStored, listType);
                    if (restaurantsModels == null){
                        ArrayList<RestaurantsModel> restaurantsModelsSave = new ArrayList<>();
                        model.setFavorite(1);
                        restaurantsModelsSave.add(model);
                        String jsonSave = gson.toJson(restaurantsModelsSave);
                        propertiesManager.writeProperty(PropertiesManager.StoredProperty.Favorites, jsonSave);
                    }else{
                        if (model.getFavorite() == 0) {
                            model.setFavorite(1);
                            restaurantsModels.add(model);
                        }else{
                            model.setFavorite(0);
                            int position = -1;
                            for (int i = 0 ; i < restaurantsModels.size(); i++){
                                if (restaurantsModels.get(i).getTitle().equalsIgnoreCase(model.getTitle())) {
                                    position = i;
                                }
                            }
                            if (position >= 0) {
                                restaurantsModels.remove(position);
                            }
                        }
                        String jsonSave = gson.toJson(restaurantsModels);
                        propertiesManager.writeProperty(PropertiesManager.StoredProperty.Favorites, jsonSave);
                    }
                    listener.updateFavorite();
                }
            });
        }
    }

}

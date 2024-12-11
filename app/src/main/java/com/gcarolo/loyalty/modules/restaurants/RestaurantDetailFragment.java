package com.gcarolo.loyalty.modules.restaurants;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.restaurants.adapter.RestaurantsAdapter;
import com.gcarolo.loyalty.modules.restaurants.holder.IRestauranteHolderView;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.utilities.AppContext;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailFragment extends BaseFragment {

    private View rootView;

    private RestaurantsModel restaurantsModel = null;
    public RestaurantDetailFragment() {
        // Required empty public constructor
    }

    public static RestaurantDetailFragment newInstance() {
        RestaurantDetailFragment fragment = new RestaurantDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == this.rootView) {
            this.rootView = inflater.inflate(R.layout.fragment_restaurant_detail, container, false);
            restaurantsModel = getArguments().getParcelable("restaurante");
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.GONE);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    private void configViews() {
        TextView lblNameRestaurant = rootView.findViewById(R.id.lbl_name_restaurant);
        TextView lblDetailRestaurant = rootView.findViewById(R.id.lbl_detail_restaurant);
        TextView lblPhoneRestaurant = rootView.findViewById(R.id.lbl_phone_restaurant);
        ImageView imgRestaurant = rootView.findViewById(R.id.img_restaurant);
        lblNameRestaurant.setText(restaurantsModel.getTitle());
        lblDetailRestaurant.setText(restaurantsModel.getAddress());
        lblPhoneRestaurant.setText(restaurantsModel.getPhone());

        Glide
                .with(AppContext.getInstance().getContext())
                .load(AppContext.getInstance().getContext().getResources().getIdentifier(restaurantsModel.getImages().get(0), "drawable", AppContext.getInstance().getContext().getPackageName()))
                .centerInside()
                .into(imgRestaurant);
    }

}
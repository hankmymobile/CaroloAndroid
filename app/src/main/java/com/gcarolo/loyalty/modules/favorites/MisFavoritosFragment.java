package com.gcarolo.loyalty.modules.favorites;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.restaurants.RestaurantDetailFragment;
import com.gcarolo.loyalty.modules.restaurants.adapter.RestaurantsAdapter;
import com.gcarolo.loyalty.modules.restaurants.holder.IRestauranteHolderView;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.utilities.PropertiesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MisFavoritosFragment extends BaseFragment implements IRestauranteHolderView {

    private View rootView;
    private RecyclerView recyclerView;
    private RestaurantsAdapter adapter;
    private PropertiesManager propertiesManager;
    List<RestaurantsModel> restaurantsModelsSaved;

    private HashMap<String, ArrayList<RestaurantsModel>> categoryRestaurantsModel;
    Bitmap bitmap;
    public MisFavoritosFragment() {
        // Required empty public constructor
    }

    public static MisFavoritosFragment newInstance() {
        MisFavoritosFragment fragment = new MisFavoritosFragment();
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
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
            propertiesManager = new PropertiesManager();
            this.rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
            configViews();
        }
        return rootView;
    }

    /*private void configViews() {

        LinearLayout lnNodata = rootView.findViewById(R.id.ln_nodata);
        lnNodata.setVisibility(View.GONE);

        LinearLayout lnData = rootView.findViewById(R.id.ln_data);
        lnData.setVisibility(View.VISIBLE);

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        this.adapter = new FavoritosAdapter(getDataMock());
        this.recyclerView.setAdapter(adapter);
    }*/

    private void configViews() {
        Gson gson = new Gson();
        String jsonStored = propertiesManager.readProperty(PropertiesManager.StoredProperty.Favorites);
        Type listType = new TypeToken< ArrayList<RestaurantsModel> >(){}.getType();
        restaurantsModelsSaved = gson.fromJson(jsonStored, listType);

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        reloadRestaurants();
    }
    private void reloadRestaurants(){
        ArrayList<RestaurantsModel> totales = new ArrayList<>();
        if (restaurantsModelsSaved != null) {
            totales.addAll(restaurantsModelsSaved);
        }
        this.adapter = new RestaurantsAdapter(totales);
        this.adapter.setListener(this);
        this.recyclerView.setAdapter(adapter);
    }

    @Override
    public void pressDetail(RestaurantsModel restaurantsModel) {
        Log.i("Data", restaurantsModel.toString());
        RestaurantDetailFragment fragment = RestaurantDetailFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putParcelable("restaurante",restaurantsModel);
        fragment.setArguments(bundle);
        displayFragment(fragment, bundle);
    }

    @Override
    public void updateFavorite() {
        this.configViews();
    }

    public int isFavorite(String name){
        for (int i = 0; i < restaurantsModelsSaved.size(); i++){
            RestaurantsModel restaurantsModel = restaurantsModelsSaved.get(i);
            if (restaurantsModel.getTitle().equalsIgnoreCase(name)) {
                return 1;
            }
        }
        return 0;
    }
}
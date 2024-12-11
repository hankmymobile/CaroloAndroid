package com.gcarolo.loyalty.modules.favorites;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.favorites.adapter.FavoritosAdapter;
import com.gcarolo.loyalty.modules.favorites.model.FavoritosModel;
import com.gcarolo.loyalty.modules.restaurants.adapter.RestaurantsAdapter;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;

import java.util.ArrayList;
import java.util.List;

public class MisFavoritosFragment extends BaseFragment {

    private View rootView;
    private RecyclerView recyclerView;
    private FavoritosAdapter adapter;
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
            this.rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

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
    }

    private List<FavoritosModel> getDataMock() {
        List<FavoritosModel> servicesMock = new ArrayList<>();
        servicesMock.add(new FavoritosModel("Aromas Duraznos 1", "Tel. 5548485618", "Plaza Parque Duraznos 39 Col. Bosques de las Lomas, Miguel Hidalgo, 11700 Ciudad de México, CDMX, México", ""));

        return servicesMock;
    }
}
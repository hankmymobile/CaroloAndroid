package com.gcarolo.loyalty.modules.restaurants;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.invoice.InvoiceFragment;
import com.gcarolo.loyalty.modules.payment.PaymentDialogFragment;
import com.gcarolo.loyalty.modules.restaurants.adapter.RestaurantsAdapter;
import com.gcarolo.loyalty.modules.restaurants.holder.IRestauranteHolderView;
import com.gcarolo.loyalty.modules.restaurants.model.CategoryRestaurantsModel;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantsFragment extends BaseFragment implements IRestauranteHolderView {

    private View rootView;
    private RecyclerView recyclerView;
    private RestaurantsAdapter adapter;
    private int apuntador = -1;
    Bitmap bitmap;

    private HashMap<String, ArrayList<RestaurantsModel>> categoryRestaurantsModel;
    public RestaurantsFragment() {
        // Required empty public constructor
    }

    public static RestaurantsFragment newInstance() {
        RestaurantsFragment fragment = new RestaurantsFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_restaurant, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    private void configViews() {
        createRestaurantCategories();

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        reloadRestaurants(-1);
    }

    private void createRestaurantCategories (){
        categoryRestaurantsModel = new HashMap<>();

        ArrayList<RestaurantsModel> cat1 = new ArrayList<>();
        ArrayList<String> imgFarina = new ArrayList<>();
        imgFarina.add("farina");imgFarina.add("farina1");imgFarina.add("farina2");imgFarina.add("farina3");imgFarina.add("farina4");imgFarina.add("farina5");
        imgFarina.add("farina6");imgFarina.add("farina7");imgFarina.add("farina8");imgFarina.add("farina9");imgFarina.add("farina10");imgFarina.add("farina11");imgFarina.add("farina12");

        cat1.add(new RestaurantsModel("Farina Lomas", "Tel. 5526232112", "Monte Líbano 13-A, Lomas de Chapultepec VI Secc, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel("Farina San Ángel", "Tel. 5555504702", "Av. Altavista 147, San Ángel Inn, Álvaro Obregón, 01060 Ciudad de México, CDMX, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel("Farina Roma", "Tel. 5575890520", "Chihuahua 139, Roma Nte., Cuauhtémoc, 06700 Ciudad de México, CDMX, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel("Farina Polanco", "Tel. 5578259921", "Av. Isaac Newton 53-1, Polanco, Polanco IV Secc, Miguel Hidalgo, 11560 Ciudad de México, CDMX, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel("Farina Interlomas", "Tel. 5559988148", "PA-38, Blvd. Palmas Hills 1, Valle de las Palmas, 52763 Naucalpan de Juárez, Méx, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel("Farina Duraznos", "Tel. 5591316154", "Bosque de Duraznos 39, Bosques de las Lomas, Miguel Hidalgo, 11700 Ciudad de México, CDMX, México", imgFarina, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));

        ArrayList<RestaurantsModel> cat2 = new ArrayList<>();
        cat2.add(new RestaurantsModel("Cachava Arcos Bosques", "Tel. 5591543921", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05110 Ciudad de México, CDMX, México", imgFarina, "https://www.cachava.com.mx/"));

        ArrayList<RestaurantsModel> cat3 = new ArrayList<>();
        cat3.add(new RestaurantsModel("La Popular Arcos Bosques", "Tel. 5591350256", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05110 Ciudad de México, CDMX, México", imgFarina, "https://www.lapopularmx.com/?fbclid=PAZXh0bgNhZW0CMTEAAaYM3vBrEqWO32teX638F0QV7IyIM_t6k9GBUde4V079rwj-iUHIClbUWs0_aem_gHwD5GYR-Wtsu9G7nMqySA"));

        ArrayList<RestaurantsModel> cat4 = new ArrayList<>();
        cat4.add(new RestaurantsModel("Carolo Bosques", "Tel. 5525918114", "Av. Secretaría de Marina 445, Lomas de Vista Hermosa, Cuajimalpa de Morelos, 05129 Ciudad de México, CDMX, México", imgFarina, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel("Carolo Carso", "Tel. 5549760134", "Calle Lago de Zurich 245, Granada, Miguel Hidalgo, 11529 Ciudad de México, CDMX, México", imgFarina, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel("Carolo Interlomas", "Tel. 5552908198", "Vialidad de la barranca, Avenida Jesús del Monte No. 6, Col. Ex Hacienda Jesús del Monte, Bosque de las Palmas, 52787 Naucalpan de Juárez, Méx., México", imgFarina, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel("Carolo Santa Fe", "Tel. 5516646069", "Juan Salvador Agraz 44, Santa Fe, Contadero, Cuajimalpa de Morelos, 01219 Ciudad de México, CDMX, México", imgFarina, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel("Carolo Hospital Ángeles Lomas", "Tel. 5552900819", "Jesús del Monte, 52764 Jesús del Monte, Méx, México", imgFarina, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));

        ArrayList<RestaurantsModel> cat5 = new ArrayList<>();
        cat5.add(new RestaurantsModel("Aromas Lomas", "Tel. 5573139072", "Monte Everest 770, Lomas de Chapultepec, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México", imgFarina, "https://www.aromas.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAabpu_uIgTPXA5t8Zs5hppdJiwVk9m797R5I8tolVzo6dNMYyMFUH1SxlBg_aem_gDNZe4BnxFo_E-t7S2bXZg"));
        cat5.add(new RestaurantsModel("Aromas Duraznos", "Tel. 5543173579", "Plaza Parque Duraznos 39, Col. Bosques de las Lomas, Miguel Hidalgo, 11700 Ciudad de México, CDMX, México", imgFarina, "https://www.aromas.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAabpu_uIgTPXA5t8Zs5hppdJiwVk9m797R5I8tolVzo6dNMYyMFUH1SxlBg_aem_gDNZe4BnxFo_E-t7S2bXZg"));

        ArrayList<RestaurantsModel> cat6 = new ArrayList<>();
        cat6.add(new RestaurantsModel("Casa O Bosques", "Tel. 5591350092", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05120 Ciudad de México, CDMX, México", imgFarina, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));
        cat6.add(new RestaurantsModel("Casa O Lomas", "Tel. 5543173579", "Monte Líbano 245, Lomas de Chapultepec, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México", imgFarina, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));
        cat6.add(new RestaurantsModel("Casa O San Ángel", "Tel. 5555503554", "Al. Altavista 147, San Ángel Inn, Álvaro Obregón, 01060 Ciudad de México, CDMX, México", imgFarina, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));

        ArrayList<RestaurantsModel> cat7 = new ArrayList<>();
        cat7.add(new RestaurantsModel("Blanco Castelar 163", "Tel. 5550270322", "Av. Emilio Castelar 163, Polanco, Polanco III Secc., Miguel Hidalgo, 11550 Ciudad de México, CDMX, México", imgFarina, "https://www.grupocarolo.com.mx/blancocastelar?fbclid=PAZXh0bgNhZW0CMTEAAabhwTVv3_WTYebkUHpfXWLFb-Ii8gRbEgqJnfuZ2isEx-1dB4v5GeqiFF0_aem_HRWSG4erclJqJMpUCNlSRA"));

        ArrayList<RestaurantsModel> cat8 = new ArrayList<>();
        cat8.add(new RestaurantsModel("Blanco Colima 168", "Tel. 5555117527", "Colima 168, Roma Nte., Cuauhtémoc, 06700 Ciudad de México, CDMX, México", imgFarina, "https://www.blancocolimamx.com/"));

        ArrayList<RestaurantsModel> cat9 = new ArrayList<>();
        cat9.add(new RestaurantsModel("Emilio Castelar 107", "Tel. 5552805877", "Av. Emilio Castelar 107, Polanco, Polanco IV Secc., Miguel Hidalgo, 11550 Ciudad de México, CDMX, México", imgFarina, "https://www.emiliorest.com/menu?fbclid=PAZXh0bgNhZW0CMTEAAaYvjWt0uJlIJTFEWKCj9yGBxT8mwK3Wo5nfaPQZhelRXp9tz6LSPzX-VxQ_aem_OAw2IIbCnnYcBlW8dVFDUw"));

        categoryRestaurantsModel.put("Farina",cat1);
        categoryRestaurantsModel.put("Cachava",cat2);
        categoryRestaurantsModel.put("La Popular",cat3);
        categoryRestaurantsModel.put("Carolo",cat4);
        categoryRestaurantsModel.put("Aromas",cat5);
        categoryRestaurantsModel.put("Casa O",cat6);
        categoryRestaurantsModel.put("Blanco Castelar",cat7);
        categoryRestaurantsModel.put("Blanco Colima",cat8);
        categoryRestaurantsModel.put("Emilio",cat9);
    }

    private void reloadRestaurants(int category){
        ArrayList<RestaurantsModel> totales = new ArrayList<>();
        if (category == -1) {
            for (int i = 0; i < categoryRestaurantsModel.get("Farina").size(); i++){
                totales.add(categoryRestaurantsModel.get("Farina").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Cachava").size(); i++){
                totales.add(categoryRestaurantsModel.get("Cachava").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("La Popular").size(); i++){
                totales.add(categoryRestaurantsModel.get("La Popular").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Carolo").size(); i++){
                totales.add(categoryRestaurantsModel.get("Carolo").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Aromas").size(); i++){
                totales.add(categoryRestaurantsModel.get("Aromas").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Casa O").size(); i++){
                totales.add(categoryRestaurantsModel.get("Casa O").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Blanco Castelar").size(); i++){
                totales.add(categoryRestaurantsModel.get("Blanco Castelar").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Blanco Colima").size(); i++){
                totales.add(categoryRestaurantsModel.get("Blanco Colima").get(i));
            }

            for (int i = 0; i < categoryRestaurantsModel.get("Emilio").size(); i++){
                totales.add(categoryRestaurantsModel.get("Emilio").get(i));
            }
        } else {

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
}
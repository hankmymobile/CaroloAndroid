package com.gcarolo.loyalty.modules.restaurants;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.FloatRange;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.restaurants.adapter.RestaurantsAdapter;
import com.gcarolo.loyalty.modules.restaurants.holder.IRestauranteHolderView;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.utilities.AppContext;
import com.gcarolo.loyalty.utilities.PropertiesManager;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RestaurantsFragment extends BaseFragment implements IRestauranteHolderView, View.OnClickListener {

    private View rootView;
    private RecyclerView recyclerView;
    private RestaurantsAdapter adapter;
    private int apuntador = -1;
    Bitmap bitmap;

    ImageView imgFarina;
    ImageView imgCachava;
    ImageView imgPopular;
    ImageView imgCarolo;
    ImageView imgAromas;
    ImageView imgCasaO;
    ImageView imgCastelar;
    ImageView imgColima;
    ImageView imgEmilio;
    private PropertiesManager propertiesManager;
    private int catSelected = 0;
    List<RestaurantsModel> restaurantsModelsSaved;
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
            propertiesManager = new PropertiesManager();
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
        Gson gson = new Gson();
        String jsonStored = propertiesManager.readProperty(PropertiesManager.StoredProperty.Favorites);
        Type listType = new TypeToken< ArrayList<RestaurantsModel> >(){}.getType();
        restaurantsModelsSaved = gson.fromJson(jsonStored, listType);
        createRestaurantCategories();
        imgFarina = rootView.findViewById(R.id.img_farima);
        imgCachava = rootView.findViewById(R.id.img_cachava);
        imgPopular = rootView.findViewById(R.id.img_popular);
        imgCarolo = rootView.findViewById(R.id.img_carolo);
        imgAromas = rootView.findViewById(R.id.img_aromas);
        imgCasaO = rootView.findViewById(R.id.img_casao);
        imgCastelar = rootView.findViewById(R.id.img_castelar);
        imgColima = rootView.findViewById(R.id.img_colima);
        imgEmilio = rootView.findViewById(R.id.img_emilio);

        imgFarina.setOnClickListener(this);
        imgCachava.setOnClickListener(this);
        imgPopular.setOnClickListener(this);
        imgCarolo.setOnClickListener(this);
        imgAromas.setOnClickListener(this);
        imgCasaO.setOnClickListener(this);
        imgCastelar.setOnClickListener(this);
        imgColima.setOnClickListener(this);
        imgEmilio.setOnClickListener(this);
        clearImageSelect();

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        reloadRestaurants(-1);
    }

    public void clearImageSelect(){
        catSelected = -1;
        imgFarina.setAlpha(0.8f);
        imgCachava.setAlpha(0.8f);
        imgPopular.setAlpha(0.8f);
        imgCarolo.setAlpha(0.8f);
        imgAromas.setAlpha(0.8f);
        imgCasaO.setAlpha(0.8f);
        imgCastelar.setAlpha(0.8f);
        imgColima.setAlpha(0.8f);
        imgEmilio.setAlpha(0.8f);

        imgFarina.setBackgroundResource(R.color.transparent);
        imgCachava.setBackgroundResource(R.color.transparent);
        imgPopular.setBackgroundResource(R.color.transparent);
        imgCarolo.setBackgroundResource(R.color.transparent);
        imgAromas.setBackgroundResource(R.color.transparent);
        imgCasaO.setBackgroundResource(R.color.transparent);
        imgCastelar.setBackgroundResource(R.color.transparent);
        imgColima.setBackgroundResource(R.color.transparent);
        imgEmilio.setBackgroundResource(R.color.transparent);

        imgFarina.setPadding(0,0,0,0);
        imgCachava.setPadding(0,0,0,0);
        imgPopular.setPadding(0,0,0,0);
        imgCarolo.setPadding(0,0,0,0);
        imgAromas.setPadding(0,0,0,0);
        imgCasaO.setPadding(0,0,0,0);
        imgCastelar.setPadding(0,0,0,0);
        imgColima.setPadding(0,0,0,0);
        imgEmilio.setPadding(0,0,0,0);

    }

    @Override
    public void onClick(View view) {
        if (view.getAlpha() == 1){
            clearImageSelect();
        }else {

            clearImageSelect();
            view.setAlpha(1.0f);
            int paddingDp = 2;
            float density = AppContext.getInstance().getContext().getResources().getDisplayMetrics().density;
            int paddingPixel = (int)(paddingDp * density);
            view.setPadding(paddingPixel,paddingPixel,paddingPixel,paddingPixel);
            view.setBackgroundResource(R.color.warning_color);
            switch (view.getId()) {
                case R.id.img_farima:
                    catSelected = 0;
                    break;
                case R.id.img_cachava:
                    catSelected = 1;
                    break;
                case R.id.img_popular:
                    catSelected = 2;
                    break;
                case R.id.img_carolo:
                    catSelected = 3;
                    break;
                case R.id.img_aromas:
                    catSelected = 4;
                    break;
                case R.id.img_casao:
                    catSelected = 5;
                    break;
                case R.id.img_castelar:
                    catSelected = 6;
                    break;
                case R.id.img_colima:
                    catSelected = 7;
                    break;
                case R.id.img_emilio:
                    catSelected = 8;
                    break;
            }
        }
        reloadRestaurants(catSelected);
    }

    private void createRestaurantCategories (){
        categoryRestaurantsModel = new HashMap<>();

        ArrayList<RestaurantsModel> cat1 = new ArrayList<>();

        ArrayList<String> imgFarinaPlatillos1 = new ArrayList<>();
        imgFarinaPlatillos1.add("farinaplatillo");imgFarinaPlatillos1.add("farinaplatillo1");imgFarinaPlatillos1.add("farinaplatillo2");imgFarinaPlatillos1.add("farinaplatillo3");imgFarinaPlatillos1.add("farinaplatillo4");imgFarinaPlatillos1.add("farinaplatillo5");
        imgFarinaPlatillos1.add("farinaplatillo6");imgFarinaPlatillos1.add("farinaplatillo7");imgFarinaPlatillos1.add("farinaplatillo8");imgFarinaPlatillos1.add("farinaplatillo10");imgFarinaPlatillos1.add("farinaplatillo11");
        imgFarinaPlatillos1.add("farinaplatillo12");imgFarinaPlatillos1.add("farinaplatillo13");imgFarinaPlatillos1.add("farinaplatillo14");

        ArrayList<String> imgFarinaPlatillos2 = new ArrayList<>();
        imgFarinaPlatillos2.add("farinaplatillo1");imgFarinaPlatillos2.add("farinaplatillo");imgFarinaPlatillos2.add("farinaplatillo2");imgFarinaPlatillos2.add("farinaplatillo3");imgFarinaPlatillos2.add("farinaplatillo4");imgFarinaPlatillos2.add("farinaplatillo5");
        imgFarinaPlatillos2.add("farinaplatillo6");imgFarinaPlatillos2.add("farinaplatillo7");imgFarinaPlatillos2.add("farinaplatillo8");imgFarinaPlatillos2.add("farinaplatillo10");imgFarinaPlatillos2.add("farinaplatillo11");
        imgFarinaPlatillos2.add("farinaplatillo12");imgFarinaPlatillos2.add("farinaplatillo13");imgFarinaPlatillos2.add("farinaplatillo14");

        ArrayList<String> imgFarinaPlatillos3 = new ArrayList<>();
        imgFarinaPlatillos3.add("farinaplatillo2");imgFarinaPlatillos3.add("farinaplatillo1");imgFarinaPlatillos3.add("farinaplatillo2");imgFarinaPlatillos3.add("farinaplatillo3");imgFarinaPlatillos3.add("farinaplatillo4");imgFarinaPlatillos3.add("farinaplatillo5");
        imgFarinaPlatillos3.add("farinaplatillo6");imgFarinaPlatillos3.add("farinaplatillo7");imgFarinaPlatillos3.add("farinaplatillo8");imgFarinaPlatillos3.add("farinaplatillo10");imgFarinaPlatillos3.add("farinaplatillo11");
        imgFarinaPlatillos3.add("farinaplatillo12");imgFarinaPlatillos3.add("farinaplatillo13");imgFarinaPlatillos3.add("farinaplatillo14");

        ArrayList<String> imgFarinaInter = new ArrayList<>();
        imgFarinaInter.add("farinainter1");imgFarinaInter.add("farinainter1");imgFarinaInter.add("farinainter1");
        imgFarinaInter.addAll(imgFarinaPlatillos1);

        ArrayList<String> imgFarinaPolanco = new ArrayList<>();
        imgFarinaPolanco.add("farinapolanco1");imgFarinaPolanco.add("farinapolanco2");imgFarinaPolanco.add("farinapolanco3");
        imgFarinaPolanco.add("farinapolanco4");imgFarinaPolanco.add("farinapolanco5");imgFarinaPolanco.add("farinapolanco6");
        imgFarinaPolanco.addAll(imgFarinaPlatillos1);

        ArrayList<String> imgFarinaAngel = new ArrayList<>();
        imgFarinaAngel.add("farinasangel1");imgFarinaAngel.add("farinasangel2");imgFarinaAngel.add("farinasangel3");
        imgFarinaAngel.addAll(imgFarinaPlatillos1);

        cat1.add(new RestaurantsModel(isFavorite("Farina Lomas"),"Farina Lomas", "Tel. 5526232112", "Monte Líbano 13-A, Lomas de Chapultepec VI Secc, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgFarinaPlatillos1, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel(isFavorite("Farina San Ángel"),"Farina San Ángel", "Tel. 5555504702", "Av. Altavista 147, San Ángel Inn, Álvaro Obregón, 01060 Ciudad de México, CDMX, México", "https://www.google.com/maps/place//data=!4m3!3m2!1s0x85d2001b6b67235f:0x3be49f2f4c729239!12e1?source=g.page.m.ia._&laa=nmx-review-solicitation-ia2","https://www.opentable.com.mx/",imgFarinaAngel, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel(isFavorite("Farina Roma"),"Farina Roma", "Tel. 5575890520", "Chihuahua 139, Roma Nte., Cuauhtémoc, 06700 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgFarinaPlatillos2, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel(isFavorite("Farina Polanco"),"Farina Polanco", "Tel. 5578259921", "Av. Isaac Newton 53-1, Polanco, Polanco IV Secc, Miguel Hidalgo, 11560 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgFarinaPolanco, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel(isFavorite("Farina Interlomas"),"Farina Interlomas", "Tel. 5559988148", "PA-38, Blvd. Palmas Hills 1, Valle de las Palmas, 52763 Naucalpan de Juárez, Méx, México","","https://www.opentable.com.mx/", imgFarinaInter, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));
        cat1.add(new RestaurantsModel(isFavorite("Farina Duraznos"),"Farina Duraznos", "Tel. 5591316154", "Bosque de Duraznos 39, Bosques de las Lomas, Miguel Hidalgo, 11700 Ciudad de México, CDMX, México","https://www.google.com/maps/place/Farina+Duraznos/@19.4029203,-99.2423979,17z/data=!3m1!4b1!4m6!3m5!1s0x85d20151466bca83:0xfa9c07ea6110e1e5!8m2!3d19.4029203!4d-99.2423979!16s%2Fg%2F11fnw0wlky?entry=ttu&g_ep=EgoyMDI0MTIwNC4wIKXMDSoASAFQAw%3D%3D","https://www.opentable.com.mx/", imgFarinaPlatillos3, "https://www.farinarest.com/?fbclid=PAZXh0bgNhZW0CMTEAAabaMGHAzRkHLgtEVj8Q9vxaOWlqRmv_l9PeG2rqgMO_QZ07_9wZG3bPJLc_aem_jXDQLZ8-hA_VU3tasNQJWw"));

        ArrayList<RestaurantsModel> cat2 = new ArrayList<>();
        ArrayList<String> imgCachava = new ArrayList<>();
        imgCachava.add("cachava");imgCachava.add("cachava1");imgCachava.add("cachava2");imgCachava.add("cachava3");imgCachava.add("cachava4");imgCachava.add("cachava5");
        imgCachava.add("cachava6");imgCachava.add("cachava7");imgCachava.add("cachava8");imgCachava.add("cachava9");imgCachava.add("cachava10");imgCachava.add("cachava1");
        imgCachava.add("cachava12");imgCachava.add("cachava13");
        cat2.add(new RestaurantsModel(isFavorite("Cachava Arcos Bosques"),"Cachava Arcos Bosques", "Tel. 5591543921", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05110 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCachava, "https://www.cachava.com.mx/"));

        ArrayList<RestaurantsModel> cat3 = new ArrayList<>();
        ArrayList<String> imgPopular = new ArrayList<>();
        imgPopular.add("popular");imgPopular.add("popular1");imgPopular.add("popular2");imgPopular.add("popular3");
        cat3.add(new RestaurantsModel(isFavorite("La Popular Arcos Bosques"),"La Popular Arcos Bosques", "Tel. 5591350256", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05110 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgPopular, "https://www.lapopularmx.com/?fbclid=PAZXh0bgNhZW0CMTEAAaYM3vBrEqWO32teX638F0QV7IyIM_t6k9GBUde4V079rwj-iUHIClbUWs0_aem_gHwD5GYR-Wtsu9G7nMqySA"));

        ArrayList<RestaurantsModel> cat4 = new ArrayList<>();

        ArrayList<String> imgCarolobosques = new ArrayList<>();
        imgCarolobosques.add("carolocarso1");imgCarolobosques.add("carolocarso");imgCarolobosques.add("carolocarso2");

        ArrayList<String> imgCarolocarso = new ArrayList<>();
        imgCarolocarso.add("carolocarso");imgCarolocarso.add("carolocarso1");imgCarolocarso.add("carolocarso2");
        ArrayList<String> imgCarolointerlomas = new ArrayList<>();
        imgCarolointerlomas.add("carolointerlomas");imgCarolointerlomas.add("carolointerlomas1");imgCarolointerlomas.add("carolointerlomas2");
        ArrayList<String> imgCarolosantafe = new ArrayList<>();
        imgCarolosantafe.add("carolosantafe");imgCarolosantafe.add("carolosantafe1");

        ArrayList<String> imgCarolohospital = new ArrayList<>();
        imgCarolohospital.add("carolointerlomas2");imgCarolohospital.add("carolosantafe");

        cat4.add(new RestaurantsModel(isFavorite("Carolo Bosques"),"Carolo Bosques", "Tel. 5525918114", "Av. Secretaría de Marina 445, Lomas de Vista Hermosa, Cuajimalpa de Morelos, 05129 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCarolobosques, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel(isFavorite("Carolo Carso"),"Carolo Carso", "Tel. 5549760134", "Calle Lago de Zurich 245, Granada, Miguel Hidalgo, 11529 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCarolocarso, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel(isFavorite("Carolo Interlomas"),"Carolo Interlomas", "Tel. 5552908198", "Vialidad de la barranca, Avenida Jesús del Monte No. 6, Col. Ex Hacienda Jesús del Monte, Bosque de las Palmas, 52787 Naucalpan de Juárez, Méx., México","","https://www.opentable.com.mx/", imgCarolointerlomas, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel(isFavorite("Carolo Santa Fe"),"Carolo Santa Fe", "Tel. 5516646069", "Juan Salvador Agraz 44, Santa Fe, Contadero, Cuajimalpa de Morelos, 01219 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCarolosantafe, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));
        cat4.add(new RestaurantsModel(isFavorite("Carolo Hospital Ángeles Lomas"),"Carolo Hospital Ángeles Lomas", "Tel. 5552900819", "Jesús del Monte, 52764 Jesús del Monte, Méx, México","","https://www.opentable.com.mx/", imgCarolohospital, "https://www.carolo.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAaZW6CwTg4uZl2QzcBHYf39iPLLoF7E2XOjCFUqL1Tu0qp_9ZyCyHBZq1MM_aem_BDb4WGNfTgCXqsn3rhL2MQ"));

        ArrayList<RestaurantsModel> cat5 = new ArrayList<>();

        ArrayList<String> imgAromasLomas = new ArrayList<>();
        imgAromasLomas.add("aromaslomas");imgAromasLomas.add("aromaslomas1");imgAromasLomas.add("aromaslomas2");imgAromasLomas.add("aromaslomas3");imgAromasLomas.add("aromaslomas4");

        ArrayList<String> imgAromasDuraznos = new ArrayList<>();
        imgAromasDuraznos.add("aromasduraznos");imgAromasDuraznos.add("aromasduraznos1");imgAromasDuraznos.add("aromasduraznos2");imgAromasDuraznos.add("aromasduraznos3");imgAromasDuraznos.add("aromasduraznos4");

        cat5.add(new RestaurantsModel(isFavorite("Aromas Lomas"),"Aromas Lomas", "Tel. 5573139072", "Monte Everest 770, Lomas de Chapultepec, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgAromasLomas, "https://www.aromas.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAabpu_uIgTPXA5t8Zs5hppdJiwVk9m797R5I8tolVzo6dNMYyMFUH1SxlBg_aem_gDNZe4BnxFo_E-t7S2bXZg"));
        cat5.add(new RestaurantsModel(isFavorite("Aromas Duraznos"),"Aromas Duraznos", "Tel. 5543173579", "Plaza Parque Duraznos 39, Col. Bosques de las Lomas, Miguel Hidalgo, 11700 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgAromasDuraznos, "https://www.aromas.com.mx/?fbclid=PAZXh0bgNhZW0CMTEAAabpu_uIgTPXA5t8Zs5hppdJiwVk9m797R5I8tolVzo6dNMYyMFUH1SxlBg_aem_gDNZe4BnxFo_E-t7S2bXZg"));

        ArrayList<RestaurantsModel> cat6 = new ArrayList<>();
        ArrayList<String> imgCasaoLomas = new ArrayList<>();
        imgCasaoLomas.add("casaolomas");imgCasaoLomas.add("casaolomas1");imgCasaoLomas.add("casaolomas2");imgCasaoLomas.add("casaolomas3");imgCasaoLomas.add("casaolomas4");

        ArrayList<String> imgCasaoBosques = new ArrayList<>();
        imgCasaoBosques.add("casaosanangel3");imgCasaoBosques.add("casaolomas");imgCasaoBosques.add("casaolomas2");imgCasaoBosques.add("casaolomas3");imgCasaoBosques.add("casaolomas4");

        ArrayList<String> imgCasaoSanAngel = new ArrayList<>();
        imgCasaoSanAngel.add("casaosanangel");imgCasaoSanAngel.add("casaosanangel2");imgCasaoSanAngel.add("casaosanangel3");imgCasaoSanAngel.add("casaosanangel4");

        cat6.add(new RestaurantsModel(isFavorite("Casa O Bosques"),"Casa O Bosques", "Tel. 5591350092", "Paseo de los Tamarindos 90, Bosques de las Lomas, Cuajimalpa de Morelos, 05120 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCasaoBosques, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));
        cat6.add(new RestaurantsModel(isFavorite("Casa O Lomas"),"Casa O Lomas", "Tel. 5543173579", "Monte Líbano 245, Lomas de Chapultepec, Miguel Hidalgo, 11000 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCasaoLomas, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));
        cat6.add(new RestaurantsModel(isFavorite("Casa O San Ángel"),"Casa O San Ángel", "Tel. 5555503554", "Al. Altavista 147, San Ángel Inn, Álvaro Obregón, 01060 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgCasaoSanAngel, "https://www.grupocarolo.com.mx/casao?fbclid=PAZXh0bgNhZW0CMTEAAaYaQSeoXoOEsM-i0m_EgsRF_Ugoy4PSjzlZZwcNYGwMSZL4BbC4FR0E_M8_aem_xh-MkgQ8-nPB1t_5YLC8QA"));

        ArrayList<RestaurantsModel> cat7 = new ArrayList<>();

        ArrayList<String> imgBlancoCastelar = new ArrayList<>();
        imgBlancoCastelar.add("castelarblanco");imgBlancoCastelar.add("castelarblanco1");imgBlancoCastelar.add("castelarblanco2");imgBlancoCastelar.add("castelarblanco3");imgBlancoCastelar.add("castelarblanco4");
        imgBlancoCastelar.add("castelarblanco5");imgBlancoCastelar.add("castelarblanco6");imgBlancoCastelar.add("castelarblanco7");imgBlancoCastelar.add("castelarblanco8");imgBlancoCastelar.add("castelarblanco9");
        imgBlancoCastelar.add("castelarblanco10");
        cat7.add(new RestaurantsModel(isFavorite("Blanco Castelar 163"),"Blanco Castelar 163", "Tel. 5550270322", "Av. Emilio Castelar 163, Polanco, Polanco III Secc., Miguel Hidalgo, 11550 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgBlancoCastelar, "https://www.grupocarolo.com.mx/blancocastelar?fbclid=PAZXh0bgNhZW0CMTEAAabhwTVv3_WTYebkUHpfXWLFb-Ii8gRbEgqJnfuZ2isEx-1dB4v5GeqiFF0_aem_HRWSG4erclJqJMpUCNlSRA"));

        ArrayList<RestaurantsModel> cat8 = new ArrayList<>();
        ArrayList<String> imgBlancoColima = new ArrayList<>();
        imgBlancoColima.add("blancocolima");imgBlancoColima.add("blancocolima1");imgBlancoColima.add("blancocolima2");imgBlancoColima.add("blancocolima3");imgBlancoColima.add("blancocolima4");
        imgBlancoColima.add("blancocolima5");imgBlancoColima.add("blancocolima6");imgBlancoColima.add("blancocolima7");imgBlancoColima.add("blancocolima8");imgBlancoColima.add("blancocolima9");
        imgBlancoColima.add("blancocolima10");
        cat8.add(new RestaurantsModel(isFavorite("Blanco Colima 168"),"Blanco Colima 168", "Tel. 5555117527", "Colima 168, Roma Nte., Cuauhtémoc, 06700 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgBlancoColima, "https://www.blancocolimamx.com/"));

        ArrayList<RestaurantsModel> cat9 = new ArrayList<>();
        ArrayList<String> imgEmilio = new ArrayList<>();
        imgEmilio.add("emilio");imgEmilio.add("emilio1");imgEmilio.add("emilio2");imgEmilio.add("emilio3");imgEmilio.add("emilio4");
        imgEmilio.add("emilio5");imgEmilio.add("emilio6");imgEmilio.add("emilio7");imgEmilio.add("emilio8");imgEmilio.add("emilio9");
        imgEmilio.add("emilio10");imgEmilio.add("emilio11");imgEmilio.add("emilio12");imgEmilio.add("emilio13");
        cat9.add(new RestaurantsModel(isFavorite("Emilio Castelar 107"),"Emilio Castelar 107", "Tel. 5552805877", "Av. Emilio Castelar 107, Polanco, Polanco IV Secc., Miguel Hidalgo, 11550 Ciudad de México, CDMX, México","","https://www.opentable.com.mx/", imgEmilio, "https://www.emiliorest.com/menu?fbclid=PAZXh0bgNhZW0CMTEAAaYvjWt0uJlIJTFEWKCj9yGBxT8mwK3Wo5nfaPQZhelRXp9tz6LSPzX-VxQ_aem_OAw2IIbCnnYcBlW8dVFDUw"));

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
            switch (category) {
                case 0:
                    for (int i = 0; i < categoryRestaurantsModel.get("Farina").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Farina").get(i));
                    }
                    break;
                case 1:
                    for (int i = 0; i < categoryRestaurantsModel.get("Cachava").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Cachava").get(i));
                    }
                    break;
                case 2:
                    for (int i = 0; i < categoryRestaurantsModel.get("La Popular").size(); i++){
                        totales.add(categoryRestaurantsModel.get("La Popular").get(i));
                    }
                    break;
                case 3:
                    for (int i = 0; i < categoryRestaurantsModel.get("Carolo").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Carolo").get(i));
                    }
                    break;
                case 4:
                    for (int i = 0; i < categoryRestaurantsModel.get("Aromas").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Aromas").get(i));
                    }
                    break;
                case 5:
                    for (int i = 0; i < categoryRestaurantsModel.get("Casa O").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Casa O").get(i));
                    }
                    break;
                case 6:
                    for (int i = 0; i < categoryRestaurantsModel.get("Blanco Castelar").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Blanco Castelar").get(i));
                    }
                    break;
                case 7:
                    for (int i = 0; i < categoryRestaurantsModel.get("Blanco Colima").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Blanco Colima").get(i));
                    }
                    break;
                case 8:
                    for (int i = 0; i < categoryRestaurantsModel.get("Emilio").size(); i++){
                        totales.add(categoryRestaurantsModel.get("Emilio").get(i));
                    }
                    break;
            }
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
        if (restaurantsModelsSaved != null) {
            for (int i = 0; i < restaurantsModelsSaved.size(); i++) {
                RestaurantsModel restaurantsModel = restaurantsModelsSaved.get(i);
                if (restaurantsModel.getTitle().equalsIgnoreCase(name)) {
                    return 1;
                }
            }
        }
        return 0;
    }

}
package com.gcarolo.loyalty.modules.restaurants;

import static androidx.core.content.ContextCompat.checkSelfPermission;
import static androidx.core.view.ViewCompat.setTransitionName;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.gcarolo.loyalty.MainActivity;

import android.Manifest;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.restaurants.model.RestaurantsModel;
import com.gcarolo.loyalty.modules.restaurants.utils.imageIndicatorListener;
import com.gcarolo.loyalty.modules.restaurants.utils.pictureFacer;
import com.gcarolo.loyalty.modules.restaurants.utils.recyclerViewPagerImageIndicator;
import com.gcarolo.loyalty.modules.webview.WebViewFragment;
import com.gcarolo.loyalty.utilities.AppContext;

import java.util.ArrayList;

public class RestaurantDetailFragment extends BaseFragment implements imageIndicatorListener {

    private View rootView;

    private  ArrayList<pictureFacer> allImages = new ArrayList<>();
    private int position;

    private ImageView image;
    private ViewPager imagePager;
    private RecyclerView indicatorRecycler;
    private int viewVisibilityController;
    private int viewVisibilitylooper;
    private ImagesPagerAdapter pagingImages;
    private int previousSelected = -1;
    private String currentPhone = "";

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
        allImages.clear();
        for (int i=0;i < restaurantsModel.getImages().size(); i++){
            pictureFacer pic = new pictureFacer();
            pic.setPicturePath(AppContext.getInstance().getContext().getResources().getIdentifier(restaurantsModel.getImages().get(i), "drawable", AppContext.getInstance().getContext().getPackageName()));
            allImages.add(pic);
        }
        LinearLayout lnUrl = rootView.findViewById(R.id.ln_url);
        LinearLayout lnOpen = rootView.findViewById(R.id.ln_open);
        LinearLayout lnComments = rootView.findViewById(R.id.ln_comments);
        TextView lblNameRestaurant = rootView.findViewById(R.id.lbl_name_restaurant);
        TextView lblDetailRestaurant = rootView.findViewById(R.id.lbl_detail_restaurant);
        TextView lblPhoneRestaurant = rootView.findViewById(R.id.lbl_phone_restaurant);
        ImageView imgRestaurant = rootView.findViewById(R.id.img_restaurant);
        lblNameRestaurant.setText(restaurantsModel.getTitle());
        lblDetailRestaurant.setText(restaurantsModel.getAddress());
        lblPhoneRestaurant.setText(restaurantsModel.getPhone());
        currentPhone = lblPhoneRestaurant.getText().toString().replace("Tel. ","");
        lnUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = WebViewFragment.newInstance(restaurantsModel.getUrl());
                displayFragment(fragment, null);
            }
        });

        lnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = WebViewFragment.newInstance(restaurantsModel.getOpenTable());
                displayFragment(fragment, null);
            }
        });
        if (restaurantsModel.getComments().isEmpty()){
            lnComments.setVisibility(View.GONE);
        }
        lnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = WebViewFragment.newInstance(restaurantsModel.getComments());
                displayFragment(fragment, null);
            }
        });

        lblPhoneRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentPhone = lblPhoneRestaurant.getText().toString();
                String phone_number = lblPhoneRestaurant.getText().toString();
                callAtRuntime();
            }
        });

        /**
         * initialisation of the recyclerView visibility control integers
         */
        viewVisibilityController = 0;
        viewVisibilitylooper = 0;

        /**
         * setting up the viewPager with images
         */
        imagePager = rootView.findViewById(R.id.imagePager);
        pagingImages = new ImagesPagerAdapter();
        imagePager.setAdapter(pagingImages);
        imagePager.setOffscreenPageLimit(3);
        imagePager.setCurrentItem(position);//displaying the image at the current position passed by the ImageDisplay Activity

        /**
         * setting up the recycler view indicator for the viewPager
         */
        indicatorRecycler = rootView.findViewById(R.id.indicatorRecycler);
        indicatorRecycler.hasFixedSize();
        indicatorRecycler.setLayoutManager(new GridLayoutManager(getContext(),1, RecyclerView.HORIZONTAL,false));
        RecyclerView.Adapter indicatorAdapter = new recyclerViewPagerImageIndicator(allImages,getContext(),this);
        indicatorRecycler.setAdapter(indicatorAdapter);
        indicatorRecycler.setVisibility(View.VISIBLE);
        //adjusting the recyclerView indicator to the current position of the viewPager, also highlights the image in recyclerView with respect to the
        //viewPager's position
        allImages.get(position).setSelected(true);
        previousSelected = position;
        indicatorAdapter.notifyDataSetChanged();
        indicatorRecycler.scrollToPosition(position);

        /**
         * this listener controls the visibility of the recyclerView
         * indication and it current position in respect to the image ViewPager
         */
        imagePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                if(previousSelected != -1){
                    allImages.get(previousSelected).setSelected(false);
                    previousSelected = position;
                    allImages.get(position).setSelected(true);
                    indicatorRecycler.getAdapter().notifyDataSetChanged();
                    indicatorRecycler.scrollToPosition(position);
                }else{
                    previousSelected = position;
                    allImages.get(position).setSelected(true);
                    indicatorRecycler.getAdapter().notifyDataSetChanged();
                    indicatorRecycler.scrollToPosition(position);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void callAtRuntime() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M

                && checkSelfPermission(getActivity(),Manifest.permission.CALL_PHONE)

                != PackageManager.PERMISSION_GRANTED){

            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);

        }

        else {

            Intent intent = new Intent(Intent.ACTION_CALL);

            intent.setData(Uri.parse("tel:"+ currentPhone));

            startActivity(intent);

        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == 1){

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){

                callAtRuntime();

            }

            else {

                Toast.makeText(getActivity(), "Permiso denegado, intente nuevamente", Toast.LENGTH_SHORT).show();

            }

        }

    }

    /**
     * this method of the imageIndicatorListerner interface helps in communication between the fragment and the recyclerView Adapter
     * each time an iten in the adapter is clicked the position of that item is communicated in the fragment and the position of the
     * viewPager is adjusted as follows
     * @param ImagePosition The position of an image item in the RecyclerView Adapter
     */
    @Override
    public void onImageIndicatorClicked(int ImagePosition) {

        //the below lines of code highlights the currently select image in  the indicatorRecycler with respect to the viewPager position
        if(previousSelected != -1){
            allImages.get(previousSelected).setSelected(false);
            previousSelected = ImagePosition;
            indicatorRecycler.getAdapter().notifyDataSetChanged();
        }else{
            previousSelected = ImagePosition;
        }

        imagePager.setCurrentItem(ImagePosition);
    }

    private class ImagesPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return allImages.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup containerCollection, int position) {
            LayoutInflater layoutinflater = (LayoutInflater) containerCollection.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutinflater.inflate(R.layout.picture_browser_pager,null);
            image = view.findViewById(R.id.image);

            setTransitionName(image, String.valueOf(position)+"picture");

            pictureFacer pic = allImages.get(position);
            Glide.with(AppContext.getInstance().getContext())
                    .load(pic.getPicturePath())
                    .apply(new RequestOptions().fitCenter())
                    .into(image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(indicatorRecycler.getVisibility() == View.GONE){
                        indicatorRecycler.setVisibility(View.VISIBLE);
                    }else{
                        indicatorRecycler.setVisibility(View.GONE);
                    }
                }
            });



            ((ViewPager) containerCollection).addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup containerCollection, int position, Object view) {
            ((ViewPager) containerCollection).removeView((View) view);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == ((View) object);
        }
    }

    /**
     * function for controlling the visibility of the recyclerView indicator
     */


}
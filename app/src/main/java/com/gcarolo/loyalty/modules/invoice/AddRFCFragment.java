package com.gcarolo.loyalty.modules.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;

public class AddRFCFragment extends BaseFragment {

    private View rootView;
    public AddRFCFragment() {
        // Required empty public constructor
    }

    public static AddRFCFragment newInstance() {
        AddRFCFragment fragment = new AddRFCFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_add_rfc, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

    }
}
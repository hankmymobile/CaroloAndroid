package com.gcarolo.loyalty.modules.address;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.recoveryPassword.ChangePasswordFragment;

public class AddAddressFragment extends BaseFragment {

    private View rootView;
    public AddAddressFragment() {
        // Required empty public constructor
    }

    public static AddAddressFragment newInstance() {
        AddAddressFragment fragment = new AddAddressFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_add_address, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

    }
}
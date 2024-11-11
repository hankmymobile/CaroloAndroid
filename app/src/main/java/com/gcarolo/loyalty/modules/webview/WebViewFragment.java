package com.gcarolo.loyalty.modules.perfil;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountPresenter;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountView;
import com.google.android.material.textfield.TextInputEditText;


public class PerfilFragment extends BaseFragment {

    private View rootView;

    public PerfilFragment() {

    }


    public static PerfilFragment newInstance() {
        PerfilFragment fragment = new PerfilFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configViews() {

        
    }
}
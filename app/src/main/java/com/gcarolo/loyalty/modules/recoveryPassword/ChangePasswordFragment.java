package com.gcarolo.loyalty.modules.recoveryPassword;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;

public class RecoveryPasswordFragment extends BaseFragment {

    public RecoveryPasswordFragment() {
        // Required empty public constructor
    }

    public static RecoveryPasswordFragment newInstance() {
        RecoveryPasswordFragment fragment = new RecoveryPasswordFragment();
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
        return inflater.inflate(R.layout.fragment_recovery_password, container, false);
    }
}
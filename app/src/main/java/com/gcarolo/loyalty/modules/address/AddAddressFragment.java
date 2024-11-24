package com.gcarolo.loyalty.modules.recoveryPassword;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;

public class RecoveryPasswordFragment extends BaseFragment {

    private View rootView;
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
        if (null == this.rootView) {
            this.rootView = inflater.inflate(R.layout.fragment_recovery_password, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {
        Button btnChangePassword = this.rootView.findViewById(R.id.btn_send_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordFragment fragment = ChangePasswordFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

    }
}
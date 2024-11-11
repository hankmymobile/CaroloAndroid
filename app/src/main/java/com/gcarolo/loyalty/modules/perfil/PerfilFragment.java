package com.gcarolo.loyalty.modules.createAccount;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupMenu;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.google.android.material.textfield.TextInputEditText;


public class CreateAccountFragment extends BaseFragment implements CreateAccountView {

    private CreateAccountPresenter presenter;
    private View rootView;
    TextInputEditText etTypeAccount;
    TextInputEditText etGenderAccount;

    public CreateAccountFragment() {
        presenter = new CreateAccountPresenter(this);
    }


    public static CreateAccountFragment newInstance() {
        CreateAccountFragment fragment = new CreateAccountFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_create_account, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configViews() {

        Button btnRegister = rootView.findViewById(R.id.btn_continue);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
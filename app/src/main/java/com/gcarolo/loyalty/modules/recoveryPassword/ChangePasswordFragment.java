package com.gcarolo.loyalty.modules.recoveryPassword;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.login.LoginFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends BaseFragment {
    private View rootView;
    public ChangePasswordFragment() {
        // Required empty public constructor
    }

    public static ChangePasswordFragment newInstance() {
        ChangePasswordFragment fragment = new ChangePasswordFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_change_password, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

        TextInputEditText tiPwd = this.rootView.findViewById(R.id.ed_login_account_password);

        TextInputLayout etPassword = this.rootView.findViewById(R.id.et_password);
        etPassword.setEndIconOnClickListener(view -> {
            if (etPassword.getTag() == "0") {
                etPassword.setTag("1");
                etPassword.setEndIconDrawable(R.drawable.drright_password_hide);
                tiPwd.setInputType( InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
                tiPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }else{
                etPassword.setTag("0");
                etPassword.setEndIconDrawable(R.drawable.drright_password);
                tiPwd.setInputType(InputType.TYPE_CLASS_TEXT);
            }
        });


        Button btnChangePassword = this.rootView.findViewById(R.id.btn_change_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DefaultDialogAlert defaultDialogAlert = new DefaultDialogAlert(getString(R.string.confirm_password_title), getString(R.string.confirm_password_alert_title), getString(R.string.alert_positive_text_default), getString(R.string.alert_negative_text_default), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginFragment fragment = LoginFragment.newInstance();
                        replaceFragment(fragment);
                    }
                }, null);
                defaultDialogAlert.show(getActivity().getSupportFragmentManager(), "DefaultDialogAlert");
            }
        });

    }
}
package com.gcarolo.loyalty.modules.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountFragment;
import com.gcarolo.loyalty.modules.recoveryPassword.RecoveryPasswordFragment;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends BaseFragment implements LoginView {

    private View rootView;
    private LoginPresenter presenter;

    public LoginFragment() {
        // Required empty public constructor
        this.presenter = new LoginPresenter(this);
    }

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (null == this.rootView) {
            this.rootView = inflater.inflate(R.layout.fragment_login, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

        TextInputEditText tiEmail = this.rootView.findViewById(R.id.ed_login_account_email);
        TextInputEditText tiPwd = this.rootView.findViewById(R.id.ed_login_account_password);

        Button btnLogin = this.rootView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loginUser(tiEmail.getEditableText().toString(), tiPwd.getEditableText().toString());
            }
        });

        Button btnCreateAccount = this.rootView.findViewById(R.id.btn_create_account);
        btnCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateAccountFragment fragment = CreateAccountFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        TextView tvRecovery = this.rootView.findViewById(R.id.tv_recover_password);
        tvRecovery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecoveryPasswordFragment fragment = RecoveryPasswordFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

    }

    public void disableButtonLogin() {
        Button btnLogin = this.rootView.findViewById(R.id.btn_login);
        btnLogin.setEnabled(false);
        btnLogin.setAlpha(.5f);
        btnLogin.setClickable(false);
    }

    public void enableButtonLogin() {
        Button btnLogin = this.rootView.findViewById(R.id.btn_login);
        btnLogin.setEnabled(true);
        btnLogin.setAlpha(1.0f);
        btnLogin.setClickable(true);
    }

    public void successLogin() {
        getActivity().finish();
    }

}
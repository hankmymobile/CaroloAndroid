package com.gcarolo.loyalty.modules.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gcarolo.loyalty.LoginActivity;
import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.SplashScreenActivity;
import com.gcarolo.loyalty.WelcomeActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.modules.createAccount.CreateAccountFragment;
import com.gcarolo.loyalty.modules.recoveryPassword.RecoveryPasswordFragment;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;
import com.gcarolo.loyalty.utilities.AppContext;
import com.gcarolo.loyalty.utilities.CaroloManager;
import com.gcarolo.loyalty.utilities.PropertiesManager;
import com.gcarolo.loyalty.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import okhttp3.internal.Util;

public class LoginFragment extends BaseFragment implements LoginView {

    private View rootView;
    private LoginPresenter presenter;
    TextInputEditText tiEmail;
    TextInputEditText tiPwd;

    private PropertiesManager propertiesManager;

    public LoginFragment() {
        // Required empty public constructor
        this.propertiesManager = new PropertiesManager();
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
        tiEmail = this.rootView.findViewById(R.id.ed_login_account_email);
        tiPwd = this.rootView.findViewById(R.id.ed_login_account_password);

        String savedUser = propertiesManager.readProperty(PropertiesManager.StoredProperty.User);
        String savedPassword = propertiesManager.readProperty(PropertiesManager.StoredProperty.Password);
        if (!savedUser.isEmpty() && !savedPassword.isEmpty()) {
            tiEmail.setText(savedUser);
            tiPwd.setText(savedPassword);
            presenter.loginUser(tiEmail.getEditableText().toString(), tiPwd.getEditableText().toString());
        }else {

            TextInputLayout etPassword = this.rootView.findViewById(R.id.et_password);
            etPassword.setEndIconOnClickListener(view -> {
                if (etPassword.getTag() == "0") {
                    etPassword.setTag("1");
                    etPassword.setEndIconDrawable(R.drawable.drright_password_hide);
                    tiPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    tiPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPassword.setTag("0");
                    etPassword.setEndIconDrawable(R.drawable.drright_password);
                    tiPwd.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            });

            Button btnLogin = this.rootView.findViewById(R.id.btn_login);
            btnLogin.setOnClickListener(view -> {
                if (!tiEmail.getEditableText().toString().isEmpty() && !tiPwd.getEditableText().toString().isEmpty()) {
                    presenter.loginUser(tiEmail.getEditableText().toString(), tiPwd.getEditableText().toString());
                } else {
                    showWarningAlert("Los campos de usuario y contraseña no deben estar vacíos");
                }
            });

            Button btnCreateAccount = this.rootView.findViewById(R.id.btn_create_account);
            btnCreateAccount.setOnClickListener(view -> {
                CreateAccountFragment fragment = CreateAccountFragment.newInstance();
                displayFragment(fragment, null);
            });

            TextView tvRecovery = this.rootView.findViewById(R.id.tv_recover_password);
            tvRecovery.setOnClickListener(view -> {
                RecoveryPasswordFragment fragment = RecoveryPasswordFragment.newInstance();
                displayFragment(fragment, null);
            });
        }
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

    @Override
    public void successLogin(String token, int id, String fullname) {

        ProfileDataSingleton.getInstance().setFullname(fullname);
        ProfileDataSingleton.getInstance().setToken(token);
        ProfileDataSingleton.getInstance().setUserId(id);
        ProfileDataSingleton.getInstance().setUsername(tiEmail.getText().toString());

        propertiesManager.writeProperty(PropertiesManager.StoredProperty.User, tiEmail.getEditableText().toString());
        propertiesManager.writeProperty(PropertiesManager.StoredProperty.Password, tiPwd.getEditableText().toString());

        WelcomePageFragment fragment = WelcomePageFragment.newInstance();
        displayFragment(fragment, null);

        Intent i = new Intent(getActivity(), WelcomeActivity.class);
        getActivity().startActivity(i);
        getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
        getActivity().finish();
    }

    @Override
    public void errorLogin(String message) {
        propertiesManager.removeProperty(PropertiesManager.StoredProperty.User);
        propertiesManager.removeProperty(PropertiesManager.StoredProperty.Password);
        showErrorAlert(message);
    }
}
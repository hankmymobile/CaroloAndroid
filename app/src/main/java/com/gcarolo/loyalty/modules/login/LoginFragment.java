package com.gcarolo.loyalty.modules.login;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import com.gcarolo.loyalty.core.dto.login.LoginData;
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

    boolean isGerente = false;
    boolean isUser = false;

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
    public void successLogin(String token, int id, String fullname, LoginData loginData) {

        ProfileDataSingleton.getInstance().setFullname(fullname);
        ProfileDataSingleton.getInstance().setToken(token);
        ProfileDataSingleton.getInstance().setUserId(id);
        ProfileDataSingleton.getInstance().setUsername(tiEmail.getText().toString());

        propertiesManager.writeProperty(PropertiesManager.StoredProperty.User, tiEmail.getEditableText().toString());
        propertiesManager.writeProperty(PropertiesManager.StoredProperty.Password, tiPwd.getEditableText().toString());

        if (loginData.getPerfiles().size() > 0) {
            for (int i = 0; i < loginData.getPerfiles().size(); i++){
                if (loginData.getPerfiles().get(i).getId() == 1){
                    isUser = true;
                }

                if (loginData.getPerfiles().get(i).getId() == 2){
                    isGerente = true;
                }
            }
        }

        if (!isGerente && !isUser) {
            //Preguntar si quiere entrar como gerente, sino entonces salir de la app
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Grupo Carolo");
            builder.setMessage("No es Gerente o Usuario registrado");

            // add the buttons
            builder.setPositiveButton("Salir de App", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                    System.exit(0);
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }else if (!isGerente && isUser) {
            ProfileDataSingleton.getInstance().setLogAsGerente(false);
            Intent i = new Intent(getActivity(), WelcomeActivity.class);
            getActivity().startActivity(i);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
            getActivity().finish();
        }else if (isGerente && !isUser) {
            ProfileDataSingleton.getInstance().setLogAsGerente(true);
            Intent i = new Intent(getActivity(), WelcomeActivity.class);
            getActivity().startActivity(i);
            getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
            getActivity().finish();
        }else if (isGerente && isUser) {
            ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
            View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog_custom, viewGroup, false);
            Button btnSi = dialogView.findViewById(R.id.btn_si);
            Button btnNo = dialogView.findViewById(R.id.btn_no);

            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setView(dialogView);
            AlertDialog alertDialog = builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            alertDialog.show();
            btnSi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    ProfileDataSingleton.getInstance().setLogAsGerente(true);
                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
                    getActivity().finish();
                }
            });

            btnNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    alertDialog.dismiss();
                    ProfileDataSingleton.getInstance().setLogAsGerente(false);
                    Intent intent = new Intent(getActivity(), WelcomeActivity.class);
                    getActivity().startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
                    getActivity().finish();
                }
            });
        }
    }

    @Override
    public void errorLogin(String message) {
        propertiesManager.removeProperty(PropertiesManager.StoredProperty.User);
        propertiesManager.removeProperty(PropertiesManager.StoredProperty.Password);
        showErrorAlert(message);
    }
}
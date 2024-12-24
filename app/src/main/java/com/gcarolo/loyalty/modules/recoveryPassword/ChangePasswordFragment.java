package com.gcarolo.loyalty.modules.recoveryPassword;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.WelcomeActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.login.LoginFragment;
import com.gcarolo.loyalty.utilities.PropertiesManager;
import com.gcarolo.loyalty.utilities.Utilities;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class ChangePasswordFragment extends BaseFragment implements ChangePasswordView {
    private View rootView;
    private ChangePasswordPresenter presenter;
    private PropertiesManager propertiesManager;
    private String mail, otp;
    public ChangePasswordFragment() {
        // Required empty public constructor
        this.presenter = new ChangePasswordPresenter(this);
        this.propertiesManager = new PropertiesManager();
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
            mail = getArguments().getString("mail","");
            otp = getArguments().getString("otp","");
            configViews();
        }
        return rootView;
    }

    private void configViews() {

        TextInputEditText tiPwd = this.rootView.findViewById(R.id.ed_login_account_password);
        TextInputEditText tiPwdConfirm = this.rootView.findViewById(R.id.ed_login_account_password_confirm);

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
                if (!tiPwd.getEditableText().toString().isEmpty() && !tiPwdConfirm.getEditableText().toString().isEmpty()) {
                    if ((tiPwd.getEditableText().toString().equalsIgnoreCase(tiPwdConfirm.getEditableText().toString()))){
                        presenter.changePassword(tiPwd.getEditableText().toString(), mail, otp);
                    }else{
                        showErrorAlert("Las contraseñas no son iguales");
                    }
                }else{
                    showErrorAlert("Los campos no pueden quedar vacíos");
                }
            }
        });

    }

    @Override
    public void successChangePassword() {
        ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog_custom, viewGroup, false);
        Button btnSi = dialogView.findViewById(R.id.btn_si);
        btnSi.setText("Ok");
        Button btnNo = dialogView.findViewById(R.id.btn_no);
        btnNo.setVisibility(View.GONE);
        TextView lblTitle = dialogView.findViewById(R.id.lbl_title);
        lblTitle.setText(getString(R.string.confirm_password_alert_title));
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.show();
        btnSi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                ProfileDataSingleton.getInstance().clearData();

                propertiesManager.removeProperty(PropertiesManager.StoredProperty.User);
                propertiesManager.removeProperty(PropertiesManager.StoredProperty.Password);

                Intent intent = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(intent);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
                getActivity().finish();
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
}
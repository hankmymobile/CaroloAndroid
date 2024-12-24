package com.gcarolo.loyalty.modules.configuration;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gcarolo.loyalty.LoginActivity;
import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.WelcomeActivity;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.login.LoginFragment;
import com.gcarolo.loyalty.modules.perfil.PerfilFragment;
import com.gcarolo.loyalty.modules.recoveryPassword.RecoveryPasswordFragment;
import com.gcarolo.loyalty.modules.webview.WebViewFragment;
import com.gcarolo.loyalty.utilities.PropertiesManager;

import org.w3c.dom.Text;

public class ConfigurationFragment extends BaseFragment {

    private View rootView;

    private PropertiesManager propertiesManager;

    public ConfigurationFragment() {
        this.propertiesManager = new PropertiesManager();
    }

    public static ConfigurationFragment newInstance() {
        ConfigurationFragment fragment = new ConfigurationFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_configuration, container, false);
            configViews();
        }

        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.GONE);

        return rootView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    private void configViews() {

        TextView btnCloseSession = this.rootView.findViewById(R.id.opt_close_session);
        btnCloseSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog_custom, viewGroup, false);
                Button btnSi = dialogView.findViewById(R.id.btn_si);
                Button btnNo = dialogView.findViewById(R.id.btn_no);
                TextView lblTitle = dialogView.findViewById(R.id.lbl_title);
                lblTitle.setText("¿Seguro deseas cerrar tu sesión?");

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

                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        getActivity().startActivity(i);
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
        });

        TextView btnChangePassword = this.rootView.findViewById(R.id.opt_change_password);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecoveryPasswordFragment fragment = RecoveryPasswordFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        TextView btnDelivery = this.rootView.findViewById(R.id.opt_delivery);
        btnDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilFragment fragment = PerfilFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        TextView btnPrivacy = this.rootView.findViewById(R.id.opt_privacy);
        btnPrivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = WebViewFragment.newInstance(getString(R.string.urlPrivacy));
                displayFragment(fragment, null);
            }
        });

        TextView btnTerms = this.rootView.findViewById(R.id.opt_terms);
        btnTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewFragment fragment = WebViewFragment.newInstance(getString(R.string.urlTerms));
                displayFragment(fragment, null);
            }
        });

        TextView btnDeleteAccount = this.rootView.findViewById(R.id.opt_delete_account);
        btnDeleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ViewGroup viewGroup = rootView.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.my_dialog_custom, viewGroup, false);
                Button btnSi = dialogView.findViewById(R.id.btn_si);
                Button btnNo = dialogView.findViewById(R.id.btn_no);
                TextView lblTitle = dialogView.findViewById(R.id.lbl_title);
                lblTitle.setText("¿Deseas eliminar tu cuenta?");

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                alertDialog.show();
                btnSi.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                btnNo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });
            }
        });

    }
}
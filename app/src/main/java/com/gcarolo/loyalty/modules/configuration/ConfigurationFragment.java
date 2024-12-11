package com.gcarolo.loyalty.modules.configuration;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcarolo.loyalty.LoginActivity;
import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.login.LoginFragment;
import com.gcarolo.loyalty.modules.perfil.PerfilFragment;
import com.gcarolo.loyalty.modules.recoveryPassword.RecoveryPasswordFragment;
import com.gcarolo.loyalty.modules.webview.WebViewFragment;
import com.gcarolo.loyalty.utilities.PropertiesManager;

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

                ProfileDataSingleton.getInstance().clearData();

                propertiesManager.removeProperty(PropertiesManager.StoredProperty.User);
                propertiesManager.removeProperty(PropertiesManager.StoredProperty.Password);

                Intent i = new Intent(getActivity(), LoginActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
                getActivity().finish();
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
                DefaultDialogAlert defaultDialogAlert = new DefaultDialogAlert(getString(R.string.message_configuration_delete_account), getString(R.string.message_configuration_delete_account_question), getString(R.string.alert_positive_text_default), getString(R.string.alert_negative_text_default), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                defaultDialogAlert.show(getActivity().getSupportFragmentManager(), "DefaultDialogAlert");
            }
        });

    }
}
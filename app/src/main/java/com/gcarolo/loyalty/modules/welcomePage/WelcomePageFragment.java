package com.gcarolo.loyalty.modules.welcomePage;

import android.content.Intent;
import android.os.Bundle;
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
import com.gcarolo.loyalty.modules.balance.BalanceFragment;

public class WelcomePageFragment extends BaseFragment {
    private View rootView;
    public WelcomePageFragment() {
        // Required empty public constructor
    }

    public static WelcomePageFragment newInstance() {
        WelcomePageFragment fragment = new WelcomePageFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_welcome_page, container, false);
            configViews();
        }
        return rootView;
    }

    private void configViews() {

        Button btnNext = this.rootView.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(i);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
                getActivity().finish();
            }
        });

        TextView tvUser = this.rootView.findViewById(R.id.title_login_user);
        tvUser.setText("¡Hola " + ProfileDataSingleton.getInstance().getFullname() + "!");

    }
}
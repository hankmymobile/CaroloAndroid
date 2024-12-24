package com.gcarolo.loyalty.modules.recoveryPassword;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.alert.DefaultDialogAlert;
import com.gcarolo.loyalty.modules.balance.BalancePresenter;
import com.gcarolo.loyalty.modules.welcomePage.WelcomePageFragment;
import com.google.android.material.textfield.TextInputEditText;

public class RecoveryPasswordFragment extends BaseFragment implements RecoveryPasswordView {

    private View rootView;

    private RelativeLayout rlOtp;
    private RelativeLayout rlMail;
    TextInputEditText edMail;
    TextInputEditText edOtp;

    private RecoveryPasswordPresenter presenter;
    public RecoveryPasswordFragment() {
        // Required empty public constructor
        this.presenter = new RecoveryPasswordPresenter(this);
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

        edMail = rootView.findViewById(R.id.ed_login_account_password);
        edOtp = rootView.findViewById(R.id.ed_login_account_password_2);

        rlOtp = rootView.findViewById(R.id.rlOtp);
        rlMail = rootView.findViewById(R.id.rlMail);

        rlMail.setVisibility(View.VISIBLE);
        rlOtp.setVisibility(View.GONE);

        Button btnGetOTP = this.rootView.findViewById(R.id.btn_send_password);
        btnGetOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.getOtp(edMail.getEditableText().toString());
            }
        });

        Button btnChangePassword = this.rootView.findViewById(R.id.btn_send_password_2);
        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validateOtp(edOtp.getEditableText().toString(),edMail.getEditableText().toString());
            }
        });
    }

    @Override
    public void successSendOTP() {
        rlMail.setVisibility(View.GONE);
        rlOtp.setVisibility(View.VISIBLE);
    }

    @Override
    public void successValidateOTP() {
        Bundle bundle = new Bundle();
        bundle.putString("otp", edOtp.getEditableText().toString());
        bundle.putString("mail", edMail.getEditableText().toString());
        ChangePasswordFragment fragment = ChangePasswordFragment.newInstance();
        fragment.setArguments(bundle);
        displayFragment(fragment, bundle);
    }
}
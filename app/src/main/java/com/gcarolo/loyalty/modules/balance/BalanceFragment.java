package com.gcarolo.loyalty.modules.balance;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.core.dto.balance.BalanceData;
import com.gcarolo.loyalty.modules.acumulate.AcumulateFragment;
import com.gcarolo.loyalty.modules.configuration.ConfigurationFragment;
import com.gcarolo.loyalty.modules.invoice.InvoiceFragment;
import com.gcarolo.loyalty.modules.login.LoginPresenter;
import com.gcarolo.loyalty.modules.payment.PaymentDialogFragment;
import com.gcarolo.loyalty.modules.perfil.PerfilFragment;
import com.gcarolo.loyalty.modules.recentActivities.RecentActivitiesFragment;
import com.gcarolo.loyalty.utilities.Utilities;

import java.text.NumberFormat;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class BalanceFragment extends BaseFragment implements BalanceView {

    private View rootView;
    private BalancePresenter presenter;

    public BalanceFragment() {
        // Required empty public constructor
        this.presenter = new BalancePresenter(this);
    }

    public static BalanceFragment newInstance() {
        BalanceFragment fragment = new BalanceFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_balance, container, false);
            configViews();
        }
        presenter.getBalance(ProfileDataSingleton.getInstance().getUserId());
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    private void configViews() {

        ImageButton btnNext = this.rootView.findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfigurationFragment fragment = ConfigurationFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        ImageView imgEditProfile = this.rootView.findViewById(R.id.img_edit_profile);
        imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerfilFragment fragment = PerfilFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        ImageButton btnRecentActivities = this.rootView.findViewById(R.id.btn_recent_activity);
        btnRecentActivities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RecentActivitiesFragment fragment = RecentActivitiesFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        ImageButton btnInvoice = this.rootView.findViewById(R.id.btn_invoice);
        btnInvoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InvoiceFragment fragment = InvoiceFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        Button btnAccumulate = this.rootView.findViewById(R.id.btn_accumulate);
        btnAccumulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AcumulateFragment fragment = AcumulateFragment.newInstance();
                displayFragment(fragment, null);
            }
        });

        Button btnPay = this.rootView.findViewById(R.id.btn_pay);
        btnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment newFragment = new PaymentDialogFragment();
                newFragment.show(getActivity().getSupportFragmentManager(), "paymentDialogFragment");
            }
        });

        TextView tvUser = this.rootView.findViewById(R.id.tv_user);
        tvUser.setText("¡Hola " + ProfileDataSingleton.getInstance().getFullname() + "!");

    }

    @Override
    public void successData(BalanceData data) {
        ProfileDataSingleton.getInstance().setSaldoActual(data.getPuntosDisponibles());
        TextView tvBalace = this.rootView.findViewById(R.id.tv_balance);
        tvBalace.setText(Utilities.moneyFormat(ProfileDataSingleton.getInstance().getSaldoActual()));
    }
}
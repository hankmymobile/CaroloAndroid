package com.gcarolo.loyalty.modules.gerente;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.gcarolo.loyalty.LoginActivity;
import com.gcarolo.loyalty.MainActivity2;
import com.gcarolo.loyalty.QRReaderActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.utilities.PropertiesManager;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class GerenteFragment extends BaseFragment {
    private ZXingScannerView mScannerView;
    private View rootView;
    private PropertiesManager propertiesManager;

    public GerenteFragment() {
        this.propertiesManager = new PropertiesManager();
    }

    public static GerenteFragment newInstance() {
        GerenteFragment fragment = new GerenteFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_gerente, container, false);
            configViews();
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity2)getActivity()).getBottomNav().setVisibility(View.GONE);
    }

    private void configViews() {

        ImageButton btnNext = this.rootView.findViewById(R.id.btn_exit);
        btnNext.setOnClickListener(new View.OnClickListener() {
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

        Button btnRedention = this.rootView.findViewById(R.id.btn_redention);
        btnRedention.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), QRReaderActivity.class);

                // on below line we are
                // starting a new activity.
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);

            }
        });

        Button btnAccomulate = this.rootView.findViewById(R.id.btn_accumulate);
        btnAccomulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), QRReaderActivity.class);

                // on below line we are
                // starting a new activity.
                startActivity(i);
                getActivity().overridePendingTransition(R.anim.fade_in, R.anim.hold);
            }
        });

        TextView tvUser = this.rootView.findViewById(R.id.tv_user);
        tvUser.setText("¡Hola " + ProfileDataSingleton.getInstance().getFullname() + "!");

    }


}
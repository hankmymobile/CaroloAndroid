package com.gcarolo.loyalty.modules.acumulate;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.perfil.PerfilFragment;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class AcumulateFragment extends BaseFragment {

    private View rootView;
    Bitmap bitmap;
    public AcumulateFragment() {
        // Required empty public constructor
    }

    public static AcumulateFragment newInstance() {
        AcumulateFragment fragment = new AcumulateFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_acumulate, container, false);
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
        ImageView imgQR = this.rootView.findViewById(R.id.img_qr);

        // Initializing the QR Encoder with your value to be encoded, type you required and Dimension
        QRGEncoder qrgEncoder = new QRGEncoder("Prueba Carolo", null, QRGContents.Type.TEXT, 300);
        qrgEncoder.setColorBlack(Color.WHITE);
        qrgEncoder.setColorWhite(Color.BLACK);
        try {
            // Getting QR-Code as Bitmap
            bitmap = qrgEncoder.getBitmap();
            // Setting Bitmap to ImageView
            imgQR.setImageBitmap(bitmap);
        } catch (Exception e) {
            Log.v("Generar QR", e.toString());
        }
    }
}
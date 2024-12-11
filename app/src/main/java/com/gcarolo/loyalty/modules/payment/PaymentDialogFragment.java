package com.gcarolo.loyalty.modules.payment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class PaymentDialogFragment extends DialogFragment {

    AlertDialog  alertDialog= null;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction.
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.CustomAlertDialog);
        // Get the layout inflater.
        LayoutInflater inflater = requireActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog.
        // Pass null as the parent view because it's going in the dialog layout.
        View view = inflater.inflate( R.layout.fragment_payment_dialog, null );
        TextView txtSaldoActual = view.findViewById(R.id.txt_saldo_actual);
        NumberFormat format = NumberFormat.getCurrencyInstance();
        txtSaldoActual.setText(format.format(ProfileDataSingleton.getInstance().getSaldoActual()));
        TextInputEditText edSaldoRedimir = view.findViewById(R.id.ed_saldo_redimir);
        Button btnAtras = view.findViewById(R.id.btn_atras);
        Button btnCanjear = view.findViewById(R.id.btn_canjear);
        btnAtras.setOnClickListener(view1 -> {
            Log.i("Click", "Atrás");
            alertDialog.dismiss();
        });

        btnCanjear.setOnClickListener(view1 -> {
            Log.i("Click", "Canjear");
            alertDialog.dismiss();
        });

        builder.setView(view);
        alertDialog = builder.create();
        return alertDialog;
    }
}
package com.gcarolo.loyalty.modules.invoice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;

public class InvoiceFragment extends BaseFragment {

    private View rootView;

    public InvoiceFragment() {
        // Required empty public constructor
    }

    public static InvoiceFragment newInstance() {
        InvoiceFragment fragment = new InvoiceFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_rfc, container, false);
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
        TextView optNewRFC = rootView.findViewById(R.id.opt_new_rfc);
        optNewRFC.setOnClickListener(view -> {
            AddRFCFragment fragment = AddRFCFragment.newInstance();
            displayFragment(fragment, null);
        });
    }
}
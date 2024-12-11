package com.gcarolo.loyalty.modules.notification;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.modules.notification.adapter.NotificationAdapter;
import com.gcarolo.loyalty.modules.notification.model.NotificationsModel;
import com.gcarolo.loyalty.modules.recentActivities.adapter.ActivitiesAdapter;
import com.gcarolo.loyalty.modules.recentActivities.model.ActivitiModel;

import java.util.ArrayList;
import java.util.List;

public class NotificationFragment extends BaseFragment {

    private View rootView;
    private RecyclerView recyclerView;
    private NotificationAdapter adapter;
    Bitmap bitmap;
    public NotificationFragment() {
        // Required empty public constructor
    }

    public static NotificationFragment newInstance() {
        NotificationFragment fragment = new NotificationFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_notification, container, false);
            configViews();
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity)getActivity()).getBottomNav().setVisibility(View.VISIBLE);
    }

    private void configViews() {

        LinearLayout lnNodata = rootView.findViewById(R.id.ln_nodata);
        lnNodata.setVisibility(View.GONE);

        LinearLayout lnData = rootView.findViewById(R.id.ln_data);
        lnData.setVisibility(View.VISIBLE);

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        this.adapter = new NotificationAdapter(getDataMock());
        this.recyclerView.setAdapter(adapter);

    }

    private List<NotificationsModel> getDataMock() {
        List<NotificationsModel> servicesMock = new ArrayList<>();
        servicesMock.add(new NotificationsModel("Pago", "26 Agosto 13:30 hrs", "Prueba 1", ""));
        servicesMock.add(new NotificationsModel("Pago", "26 Agosto 13:30 hrs", "Prueba 2", ""));
        servicesMock.add(new NotificationsModel("Pago", "26 Agosto 13:30 hrs", "Prueba 3", ""));
        servicesMock.add(new NotificationsModel("Pago", "26 Agosto 13:30 hrs", "Prueba 4", ""));

        return servicesMock;
    }
}
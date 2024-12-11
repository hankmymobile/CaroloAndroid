package com.gcarolo.loyalty.modules.recentActivities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.MainActivity;
import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.common.BaseFragment;
import com.gcarolo.loyalty.common.ProfileDataSingleton;
import com.gcarolo.loyalty.modules.notification.adapter.NotificationAdapter;
import com.gcarolo.loyalty.modules.notification.model.NotificationsModel;
import com.gcarolo.loyalty.modules.recentActivities.adapter.ActivitiesAdapter;
import com.gcarolo.loyalty.modules.recentActivities.model.ActivitiModel;
import com.gcarolo.loyalty.utilities.Utilities;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RecentActivitiesFragment extends BaseFragment {

    private View rootView;

    private RecyclerView recyclerView;

    private ActivitiesAdapter adapter;

    public RecentActivitiesFragment() {
        // Required empty public constructor
    }

    public static RecentActivitiesFragment newInstance() {
        RecentActivitiesFragment fragment = new RecentActivitiesFragment();
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
            this.rootView = inflater.inflate(R.layout.fragment_recent_activities, container, false);
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

        LinearLayout lnNodata = rootView.findViewById(R.id.ln_nodata);
        lnNodata.setVisibility(View.GONE);

        LinearLayout lnData = rootView.findViewById(R.id.ln_data);
        lnData.setVisibility(View.VISIBLE);

        TextView lblUser = rootView.findViewById(R.id.lbl_username);
        TextView lblAmount = rootView.findViewById(R.id.lbl_amount);

        lblUser.setText("¡Hola " + ProfileDataSingleton.getInstance().getFullname() + "!");
        lblAmount.setText(Utilities.moneyFormat(ProfileDataSingleton.getInstance().getSaldoActual()));

        this.recyclerView = this.rootView.findViewById(R.id.recycler_my_services);
        LinearLayoutManager lm = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        lm.setOrientation(RecyclerView.VERTICAL);
        this.recyclerView.setLayoutManager(lm);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setNestedScrollingEnabled(true);
        this.adapter = new ActivitiesAdapter(getDataMock());
        this.recyclerView.setAdapter(adapter);
    }

    private List<ActivitiModel> getDataMock() {
        List<ActivitiModel> servicesMock = new ArrayList<>();
        servicesMock.add(new ActivitiModel("Actividad", "26 Agosto 13:30 hrs", "Actividad 1", ""));
        servicesMock.add(new ActivitiModel("Actividad", "26 Agosto 13:30 hrs", "Actividad 2", ""));
        servicesMock.add(new ActivitiModel("Actividad", "26 Agosto 13:30 hrs", "Actividad 3", ""));
        servicesMock.add(new ActivitiModel("Actividad", "26 Agosto 13:30 hrs", "Actividad 4", ""));

        return servicesMock;
    }
}
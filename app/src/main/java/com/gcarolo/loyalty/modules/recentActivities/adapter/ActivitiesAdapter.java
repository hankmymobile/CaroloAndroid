package com.gcarolo.loyalty.modules.recentActivities.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.recentActivities.holder.ActivitiesHolder;
import com.gcarolo.loyalty.modules.recentActivities.model.ActivitiModel;

import java.util.List;

public class ActivitiesAdapter extends RecyclerView.Adapter {
    private List<ActivitiModel> myActivities;

    public ActivitiesAdapter(List<ActivitiModel> myActivities) {
        this.myActivities = myActivities;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.activities_holder, parent, false);

        return new ActivitiesHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ActivitiModel model = this.myActivities.get(position);
        ActivitiesHolder holderItem = (ActivitiesHolder) holder;
        holderItem.setModel(model);
        holderItem.bindHolder();
    }

    @Override
    public int getItemCount() {
        return this.myActivities.size();
    }

    public void setMyActivities(List<ActivitiModel> myNotifications) {
        this.myActivities = myNotifications;
    }


}

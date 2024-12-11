package com.gcarolo.loyalty.modules.notification.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.notification.model.NotificationsModel;
import com.gcarolo.loyalty.modules.notification.holder.NotificationHolder;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter {
    private List<NotificationsModel> myNotifications;

    public NotificationAdapter(List<NotificationsModel> myNotifications) {
        this.myNotifications = myNotifications;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.notifications_holder, parent, false);

        return new NotificationHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NotificationsModel model = this.myNotifications.get(position);
        NotificationHolder holderItem = (NotificationHolder) holder;
        holderItem.setModel(model);
        holderItem.bindHolder();
    }

    @Override
    public int getItemCount() {
        return this.myNotifications.size();
    }

    public void setNotifications(List<NotificationsModel> myNotifications) {
        this.myNotifications = myNotifications;
    }


}

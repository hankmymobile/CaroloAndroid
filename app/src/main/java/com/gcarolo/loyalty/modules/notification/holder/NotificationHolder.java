package com.gcarolo.loyalty.modules.notification.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gcarolo.loyalty.R;
import com.gcarolo.loyalty.modules.notification.model.NotificationsModel;

public class NotificationHolder extends RecyclerView.ViewHolder {

    private NotificationsModel model;
    private TextView tvTitle;
    private TextView tvDescription;
    private TextView tvDate;
    private ImageView imgNotification;

    public NotificationHolder(@NonNull View itemView) {
        super(itemView);
        this.tvTitle = itemView.findViewById(R.id.tv_title);
        this.tvDescription = itemView.findViewById(R.id.tv_description);
        this.tvDate = itemView.findViewById(R.id.tv_date);
        this.imgNotification = itemView.findViewById(R.id.img_notification);
    }

    public void setModel(NotificationsModel model) {
        this.model = model;
    }

    public void bindHolder() {
        if (null != this.model) {
            this.tvTitle.setText(this.model.getType());
            this.tvDescription.setText(this.model.getMessage());
            this.tvDate.setText(this.model.getDate());

        }
    }

}

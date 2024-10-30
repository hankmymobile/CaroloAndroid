package com.gcarolo.loyalty.utilities;


import android.content.Context;

public class AppContext {

    private static AppContext instance;
    private Context context;

    private AppContext() {

    }

    public static AppContext getInstance() {
        if (null == instance) {
            synchronized (AppContext.class) {
                if (null == instance) {
                    instance = new AppContext();
                }
            }
        }

        return instance;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}

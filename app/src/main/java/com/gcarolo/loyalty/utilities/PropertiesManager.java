package com.gcarolo.loyalty.utilities;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.annotation.NonNull;

import java.security.KeyStore;

public class PropertiesManager {

    public enum StoredProperty {

        UserSession("user_session", false);

        private String propertyKey;
        private boolean secure;

        public String getPropertyKey() {
            return propertyKey;
        }

        public boolean isSecure() {
            return secure;
        }

        StoredProperty(String propertyKey, boolean secure) {
            this.propertyKey = propertyKey;
            this.secure = secure;
        }
    }

    private static final String PREFERENCES_FILE_KEY = "mx.com.myMassages.PREFERENCES_FILE_KEY";

    public static final String ALIAS = "Carolo";

    private SharedPreferences sharedPreferences;


    private SharedPreferences.Editor editor;


    private KeyStore keyStore;


    PropertiesManager() {
        this.sharedPreferences = AppContext
                .getInstance()
                .getContext()
                .getSharedPreferences(PREFERENCES_FILE_KEY, Context.MODE_PRIVATE);

        try {
            this.keyStore = KeyStore.getInstance("AndroidKeystore");
            this.keyStore.load(null);
        } catch (Exception ex) {
            this.keyStore = null;
        }
    }

    public boolean hasProperty(StoredProperty property) {
        if (null == property)
            return false;

        return this.sharedPreferences.contains(property.getPropertyKey());
    }

    public void removeProperty(@NonNull StoredProperty property) {

        if (this.sharedPreferences.contains(property.getPropertyKey())) {
            this.editor = this.sharedPreferences.edit();
            this.editor.remove(property.getPropertyKey());
            this.editor.commit();
        }
    }

    public void writeProperty(@NonNull String nameKey, @NonNull String propertyValue) {
        this.editor = sharedPreferences.edit();
        this.editor.putString(nameKey, propertyValue);
        this.editor.commit();
    }

    public void writeProperty(@NonNull StoredProperty property, @NonNull String propertyValue) {
        if (TextUtils.isEmpty(propertyValue)) {
            return;
        }

        if (property.isSecure())
            propertyValue = Utilities.getInstance().encrypt(propertyValue);
        if (null == propertyValue)
            return;

        this.editor = sharedPreferences.edit();
        this.editor.putString(property.getPropertyKey(), propertyValue);
        this.editor.commit();
    }


    public String readProperty(String nameKey) {
        String propertyValue = this.sharedPreferences.getString(nameKey, "");
        return propertyValue;
    }


    public String readProperty(@NonNull StoredProperty property) {
        if (!this.sharedPreferences.contains(property.getPropertyKey())) {
            return null;
        } else {
            String propertyValue = this.sharedPreferences.getString(property.getPropertyKey(), null);
            if (property.isSecure() && !TextUtils.isEmpty(propertyValue))
                propertyValue = Utilities.getInstance().decrypt(propertyValue);

            return propertyValue;
        }
    }
}

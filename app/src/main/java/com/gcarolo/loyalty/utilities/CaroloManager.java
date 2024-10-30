package com.gcarolo.loyalty.utilities;

public class CaroloManager {

    private final static String PREFS_NAME = "PrefsMassagesFile";
    private PropertiesManager propertiesManager;

    private static CaroloManager instance;

    private CaroloManager() {
        this.propertiesManager = new PropertiesManager();
    }

    public static CaroloManager getInstance() {
        if (null == instance) {
            synchronized (CaroloManager.class) {
                if (null == instance) {
                    instance = new CaroloManager();
                }
            }
        }
        return instance;
    }

    public void clearSession() {
        for (PropertiesManager.StoredProperty property : PropertiesManager.StoredProperty.values()) {
            this.propertiesManager.removeProperty(property);
        }
    }

    public boolean hasSession() {
        if (this.propertiesManager.hasProperty(PropertiesManager.StoredProperty.UserSession)) {
            String session = this.propertiesManager.readProperty(PropertiesManager.StoredProperty.UserSession);
            return Boolean.parseBoolean(session);
        }
        return false;
    }

    public void setSession(boolean session) {
        this.propertiesManager.writeProperty(PropertiesManager.StoredProperty.UserSession, String.valueOf(session));
    }

}

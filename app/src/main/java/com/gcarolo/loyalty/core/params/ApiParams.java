package com.gcarolo.loyalty.core.params;

import java.util.HashMap;
import java.util.Map;

public abstract class ApiParams {

    protected HashMap<String, String> paramsMap;
    protected HashMap<String, String> dynamicUrlParams;

    protected ApiParams() {
        this.paramsMap = new HashMap<>();
        this.dynamicUrlParams = new HashMap<>();
    }

    public Map<String, String> getParams() {
        return this.paramsMap;
    }

    public HashMap<String, String> getDynamicUrlParams() {
        return this.dynamicUrlParams;
    }

    protected void appendParameter(String paramKey, String paramValue) {
        if (null == paramKey || paramKey.isEmpty()) {
            return;
        }

        if (null == paramValue) {
            return;
        }

        this.paramsMap.put(paramKey, paramValue);
    }

    protected void appendParameter(String paramKey, Integer paramValue) {
        appendParameter(paramKey, (null == paramValue) ? null : paramValue.toString());
    }

    protected void appendParameter(String paramKey, Boolean paramValue) {
        appendParameter(paramKey, (null == paramValue) ? null : paramValue.toString());
    }
}

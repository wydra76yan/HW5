package com.example;

/**
 * Created by YanVydra on 28.10.2017.
 */

public class ConfigApi {

    private static final String ADDITIONAL_URL = "config.json";

    private String mBasePath;

    public ConfigApi(final String pBasePath) {
        if (pBasePath.charAt(pBasePath.length() - 1) == '/') {
            mBasePath = pBasePath;
        } else {
            mBasePath = pBasePath + "/";
        }
        mBasePath = pBasePath;
    }

    public String getUrl() {
        return mBasePath + ADDITIONAL_URL;
    }
}

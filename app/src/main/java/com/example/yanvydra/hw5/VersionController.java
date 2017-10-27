package com.example.yanvydra.hw5;



import com.example.Config;
import com.example.ConfigApi;
import com.example.yanvydra.hw5.http.HttpClient;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by YanVydra on 28.10.2017.
 */

public class VersionController {

    private Config mConfig;



    VersionController() {
        final String url = new ConfigApi(BuildConfig.BASE_CHECKER_URL).getUrl();
        final ConfigurationResponseListener listener = new ConfigurationResponseListener();
        new HttpClient().request(url, listener);
        mConfig = listener.getConfig();
    }

    Boolean isCorrectVersion() {
        return mConfig.getVersion() <= BuildConfig.VERSION_CODE;
    }

    Boolean isForceUpdate() {
        return mConfig.getUpdate();
    }


    private static class ConfigurationResponseListener implements HttpClient.IResponseListener {

        private Config mConfig;
        private Throwable mThrowable;

        @Override
        public void onResponse(final InputStream pInputStream) {
            InputStreamReader inputStreamReader = null;
            try {
                inputStreamReader = new InputStreamReader(pInputStream);
                mConfig = new GsonBuilder()
                        .setLenient()
                        .create()
                        .fromJson(inputStreamReader, Config.class);
            } finally {
                if (inputStreamReader != null) {
                    try {
                        inputStreamReader.close();
                    } catch (final Exception e) {
                        e.getMessage();
                    }
                }
            }
        }

        public Throwable getThrowable() {
            return mThrowable;
        }

        Config getConfig() {
            return mConfig;
        }
    }
}

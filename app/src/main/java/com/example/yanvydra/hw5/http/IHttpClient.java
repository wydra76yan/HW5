package com.example.yanvydra.hw5.http;

/**
 * Created by YanVydra on 28.10.2017.
 */

public interface IHttpClient {

    void request(String url, HttpClient.IResponseListener listener);

}

package com.apnozhan.villa.requester;

import java.util.HashMap;

/**
 * Created by Lenovo on 12/16/2017.
 * request params
 */

public class RequestParams {
    private HashMap<String, String> params;


    public RequestParams() {
        params = new HashMap<>();
    }

    public void put(String key, String value) {
        params.put(key, value);
    }

    public HashMap<String, String> getParams() {
        return params;
    }

    public void put(String key, int value) {
        params.put(key, String.valueOf(value));
    }

    public void put(String key, double value) {
        params.put(key, String.valueOf(value));
    }

    public void put(String key, boolean b) {
        params.put(key, String.valueOf(b));
    }


}

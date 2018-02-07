package com.apnozhan.villa.models;

/**
 * Created by Y50-70 on 1/27/2018.
 */

public class Token implements Model {
    public String access_token;
    public String refresh_token;
    public String token_type;

    @Override
    public String toString() {
        return token_type + " " + access_token;
    }
}

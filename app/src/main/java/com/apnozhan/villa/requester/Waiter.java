package com.apnozhan.villa.requester;

/**
 * Created by Lenovo on 12/16/2017.
 * waiter parent
 */

public interface Waiter {
    boolean isAlive();

    void onFailure(int requestId, int statusCode);
}

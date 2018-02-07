package com.apnozhan.villa.requester;

/**
 * Created by Lenovo on 12/16/2017.
 * response waiter
 */

public interface ResponseWaiter extends Waiter {
    void onSuccess(int requestId, int statusCode);
}

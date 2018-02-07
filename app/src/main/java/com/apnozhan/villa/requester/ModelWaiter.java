package com.apnozhan.villa.requester;


import com.apnozhan.villa.models.Model;

/**
 * Created by Lenovo on 12/16/2017.
 * model waiter
 */

public interface ModelWaiter<E extends Model> extends Waiter {
    void onSuccess(int requestId, int statusCode, E response);
}

package com.apnozhan.villa.requester;

import com.apnozhan.villa.models.Model;

import java.util.List;


/**
 * Created by Lenovo on 12/16/2017.
 * list waiter
 */

public interface ListWaiter<E extends Model> extends Waiter {
    void onSuccess(int requestId, int statusCode, List<E> response);
}

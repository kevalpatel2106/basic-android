package com.kevalpatel2106.baseapplication.network;

/**
 * Created by Keval on 01-Jun-17.
 */

public interface ResponseListener {

    void onSuccess(BaseResult result);

    void onError(String message);
}

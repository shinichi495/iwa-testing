package com.iwa.iwatesting.network.task;

import android.telecom.Call;

public interface TaskRunner<T> {
    CancelTask<?> excuter (Callback<T> callback) ;
    void cancel();
}

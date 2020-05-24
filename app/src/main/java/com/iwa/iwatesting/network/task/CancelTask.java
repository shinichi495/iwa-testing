package com.iwa.iwatesting.network.task;

import java.lang.reflect.Constructor;
import java.util.concurrent.Future;

public class CancelTask<T> {
    private Future<?> future;
    CancelTask(Future<T> future) {
        this.future = future;
    }
    void cancel () {
        future.cancel(true);
    }

}

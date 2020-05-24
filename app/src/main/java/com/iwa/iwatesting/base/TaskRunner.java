package com.iwa.iwatesting.base;


import android.os.Handler;
import android.os.Looper;


import com.iwa.iwatesting.R;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;


public class TaskRunner {
    private final Executor executor = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());

    public interface Callback<T> {
        void onComplete ();
        void onFail();
    }

    public <T> void excuteAsync (final Callable<T> callable, final Callback<T> callback)  {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final T result = callable.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onComplete();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    callback.onFail();
                }
            }
        });
    }
}

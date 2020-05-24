package com.iwa.iwatesting.network.task;

import android.os.Handler;
import android.os.Looper;

import java.io.InterruptedIOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskRunnerImp<T> implements TaskRunner {
    private Task<T> task;
    public TaskRunnerImp(Task<T> task) {
        this.task = task;
    }
    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private Handler handler = new Handler(Looper.getMainLooper());
    @Override
    public CancelTask excuter(Callback callback) {
        return excute(task,callback);
    }

    @Override
    public void cancel() {
        executorService.shutdown();
    }

    private CancelTask<?> excute (final Task<T> task , final Callback<T> callback ) {
        return new CancelTask(executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    final T res = task.call();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (Thread.interrupted()) {
                                try {
                                    throw new InterruptedIOException("Thread interrupted");
                                } catch (InterruptedIOException e) {
                                    e.printStackTrace();
                                }
                            }
                            callback.onResponse(res);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));

    }
}

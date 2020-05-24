package com.iwa.iwatesting.network.task;

public interface Callback<T> {
    void onResponse (T data);
    void onFail (Throwable t);
}

package com.iwa.iwatesting.network.client;

public class HandleError extends Exception {
    private int resCode ;
    private String mess;

    HandleError (int code , String mess) {
        this.resCode = code;
        this.mess = mess;
    }
}

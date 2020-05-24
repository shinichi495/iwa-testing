package com.iwa.iwatesting.api;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.network.client.HttpClient;
import com.iwa.iwatesting.serializer.ObjectSerialier;


public class ApiService implements API {

    private String baseURL = null;
    private HttpClient client = null;
    private ObjectSerialier serialier = null;

    public ApiService(String baseURL, HttpClient client, ObjectSerialier serialier) {
        this.baseURL = baseURL;
        this.client = client;
        this.serialier = serialier;
    }

    @Override
    public ApiResponse getUser() {
        String url = baseURL + "users";
        String response = client.doGet(url);
        return parse(response);
    }

    @Override
    public DetailUser getUserDetail(String user) {
        String url = baseURL + "users/" + user;
        String response = client.doGet(url);
        return parseDetailUser(response);
    }

    @Override
    public ApiResponse getFollower(String user) {
        String url = baseURL + "users/" + user + "/followers";
        String response = client.doGet(url);
        return parse(response);
    }

    @Override
    public void register(String user, String email, String pass) {

    }

    private ApiResponse parse(String res) {
        return serialier.deserializer(res);
    }

    private DetailUser parseDetailUser(String res) {
        return serialier.deserializerDetailUse(res);
    }
}

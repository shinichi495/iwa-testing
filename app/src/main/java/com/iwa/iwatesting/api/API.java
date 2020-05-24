package com.iwa.iwatesting.api;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.model.User;

public interface API {
    ApiResponse getUser () ;
    DetailUser getUserDetail (String user);
    ApiResponse getFollower (String user) ;
    void register (String user, String email, String pass);
}

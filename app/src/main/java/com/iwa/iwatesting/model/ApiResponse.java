package com.iwa.iwatesting.model;

import java.util.List;

public class ApiResponse {
    public List<User> users;
    public ApiResponse(List<User> users) {
        this.users = users;
    }
}

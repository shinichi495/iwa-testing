package com.iwa.iwatesting.reponsitory;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.network.task.TaskRunner;

public interface UserRepository {
    TaskRunner<ApiResponse> getUsers();
    TaskRunner<DetailUser> getUserDetail(String user);
    TaskRunner<ApiResponse> getFollowers(String user);
}

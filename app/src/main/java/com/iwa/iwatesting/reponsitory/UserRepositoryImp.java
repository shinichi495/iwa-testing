package com.iwa.iwatesting.reponsitory;

import com.iwa.iwatesting.api.API;
import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.network.task.Task;
import com.iwa.iwatesting.network.task.TaskRunner;
import com.iwa.iwatesting.network.task.TaskRunnerImp;

public class UserRepositoryImp implements UserRepository {
    private API api;

    public UserRepositoryImp(API api) {
        this.api = api;
    }

    @Override
    public TaskRunner<ApiResponse> getUsers() {
        return new TaskRunnerImp(new Task<ApiResponse>() {
            @Override
            public ApiResponse call() {
                ApiResponse res = api.getUser();
                return res;
            }
        });
    }

    @Override
    public TaskRunner<DetailUser> getUserDetail(final String user) {
        return new TaskRunnerImp(new Task<DetailUser>() {

            @Override
            public DetailUser call() {
                DetailUser res = api.getUserDetail(user);
                return res;
            }
        });
    }

    @Override
    public TaskRunner<ApiResponse> getFollowers(final String user) {
        return new TaskRunnerImp(new Task<ApiResponse>() {
            @Override
            public ApiResponse call() {
                ApiResponse res = api.getFollower(user);
                return res;
            }
        });
    }
}

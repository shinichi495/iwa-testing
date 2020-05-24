package com.iwa.iwatesting.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.model.User;
import com.iwa.iwatesting.network.task.Callback;
import com.iwa.iwatesting.reponsitory.UserRepository;

import java.util.List;

public class DetailViewModel extends ViewModel {
    private UserRepository repository;
    public MutableLiveData<DetailUser> userDetail = new MutableLiveData();
    public MutableLiveData<List<User>> followers = new MutableLiveData();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData();
    public DetailViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void getUSerDetail (final String user) {
        isLoading.setValue(true);
        repository.getUserDetail(user).excuter(new Callback<DetailUser>() {
            @Override
            public void onResponse(DetailUser data) {
                isLoading.setValue(false);
                userDetail.setValue(data);
            }

            @Override
            public void onFail(Throwable t) {
                isLoading.setValue(false);
            }
        });
    }


    public void getFollower (final String user) {
        isLoading.setValue(true);
        repository.getFollowers(user).excuter(new Callback<ApiResponse>() {
            @Override
            public void onResponse(ApiResponse data) {
                isLoading.setValue(false);
                followers.setValue(data.users);
            }

            @Override
            public void onFail(Throwable t) {
                isLoading.setValue(false);
            }
        });

    }
}

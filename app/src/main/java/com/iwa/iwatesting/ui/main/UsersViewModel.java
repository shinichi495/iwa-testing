package com.iwa.iwatesting.ui.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.User;
import com.iwa.iwatesting.network.task.Callback;
import com.iwa.iwatesting.reponsitory.UserRepository;

import java.util.List;

public class UsersViewModel extends ViewModel {
    private UserRepository repository = null;
    public MutableLiveData<List<User>> users = new MutableLiveData();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData();

    public UsersViewModel(UserRepository repository) {
        this.repository = repository;
    }

    public void getUser() {
        isLoading.setValue(true);
        repository.getUsers().excuter(new Callback<ApiResponse>() {
            @Override
            public void onResponse(ApiResponse data) {
                isLoading.setValue(false);
                users.setValue(data.users);
            }

            @Override
            public void onFail(Throwable t) {
                isLoading.setValue(false);
            }
        });
    }
}

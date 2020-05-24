package com.iwa.iwatesting.base;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iwa.iwatesting.reponsitory.UserRepository;
import com.iwa.iwatesting.ui.main.UsersViewModel;

public class BaseViewModel extends ViewModel {
    protected UserRepository repository;
    public BaseViewModel(UserRepository repository) {
        this.repository = repository;
    }
}

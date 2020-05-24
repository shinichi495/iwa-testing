package com.iwa.iwatesting.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.iwa.iwatesting.reponsitory.UserRepository;
import com.iwa.iwatesting.ui.main.DetailViewModel;
import com.iwa.iwatesting.ui.main.UsersViewModel;

public class ViewMDFactory implements ViewModelProvider.Factory {
    private UserRepository repository;

    public ViewMDFactory(UserRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == UsersViewModel.class)
            return (T) new UsersViewModel(repository);
        if (modelClass == DetailViewModel.class)
            return (T) new DetailViewModel(repository);
        return null;
    }
}

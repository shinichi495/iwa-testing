package com.iwa.iwatesting.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iwa.iwatesting.MainActivity;
import com.iwa.iwatesting.R;

public class SplashFragment extends Fragment {

    private MainActivity activity;

    public static SplashFragment newInstance() {
        return new SplashFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.splash_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        activity = (MainActivity) getActivity();
        activity.showLoading(true);
        delayTiming();
    }



    private void delayTiming () {
        new CountDownTimer(3000,1000) {

            @Override
            public void onTick(long l) {
                // do some thing when load splash
            }

            @Override
            public void onFinish() {
                Navigation.findNavController(getActivity(),R.id.home_fragment).navigate(R.id.action_splashFragment_to_signUpFragment);
                activity.showLoading(false);
            }
        }.start();
    }
}

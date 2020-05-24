package com.iwa.iwatesting.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iwa.iwatesting.MainActivity;
import com.iwa.iwatesting.R;
import com.iwa.iwatesting.adapter.UsersAdapter;
import com.iwa.iwatesting.base.ServiceFactoryBase;
import com.iwa.iwatesting.base.ServiceFactoryImp;
import com.iwa.iwatesting.base.ViewMDFactory;
import com.iwa.iwatesting.databinding.DetailFragmentBinding;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.model.User;

import java.util.List;

public class DetailFragment extends Fragment {

    private DetailViewModel mViewModel;
    private DetailFragmentBinding binding;
    private ServiceFactoryBase factory;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.detail_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        factory = ServiceFactoryImp.getInstance();
        mViewModel = new ViewModelProvider(this, new ViewMDFactory(factory.getUserRepo())).get(DetailViewModel.class);
        String username = getArguments().getString("username");
        if (!username.isEmpty()) {
            mViewModel.getUSerDetail(username);
            mViewModel.getFollower(username);
        }
        attachObservable();
    }

    private void attachObservable() {
        mViewModel.isLoading.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ((MainActivity) getActivity()).showLoading(aBoolean);
            }
        });
        mViewModel.userDetail.observe(getActivity(), new Observer<DetailUser>() {
            @Override
            public void onChanged(DetailUser detailUser) {
                binding.detailView.txtName.setText(detailUser.getLogin());
                binding.detailView.txtBlog.setText(detailUser.getBlog());
                binding.detailView.txtCompany.setText(detailUser.getCompany());
                binding.detailView.txtLocation.setText(detailUser.getLocation());
            }
        });
        mViewModel.followers.observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> followers) {
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.lstFollowers.setLayoutManager(manager);
                binding.lstFollowers.setHasFixedSize(true);
                binding.lstFollowers.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.lstFollowers.setAdapter(new UsersAdapter(getContext(), followers, null));
                binding.txtTotalFl.setText(Integer.toString(followers.size()    ));
            }
        });

        binding.txtExpandCollap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (binding.lstFollowers.getVisibility() == View.GONE) {
                    binding.lstFollowers.setVisibility(View.VISIBLE);
                } else {
                    binding.lstFollowers.setVisibility(View.GONE);
                }
            }
        });
    }

}

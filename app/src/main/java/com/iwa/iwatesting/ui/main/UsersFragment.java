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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.iwa.iwatesting.MainActivity;
import com.iwa.iwatesting.R;
import com.iwa.iwatesting.adapter.OnItemClick;
import com.iwa.iwatesting.adapter.UsersAdapter;
import com.iwa.iwatesting.base.ServiceFactoryBase;
import com.iwa.iwatesting.base.ServiceFactoryImp;
import com.iwa.iwatesting.base.ViewMDFactory;
import com.iwa.iwatesting.databinding.UsersFragmentBinding;
import com.iwa.iwatesting.model.User;

import java.util.List;

public class UsersFragment extends Fragment {

    private UsersViewModel mViewModel;
    private ServiceFactoryBase factory;
    private UsersFragmentBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.users_fragment, container, false);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        factory = ServiceFactoryImp.getInstance();
        mViewModel = new ViewModelProvider(this, new ViewMDFactory(factory.getUserRepo())).get(UsersViewModel.class);
        attachObservable();
        mViewModel.getUser();
    }

    private void attachObservable() {
        mViewModel.users.observe(getActivity(), new Observer<List<User>>() {
            @Override
            public void onChanged(final List<User> users) {
                LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
                binding.lsUser.setLayoutManager(manager);
                binding.lsUser.setHasFixedSize(true);
                binding.lsUser.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
                binding.lsUser.setAdapter(new UsersAdapter(getContext(), users, new OnItemClick() {
                    @Override
                    public void onItemClick(View view, int pos) {
                        Bundle bundle = new Bundle();
                        bundle.putString("username",users.get(pos).getLogin());
                        Navigation.findNavController(getActivity(),R.id.home_fragment).navigate(R.id.action_usersFragment_to_detailFragment,bundle);
                    }
                }));

            }
        });
        mViewModel.isLoading.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                ((MainActivity) getActivity()).showLoading(aBoolean);
            }
        });
    }

}

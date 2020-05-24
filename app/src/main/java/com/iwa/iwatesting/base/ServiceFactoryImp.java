package com.iwa.iwatesting.base;

import com.iwa.iwatesting.BuildConfig;
import com.iwa.iwatesting.api.ApiService;
import com.iwa.iwatesting.network.client.HttpClient;
import com.iwa.iwatesting.network.client.HttpClientImp;
import com.iwa.iwatesting.reponsitory.UserRepository;
import com.iwa.iwatesting.reponsitory.UserRepositoryImp;
import com.iwa.iwatesting.serializer.ObjectSerialier;
import com.iwa.iwatesting.serializer.ObjectSerializerHandler;

public class ServiceFactoryImp implements ServiceFactoryBase  {
    private static ServiceFactoryImp instance ;
    private ServiceFactoryImp() {

    }

    public static synchronized ServiceFactoryImp getInstance() {
        if (instance == null) {
            instance = new ServiceFactoryImp();
        }
        return instance;
    }
    @Override
    public UserRepository getUserRepo() {
        return new UserRepositoryImp(getApiServervice());
    }

    @Override
    public ApiService getApiServervice() {
        return new ApiService(baseUrl(),client(),serializer());
    }

    @Override
    public String baseUrl() {
        return BuildConfig.BASE_URL;
    }

    @Override
    public ObjectSerialier serializer() {
        return new ObjectSerializerHandler();
    }

    @Override
    public HttpClient client() {
        return new HttpClientImp();
    }
}

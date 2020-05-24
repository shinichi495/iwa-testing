package com.iwa.iwatesting.base;

import com.iwa.iwatesting.BuildConfig;
import com.iwa.iwatesting.api.ApiService;
import com.iwa.iwatesting.network.client.HttpClient;
import com.iwa.iwatesting.network.client.HttpClientImp;
import com.iwa.iwatesting.reponsitory.UserRepository;
import com.iwa.iwatesting.reponsitory.UserRepositoryImp;
import com.iwa.iwatesting.serializer.ObjectSerialier;
import com.iwa.iwatesting.serializer.ObjectSerializerHandler;

public interface ServiceFactoryBase {
    UserRepository getUserRepo () ;
    ApiService getApiServervice () ;
    String baseUrl ();
    ObjectSerialier serializer();
    HttpClient client();

}


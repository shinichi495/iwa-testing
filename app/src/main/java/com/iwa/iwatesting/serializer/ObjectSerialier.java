package com.iwa.iwatesting.serializer;

import com.iwa.iwatesting.model.ApiResponse;
import com.iwa.iwatesting.model.DetailUser;
import com.iwa.iwatesting.model.User;

import java.util.List;

public interface ObjectSerialier {
    ApiResponse deserializer (String response);
    DetailUser deserializerDetailUse (String res);
}

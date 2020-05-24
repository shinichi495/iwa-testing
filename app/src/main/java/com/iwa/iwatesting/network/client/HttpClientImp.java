package com.iwa.iwatesting.network.client;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class HttpClientImp implements HttpClient {
    @Override
    public String doGet(String url) {
        BufferedReader reader = null;
        int resCode = 0;
        try {
            URL lUrl = new URL(url) ;
            HttpURLConnection connection = (HttpURLConnection) lUrl.openConnection() ;
            connection.setRequestMethod("GET");
            connection.connect();

            resCode = connection.getResponseCode();
            if (resCode != 200) throw new HandleError(resCode, "Error Request");
            InputStream inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String result = reader.lines().collect(Collectors.joining());
            if (result == null || result.isEmpty()) return  "";
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            try {
                throw new HandleError(resCode , "Error Request");
            } catch (HandleError handleError) {
                handleError.printStackTrace();
            }
        }
        return null;
    }
}

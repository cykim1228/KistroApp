package com.qsol.kistroapp.retrofit;

import com.qsol.kistroapp.service.LoginService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetRetrofit {

    private static NetRetrofit ourInstance = new NetRetrofit();
    public static NetRetrofit getInstance() {
        return ourInstance;
    }
    private NetRetrofit() {
    }

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://qsol.synology.me:18000/DEV/")
            .addConverterFactory(GsonConverterFactory.create()) // 파싱등록
            .build();

    LoginService service = retrofit.create(LoginService.class);

    public LoginService getService() {
        return service;
    }

}

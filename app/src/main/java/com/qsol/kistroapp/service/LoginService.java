package com.qsol.kistroapp.service;

import com.qsol.kistroapp.data.VO.UserVO;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoginService {

    @FormUrlEncoded
    @POST("Login")
    Call<UserVO> do_login(@Field("UserID") String UserID, @Field("UserPW") String UserPW, @Field("UseToken") String UseToken);

    @FormUrlEncoded
    @POST("IuserinfoJ")
    Call<UserVO> do_signup(@Field("UserID") String UserID, @Field("UserPW") String UserPW, @Field("UserName") String UserName, @Field("UserAddress1") String UserAddress1, @Field("UserAddress2") String UserAddress2, @Field("ZIPCODE") String Zipcode, @Field("UserEmail") String UserEmail, @Field("UserHP") String UserHP);

    @FormUrlEncoded
    @POST("UuserinfoJ")
    Call<UserVO> do_modify(@Field("UserID") String UserID, @Field("UserPW") String UserPW, @Field("UserName") String UserName, @Field("UserAddress1") String UserAddress1, @Field("UserAddress2") String UserAddress2, @Field("ZIPCODE") String Zipcode, @Field("UserEmail") String UserEmail, @Field("UserHP") String UserHP);

    @FormUrlEncoded
    @POST("SuserinfoJ")
    Call<UserVO> do_select(@Field("UserID") String UserID);

    @FormUrlEncoded
    @POST("DuserinfoJ")
    Call<UserVO> do_signout(@Field("UserID") String UserID);


}

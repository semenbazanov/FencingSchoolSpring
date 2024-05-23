package com.semenbazanov.fencingschoolfx.retrofit;

import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserService {
    @POST(".")
    Call<ResponseResult<User>> post(@Body User user);

    @GET("{id}")
    Call<ResponseResult<User>> get(@Path("id") long id);

    @GET("search")
    Call<ResponseResult<User>> get(@Query("login") String login, @Query("password") String password);

    @DELETE("{id}")
    Call<ResponseResult<User>> delete(@Path("id") long id);
}

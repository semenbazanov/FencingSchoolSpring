package com.semenbazanov.fencingschoolfx.retrofit;

import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface TrainerService {
    @POST(".")
    Call<ResponseResult<Trainer>> post(@Body Trainer trainer);

    @GET("{id}")
    Call<ResponseResult<Trainer>> get(@Path("id") long id);

    @GET(".")
    Call<ResponseResult<List<Trainer>>> get();

    @PUT(".")
    Call<ResponseResult<Trainer>> update(@Body Trainer trainer);

    @DELETE("{id}")
    Call<ResponseResult<Trainer>> delete(@Path("id") long id);
}

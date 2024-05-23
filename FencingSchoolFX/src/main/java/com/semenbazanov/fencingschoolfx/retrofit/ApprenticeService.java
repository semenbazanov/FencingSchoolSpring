package com.semenbazanov.fencingschoolfx.retrofit;

import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Apprentice;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ApprenticeService {
    @POST(".")
    Call<ResponseResult<Apprentice>> post(@Body Apprentice apprentice);

    @GET("{id}")
    Call<ResponseResult<Apprentice>> get(@Path("id") long id);

    @GET(".")
    Call<ResponseResult<List<Apprentice>>> get();

    @DELETE("{id}")
    Call<ResponseResult<Apprentice>> delete(@Path("id") long id);

    @PUT(".")
    Call<ResponseResult<Apprentice>> update(@Body Apprentice apprentice);
}

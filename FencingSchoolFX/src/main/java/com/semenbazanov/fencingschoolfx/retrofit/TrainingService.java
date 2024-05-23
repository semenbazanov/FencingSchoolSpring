package com.semenbazanov.fencingschoolfx.retrofit;

import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Training;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface TrainingService {
    /*@POST("{trainerId}/{apprenticeId}")
    Call<ResponseResult<Training>> post(@Path("trainerId") long trainerId,
                                        @Path("apprenticeId") long apprenticeId,
                                        @Query("numberGym") int numberGym,
                                        @Query("date") LocalDate date,
                                        @Query("timeStart") LocalTime timeStart);*/

    @POST("{trainerId}/{apprenticeId}")
    Call<ResponseResult<Training>> post(@Path("trainerId") long trainerId,
                                        @Path("apprenticeId") long apprenticeId,
                                        @Body Training training);

    @GET("apprentice/{apprenticeId}")
    Call<ResponseResult<List<Training>>> get(@Path("apprenticeId") long apprenticeId);

    @GET("time/{trainerId}")
    Call<ResponseResult<List<LocalTime>>> get(@Path("trainerId") long trainerId, @Query("date") String date);

    @GET("date/{trainerId}")
    Call<ResponseResult<List<LocalDate>>> getFreeDate(@Path("trainerId") long trainerId);

    @DELETE("{id}")
    Call<ResponseResult<Training>> delete(@Path("id") long id);
}

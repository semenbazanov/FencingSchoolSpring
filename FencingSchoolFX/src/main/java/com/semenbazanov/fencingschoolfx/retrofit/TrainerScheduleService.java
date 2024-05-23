package com.semenbazanov.fencingschoolfx.retrofit;

import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.TrainerSchedule;
import retrofit2.Call;
import retrofit2.http.*;

import java.time.LocalTime;

public interface TrainerScheduleService {
    @POST("{id}")
    Call<ResponseResult<TrainerSchedule>> post(@Path("id") long id, @Query("dayWeek") String dayWeek,
                                               @Query("start") LocalTime start, @Query("end") LocalTime end);

    @GET("{id}")
    Call<ResponseResult<TrainerSchedule>> get(@Path("id") long id);

    @DELETE("{id}")
    Call<ResponseResult<TrainerSchedule>> delete(@Path("id") long id, @Query("dayWeek") String dayWeek);
}

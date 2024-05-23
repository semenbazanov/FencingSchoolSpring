package com.semenbazanov.fencingschoolfx.retrofit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.TrainerSchedule;
import com.semenbazanov.fencingschoolfx.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalTime;

public class TrainerScheduleRepository {
    private final ObjectMapper objectMapper;

    private TrainerScheduleService trainerScheduleService;

    public TrainerScheduleRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL + "trainer_schedule/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainerScheduleService = retrofit.create(TrainerScheduleService.class);
    }

    private <T> T getData(Response<ResponseResult<T>> execute) throws IOException {
        if (execute.code() != 200) {
            String message = objectMapper.readValue(execute.errorBody().string(),
                    new TypeReference<ResponseResult<T>>() {
                    }).getMessage();
            throw new IllegalArgumentException(message);
        }
        return execute.body().getData();
    }

    public TrainerSchedule post(long id, String dayWeek, LocalTime start, LocalTime end) throws IOException {
        Response<ResponseResult<TrainerSchedule>> execute =
                this.trainerScheduleService.post(id, dayWeek, start, end).execute();
        return getData(execute);
    }

    public TrainerSchedule get(long id) throws IOException {
        Response<ResponseResult<TrainerSchedule>> execute = this.trainerScheduleService.get(id).execute();
        return getData(execute);
    }

    public TrainerSchedule delete(long id, String dayWeek) throws IOException {
        Response<ResponseResult<TrainerSchedule>> execute = this.trainerScheduleService.delete(id, dayWeek).execute();
        return getData(execute);
    }
}

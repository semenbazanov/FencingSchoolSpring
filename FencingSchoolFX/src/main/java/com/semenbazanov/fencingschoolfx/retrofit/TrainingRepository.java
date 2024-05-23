package com.semenbazanov.fencingschoolfx.retrofit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Training;
import com.semenbazanov.fencingschoolfx.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class TrainingRepository {
    private final ObjectMapper objectMapper;

    private TrainingService trainingService;

    public TrainingRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL + "training/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainingService = retrofit.create(TrainingService.class);
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

    /*public Training post(long trainerId, long apprenticeId, int numberGym, LocalDate date, LocalTime timeStart) throws IOException {
        Response<ResponseResult<Training>> execute =
                this.trainingService.post(trainerId, apprenticeId, numberGym, date, timeStart).execute();
        return getData(execute);
    }*/

    public Training post(long trainerId, long apprenticeId, Training training) throws IOException {
        Response<ResponseResult<Training>> execute =
                this.trainingService.post(trainerId, apprenticeId, training).execute();
        return getData(execute);
    }

    public List<Training> get(long apprenticeId) throws IOException {
        Response<ResponseResult<List<Training>>> execute = this.trainingService.get(apprenticeId).execute();
        return getData(execute);
    }

    public List<LocalTime> get(long trainerId, String date) throws IOException {
        Response<ResponseResult<List<LocalTime>>> execute = this.trainingService.get(trainerId, date).execute();
        return getData(execute);
    }

    public List<LocalDate> getFreeDate(long trainerId) throws IOException {
        Response<ResponseResult<List<LocalDate>>> execute = this.trainingService.getFreeDate(trainerId).execute();
        return getData(execute);
    }

    public Training delete(long id) throws IOException {
        Response<ResponseResult<Training>> execute = this.trainingService.delete(id).execute();
        return getData(execute);
    }
}

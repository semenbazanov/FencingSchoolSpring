package com.semenbazanov.fencingschoolfx.retrofit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Trainer;
import com.semenbazanov.fencingschoolfx.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class TrainerRepository {
    private final ObjectMapper objectMapper;
    private TrainerService trainerService;

    public TrainerRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL + "trainer/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.trainerService = retrofit.create(TrainerService.class);
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

    public Trainer post(Trainer trainer) throws IOException {
        Response<ResponseResult<Trainer>> execute = trainerService.post(trainer).execute();
        return getData(execute);
    }

    public Trainer get(long id) throws IOException {
        Response<ResponseResult<Trainer>> execute = trainerService.get(id).execute();
        return getData(execute);
    }

    public List<Trainer> get() throws IOException {
        Response<ResponseResult<List<Trainer>>> execute = trainerService.get().execute();
        return getData(execute);
    }

    public Trainer update(Trainer trainer) throws IOException {
        Response<ResponseResult<Trainer>> execute = this.trainerService.update(trainer).execute();
        return getData(execute);
    }

    public Trainer delete(long id) throws IOException {
        Response<ResponseResult<Trainer>> execute = trainerService.delete(id).execute();
        return getData(execute);
    }
}

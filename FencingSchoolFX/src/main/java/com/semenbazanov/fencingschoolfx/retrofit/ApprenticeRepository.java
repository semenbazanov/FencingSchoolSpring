package com.semenbazanov.fencingschoolfx.retrofit;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.semenbazanov.fencingschoolfx.dto.ResponseResult;
import com.semenbazanov.fencingschoolfx.model.Apprentice;
import com.semenbazanov.fencingschoolfx.util.Constants;
import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;

public class ApprenticeRepository {

    private final ObjectMapper objectMapper;
    private ApprenticeService apprenticeService;

    public ApprenticeRepository() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.URL + "apprentice/")
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .client(client)
                .build();
        this.apprenticeService = retrofit.create(ApprenticeService.class);
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

    public Apprentice post(Apprentice apprentice) throws IOException {
        Response<ResponseResult<Apprentice>> execute = apprenticeService.post(apprentice).execute();
        return getData(execute);
    }

    public Apprentice get(long id) throws IOException {
        Response<ResponseResult<Apprentice>> execute = apprenticeService.get(id).execute();
        return getData(execute);
    }

    public List<Apprentice> get() throws IOException {
        Response<ResponseResult<List<Apprentice>>> execute = apprenticeService.get().execute();
        return getData(execute);
    }

    public Apprentice update(Apprentice apprentice) throws IOException {
        Response<ResponseResult<Apprentice>> execute = this.apprenticeService.update(apprentice).execute();
        return getData(execute);
    }

    public Apprentice delete(long id) throws IOException {
        Response<ResponseResult<Apprentice>> execute = apprenticeService.delete(id).execute();
        return getData(execute);
    }
}

package com.lombok.iak.project_01networking.api;

import com.lombok.iak.project_01networking.model.Job;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    /**
     * TODO - 05: Membuat Caller dan callback untuk Retrofit
     */

    Retrofit retrofit;
    GitAPI api;

    public Repository() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl("https://jobs.github.com/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = builder.build();
        api=retrofit.create(GitAPI.class);
    }

    GitAPI getApiClient() {
        return retrofit.create(GitAPI.class);
    }
    /**
     * TODO - 06: Lakukan eksekusi secara asyncronous (dibelakang layar)
     */

    public void getJob(String id, Callback<Job> callback) {
        Call<Job> apiCall = getApiClient().getJob(id);
        apiCall.enqueue(callback);
    }

    public void getAllJob(String keyword, Callback<List<Job>> callback) {
        Call<List<Job>> apiCall = api.ambilSemua(keyword);
        apiCall.enqueue(callback);
    }

}

package com.lombok.iak.project_01networking.api;

import com.lombok.iak.project_01networking.model.Job;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitAPI {

    /**
     * TODO - 04: Membuat Interface Endpoint.
     *
     */


    @GET("positions/{id}.json")
    Call<Job> getJob(@Path("id") String idJob);

    @GET("positions.json")
    Call<List<Job>> ambilSemua(@Query("search") String keyword);
}

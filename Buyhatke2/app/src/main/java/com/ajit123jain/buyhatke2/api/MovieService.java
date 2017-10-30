package com.ajit123jain.buyhatke2.api;

import com.ajit123jain.buyhatke2.model.TopRatedMovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ajit on 30/10/17.
 */

public interface MovieService {

    @GET("movie/top_rated")
    Call<TopRatedMovies> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int pageIndex
    );

}

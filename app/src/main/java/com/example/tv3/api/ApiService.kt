package com.example.tv3.api

import com.example.tv3.model.DetailResponseModel
import com.example.tv3.model.MovieCastDetailModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey : String
    ) : Response<DetailResponseModel>

    @GET("movie/{movie_id}/credits")
    suspend fun getCastDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey : String
    ) : Response<MovieCastDetailModel>
}
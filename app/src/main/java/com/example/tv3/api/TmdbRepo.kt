package com.example.tv3.api

import com.example.tv3.model.DetailResponseModel
import com.example.tv3.model.MovieCastDetailModel
import com.example.tv3.utils.Constants.API_KEY
import retrofit2.Response

class TmdbRepo(private val service: ApiService) {
    suspend fun getMovieDetails(id: Int): Response<DetailResponseModel> {
        val result = service.getMovieDetail(id, API_KEY)
        return result
    }

}
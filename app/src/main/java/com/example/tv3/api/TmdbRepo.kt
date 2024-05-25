package com.example.tv3.api

import com.example.tv3.model4.NewComposeModel
import retrofit2.Response

class TmdbRepo(private val service: ApiService) {
    suspend fun getMovieDetails(id: Int): Response<NewComposeModel> {
        val result = service.getMovieDetail(id, "")
        return result
    }

}
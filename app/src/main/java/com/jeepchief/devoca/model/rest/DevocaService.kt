package com.jeepchief.devoca.model.rest

import retrofit2.Response
import retrofit2.http.GET

interface DevocaService {
    @GET(NetworkConstants.API_TEST)
    suspend fun getTest() : Response<String>
}
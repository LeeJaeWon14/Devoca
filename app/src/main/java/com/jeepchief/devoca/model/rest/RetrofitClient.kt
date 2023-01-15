package com.jeepchief.devoca.model.rest

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null

    @Synchronized
    fun getInstance() : Retrofit {
        return instance ?: run {
            instance = Retrofit.Builder()
                .baseUrl("")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            instance!!
        }
    }
}
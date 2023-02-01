package com.jeepchief.devoca.model.rest

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var instance: Retrofit? = null
    private val gson = GsonBuilder().setLenient().create()

    @Synchronized
    fun getInstance() : Retrofit {
        return instance ?: run {
            instance = Retrofit.Builder()
                .baseUrl(NetworkConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            instance!!
        }
    }
}
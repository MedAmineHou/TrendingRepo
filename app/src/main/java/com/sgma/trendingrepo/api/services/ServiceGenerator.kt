package com.sgma.trendingrepo.api.services

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ServiceGenerator {

    companion object{

        fun <S> createService(serviceClass: Class<S>, baseUrl: String, timeout: Long): S{
            val httpClient = OkHttpClient.Builder()

            httpClient.connectTimeout(timeout, TimeUnit.SECONDS)
            httpClient.readTimeout(timeout, TimeUnit.SECONDS)

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create()))
                .client(httpClient.build())
                .build()
            return retrofit.create(serviceClass)
        }
    }
}
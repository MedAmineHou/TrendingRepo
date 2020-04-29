package com.sgma.trendingrepo.api.services

import com.sgma.trendingrepo.BuildConfig
import com.sgma.trendingrepo.api.models.TrendingRepo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface TrendingApiService {

    @GET(BuildConfig.TRENDING_ENDPOINT)
    fun getTrendingRepo(
        @Query("q") q: String, @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"
    ): Observable<TrendingRepo>
}
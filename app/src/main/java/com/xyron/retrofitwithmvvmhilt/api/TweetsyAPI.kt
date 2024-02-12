package com.xyron.retrofitwithmvvmhilt.api

import com.xyron.retrofitwithmvvmhilt.models.TweetListItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers

interface TweetsyAPI {

    //Dynamic Header
    @GET("/v3/b/64b3dd858e4aa6225ebf1315?meta=false")
    suspend fun getTweets(@Header("X-JSON-Path") category: String) : Response<List<TweetListItem>>

    //Static Header
    @GET("/v3/b/64b3dd858e4aa6225ebf1315?meta=false")
    @Headers("X-JSON-Path : tweets..category")
    suspend fun getCategories() : Response<List<String>>

}
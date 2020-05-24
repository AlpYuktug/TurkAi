package com.alpyuktug.turkai.service

import com.alpyuktug.turkai.model.PostList
import com.alpyuktug.turkai.model.User
import com.alpyuktug.turkai.util.CustomSharedPrefences
import com.google.gson.GsonBuilder
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class APIService {

    private val BASE_URL = "https://alperenyukselaltug.com/api/TurkAi/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private  val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(API::class.java)

    fun getPostList() : Single<List<PostList>>
    {
        return api.getPostList()
    }


}
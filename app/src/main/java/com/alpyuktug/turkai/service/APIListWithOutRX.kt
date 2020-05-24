package com.alpyuktug.turkai.service

import com.alpyuktug.turkai.model.*
import retrofit2.Call
import retrofit2.http.*

interface APIListWithOutRX {

    @FormUrlEncoded
    @POST("userregister.php")
    fun Register(
        @Field("UserEmail") UserEmail:String,
        @Field("UserPassword") UserPassword:String,
        @Field("UserProfilePicture") UserProfilePicture:String
    ):Call<String>

    @FormUrlEncoded
    @POST("userlogin.php")
    fun Login(
        @Field("UserEmail") UserEmail:String,
        @Field("UserPassword") UserPassword:String
    ):Call<String>

    @FormUrlEncoded
    @POST("sharepost.php")
    fun SharePost(
        @Field("PostOwnerEMail") PostOwnerEMail:String,
        @Field("PostOwnerPicture") PostOwnerPicture:String,
        @Field("PostDetail") PostDetail:String,
        @Field("PostRate") PostRate:Float
    ):Call<String>

}
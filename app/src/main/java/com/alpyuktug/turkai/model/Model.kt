package com.alpyuktug.turkai.model

import com.google.gson.annotations.SerializedName

data class PostList(
    @SerializedName("PostID")
    val PostID: Int?,
    @SerializedName("PostOwnerEMail")
    val PostOwnerEMail: String?,
    @SerializedName("PostOwnerPicture")
    val PostOwnerPicture: String?,
    @SerializedName("PostDetail")
    val PostDetail: String?,
    @SerializedName("PostRate")
    val PostRate: Double?
)

data class User(
    @SerializedName("UserEmail")
    val UserEmail: String?,
    @SerializedName("UserPassword")
    val UserPassword: String?,
    @SerializedName("UserProfilePicture")
    val UserProfilePicture: String?
)

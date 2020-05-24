package com.alpyuktug.turkai.service
import com.alpyuktug.turkai.model.PostList
import com.alpyuktug.turkai.model.User
import io.reactivex.Single
import retrofit2.http.*

interface API {

    @GET("postlist.php")
    fun getPostList():Single<List<PostList>>

}
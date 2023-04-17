package com.example.myapplication.api

import com.example.myapplication.model.User
import com.example.myapplication.model.UserDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface Api {
    @GET("users")
    @Headers("Authorization: token " + "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")
    fun getUser():Call<ArrayList<User>>

    @GET("/users/{username}")
    fun findUserDetailByUsername(
        @Path("username") username: String
    ) :Call<UserDetail>

}
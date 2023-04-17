package com.example.myapplication.model

data class UserDetail(
    val login : String,
    val avatar_url:String,
    val name : String,
    val location:String,
    val followers:Int,
    val following:Int
)

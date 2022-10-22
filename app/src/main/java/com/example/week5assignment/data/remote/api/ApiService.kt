package com.example.week5assignment.data.remote.api

import com.example.week5assignment.data.model.Post
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    //tüm postlari alir
    @GET("posts")
    fun getPosts(): Call<List<Post>>

    //sadece tek bir post alinir
    @GET("posts/{id}")
    fun getPost(@Path("id") id:String) : Call<Post>
}
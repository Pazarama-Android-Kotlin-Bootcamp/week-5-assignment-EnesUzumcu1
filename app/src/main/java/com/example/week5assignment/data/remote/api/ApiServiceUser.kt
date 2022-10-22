package com.example.week5assignment.data.remote.api

import com.example.week5assignment.data.model.Users
import retrofit2.Call
import retrofit2.http.GET

interface ApiServiceUser {
    //tüm userleri alir
    @GET("users")
    fun getUsers(): Call<List<Users>>
}
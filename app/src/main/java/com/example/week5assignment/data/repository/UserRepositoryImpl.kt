package com.example.week5assignment.data.repository

import com.example.week5assignment.data.model.Users
import com.example.week5assignment.data.remote.api.ApiServiceUser
import retrofit2.Call

class UserRepositoryImpl constructor(
    private val apiServiceUser: ApiServiceUser
) : UserRepository{
    override fun getUsers(): Call<List<Users>> {
        return apiServiceUser.getUsers()
    }
}
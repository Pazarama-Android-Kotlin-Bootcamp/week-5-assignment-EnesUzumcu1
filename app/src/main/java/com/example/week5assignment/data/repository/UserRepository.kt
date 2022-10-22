package com.example.week5assignment.data.repository

import com.example.week5assignment.data.model.Users
import retrofit2.Call

interface UserRepository {
    fun getUsers(): Call<List<Users>>
}